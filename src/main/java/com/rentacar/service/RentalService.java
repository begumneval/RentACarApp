package com.rentacar.service;

import com.rentacar.dao.RentalDAO;
import com.rentacar.model.Rental;
import com.rentacar.model.User;
import com.rentacar.model.Vehicle;

import java.time.LocalDateTime;
import java.util.List;

public class RentalService {

    private final RentalDAO rentalDAO = new RentalDAO();

    // Kiralama işlemi yapar
    public void rentVehicle(User user, Vehicle vehicle, String durationType, int durationValue) {
        double unitPrice;

        // Araç türüne göre birim fiyat belirle
        switch (durationType.toUpperCase()) {
            case "HOUR":
                unitPrice = 100;
                break;
            case "DAY":
                unitPrice = 500;
                break;
            case "WEEK":
                unitPrice = 2500;
                break;
            case "MONTH":
                unitPrice = 8000;
                // Kurumsal kullanıcı kuralı
                if (!user.getType().equalsIgnoreCase("CORPORATE")) {
                    System.out.println("❌ Aylık kiralama sadece kurumsal kullanıcılar içindir.");
                    return;
                }
                break;
            default:
                System.out.println("❌ Geçersiz süre tipi.");
                return;
        }

        double total = unitPrice * durationValue;
        double deposit = vehicle.isDepositRequired() ? vehicle.getPrice() * 0.10 : 0;

        Rental rental = new Rental(
                0,
                user.getId(),
                vehicle.getId(),
                durationType.toUpperCase(),
                durationValue,
                total,
                deposit,
                LocalDateTime.now()
        );

        rentalDAO.save(rental);
    }

    // Kullanıcının geçmiş kiralamalarını listeler
    public void listUserRentals(User user) {
        List<Rental> rentals = rentalDAO.findByUserId(user.getId());

        if (rentals.isEmpty()) {
            System.out.println("📭 Herhangi bir kiralama bulunamadı.");
            return;
        }

        for (Rental r : rentals) {
            System.out.println("🔹 ID: " + r.getId() +
                    " | Araç ID: " + r.getVehicleId() +
                    " | Süre: " + r.getDurationValue() + " " + r.getDurationType() +
                    " | Ücret: " + r.getTotalPrice() + "₺" +
                    " | Depozito: " + r.getDepositPaid() +
                    " | Tarih: " + r.getRentedAt());
        }
    }
}
