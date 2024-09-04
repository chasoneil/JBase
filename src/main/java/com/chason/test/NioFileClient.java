package com.chason.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NioFileClient {
    public static void main(String[] args) {
        String fileName = "send.pdf";

        try {
            new NioFileClient().sendFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFile(String fileName) throws IOException {

        String path = "D:\\temp\\";
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 6666));
        socketChannel.configureBlocking(true);

        FileChannel fileChannel = FileChannel.open(Paths.get(path + fileName), StandardOpenOption.READ);
        long fileSize = fileChannel.size();
        ByteBuffer buffer = ByteBuffer.allocate(4096);

        // 先发送文件名
        buffer.put(fileName.getBytes());
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();

        // 再发送文件大小
        buffer.clear();
        buffer.putLong(fileSize);
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();

        // 使用多个线程来并行发送文件
        int numThreads = 4;

        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            long start = i * (fileSize / numThreads);
            long end = (i == numThreads - 1) ? fileSize : (i + 1) * (fileSize / numThreads);
            threads[i] = new Thread(new FileSender(socketChannel, fileChannel, start, end));
            threads[i].start();
        }

        // 等待所有线程完成
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("File sent: " + fileName);

        fileChannel.close();
        socketChannel.close();
    }
}

class FileSender implements Runnable {
    private SocketChannel socketChannel;
    private FileChannel fileChannel;
    private long start;
    private long end;

    public FileSender(SocketChannel socketChannel, FileChannel fileChannel, long start, long end) {
        this.socketChannel = socketChannel;
        this.fileChannel = fileChannel;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            fileChannel.position(start);
            ByteBuffer buffer = ByteBuffer.allocate(4096);
            long remaining = end - start;

            while (remaining > 0) {
                int bytesRead = fileChannel.read(buffer);
                if (bytesRead == -1) break;

                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();
                remaining -= bytesRead;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
