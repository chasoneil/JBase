package com.chason.encrypt.rc;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;
import java.util.Base64;


public class RC5ECBUtils {

    static {
        // 注册Bouncy Castle提供者
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) throws Exception {
        // 明文
        String plaintext = "Hello, RC5!";

        // 生成密钥
        KeyGenerator keyGen = KeyGenerator.getInstance("RC5");
        // RC5的密钥长度可以是128位、192位或256位
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();

        // 加密
        String encryptedText = encrypt(plaintext, secretKey);
        System.out.println("加密后的文本: " + encryptedText);

        // 解密
        String decryptedText = decrypt(encryptedText, secretKey);
        System.out.println("解密后的文本: " + decryptedText);
    }

    public static String encrypt(String plaintext, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RC5/ECB/PKCS7Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RC5/ECB/PKCS7Padding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes);
    }

}
