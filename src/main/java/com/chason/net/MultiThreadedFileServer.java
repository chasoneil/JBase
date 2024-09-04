package com.chason.net;

import java.io.*;
import java.net.*;

public class MultiThreadedFileServer {



    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("--> 服务端启动成功！");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println(socket.getRemoteSocketAddress() + "客户端连接成功!");
                new Thread(new FileTransferHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class FileTransferHandler implements Runnable {

    public static final String RECEIVE_PATH = "D:\\temp\\receive\\";

    private Socket socket;

    public FileTransferHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String fileName = dataInputStream.readUTF();

            long fileSize = dataInputStream.readLong();
            System.out.println("收到了文件:" + fileName + " 文件大小：" + fileSize);
            int numberOfThreads = dataInputStream.readInt();

            RandomAccessFile file = new RandomAccessFile(RECEIVE_PATH + "received_" + fileName, "rw");

            Thread[] threads = new Thread[numberOfThreads];

            long blockSize = fileSize / numberOfThreads;
            for (int i = 0; i < numberOfThreads; i++) {
                long start = i * blockSize;
                long end = (i == numberOfThreads - 1) ? fileSize : (i + 1) * blockSize;
                threads[i] = new Thread(new FileReceiver(dataInputStream, file, start, end));
                System.out.println("开始接受第" + i + "个文件，start:" + start + " end:" + end);
                threads[i].start();
            }

            for (Thread thread : threads) {
                thread.join();
            }

            System.out.println("File " + fileName + " received.");

            file.close();
            dataInputStream.close();
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class FileReceiver implements Runnable {
    private DataInputStream dataInputStream;
    private RandomAccessFile file;
    private long start;
    private long end;

    public FileReceiver(DataInputStream dataInputStream, RandomAccessFile file, long start, long end) {
        this.dataInputStream = dataInputStream;
        this.file = file;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            file.seek(start);
            byte[] buffer = new byte[4096];
            long remaining = end - start;
            int bytesRead;

            while (remaining > 0 && (bytesRead = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, remaining))) != -1) {
                file.write(buffer, 0, bytesRead);
                remaining -= bytesRead;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
