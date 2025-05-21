package com.rentacar.dao;

import com.rentacar.model.Rental;
import com.rentacar.util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentalDAO {

    // Yeni kiralama kaydı ekler
    public void save(Rental rental) {
        String sql = "INSERT INTO rentals (user_id, vehicle_id, duration_type, duration_value, total_price, deposit_paid) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, rental.getUserId());
            stmt.setInt(2, rental.getVehicleId());
            stmt.setString(3, rental.getDurationType());
            stmt.setInt(4, rental.getDurationValue());
            stmt.setDouble(5, rental.getTotalPrice());
            stmt.setDouble(6, rental.getDepositPaid());

            stmt.executeUpdate();
            System.out.println("✅ Kiralama başarıyla kaydedildi.");

        } catch (SQLException e) {
            System.out.println("❌ Kiralama kaydedilemedi: " + e.getMessage());
        }
    }

    // Kullanıcının tüm kiralamalarını getirir
    public List<Rental> findByUserId(int userId) {
        List<Rental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM rentals WHERE user_id = ? ORDER BY rented_at DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                rentals.add(new Rental(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("vehicle_id"),
                        rs.getString("duration_type"),
                        rs.getInt("duration_value"),
                        rs.getDouble("total_price"),
                        rs.getDouble("deposit_paid"),
                        rs.getTimestamp("rented_at").toLocalDateTime()
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Kiralama geçmişi alınamadı: " + e.getMessage());
        }

        return rentals;
    }
}
