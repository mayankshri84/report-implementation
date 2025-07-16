package org.sarb.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class EncryptAndSaveKey {
    public static void main(String[] args) throws Exception {

        if(isFilePresent(System.getProperty("user.dir")+"/secret.key")){
            generateSecretKey();
        }
        String encryptedText = encryptCode("Mayank");
        decrytCode(encryptedText);
    }

    public static void generateSecretKey() throws Exception{
        // Step 1: Generate SecretKey
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // You can also use 192 or 256 bits
        SecretKey secretKey = keyGen.generateKey();

        // Step 2: Save SecretKey to a file (as bytes)
        byte[] keyBytes = secretKey.getEncoded();
        Files.write(Paths.get("secret.key"), keyBytes); // Store securely in production

    }

    public static String encryptCode(String passcode) throws Exception{
        byte[] savedKeyBytes = Files.readAllBytes(Paths.get("secret.key"));
        SecretKey loadedKey = new SecretKeySpec(savedKeyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, loadedKey);
        byte[] encryptedBytes = cipher.doFinal(passcode.getBytes());

        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Encrypted Text: " + encryptedText);
        return encryptedText;
    }

    public static void decrytCode(String encryptedText) throws Exception{
        byte[] savedKeyBytes = Files.readAllBytes(Paths.get("secret.key"));
        SecretKey loadedKey = new SecretKeySpec(savedKeyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, loadedKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        String decryptedText = new String(decryptedBytes);

        System.out.println("Decrypted Text: " + decryptedText);
    }


    public static boolean isFilePresent(String filePath){
        File file = new File(filePath);
        boolean isPresent = true;

        if (file.exists()) {
            isPresent = false;
        }
        return isPresent;
    }
}