CREATE TABLE role
(
    role_id SERIAL PRIMARY KEY,
    role    VARCHAR(128) NOT NUll
);

CREATE TABLE driver_license
(
    driver_license_id SERIAL PRIMARY KEY,
    serial_number     VARCHAR(128) NOT NULL,
    expired_date      DATE         NOT NULL
);

CREATE TABLE passport
(
    passport_id   SERIAL PRIMARY KEY,
    serial_number VARCHAR(128) NOT NULL,
    expired_date  DATE         NOT NULL
);

CREATE TABLE bank_card
(
    bank_card_id  SERIAL PRIMARY KEY,
    serial_number VARCHAR(128) NOT NULL,
    expired_date  DATE         NOT NULL
);

CREATE TABLE client
(
    client_id           SERIAL PRIMARY KEY,
    full_name           VARCHAR(128) NOT NULL,
    phone_number        INT          NOT NULL,
    email               VARCHAR(128) NULL,
    residential_address VARCHAR(128) NULL,
    role_id             INT REFERENCES role (role_id),
    passport_id         INT REFERENCES passport (passport_id),
    driver_license_id   INT REFERENCES driver_license (driver_license_id),
    bank_card_id        INT REFERENCES bank_card (bank_card_id)
);

CREATE TABLE model_car
(
    model_car_id SERIAL PRIMARY KEY,
    model        VARCHAR(128)
);

CREATE TABLE type_car
(
    type_car_id SERIAL PRIMARY KEY,
    typ         VARCHAR(128)
);

CREATE TABLE status_car
(
    status_car_id SERIAL PRIMARY KEY,
    status_car    VARCHAR NOT NULL
);

CREATE TABLE car_color
(
    car_color_id SERIAL PRIMARY KEY,
    color    VARCHAR(128) NULL
);

CREATE TABLE car
(
    car_id               SERIAL PRIMARY KEY,
    year                 DATE      NOT NULL,
    number_seats         INT     NULL,
    rental_price_per_day DECIMAL NOT NULL,
    registration_number  VARCHAR NOT NULL,
    color_id             INT REFERENCES car_color (car_color_id),
    model_id             INT REFERENCES model_car (model_car_id),
    status_id            INT REFERENCES status_car (status_car_id),
    type_id              INT REFERENCES type_car (type_car_id)
);

CREATE TABLE status_order
(
    status_order_id SERIAL PRIMARY KEY,
    status_order    VARCHAR(128) NOT NULL
);

CREATE TABLE order_rental
(
    order_rental_id   SERIAL PRIMARY KEY,
    id_client         INT REFERENCES client (client_id),
    id_car            INT REFERENCES car (car_id),
    rental_start_date DATE    NOT NULL,
    rental_end_date   DATE    NOT NULL,
    total_rental_cost DECIMAL NULL,
    status_order_id   INT REFERENCES status_order (status_order_id)
);

-- DROP TABLE order_rental;
-- DROP TABLE status_order;
-- DROP TABLE client;
-- DROP TABLE bank_card;
-- DROP TABLE passport;
-- DROP TABLE driver_license;
-- DROP TABLE role;
-- DROP TABLE car;
-- DROP TABLE model_car;
-- DROP TABLE type_car;
-- DROP TABLE status_car;
-- DROP TABLE car_color;