package br.com.infox.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptoString {

    public static String encryptString(String str) {
        StringBuilder sb = new StringBuilder();

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = md.digest(str.getBytes(StandardCharsets.UTF_8));

            for (byte b : messageDigest) {
                sb.append(String.format("%02X", 0xFF & b));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

}
