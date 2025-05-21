package com.rentacar.dao;

import com.rentacar.model.Vehicle;
import com.rentacar.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;



public class VehicleDAO {

    // Veritabanına yeni araç ekler
    public void save(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles (type, model, price, deposit_required, age_limit) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicle.getType());
            stmt.setString(2, vehicle.getModel());
            stmt.setDouble(3, vehicle.getPrice());
            stmt.setBoolean(4, vehicle.isDepositRequired());
            stmt.setInt(5, vehicle.getAgeLimit());

            stmt.executeUpdate();
            System.out.println("✅ Araç başarıyla eklendi.");

        } catch (SQLException e) {
            System.out.println("❌ Araç eklenirken hata oluştu: " + e.getMessage());
        }
    }

    // Tüm araçları getir
    public List<Vehicle> findAll() {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                vehicles.add(new Vehicle(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("model"),
                        rs.getDouble("price"),
                        rs.getBoolean("deposit_required"),
                        rs.getInt("age_limit")
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Araçlar getirilirken hata: " + e.getMessage());
        }

        return vehicles;
    }

    // Kategoriye göre filtrele
    public List<Vehicle> findByType(String type) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE type = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, type.toUpperCase());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                vehicles.add(new Vehicle(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("model"),
                        rs.getDouble("price"),
                        rs.getBoolean("deposit_required"),
                        rs.getInt("age_limit")
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Filtreli araç getirilirken hata: " + e.getMessage());
        }

        return vehicles;
    }

    // Belirli ID'ye göre araç döner
    public Vehicle findById(int id) {
        String sql = "SELECT * FROM vehicles WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Vehicle(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("model"),
                        rs.getDouble("price"),
                        rs.getBoolean("deposit_required"),
                        rs.getInt("age_limit")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Araç getirme hatası: " + e.getMessage());
        }

        return null;
    }


}
