\c travelagency

DROP TABLE IF EXISTS roles CASCADE ;
DROP TABLE IF EXISTS users CASCADE ;
DROP TABLE IF EXISTS countries CASCADE ;
DROP TABLE IF EXISTS resorts CASCADE ;
DROP TABLE IF EXISTS hotels CASCADE ;
DROP TABLE IF EXISTS transport_types CASCADE ;
DROP TABLE IF EXISTS flights CASCADE ;
DROP TABLE IF EXISTS tour_types CASCADE ;
DROP TABLE IF EXISTS tours CASCADE ;
DROP TABLE IF EXISTS orders CASCADE ;
DROP TABLE IF EXISTS statuses CASCADE ;



CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(32)
);

INSERT INTO roles (name) VALUES ('admin');
INSERT INTO roles (name) VALUES ('manager');
INSERT INTO roles (name) VALUES ('client');

-- SELECT * FROM roles;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    login VARCHAR(32) UNIQUE,
    password VARCHAR(52),
    email VARCHAR(52),
    surname VARCHAR(32),
    middle_name VARCHAR(32),
    name VARCHAR(32),
    phone VARCHAR(10),
    role_id INTEGER REFERENCES roles(id),
    blocked BOOLEAN,
    passport_id VARCHAR(8),
    international_passport_id VARCHAR(8)
);

INSERT INTO users (login, password, email, surname, middle_name, name, phone, role_id, blocked, passport_id, international_passport_id)
    VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', 'admin@gmail.com', 'Admin', 'Admin', 'Admin', 0501740428, 1, FALSE,
            'MN789456', 'MN943845');
INSERT INTO users (login, password, email, surname, middle_name, name, phone, role_id, blocked, passport_id, international_passport_id)
    VALUES ('jo1nsaint', '21232f297a57a5a743894a0e4a801fc3', 'andrii.chehrynets@gmail.com', 'Admin', 'Admin', 'Admin', 0501740428, 3, FALSE,
            'MN789456', 'MN943845');
INSERT INTO users (login, password, email, surname, middle_name, name, phone, role_id, blocked, passport_id, international_passport_id)
VALUES ('manager', '1d0258c2440a8d19e716292b231e3190', 'manager@gmail.com', 'Manager', 'Manager', 'Manager', 0930664424,  2, FALSE,
            'MN789456', 'MN943845');
INSERT INTO users (login, password, email, surname, middle_name, name, phone, role_id, blocked, passport_id, international_passport_id)
VALUES ('client', '62608e08adc29a8d6dbc9754e659f125', 'client@gmail.com', 'Client', 'Client', 'Client', 0504356765, 3, FALSE,
            'MN789456', 'MN943845');
INSERT INTO users (login, password, email, surname, middle_name, name, phone, role_id, blocked, passport_id, international_passport_id)
VALUES ('clientone', '62608e08adc29a8d6dbc9754e659f125', 'client@gmail.com', 'Client', 'Client', 'Client', 0504356765, 3, FALSE,
            'MN789456', 'MN943845');
INSERT INTO users (login, password, email, surname, middle_name, name, phone, role_id, blocked, passport_id, international_passport_id)
VALUES ('blocked', '62608e08adc29a8d6dbc9754e659f125', 'client@gmail.com', 'Client', 'Client', 'Client', 0948557625, 3, TRUE,
            'MN789456', 'MN943845');

-- SELECT * from users;

CREATE TABLE countries (
    id SERIAL PRIMARY KEY,
    name VARCHAR(32),
    visa BOOLEAN
);

INSERT INTO countries (name, visa) VALUES ('Turkey', FALSE);
INSERT INTO countries (name, visa) VALUES ('Украина', FALSE);
INSERT INTO countries (name, visa) VALUES ('Spain', TRUE );

-- SELECT * FROM countries;

CREATE TABLE resorts (
    id SERIAL PRIMARY KEY,
    country_id INTEGER REFERENCES countries(id),
    name VARCHAR(32),
    description TEXT,
    image_path VARCHAR
);


INSERT INTO resorts(country_id, name, description, image_path) VALUES (
    1, 'Анталия', 'описание курорта',
        '/home/jo1nsaint/projects/javaProjects/TA/src/main/webapp/WEB-INF/images/resorts/Анталия.jpg'
);
INSERT INTO resorts(country_id, name, description, image_path) VALUES (
    2, 'Карпаты', 'описание курорта',
        '/home/jo1nsaint/projects/javaProjects/TA/src/main/webapp/WEB-INF/images/resorts/Карпаты.jpg'
);
INSERT INTO resorts (country_id, name, description, image_path) VALUES (
    3, 'Ibiza', 'something about resort',
        '/home/jo1nsaint/projects/javaProjects/TA/src/main/webapp/WEB-INF/images/resorts/Ibiza.png'
);

-- SELECT * FROM resorts;

CREATE TABLE hotels (
    id SERIAL PRIMARY KEY,
    name VARCHAR(52),
    resort_id INTEGER REFERENCES resorts(id),
    country_id INTEGER REFERENCES countries(id),
    stars INTEGER,
    description TEXT,
    price FLOAT,
    image_path VARCHAR
);

INSERT INTO hotels (name, resort_id, country_id, stars, description, price, image_path) VALUES
    ('Hard Rock Hotel Ibiza', 3, 3, 5, 'The most popular hotel in Ibiza', 200,
     '/home/jo1nsaint/projects/javaProjects/TA/src/main/webapp/WEB-INF/images/hotels/HardRockHotelIbiza.png');

INSERT INTO hotels (name, resort_id, country_id, stars, description, price, image_path) VALUES
    ('Hard Rock Hotel Ibiza', 3, 3, 5, 'The most popular hotel in Ibiza', 200,
     '/home/jo1nsaint/projects/javaProjects/TA/src/main/webapp/WEB-INF/images/hotels/HardRockHotelIbiza.png');

INSERT INTO hotels (name, resort_id, country_id, stars, description, price, image_path) VALUES
    ('Hard Rock Hotel Ibiza', 3, 3, 5, 'The most popular hotel in Ibiza', 200,
     '/home/jo1nsaint/projects/javaProjects/TA/src/main/webapp/WEB-INF/images/hotels/HardRockHotelIbiza.png');

-- SELECT * FROM hotels;

CREATE TABLE transport_types (
    id SERIAL PRIMARY KEY,
    name VARCHAR(32)
);

INSERT INTO transport_types (name) VALUES ('aircraft');
INSERT INTO transport_types (name) VALUES ('bus');
INSERT INTO transport_types (name) VALUES ('ship');

SELECT * FROM transport_types;

CREATE TABLE flights (
    id SERIAL PRIMARY KEY,
    transport_type_id INTEGER REFERENCES transport_types (id),
    departure_point VARCHAR(32),
    departure_date TIMESTAMP,
    travel_time TIME,
    arrival_point VARCHAR(32),
    arrival_date TIMESTAMP,
    price FLOAT
);

INSERT INTO flights (transport_type_id, departure_point, departure_date, travel_time, arrival_point, arrival_date, price)
    VALUES (1, 'Kiev', '2017-09-20 14:00', '03:00', 'Barcelona', '2017-09-20 17:00', 150);
INSERT INTO flights (transport_type_id, departure_point, departure_date, travel_time, arrival_point, arrival_date, price)
    VALUES (2, 'Kharkiv', '2017-09-30 11:00', '22:00', 'Lviv', '2017-10-01 09:00', 20);
INSERT INTO flights (transport_type_id, departure_point, departure_date, travel_time, arrival_point, arrival_date, price)
    VALUES (3, 'Odessa', '2017-09-15 11:00', '10:00', 'Antalya', '2017-09-30 21:00', 120);

-- /////////////////////////////

CREATE TABLE tour_types (
    id SERIAL PRIMARY KEY,
    name VARCHAR(32)
);

INSERT INTO tour_types (name) VALUES ('rest');
INSERT INTO tour_types (name) VALUES ('excursion');
INSERT INTO tour_types (name) VALUES ('shopping');

SELECT * FROM tour_types;

CREATE TABLE tours (
    id SERIAL PRIMARY KEY,
    name VARCHAR(32),
    hot_tour BOOLEAN,
    place_quantity INTEGER,
    tour_date DATE,
    total_price FLOAT,
    amount_of_days INTEGER,
    tour_type_id INTEGER REFERENCES tour_types (id),
    flight_id INTEGER REFERENCES flights (id),
    hotel_id INTEGER REFERENCES hotels (id),
    resort_id INTEGER REFERENCES resorts (id),
    country_id INTEGER REFERENCES countries (id)
);

INSERT INTO tours (name, hot_tour, place_quantity, tour_date, total_price, amount_of_days, tour_type_id, flight_id, hotel_id, resort_id, country_id)
    VALUES ('Светлая Испания', FALSE, 30, '2017-09-20', 700, 7, 1, 1, 1, 3, 3);
INSERT INTO tours (name, hot_tour, place_quantity, tour_date, total_price, amount_of_days, tour_type_id, flight_id, hotel_id, resort_id, country_id)
    VALUES ('Weekend in the mountains', TRUE , 40, '2017-09-30', 240, 3, 2, 2, 2, 2, 2);
INSERT INTO tours (name, hot_tour, place_quantity, tour_date, total_price, amount_of_days, tour_type_id, flight_id, hotel_id, resort_id, country_id)
    VALUES ('Weekend in the mountains', TRUE , 40, '2017-09-01', 440, 3, 2, 2, 2, 2, 2);

-- /////////////////////////////////////

CREATE TABLE statuses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(22)
);

INSERT INTO statuses (name) VALUES ('registered');
INSERT INTO statuses (name) VALUES ('paid');
INSERT INTO statuses (name) VALUES ('canceled');

-- SELECT * FROM statuses;


CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    total_price FLOAT,
    people_amount INTEGER,
    user_id INTEGER REFERENCES users (id),
    status_id INTEGER REFERENCES statuses (id),
    tour_id INTEGER REFERENCES tours (id)
);

-- Посчитать сумму по всем турам для каждого пользователя (статус оплачен)
