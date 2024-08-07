package com.chason.compress.bzip2;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class BZip2Utils {

    // 使用 BZip2 压缩字符串
    public static byte[] compress(String input) throws IOException {
        byte[] data = input.getBytes(StandardCharsets.UTF_8);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (BZip2CompressorOutputStream bzip2Out = new BZip2CompressorOutputStream(bos)) {
            bzip2Out.write(data);
        }
        return bos.toByteArray();
    }

    // 使用 BZip2 解压缩数据
    public static String decompress(byte[] compressedData) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(compressedData);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (BZip2CompressorInputStream bzip2In = new BZip2CompressorInputStream(bis)) {
            byte[] buffer = new byte[1024];
            int n;
            while ((n = bzip2In.read(buffer)) != -1) {
                bos.write(buffer, 0, n);
            }
        }
        return bos.toString(StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        try {
            String input = "This is a test string for BZip2 compression and decompression.";
            System.out.println("Original: " + input);

            // 压缩
            byte[] compressed = compress(input);
            System.out.println("Compressed: " + compressed.length + " bytes");

            // 解压缩
            String decompressed = decompress(compressed);
            System.out.println("Decompressed: " + decompressed);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
