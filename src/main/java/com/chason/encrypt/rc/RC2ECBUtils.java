package com.chason.encrypt.rc;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;


/**
 * RC2 ECB
 */
public class RC2ECBUtils {

    public static void main(String[] args) throws Exception {
        // 明文
        String plaintext = "Hello, RC2!";

        // 生成密钥
        KeyGenerator keyGen = KeyGenerator.getInstance("RC2");
        keyGen.init(128); // RC2的密钥长度通常为40位到128位之间
        SecretKey secretKey = keyGen.generateKey();

        // 加密
        String encryptedText = encrypt(plaintext, secretKey);
        System.out.println("加密后的文本: " + encryptedText);

        // 解密
        String decryptedText = decrypt(encryptedText, secretKey);
        System.out.println("解密后的文本: " + decryptedText);
    }

    public static String encrypt(String plaintext, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RC2/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RC2/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes);
    }


}
