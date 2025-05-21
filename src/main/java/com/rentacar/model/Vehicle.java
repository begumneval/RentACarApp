package com.rentacar.model;

public class Vehicle {
    private int id;
    private String type;      // Car, Motorcycle, Helicopter
    private String model;     // Araç modeli (ör: Audi A4)
    private double price;     // Toplam araç bedeli
    private boolean depositRequired;
    private int ageLimit;

    public Vehicle(int id, String type, String model, double price, boolean depositRequired, int ageLimit) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.price = price;
        this.depositRequired = depositRequired;
        this.ageLimit = ageLimit;
    }

    // Getter'lar
    public int getId() { return id; }
    public String getType() { return type; }
    public String getModel() { return model; }
    public double getPrice() { return price; }
    public boolean isDepositRequired() { return depositRequired; }
    public int getAgeLimit() { return ageLimit; }
}
