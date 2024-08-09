package com.chason.encrypt.rc;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;
import java.security.SecureRandom;
import java.util.Base64;

public class RC5CBCUtils {

    static {
        // 注册Bouncy Castle提供者
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) throws Exception {
        // 明文
        String plaintext = "Hello, RC5 CBC!";

        // 生成密钥
        KeyGenerator keyGen = KeyGenerator.getInstance("RC5", "BC");
        // RC5的密钥长度可以是128位、192位或256位
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();

        // 生成随机初始化向量 (IV)
        // RC5通常使用64位8 byte
        byte[] iv = new byte[8];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // 加密
        String encryptedText = encrypt(plaintext, secretKey, ivSpec);
        System.out.println("加密后的文本: " + encryptedText);

        // 解密
        String decryptedText = decrypt(encryptedText, secretKey, ivSpec);
        System.out.println("解密后的文本: " + decryptedText);
    }

    public static String encrypt(String plaintext, SecretKey secretKey, IvParameterSpec ivSpec) throws Exception {
        Cipher cipher = Cipher.getInstance("RC5/CBC/PKCS7Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText, SecretKey secretKey, IvParameterSpec ivSpec) throws Exception {
        Cipher cipher = Cipher.getInstance("RC5/CBC/PKCS7Padding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes);
    }


}
