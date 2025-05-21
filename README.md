# 🚗 Araç Kiralama Uygulaması

Java ve PostgreSQL kullanılarak geliştirilen, terminal tabanlı bir araç kiralama yönetim sistemidir. Katmanlı mimariyle yazılmıştır. Kullanıcılar sisteme giriş yapabilir, araçları listeleyebilir ve kiralama işlemi gerçekleştirilebilir.

---

## 💡 Özellikler

* Kullanıcı girişi (şifreler SHA-256 ile şifrelenir)
* Admin paneli: araç ekleme
* Müşteri paneli:

    * Araç listeleme
    * Kategoriye göre filtreleme
    * Araç kiralama (saatlik/günlük/haftalık/aylık)
    * Kiralama geçmişi görüntüleme
* Yaş ve fiyat bazlı kurallar
* Katmanlı mimari (Model, DAO, Service, Main)

---

## 💠 Kullanılan Teknolojiler

* Java 23
* Maven
* PostgreSQL 15
* JDBC
* Terminal tabanlı kullanıcı arayüzü

---

## 🧱 Veritabanı Kurulumu

### 1. PostgreSQL terminaline bağlan:

```bash
psql -U postgres
```

### 2. Veritabanını oluştur:

```sql
CREATE DATABASE rentacar;
\c rentacar
```

### 3. Tabloları oluştur:

```sql
-- USERS\CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    age INTEGER NOT NULL,
    type VARCHAR(10) CHECK (type IN ('ADMIN', 'CUSTOMER')) NOT NULL
);

-- VEHICLES
CREATE TABLE vehicles (
    id SERIAL PRIMARY KEY,
    type VARCHAR(20) NOT NULL CHECK (type IN ('CAR', 'MOTORCYCLE', 'HELICOPTER')),
    model VARCHAR(100) NOT NULL,
    price NUMERIC(12, 2) NOT NULL,
    deposit_required BOOLEAN NOT NULL,
    age_limit INTEGER NOT NULL
);

-- RENTALS
CREATE TABLE rentals (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    vehicle_id INTEGER REFERENCES vehicles(id),
    duration_type VARCHAR(10) CHECK (duration_type IN ('HOUR', 'DAY', 'WEEK', 'MONTH')),
    duration_value INTEGER NOT NULL,
    total_price NUMERIC(12,2) NOT NULL,
    deposit_paid NUMERIC(12,2) DEFAULT 0,
    rented_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## 👤 Örnek Giriş Bilgileri

```sql
-- Şifre: 123456 (SHA-256 + Base64)
INSERT INTO users (name, email, password, age, type)
VALUES
('Admin User', 'admin@example.com', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=', 35, 'ADMIN'),
('Test Kullanıcı', 'test@example.com', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=', 25, 'CUSTOMER');
```

---

## 🚙 Örnek Araçlar

```sql
INSERT INTO vehicles (type, model, price, deposit_required, age_limit)
VALUES
('CAR', 'Audi A4', 1200000, false, 18),
('HELICOPTER', 'Airbus H125', 5000000, true, 30),
('MOTORCYCLE', 'Yamaha MT-07', 900000, false, 18);
```

---

## ▶️ Çalıştırma

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.rentacar.app.Main"
```

---

## 📟 Katmanlar

* `model` → veri yapıları (`User`, `Vehicle`, `Rental`)
* `dao` → veritabanı işlemleri (`UserDAO`, `VehicleDAO`, `RentalDAO`)
* `service` → iş mantığı (`UserService`, `VehicleService`, `RentalService`)
* `app` → kullanıcı arayüzü (`Main` sınıfı)

---

## 📌 Notlar

* SHA-256 ile şifrelenmiş giriş kullanılır
* Aylık kiralama sadece `CORPORATE` kullanıcılar içindir
* 2M+ TL araçlar için yaş sınırı 30 ve %10 depozito alınır

---

## 📄 Geliştirici

> Bitirme projesi kapsamında geliştirilmiştir.
> Öğrenci: **Begüm Neval Yılmaz**
