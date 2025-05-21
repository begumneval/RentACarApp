package com.rentacar.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Veritabanı bağlantı bilgileri
    private static final String URL = "jdbc:postgresql://localhost:5432/rentacar";
    private static final String USER = "postgres";       // PostgreSQL kullanıcı adın
    private static final String PASSWORD = "begum41"; // PostgreSQL şifren

    // Bağlantı nesnesi döndürür
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
