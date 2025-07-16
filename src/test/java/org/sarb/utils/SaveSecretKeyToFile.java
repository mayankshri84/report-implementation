package org.sarb.utils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class SaveSecretKeyToFile {
    public static void main(String[] args) throws Exception {
        // Step 1: Generate the AES SecretKey
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // Can be 192 or 256 as well
        SecretKey secretKey = keyGen.generateKey();

        // Step 2: Get the byte array representation of the key
        byte[] keyBytes = secretKey.getEncoded();

        // Step 3: Save to a file
        try {
            Files.write(Paths.get("secret.key"), keyBytes);
            System.out.println("Secret key saved to 'secret.key'");
        } catch (IOException e) {
            System.err.println("Error writing key to file: " + e.getMessage());
        }
    }
}