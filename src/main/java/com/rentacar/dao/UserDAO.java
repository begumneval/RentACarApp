package com.rentacar.dao;

import com.rentacar.model.User;
import com.rentacar.util.DBConnection;

import java.sql.*;

public class UserDAO {

    // E-posta ve şifre ile kullanıcıyı bulur
    public User findByEmailAndPassword(String email, String hashedPassword) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, hashedPassword);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("type"),
                        rs.getInt("age")
                );
            }

        } catch (SQLException e) {
            System.out.println("Veritabanı hatası: " + e.getMessage());
        }

        return null; // Kullanıcı bulunamazsa null döner
    }
}
