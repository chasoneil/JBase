package com.chason.net;

import java.io.*;
import java.net.*;

public class MultiThreadedFileClient {

    private static final String SEND_PATH = "D:\\temp\\";

    public static void main(String[] args) {

        String fileName = "send.pdf";

        try {
            File file = new File(SEND_PATH + fileName);
            long fileSize = file.length();

            System.out.println("准备发送文件：" + fileName + " 文件大小:" + fileSize);

            Socket socket = new Socket("localhost", 6666);

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(fileName);
            dos.flush();
            dos.writeLong(fileSize);
            dos.flush();

            Thread[] threads = new Thread[Constant.THREADS];
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

            long blockSize = fileSize / Constant.THREADS;

            for (int i = 0; i < Constant.THREADS; i++) {

                long start = i * blockSize;
                long end = (i == Constant.THREADS - 1) ? fileSize : (i + 1) * blockSize;
                threads[i] = new Thread(new FileSender(dos, randomAccessFile, start, end));

                System.out.println("发送第" + i + "个文件快, start:" + start + " end:" + end);
                threads[i].start();
            }

            for (Thread thread : threads) {
                thread.join();
            }

            System.out.println("文件 " + fileName + " 成功发送！");

            randomAccessFile.close();
            dos.close();
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class FileSender implements Runnable {

    private DataOutputStream dataOutputStream;
    private RandomAccessFile file;
    private long start;
    private long end;

    public FileSender(DataOutputStream dataOutputStream, RandomAccessFile file, long start, long end) {
        this.dataOutputStream = dataOutputStream;
        this.file = file;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            file.seek(start);
            byte[] buffer = new byte[4 * 1024];
            long remaining = end - start;
            int bytesRead;

            while (remaining > 0 && (bytesRead = file.read(buffer, 0, (int) Math.min(buffer.length, remaining))) != -1) {
                dataOutputStream.write(buffer, 0, bytesRead);
                remaining -= bytesRead;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
