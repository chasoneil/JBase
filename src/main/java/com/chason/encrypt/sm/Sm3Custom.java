package com.chason.encrypt.sm;

/**
 * sm3
 *
 * 1.填充消息：消息被填充为长度为 512 位的倍数。
 * 2.初始化向量：SM3 使用 8 个 32 位的常量初始化向量（IV）。
 * 3.消息扩展：消息被扩展为 132 个 32 位的字。
 * 4.压缩函数：使用消息扩展生成的字和初始向量对每个 512 位块进行压缩计算。
 * 5.最终输出：8 个 32 位字拼接成 256 位的哈希值。
 *
 */
public class Sm3Custom {

    private static final int[] IV = {
            0x7380166F, 0x4914B2B9, 0x172442D7, 0xDA8A0600,
            0xA96F30BC, 0x163138AA, 0xE38DEE4D, 0xB0FB0E4E
    };

    public static void main(String[] args) {
        String plaintext = "Hello, SM3!";
        String hashedText = sm3Encrypt(plaintext);
        System.out.println("加密后的文本 (SM3 Hash): " + hashedText);
    }

    public static String sm3Encrypt(String message) {

        // 消息填充：padMessage方法填充消息，使其长度达到512位的倍数，并在填充的末尾添加消息的长度（以比特为单位）。
        byte[] paddedMessage = padMessage(message.getBytes());

        //初始化向量 (IV)：SM3的IV由八个32位的常量组成。
        int[] V = IV.clone();

        /*
         * 消息扩展和压缩：
         *  W数组和W1数组是扩展后的消息字。
         *  B数组存储压缩后的值。
         *  循环处理每一个512位的消息块，并对B进行更新。
         *  FF和GG是两个布尔函数，P0和P1是置换函数。
         */
        for (int i = 0; i < paddedMessage.length / 64; i++) {
            int[] W = new int[68];
            int[] W1 = new int[64];
            int[] B = new int[8];

            for (int j = 0; j < 16; j++) {
                W[j] = (paddedMessage[i * 64 + j * 4] & 0xff) << 24 |
                        (paddedMessage[i * 64 + j * 4 + 1] & 0xff) << 16 |
                        (paddedMessage[i * 64 + j * 4 + 2] & 0xff) << 8 |
                        (paddedMessage[i * 64 + j * 4 + 3] & 0xff);
            }

            for (int j = 16; j < 68; j++) {
                W[j] = P1(W[j-16] ^ W[j-9] ^ leftRotate(W[j-3], 15)) ^ leftRotate(W[j-13], 7) ^ W[j-6];
            }

            for (int j = 0; j < 64; j++) {
                W1[j] = W[j] ^ W[j + 4];
            }

            System.arraycopy(V, 0, B, 0, 8);

            for (int j = 0; j < 64; j++) {
                int SS1 = leftRotate(leftRotate(B[0], 12) + B[4] + leftRotate(T(j), j), 7);
                int SS2 = SS1 ^ leftRotate(B[0], 12);
                int TT1 = FF(j, B[0], B[1], B[2]) + B[3] + SS2 + W1[j];
                int TT2 = GG(j, B[4], B[5], B[6]) + B[7] + SS1 + W[j];
                B[3] = B[2];
                B[2] = leftRotate(B[1], 9);
                B[1] = B[0];
                B[0] = TT1;
                B[7] = B[6];
                B[6] = leftRotate(B[5], 19);
                B[5] = B[4];
                B[4] = P0(TT2);
            }

            for (int j = 0; j < 8; j++) {
                V[j] ^= B[j];
            }
        }

        // 最终输出：将8个32位的寄存器值拼接成256位的哈希值，并转换为十六进制字符串。
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            hexString.append(toHexString(V[i]));
        }

        return hexString.toString();
    }

    private static byte[] padMessage(byte[] message) {
        int originalLength = message.length;
        int padLength = (64 - (originalLength + 9) % 64);
        byte[] paddedMessage = new byte[originalLength + padLength + 9];

        System.arraycopy(message, 0, paddedMessage, 0, originalLength);
        paddedMessage[originalLength] = (byte) 0x80;

        long bitLength = (long) originalLength * 8;
        for (int i = 0; i < 8; i++) {
            paddedMessage[originalLength + padLength + 1 + i] = (byte) (bitLength >>> (56 - i * 8));
        }

        return paddedMessage;
    }

    private static int T(int j) {
        return j < 16 ? 0x79CC4519 : 0x7A879D8A;
    }

    private static int FF(int j, int X, int Y, int Z) {
        return j < 16 ? (X ^ Y ^ Z) : ((X & Y) | (X & Z) | (Y & Z));
    }

    private static int GG(int j, int X, int Y, int Z) {
        return j < 16 ? (X ^ Y ^ Z) : ((X & Y) | (~X & Z));
    }

    private static int P0(int X) {
        return X ^ leftRotate(X, 9) ^ leftRotate(X, 17);
    }

    private static int P1(int X) {
        return X ^ leftRotate(X, 15) ^ leftRotate(X, 23);
    }

    private static int leftRotate(int x, int n) {
        return (x << n) | (x >>> (32 - n));
    }

    private static String toHexString(int value) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String hex = Integer.toHexString((value >>> (24 - i * 8)) & 0xff);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }


}
