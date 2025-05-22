-- CREATE DATABASE rentacar;
-- \c rentacar

-- USERS
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    age INTEGER NOT NULL,
    type VARCHAR(10) CHECK (type IN ('ADMIN', 'CUSTOMER', 'CORPORATE')) NOT NULL
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

-- Örnek kullanıcılar (şifre: 123456 hashlenmiş hali)
INSERT INTO users (name, email, password, age, type) VALUES
('Admin User', 'admin@example.com', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=', 40, 'ADMIN'),
('Bireysel Kullanıcı', 'test@example.com', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=', 28, 'CUSTOMER'),
('Genç Kullanıcı', 'young@example.com', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=', 22, 'CUSTOMER'),
('Kurumsal Kullanıcı', 'kurumsal@example.com', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=', 35, 'CORPORATE');

-- Örnek araçlar
INSERT INTO vehicles (type, model, price, deposit_required, age_limit) VALUES
('CAR', 'BMW 320i', 1200000, false, 18),
('CAR', 'Mercedes C200', 2100000, true, 30),
('MOTORCYCLE', 'Honda CBR 500R', 800000, false, 18),
('HELICOPTER', 'Bell 206', 7200000, true, 30),
('CAR', 'Tofaş Şahin', 350000, false, 18);
