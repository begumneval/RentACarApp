package com.rentacar.model;

import java.time.LocalDateTime;

public class Rental {
    private int id;
    private int userId;
    private int vehicleId;
    private String durationType; // HOUR, DAY, WEEK, MONTH
    private int durationValue;
    private double totalPrice;
    private double depositPaid;
    private LocalDateTime rentedAt;

    public Rental(int id, int userId, int vehicleId, String durationType, int durationValue, double totalPrice, double depositPaid, LocalDateTime rentedAt) {
        this.id = id;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.durationType = durationType;
        this.durationValue = durationValue;
        this.totalPrice = totalPrice;
        this.depositPaid = depositPaid;
        this.rentedAt = rentedAt;
    }

    // Getter metodları
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getVehicleId() { return vehicleId; }
    public String getDurationType() { return durationType; }
    public int getDurationValue() { return durationValue; }
    public double getTotalPrice() { return totalPrice; }
    public double getDepositPaid() { return depositPaid; }
    public LocalDateTime getRentedAt() { return rentedAt; }
}
