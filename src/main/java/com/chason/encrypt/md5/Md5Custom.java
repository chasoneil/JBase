package com.chason.encrypt.md5;


/**
 * MD5
 * 1、填充消息：消息被填充为长度为512位的倍数。
 * 2、附加长度：消息长度（以字节为单位）附加到填充后的消息末尾。
 * 3、初始化MD缓冲区：初始化四个32位整数缓冲区（A、B、C、D），用于存储中间数据。
 * 4、处理消息块：将填充后的消息分块，每块为512位，循环处理。
 * 5、输出：最终输出四个缓冲区的组合结果，即128位的哈希值。
 */
public class Md5Custom {

    // 定义MD5的常量
    private static final int[] S = { 7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22,
            5,  9, 14, 20, 5,  9, 14, 20, 5,  9, 14, 20, 5,  9, 14, 20,
            4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23,
            6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21 };

    private static final int[] K = new int[64];
    static {
        for (int i = 0; i < 64; i++) {
            K[i] = (int) (long) ((1L << 32) * Math.abs(Math.sin(i + 1)));
        }
    }

    private static final int A_INIT = 0x67452301;
    private static final int B_INIT = 0xefcdab89;
    private static final int C_INIT = 0x98badcfe;
    private static final int D_INIT = 0x10325476;

    public static void main(String[] args) {
        String plaintext = "Hello, MD5!";
        String hashedText = md5Encrypt(plaintext);
        System.out.println("加密后的文本 (MD5 Hash): " + hashedText);
    }

    public static String md5Encrypt(String message) {
        // Step 1: Padding
        byte[] paddedMessage = padMessage(message.getBytes());

        // Step 2: Initialize buffer
        int a = A_INIT;
        int b = B_INIT;
        int c = C_INIT;
        int d = D_INIT;

        // Step 3: Process the message in 512-bit chunks
        for (int i = 0; i < paddedMessage.length / 64; i++) {
            int[] M = new int[16];
            for (int j = 0; j < 16; j++) {
                M[j] = (paddedMessage[i * 64 + j * 4] & 0xff) |
                        ((paddedMessage[i * 64 + j * 4 + 1] & 0xff) << 8) |
                        ((paddedMessage[i * 64 + j * 4 + 2] & 0xff) << 16) |
                        ((paddedMessage[i * 64 + j * 4 + 3] & 0xff) << 24);
            }

            int A = a;
            int B = b;
            int C = c;
            int D = d;

            for (int j = 0; j < 64; j++) {
                int F, g;
                if (j < 16) {
                    F = (B & C) | (~B & D);
                    g = j;
                } else if (j < 32) {
                    F = (D & B) | (~D & C);
                    g = (5 * j + 1) % 16;
                } else if (j < 48) {
                    F = B ^ C ^ D;
                    g = (3 * j + 5) % 16;
                } else {
                    F = C ^ (B | ~D);
                    g = (7 * j) % 16;
                }
                F = F + A + K[j] + M[g];
                A = D;
                D = C;
                C = B;
                B = B + leftRotate(F, S[j]);
            }

            a += A;
            b += B;
            c += C;
            d += D;
        }

        // Step 4: Output the result as a 32-character hexadecimal string
        return toHexString(a) + toHexString(b) + toHexString(c) + toHexString(d);
    }

    private static byte[] padMessage(byte[] message) {
        int originalLength = message.length;
        int padLength = (56 - (originalLength + 1) % 64) + 1;
        if (padLength < 1) padLength += 64;

        byte[] paddedMessage = new byte[originalLength + padLength + 8];
        System.arraycopy(message, 0, paddedMessage, 0, originalLength);

        paddedMessage[originalLength] = (byte) 0x80; // Padding with 1 followed by zeros

        long messageLengthBits = (long) originalLength * 8;
        for (int i = 0; i < 8; i++) {
            paddedMessage[originalLength + padLength + i] = (byte) (messageLengthBits >>> (8 * i));
        }

        return paddedMessage;
    }

    private static int leftRotate(int x, int amount) {
        return (x << amount) | (x >>> (32 - amount));
    }

    private static String toHexString(int value) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String hex = Integer.toHexString((value >>> (i * 8)) & 0xff);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }


}
