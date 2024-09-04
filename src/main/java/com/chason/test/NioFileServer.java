package com.chason.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

public class NioFileServer {
    private Selector selector;

    public static void main(String[] args) {
        try {
            new NioFileServer().startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer() throws IOException {
        selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(6666));
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Server started on port 6666");

        while (true) {
            selector.select();
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();

                if (!key.isValid()) continue;

                if (key.isAcceptable()) {
                    accept(key);
                } else if (key.isReadable()) {
                    read(key);
                }
            }
        }
    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("Accepted connection from " + socketChannel);
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(4096);

        // 读取文件名
        int bytesRead = socketChannel.read(buffer);
        if (bytesRead == -1) {
            socketChannel.close();
            key.cancel();
            return;
        }

        buffer.flip();
        String fileName = new String(buffer.array(), 0, bytesRead).trim();
        System.out.println("Receiving file: " + fileName);

        buffer.clear();

        // 读取文件大小
        // 读取文件大小
        bytesRead = socketChannel.read(buffer);
        if (bytesRead == -1 || buffer.remaining() < Long.BYTES) {
            socketChannel.close();
            key.cancel();
            return;
        }

        buffer.flip();
        long fileSize = buffer.getLong();
        System.out.println("File size: " + fileSize + " bytes");

        buffer.clear();

        String path = "D:\\temp\\receive\\";
        FileChannel fileChannel = FileChannel.open(Paths.get(path + "received_" + fileName), StandardOpenOption.CREATE, StandardOpenOption.WRITE);

        // 使用多个线程来并行写入文件
        int numThreads = 4;

        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            long start = i * (fileSize / numThreads);
            long end = (i == numThreads - 1) ? fileSize : (i + 1) * (fileSize / numThreads);
            threads[i] = new Thread(new FileReceiver(socketChannel, fileChannel, start, end));
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

        System.out.println("File received: " + fileName);
        fileChannel.close();
        socketChannel.close();
        key.cancel();
    }
}

class FileReceiver implements Runnable {
    private SocketChannel socketChannel;
    private FileChannel fileChannel;
    private long start;
    private long end;

    public FileReceiver(SocketChannel socketChannel, FileChannel fileChannel, long start, long end) {
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
            int bytesRead;

            while (remaining > 0 && (bytesRead = socketChannel.read(buffer)) != -1) {
                buffer.flip();
                fileChannel.write(buffer);
                buffer.clear();
                remaining -= bytesRead;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
