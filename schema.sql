/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Subechha Karki
 * Created: May 19, 2026
 */

-- SmartRent Database Schema
-- Run this once in MySQL Workbench
-- CREATE DATABASE first, then USE it

CREATE DATABASE IF NOT EXISTS smartrent;
USE smartrent;

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
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    login_attempts INT DEFAULT 0,
    locked_until   TIMESTAMP NULL
);

-- =====================
-- 2. PROPERTY OWNERS
-- =====================
CREATE TABLE property_owners (
    owner_id        INT PRIMARY KEY,
    approval_status ENUM('PENDING', 'APPROVED', 'REJECTED') NOT NULL,
    approval_note   TEXT,
    FOREIGN KEY (owner_id) REFERENCES users(user_id)
);

-- =====================
-- 3. PROPERTIES
-- =====================
CREATE TABLE properties (
    property_id    INT AUTO_INCREMENT PRIMARY KEY,
    owner_id       INT NOT NULL,
    title          VARCHAR(200) NOT NULL,
    address        VARCHAR(300) NOT NULL,
    property_type  ENUM('APARTMENT', 'HOUSE', 'ROOM', 'STUDIO') NOT NULL,
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
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (owner_id) REFERENCES property_owners(owner_id)
);

-- =====================
-- 4. RENTAL APPLICATIONS
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
    FOREIGN KEY (property_id) REFERENCES properties(property_id),
    FOREIGN KEY (renter_id) REFERENCES users(user_id)
);

-- =====================
-- 5. PROPERTY RATINGS
-- =====================
CREATE TABLE property_ratings (
    rating_id      INT AUTO_INCREMENT PRIMARY KEY,
    property_id    INT NOT NULL,
    renter_id      INT NOT NULL,
    rating         INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    review_comment TEXT,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (property_id) REFERENCES properties(property_id),
    FOREIGN KEY (renter_id) REFERENCES users(user_id)
);

-- =====================
-- 6. SAVED PROPERTIES
-- =====================
CREATE TABLE saved_properties (
    saved_id    INT AUTO_INCREMENT PRIMARY KEY,
    renter_id   INT NOT NULL,
    property_id INT NOT NULL,
    saved_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (renter_id) REFERENCES users(user_id),
    FOREIGN KEY (property_id) REFERENCES properties(property_id)
);

-- =====================
-- 7. LEASES
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
    lease_status   ENUM('ACTIVE', 'EXPIRED', 'TERMINATED') NOT NULL DEFAULT 'ACTIVE',
    FOREIGN KEY (application_id) REFERENCES rental_applications(application_id),
    FOREIGN KEY (property_id) REFERENCES properties(property_id),
    FOREIGN KEY (renter_id) REFERENCES users(user_id),
    FOREIGN KEY (owner_id) REFERENCES property_owners(owner_id)
);

-- =====================
-- DEFAULT SUPER ADMIN
-- =====================
-- Password is: Admin@1234 (BCrypt hashed)
INSERT INTO users (full_name, email, password_hash, role, user_status)
VALUES (
    'Super Admin',
    'admin@smartrent.com',
    '$2a$12$eImiTXuWVxfM37uY4JANjQ==XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX',
    'SUPER_ADMIN',
    'ACTIVE'
);
