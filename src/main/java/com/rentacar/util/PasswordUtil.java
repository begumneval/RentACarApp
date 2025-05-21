package com.rentacar.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordUtil {

    // Şifreyi SHA-256 ile hash'ler ve Base64 formatına çevirir
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // SHA-256 nesnesi
            byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8)); // UTF-8 ile hashle
            return Base64.getEncoder().encodeToString(hashedBytes); // Base64 string'e çevir
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algoritması yok", e); // Hata durumunda
        }
    }
}
