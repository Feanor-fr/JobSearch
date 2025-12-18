DROP TABLE IF EXISTS users;

CREATE TABLE IF not EXISTS users
(
    name          VARCHAR(50) NOT NULL,
    surname       VARCHAR(50) NOT NULL,
    age           INT NOT NULL,
    email         VARCHAR(50) PRIMARY KEY,
    password      VARCHAR(50) NOT NULL,
    phone_number  VARCHAR(50) NOT NULL,
    avatar        VARCHAR(50),
    role          VARCHAR(50) NOT NULL
);
INSERT INTO users(name, surname, age, email, password, phone_number, avatar,  role)
VALUES ('Baltazar', 'Black', 40, 'balt@gmail.com', 'balt', '0223344556', '', 'employer'),
       ('Larra', 'Dorren', 30, 'lar@gmail.com', 'lara', '0555123456', '', 'applicant');