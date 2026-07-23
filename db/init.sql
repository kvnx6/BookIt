DROP DATABASE bookit;
CREATE DATABASE IF NOT EXISTS bookit;
USE bookit;

DROP TABLE IF EXISTS users;
CREATE TABLE users(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    email           VARCHAR(255) NOT NULL,
    password_hash   VARCHAR(255) NOT NULL,
    name            VARCHAR(150) NOT NULL,
    surname         VARCHAR(150) NOT NULL,
    created_at      DATE NOT NULL,

    CONSTRAINT uq_users_email UNIQUE (email)
);

DROP TABLE IF EXISTS categories;
CREATE TABLE categories(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(100) NOT NULL
);

DROP TABLE IF EXISTS businesses;
CREATE TABLE businesses (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    owner_id        INT NOT NULL,
    category_id     INT NOT NULL,
    name            VARCHAR(255) NOT NULL,
    url_name        VARCHAR(255) NOT NULL,
    description     VARCHAR(500),
    address         VARCHAR(255),
    city            VARCHAR(100),
    created_at      DATE NOT NULL,

    CONSTRAINT uq_businesses_url_name UNIQUE (url_name),
    CONSTRAINT fk_businesses_owner
        FOREIGN KEY (owner_id) REFERENCES users(id),
    CONSTRAINT fk_category
        FOREIGN KEY (category_id) REFERENCES categories(id)
);

DROP TABLE IF EXISTS services;
CREATE TABLE services(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    name           VARCHAR(100) NOT NULL,
    description    VARCHAR(255) NOT NULL,
    price          DECIMAL(10,2) NOT NULL,
    business_id    INT NOT NULL,
    duration       INT NOT NULL,
    CONSTRAINT fk_business
                     foreign key (business_id) REFERENCES businesses(id)
);

DROP TABLE IF EXISTS staffmembers;
CREATE TABLE staffmembers (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    business_id     INT NOT NULL,
    display_name    VARCHAR(150) NOT NULL,
    created_at      DATE NOT NULL,

    CONSTRAINT fk_staff_business
        FOREIGN KEY (business_id) REFERENCES businesses(id)
        ON DELETE CASCADE
);

DROP TABLE IF EXISTS bookings;
CREATE TABLE bookings (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    business_id         INT NOT NULL,
    staffmember_id      INT NOT NULL,
    customer_id         INT NOT NULL,
    service_id          INT NOT NULL,
    start_time          DATETIME NOT NULL,
    status              ENUM('CONFIRMED', 'CANCELLED', 'COMPLETED') NOT NULL DEFAULT 'CONFIRMED',
    created_at          DATE NOT NULL,

    CONSTRAINT fk_booking_business
        FOREIGN KEY (business_id) REFERENCES businesses(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_booking_staff
        FOREIGN KEY (staffmember_id) REFERENCES staffmembers(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_booking_customer
        FOREIGN KEY (customer_id) REFERENCES users(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_service
        FOREIGN KEY (service_id) REFERENCES services(id),
    CONSTRAINT uq_booking_staff_slot UNIQUE (staffmember_id, start_time)
);