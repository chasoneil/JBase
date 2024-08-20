package com.chason.encrypt.sm;
import java.util.Arrays;

public class SM4 {

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

    private static final int[] R_CON = {
            0x00000000, 0x01000000, 0x02000000, 0x04000000,
            0x08000000, 0x10000000, 0x20000000, 0x40000000,
            0x80000000, 0x1B000000, 0x36000000, 0x6C000000,
            0xD8000000, 0xAB000000, 0x4D000000, 0x9A000000
    };

    public static void main(String[] args) {
        // Example usage
        String keyHex = "0123456789abcdef0123456789abcdef";  // 32 characters = 16 bytes
        String plaintextHex = "0123456789abcdef0123456789abcdef"; // 32 characters = 16 bytes

        byte[] key = hexStringToByteArray(keyHex);
        byte[] plaintext = hexStringToByteArray(plaintextHex);

        if (key.length != 16 || plaintext.length != 16) {
            throw new IllegalArgumentException("Key and plaintext must be 16 bytes long");
        }

        SM4 sm4 = new SM4();
        byte[] ciphertext = sm4.encrypt(plaintext, key);
        byte[] decryptedText = sm4.decrypt(ciphertext, key);

        System.out.printf("Plaintext: %s%n", bytesToHex(plaintext));
        System.out.printf("Ciphertext: %s%n", bytesToHex(ciphertext));
        System.out.printf("Decrypted: %s%n", bytesToHex(decryptedText));
    }

    private byte[] encrypt(byte[] plaintext, byte[] key) {
        int[] roundKeys = keyExpansion(key);
        int[] block = bytesToInts(plaintext);

        block = encryptBlock(block, roundKeys);

        return intsToBytes(block);
    }

    private byte[] decrypt(byte[] ciphertext, byte[] key) {
        int[] roundKeys = keyExpansion(key);
        int[] block = bytesToInts(ciphertext);

        block = decryptBlock(block, roundKeys);

        return intsToBytes(block);
    }

    private int[] keyExpansion(byte[] key) {
        int[] K = new int[36];
        int[] MK = bytesToInts(key);

        for (int i = 0; i < 4; i++) {
            K[i] = MK[i];
        }

        for (int i = 0; i < 32; i++) {
            int temp = K[i + 1];
            K[i + 1] = K[i] ^ (T(K[i + 1]) ^ R_CON[i]);
            K[i + 1] = (K[i + 1] << 13) | (K[i + 1] >>> 19);
            K[i + 1] ^= temp;
        }

        return Arrays.copyOfRange(K, 0, 32);
    }

    private int[] encryptBlock(int[] block, int[] roundKeys) {
        for (int i = 0; i < 32; i++) {
            block = round(block, roundKeys[i]);
        }
        return block;
    }

    private int[] decryptBlock(int[] block, int[] roundKeys) {
        for (int i = 31; i >= 0; i--) {
            block = round(block, roundKeys[i]);
        }
        return block;
    }

    private int[] round(int[] block, int roundKey) {
        int[] result = new int[4];
        result[0] = block[1] ^ (T(block[2]) ^ roundKey);
        result[1] = block[2];
        result[2] = block[3];
        result[3] = block[0];
        return result;
    }

    private int T(int value) {
        return SBOX[value & 0xFF] << 24 |
                SBOX[(value >> 8) & 0xFF] << 16 |
                SBOX[(value >> 16) & 0xFF] << 8 |
                SBOX[(value >> 24) & 0xFF];
    }

    private int[] bytesToInts(byte[] bytes) {
        int[] ints = new int[4];
        for (int i = 0; i < 4; i++) {
            ints[i] = (bytes[i * 4] & 0xFF) << 24 |
                    (bytes[i * 4 + 1] & 0xFF) << 16 |
                    (bytes[i * 4 + 2] & 0xFF) << 8 |
                    (bytes[i * 4 + 3] & 0xFF);
        }
        return ints;
    }

    private byte[] intsToBytes(int[] ints) {
        byte[] bytes = new byte[16];
        for (int i = 0; i < 4; i++) {
            bytes[i * 4] = (byte) (ints[i] >>> 24);
            bytes[i * 4 + 1] = (byte) (ints[i] >>> 16);
            bytes[i * 4 + 2] = (byte) (ints[i] >>> 8);
            bytes[i * 4 + 3] = (byte) (ints[i]);
        }
        return bytes;
    }

    private static byte[] hexStringToByteArray(String s) {
        if (s.length() % 2 != 0) {
            throw new IllegalArgumentException("Hex string must have an even length");
        }
        byte[] data = new byte[s.length() / 2];
        for (int i = 0; i < s.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
