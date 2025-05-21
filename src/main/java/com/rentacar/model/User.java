package com.rentacar.model;

public class User {
    private int id;             // Kullanıcı ID
    private String name;        // Ad Soyad
    private String email;       // E-posta
    private String password;    // Şifre (hash'li)
    private String type;        // ADMIN veya CUSTOMER
    private int age;            // Yaş

    // constructor
    public User(int id, String name, String email, String password, String type, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.age = age;
    }

    // getter metotları
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getType() { return type; }
    public int getAge() { return age; }
}
