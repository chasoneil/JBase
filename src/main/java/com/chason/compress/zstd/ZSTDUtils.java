package com.chason.compress.zstd;

import com.github.luben.zstd.Zstd;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ZSTDUtils {

    // 压缩字符串
    public static byte[] compress(String input) {
        byte[] data = input.getBytes(StandardCharsets.UTF_8);
        return Zstd.compress(data);
    }

    // 解压缩数据
    public static String decompress(byte[] compressedData) {
        long decompressedSize = Zstd.decompressedSize(compressedData);
        byte[] decompressed = new byte[(int) decompressedSize];
        Zstd.decompress(decompressed, compressedData);
        return new String(decompressed, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        String input = "This is a test string for zstd compression and decompression.";
        System.out.println("Original: " + input);

        // 压缩
        byte[] compressed = compress(input);
        System.out.println("Compressed: " + Arrays.toString(compressed));

        // 解压缩
        String decompressed = decompress(compressed);
        System.out.println("Decompressed: " + decompressed);
    }

}
