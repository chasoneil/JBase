package com.chason.encrypt.sm;

import java.security.SecureRandom;

/**
 * Sm4
 *
 * 密钥扩展：生成轮密钥
 * 加密过程：输入数据经过32轮迭代加密
 * 解密过程：解密过程与加密类似，只是轮密钥的顺序相反
 */
public class Sm4Custom {

    private static final int[] SBOX = {
            0xd6, 0x90, 0xe9, 0xfe, 0xcc, 0xe1, 0x3d, 0xb7, 0x16, 0xb6, 0x14, 0xc2, 0x28, 0xfb, 0x2c, 0x05,
            0x2b, 0x67, 0x9a, 0x76, 0x2a, 0xbe, 0x04, 0xc3, 0xaa, 0x44, 0x13, 0x26, 0x49, 0x86, 0x06, 0x99,
            0x9c, 0x42, 0x50, 0xf4, 0x91, 0xef, 0x98, 0x7a, 0x33, 0x54, 0x0b, 0x43, 0xed, 0xcf, 0xac, 0x62,
            0xe4, 0xb3, 0x1c, 0xa9, 0xc9, 0x08, 0xe8, 0x95, 0x80, 0xdf, 0x94, 0xfa, 0x75, 0x8f, 0x3f, 0xa6,
            0x47, 0x07, 0xa7, 0xfc, 0xf3, 0x73, 0x17, 0xba, 0x83, 0x59, 0x3c, 0x19, 0xe6, 0x85, 0x4f, 0xa8,
            0x68, 0x6b, 0x81, 0xb2, 0x71, 0x64, 0xda, 0x8b, 0xf8, 0xeb, 0x0f, 0x4b, 0x70, 0x56, 0x9d, 0x35,
            0x1e, 0x24, 0x0e, 0x5e, 0x63, 0x58, 0xd1, 0xa2, 0x25, 0x22, 0x7c, 0x3b, 0x01, 0x21, 0x78, 0x87,
            0xd4, 0x00, 0x46, 0x57, 0x9f, 0xd3, 0x27, 0x52, 0x4c, 0x36, 0x02, 0xe7, 0xa0, 0xc4, 0xc8, 0x9e,
            0xea, 0xbf, 0x8a, 0xd2, 0x40, 0xc7, 0x38, 0xb5, 0xa3, 0xf7, 0xf2, 0xce, 0xf9, 0x61, 0x15, 0xa1,
            0xe0, 0xae, 0x5d, 0xa4, 0x9b, 0x34, 0x1a, 0x55, 0xad, 0x93, 0x32, 0x30, 0xf5, 0x8c, 0xb1, 0xe3,
            0x1d, 0xf6, 0xe2, 0x2e, 0x82, 0x66, 0xca, 0x60, 0xc0, 0x29, 0x23, 0xab, 0x0d, 0x53, 0x4e, 0x6f,
            0xd5, 0xdb, 0x37, 0x45, 0xde, 0xfd, 0x8e, 0x2f, 0x03, 0xff, 0x6a, 0x72, 0x6d, 0x6c, 0x5b, 0x51,
            0x8d, 0x1b, 0xaf, 0x92, 0xbb, 0xdd, 0xbc, 0x7f, 0x11, 0xd9, 0x5c, 0x41, 0x1f, 0x10, 0x5a, 0xd8,
            0x0a, 0xc1, 0x31, 0x88, 0xa5, 0xcd, 0x7b, 0xbd, 0x2d, 0x74, 0xd0, 0x12, 0xb8, 0xe5, 0xb4, 0xb0,
            0x89, 0x69, 0x97, 0x4a, 0x0c, 0x96, 0x77, 0x7e, 0x65, 0xb9, 0xf1, 0x09, 0xc5, 0x6e, 0xc6, 0x84,
            0x18, 0xf0, 0x7d, 0xec, 0x3a, 0xdc, 0x4d, 0x20, 0x79, 0xee, 0x5f, 0x3e, 0xd7, 0xcb, 0x39, 0x48
    };

    private static final int[] CK = {
            0x00070e15, 0x1c232a31, 0x383f464d, 0x545b6269,
            0x70777e85, 0x8c939aa1, 0xa8afb6bd, 0xc4cbd2d9,
            0xe0e7eef5, 0xfc030a11, 0x181f262d, 0x343b4249,
            0x50575e65, 0x6c737a81, 0x888f969d, 0xa4abb2b9,
            0xc0c7ced5, 0xdce3eaf1, 0xf8ff060d, 0x141b2229,
            0x30373e45, 0x4c535a61, 0x686f767d, 0x848b9299,
            0xa0a7aeb5, 0xbcc3cad1, 0xd8dfe6ed, 0xf4fb0209,
            0x10171e25, 0x2c333a41, 0x484f565d, 0x646b7279
    };

    public static void main(String[] args) {
        String plaintext = "Hello, SM4!";
        byte[] key = generateKey();
        // 使用前16字节作为密钥
        // System.arraycopy(plaintext.getBytes(), 0, key, 0, Math.min(plaintext.length(), key.length));



        byte[] encryptedText = sm4Encrypt(plaintext.getBytes(), key);
        System.out.println("加密后的文本 (SM4): " + bytesToHex(encryptedText));

        byte[] decryptedText = sm4Decrypt(encryptedText, key);
        System.out.println("解密后的文本 (SM4): " + new String(decryptedText));
    }

    public static byte[] generateKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16]; // 16 bytes = 128 bits
        random.nextBytes(key);
        return key;
    }

    public static byte[] sm4Encrypt(byte[] plaintext, byte[] key) {
        int[] roundKeys = keyExpansion(key);
        System.out.println("Round key:" + roundKeys.length);
        return crypt(plaintext, roundKeys);
    }

    public static byte[] sm4Decrypt(byte[] ciphertext, byte[] key) {

        // S盒和轮常量表：SBOX是用于字节替换的查找表，CK是用于密钥扩展的轮常量
        // 密钥扩展：keyExpansion方法将128位的密钥扩展为32个32位的轮密钥
        int[] roundKeys = keyExpansion(key);
        int[] reversedRoundKeys = new int[roundKeys.length];
        for (int i = 0; i < roundKeys.length; i++) {
            reversedRoundKeys[i] = roundKeys[roundKeys.length - 1 - i];
        }
        return crypt(ciphertext, reversedRoundKeys);
    }

    private static byte[] crypt(byte[] input, int[] roundKeys) {
        int[] x = new int[4];
        for (int i = 0; i < 4; i++) {
            x[i] = (input[i * 4] & 0xff) << 24 |
                    (input[i * 4 + 1] & 0xff) << 16 |
                    (input[i * 4 + 2] & 0xff) << 8 |
                    (input[i * 4 + 3] & 0xff);
        }

        for (int i = 0; i < 32; i++) {
            int tmp = x[1] ^ x[2] ^ x[3] ^ roundKeys[i];
            int t = substitute(tmp);
            t = t ^ leftRotate(t, 2) ^ leftRotate(t, 10) ^ leftRotate(t, 18) ^ leftRotate(t, 24);
            x = new int[]{x[1], x[2], x[3], x[0] ^ t};
        }

        int[] result = {x[3], x[2], x[1], x[0]};
        byte[] output = new byte[16];
        for (int i = 0; i < 4; i++) {
            output[i * 4] = (byte) (result[i] >>> 24);
            output[i * 4 + 1] = (byte) (result[i] >>> 16);
            output[i * 4 + 2] = (byte) (result[i] >>> 8);
            output[i * 4 + 3] = (byte) result[i];
        }
        return output;
    }

    private static int[] keyExpansion(byte[] key) {
        if (key.length != 16) {
            throw new IllegalArgumentException("密钥长度必须为16字节");
        }

        int[] mk = new int[4];
        for (int i = 0; i < 4; i++) {
            mk[i] = (key[i * 4] & 0xff) << 24 |
                    (key[i * 4 + 1] & 0xff) << 16 |
                    (key[i * 4 + 2] & 0xff) << 8 |
                    (key[i * 4 + 3] & 0xff);
        }

        int[] k = new int[36];
        System.arraycopy(mk, 0, k, 0, 4);

        for (int i = 0; i < 32; i++) {
            int tmp = k[i + 1] ^ k[i + 2] ^ k[i + 3] ^ CK[i];
            int t = substitute(tmp);
            t = t ^ leftRotate(t, 13) ^ leftRotate(t, 23);
            k[i + 4] = k[i] ^ t;
        }

        int[] roundKeys = new int[32];
        System.arraycopy(k, 4, roundKeys, 0, 32);
        return roundKeys;
    }

    // 执行S盒替换
    private static int substitute(int x) {
        return (SBOX[(x >>> 24) & 0xff] & 0xff) << 24 |
                (SBOX[(x >>> 16) & 0xff] & 0xff) << 16 |
                (SBOX[(x >>> 8) & 0xff] & 0xff) << 8 |
                (SBOX[x & 0xff] & 0xff);
    }

    // 执行左循环移位操作
    private static int leftRotate(int x, int n) {
        return (x << n) | (x >>> (32 - n));
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
