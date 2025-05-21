package com.rentacar.service;

import com.rentacar.dao.VehicleDAO;
import com.rentacar.model.User;
import com.rentacar.model.Vehicle;

import java.util.List;

public class VehicleService {
    private final VehicleDAO vehicleDAO = new VehicleDAO();

    // Yeni araç ekleme işlemi – yaş ve fiyat kısıtlarıyla birlikte
    public void addVehicle(User currentUser, String type, String model, double price) {
        boolean depositRequired = false;
        int ageLimit = 18;

        // Eğer araç 2 milyon TL'den pahalıysa:
        if (price > 2_000_000) {
            if (currentUser.getAge() < 30) {
                System.out.println("❌ Bu fiyat seviyesindeki aracı sadece 30 yaş ve üzeri kullanıcılar ekleyebilir.");
                return;
            }
            depositRequired = true;
            ageLimit = 30;
        }

        Vehicle vehicle = new Vehicle(0, type.toUpperCase(), model, price, depositRequired, ageLimit);
        vehicleDAO.save(vehicle);
    }

    // Tüm araçları listeler
    public void listAllVehicles() {
        List<Vehicle> vehicles = vehicleDAO.findAll();

        if (vehicles.isEmpty()) {
            System.out.println("📭 Sistem üzerinde kayıtlı araç bulunmamaktadır.");
            return;
        }

        for (Vehicle v : vehicles) {
            printVehicle(v);
        }
    }

    // ID'ye göre araç getirir
    public Vehicle findById(int id) {
        return vehicleDAO.findById(id);
    }

    // Araçları kategoriye göre filtreler (CAR, MOTORCYCLE, HELICOPTER)
    public void listVehiclesByType(String type) {
        List<Vehicle> vehicles = vehicleDAO.findByType(type);

        if (vehicles.isEmpty()) {
            System.out.println("📭 Bu kategoriye ait araç bulunamadı.");
            return;
        }

        for (Vehicle v : vehicles) {
            printVehicle(v);
        }
    }

    // Araç bilgilerini yazdıran yardımcı metot
    private void printVehicle(Vehicle v) {
        System.out.println("🔹 ID: " + v.getId() +
                " | Tip: " + v.getType() +
                " | Model: " + v.getModel() +
                " | Fiyat: " + v.getPrice() +
                "₺ | Depozito: " + (v.isDepositRequired() ? "Evet" : "Hayır") +
                " | Yaş Sınırı: " + v.getAgeLimit());
    }

}
