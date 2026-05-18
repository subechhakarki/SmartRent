-- ===================================================
-- SmartRent Complete Database Schema for MySQL Workbench
-- ===================================================

CREATE DATABASE IF NOT EXISTS SmartRent;
USE SmartRent;

-- Drop tables in reverse order of foreign keys to avoid conflicts
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS leases;
DROP TABLE IF EXISTS saved_properties;
DROP TABLE IF EXISTS property_ratings;
DROP TABLE IF EXISTS rental_applications;
DROP TABLE IF EXISTS properties;
DROP TABLE IF EXISTS renters;
DROP TABLE IF EXISTS property_owners;
DROP TABLE IF EXISTS users;

-- =====================
-- 1. USERS
-- =====================
CREATE TABLE users (
    user_id        INT AUTO_INCREMENT PRIMARY KEY,
    full_name      VARCHAR(100) NOT NULL,
    email          VARCHAR(150) NOT NULL UNIQUE,
    password_hash  VARCHAR(255) NOT NULL,
    role           ENUM('SUPER_ADMIN', 'PROPERTY_OWNER', 'RENTER') NOT NULL,
    user_status    ENUM('ACTIVE', 'PENDING', 'SUSPENDED', 'REJECTED') NOT NULL,
    phone          VARCHAR(20),
    login_attempts INT DEFAULT 0,
    locked_until   TIMESTAMP NULL,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =====================
-- 2. PROPERTY OWNERS
-- =====================
CREATE TABLE property_owners (
    owner_id        INT PRIMARY KEY,
    approval_status ENUM('PENDING', 'APPROVED', 'REJECTED') NOT NULL DEFAULT 'PENDING',
    approval_note   TEXT,
    FOREIGN KEY (owner_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- =====================
-- 3. RENTERS
-- =====================
CREATE TABLE renters (
    renter_id         INT PRIMARY KEY,
    employment_status VARCHAR(100),
    monthly_income    DECIMAL(10,2),
    FOREIGN KEY (renter_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- =====================
-- 4. PROPERTIES
-- =====================
CREATE TABLE properties (
    property_id    INT AUTO_INCREMENT PRIMARY KEY,
    owner_id       INT NOT NULL,
    title          VARCHAR(200) NOT NULL,
    address        VARCHAR(300) NOT NULL,
    property_type  VARCHAR(100) NOT NULL, -- Flexible types: Apartment, House, Room, Studio, etc.
    bedrooms       INT NOT NULL,
    bathrooms      INT NOT NULL,
    monthly_rent   DECIMAL(10,2) NOT NULL,
    deposit        DECIMAL(10,2) NOT NULL,
    prop_status    ENUM('AVAILABLE', 'OCCUPIED', 'MAINTENANCE') NOT NULL DEFAULT 'AVAILABLE',
    available_from DATE NOT NULL,
    avg_rating     DECIMAL(2,1) DEFAULT 0.0,
    image_path     VARCHAR(500),
    is_primary     TINYINT(1) DEFAULT 0,
    sort_order     INT DEFAULT 0,
    description    TEXT,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (owner_id) REFERENCES property_owners(owner_id) ON DELETE CASCADE
);

-- =====================
-- 5. RENTAL APPLICATIONS
-- =====================
CREATE TABLE rental_applications (
    application_id INT AUTO_INCREMENT PRIMARY KEY,
    property_id    INT NOT NULL,
    renter_id      INT NOT NULL,
    move_in_date   DATE NOT NULL,
    cover_message  TEXT,
    app_status     ENUM('SUBMITTED', 'UNDER_REVIEW', 'APPROVED', 'REJECTED', 'WITHDRAWN') NOT NULL DEFAULT 'SUBMITTED',
    rejection_note TEXT,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY unique_application (renter_id, property_id),
    FOREIGN KEY (property_id) REFERENCES properties(property_id) ON DELETE CASCADE,
    FOREIGN KEY (renter_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- =====================
-- 6. PROPERTY RATINGS
-- =====================
CREATE TABLE property_ratings (
    rating_id      INT AUTO_INCREMENT PRIMARY KEY,
    property_id    INT NOT NULL,
    renter_id      INT NOT NULL,
    rating         INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    review_comment TEXT,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (property_id) REFERENCES properties(property_id) ON DELETE CASCADE,
    FOREIGN KEY (renter_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- =====================
-- 7. SAVED PROPERTIES
-- =====================
CREATE TABLE saved_properties (
    saved_id    INT AUTO_INCREMENT PRIMARY KEY,
    renter_id   INT NOT NULL,
    property_id INT NOT NULL,
    saved_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (renter_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (property_id) REFERENCES properties(property_id) ON DELETE CASCADE
);

-- =====================
-- 8. LEASES
-- =====================
CREATE TABLE leases (
    lease_id       INT AUTO_INCREMENT PRIMARY KEY,
    application_id INT NOT NULL,
    property_id    INT NOT NULL,
    renter_id      INT NOT NULL,
    owner_id       INT NOT NULL,
    start_date     DATE NOT NULL,
    end_date       DATE NOT NULL,
    monthly_rent   DECIMAL(10,2) NOT NULL,
    deposit        DECIMAL(10,2) NOT NULL,
    terms          TEXT,
    status         ENUM('ACTIVE', 'EXPIRED', 'TERMINATED') NOT NULL DEFAULT 'ACTIVE',
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (application_id) REFERENCES rental_applications(application_id) ON DELETE CASCADE,
    FOREIGN KEY (property_id) REFERENCES properties(property_id) ON DELETE CASCADE,
    FOREIGN KEY (renter_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (owner_id) REFERENCES property_owners(owner_id) ON DELETE CASCADE
);

-- =====================
-- 9. MESSAGES
-- =====================
CREATE TABLE messages (
    message_id  INT AUTO_INCREMENT PRIMARY KEY,
    sender_id   INT NOT NULL,
    receiver_id INT NOT NULL,
    content     TEXT NOT NULL,
    is_read     TINYINT(1) DEFAULT 0,
    sent_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (receiver_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- ===================================================
-- SEED DEFAULT USERS (Password for all is: Admin@1234)
-- ===================================================

-- 1. Seed System Admin
INSERT INTO users (full_name, email, password_hash, role, user_status, phone)
VALUES ('System Admin', 'admin@smartrent.com', '$2a$12$Zfnb90VwFNxPyCzA/HwSl.bXxmq2Gy7jVkFE/T9T/3ooaPu0wc4Fy', 'SUPER_ADMIN', 'ACTIVE', '0000000000');

-- 2. Seed Property Owner User
INSERT INTO users (full_name, email, password_hash, role, user_status, phone)
VALUES ('John Owner', 'owner@smartrent.com', '$2a$12$Zfnb90VwFNxPyCzA/HwSl.bXxmq2Gy7jVkFE/T9T/3ooaPu0wc4Fy', 'PROPERTY_OWNER', 'ACTIVE', '1111111111');

-- Set up Owner Profile
INSERT INTO property_owners (owner_id, approval_status)
VALUES (LAST_INSERT_ID(), 'APPROVED');

-- Save owner_id for seeding properties (assuming it gets user_id = 2)
SET @owner_id = 2;

-- 3. Seed Renter User
INSERT INTO users (full_name, email, password_hash, role, user_status, phone)
VALUES ('Jane Renter', 'renter@smartrent.com', '$2a$12$Zfnb90VwFNxPyCzA/HwSl.bXxmq2Gy7jVkFE/T9T/3ooaPu0wc4Fy', 'RENTER', 'ACTIVE', '2222222222');

-- Set up Renter Profile
INSERT INTO renters (renter_id, employment_status, monthly_income)
VALUES (LAST_INSERT_ID(), 'Employed', 50000.0);

-- Save renter_id for seeding applications/leases (assuming it gets user_id = 3)
SET @renter_id = 3;

-- ===================================================
-- SEED DUMMY PROPERTIES & APPLICATIONS
-- ===================================================

-- Add Properties
INSERT INTO properties (owner_id, title, address, property_type, bedrooms, bathrooms, monthly_rent, deposit, prop_status, available_from, avg_rating, description)
VALUES 
(@owner_id, 'Lakeside Apartment', 'Lakeside Drive, Mumbai', 'Apartment', 2, 2, 45000.00, 90000.00, 'OCCUPIED', '2026-06-01', 4.5, 'Beautiful waterfront 2 BHK apartment with scenic views and excellent amenities.'),
(@owner_id, 'Greenview Villa', 'Golf Links Road, Bangalore', 'House', 3, 3, 75000.00, 150000.00, 'AVAILABLE', '2026-06-15', 0.0, 'Spacious 3 BHK villa with private garden, garage parking, and premium fittings.'),
(@owner_id, 'Urban Loft', 'Sector 4, Noida, Delhi', 'Studio', 1, 1, 35000.00, 70000.00, 'OCCUPIED', '2026-05-10', 4.0, 'Modern industrial-style 1 BHK studio loft ideal for working professionals.');

-- Save property_id for Lakeside Apartment (assuming it gets property_id = 1)
SET @property_id_lake = 1;

-- Add Application
INSERT INTO rental_applications (property_id, renter_id, move_in_date, cover_message, app_status)
VALUES 
(@property_id_lake, @renter_id, '2026-06-01', 'I am interested in this beautiful lakeside apartment.', 'APPROVED');

-- Save application_id (assuming it gets application_id = 1)
SET @application_id = 1;

-- Add Lease Agreement
INSERT INTO leases (application_id, property_id, renter_id, owner_id, start_date, end_date, monthly_rent, deposit, terms, status)
VALUES 
(@application_id, @property_id_lake, @renter_id, @owner_id, '2026-06-01', '2027-06-01', 45000.00, 90000.00, 'Standard 1-year residential lease agreement with automatic renewal option.', 'ACTIVE');

-- Add Rating
INSERT INTO property_ratings (property_id, renter_id, rating, review_comment)
VALUES 
(@property_id_lake, @renter_id, 5, 'Exceptional property and great response from the owner. Highly recommended!');
