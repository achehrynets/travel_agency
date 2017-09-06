\c travelagency

DROP TABLE IF EXISTS roles CASCADE ;
DROP TABLE IF EXISTS users CASCADE ;


CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(32)
);

INSERT INTO roles (name) VALUES ('admin');
INSERT INTO roles (name) VALUES ('manager');
INSERT INTO roles (name) VALUES ('client');

SELECT * FROM roles;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    login VARCHAR(32) UNIQUE,
    password VARCHAR(52),
    email VARCHAR(52),
    surname VARCHAR(32),
    middlename VARCHAR(32),
    name VARCHAR(32),
    roleId INTEGER REFERENCES roles(id),
    blocked BOOLEAN,
    passportId VARCHAR(8),
    internationalPassportId VARCHAR(8)
);

INSERT INTO users (login, password, email, surname, middle_name, name, roleId, blocked, passportId, internationalPassportId)
    VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', 'admin@gmail.com', 'Admin', 'Admin', 'Admin', 1, FALSE,
            'MN789456', 'MN943845');
INSERT INTO users (login, password, email, surname, middle_name, name, roleId, blocked, passportId, internationalPassportId)
VALUES ('manager', '1d0258c2440a8d19e716292b231e3190', 'manager@gmail.com', 'Manager', 'Manager', 'Manager', 2, FALSE,
            'MN789456', 'MN943845');
INSERT INTO users (login, password, email, surname, middle_name, name, roleId, blocked, passportId, internationalPassportId)
VALUES ('client', '62608e08adc29a8d6dbc9754e659f125', 'client@gmail.com', 'Client', 'Client', 'Client', 3, FALSE,
            'MN789456', 'MN943845');

SELECT * from users;