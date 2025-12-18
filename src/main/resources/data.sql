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

DROP TABLE IF EXISTS categories;

CREATE TABLE IF not EXISTS categories
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(50) NOT NULL,
    parent_id  INT REFERENCES categories(id)
    );

INSERT INTO categories(name, parent_id)
VALUES
    ('Юриспруденция', NULL),
    ('Бухгалтерия', NULL);

DROP TABLE IF EXISTS resumes;

CREATE TABLE IF not EXISTS resumes
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    applicant     VARCHAR(50) NOT NULL REFERENCES users(email),
    name          VARCHAR(50) NOT NULL,
    category_id   INT NOT NULL REFERENCES categories(id),
    salary        DOUBLE NOT NULL,
    is_active     BOOLEAN NOT NULL,
    created_date  DATE,
    update_date   DATE
);

INSERT INTO resumes(applicant, name, category_id, salary, is_active, created_date, update_date)
VALUES
    ('lar@gmail.com', 'Юрист', 1, 1000.0, TRUE, '2025-12-19', '2025-12-19'),
    ('lar@gmail.com', 'Бугалтер', 2, 900.0, TRUE, '2025-12-19', '2025-12-19');

DROP TABLE IF EXISTS vacancies;

CREATE TABLE IF not EXISTS vacancies
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(50) NOT NULL,
    description   VARCHAR(50) NOT NULL,
    category_id   INT NOT NULL REFERENCES categories(id),
    salary        DOUBLE NOT NULL,
    exp_from      INT NOT NULL,
    exp_to        INT NOT NULL,
    is_active     BOOLEAN NOT NULL,
    author        VARCHAR(50) NOT NULL REFERENCES users(email),
    created_date  DATE,
    update_date   DATE
);

INSERT INTO vacancies(name, description, category_id, salary, exp_from, exp_to, is_active, author, created_date, update_date)
VALUES
    ('Юрист', 'Работа в юристом', 1, 1200.0, 2, 5, TRUE, 'balt@gmail.com', '2025-12-19', '2025-12-19'),
    ('Бугалтер', 'Работа бугалтером', 2, 1100.0, 1, 3, TRUE, 'balt@gmail.com', '2025-12-19', '2025-12-19');


