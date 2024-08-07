package com.chason.encrypt.elgamal;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.encodings.OAEPEncoding;
import org.bouncycastle.crypto.engines.ElGamalEngine;
import org.bouncycastle.crypto.generators.ElGamalKeyPairGenerator;
import org.bouncycastle.crypto.generators.ElGamalParametersGenerator;
import org.bouncycastle.crypto.params.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

public class ElgamalUtils {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static AsymmetricCipherKeyPair generateElGamalKeyPair() {
        // Generate ElGamal parameters
        ElGamalParametersGenerator paramGen = new ElGamalParametersGenerator();
        paramGen.init(1024, 20, new SecureRandom()); // 1024-bit key size
        ElGamalParameters elGamalParameters = paramGen.generateParameters();

        // Generate the key pair
        ElGamalKeyPairGenerator keyPairGen = new ElGamalKeyPairGenerator();
        keyPairGen.init(new ElGamalKeyGenerationParameters(new SecureRandom(), elGamalParameters));
        return keyPairGen.generateKeyPair();
    }

    public static String encrypt(String plaintext, ElGamalPublicKeyParameters publicKey) throws Exception {
        ElGamalEngine engine = new ElGamalEngine();
        OAEPEncoding cipher = new OAEPEncoding(engine);
        cipher.init(true, publicKey);

        byte[] plaintextBytes = plaintext.getBytes();
        byte[] encryptedBytes = cipher.processBlock(plaintextBytes, 0, plaintextBytes.length);
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String ciphertext, ElGamalPrivateKeyParameters privateKey) throws Exception {
        ElGamalEngine engine = new ElGamalEngine();
        OAEPEncoding cipher = new OAEPEncoding(engine);
        cipher.init(false, privateKey);

        byte[] ciphertextBytes = Base64.getDecoder().decode(ciphertext);
        byte[] decryptedBytes = cipher.processBlock(ciphertextBytes, 0, ciphertextBytes.length);
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            // Generate key pair
            AsymmetricCipherKeyPair keyPair = generateElGamalKeyPair();
            ElGamalPublicKeyParameters publicKey = (ElGamalPublicKeyParameters) keyPair.getPublic();
            ElGamalPrivateKeyParameters privateKey = (ElGamalPrivateKeyParameters) keyPair.getPrivate();

            // Original string
            String plaintext = "This is a test string for ElGamal encryption and decryption.";
            System.out.println("Original: " + plaintext);

            // Encrypt
            String encryptedText = encrypt(plaintext, publicKey);
            System.out.println("Encrypted: " + encryptedText);

            // Decrypt
            String decryptedText = decrypt(encryptedText, privateKey);
            System.out.println("Decrypted: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
