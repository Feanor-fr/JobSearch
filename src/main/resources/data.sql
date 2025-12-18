DROP TABLE IF EXISTS work_experience_info;
DROP TABLE IF EXISTS education_info;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS responded_applicants;
DROP TABLE IF EXISTS vacancies;
DROP TABLE IF EXISTS resumes;
DROP TABLE IF EXISTS categories;
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



CREATE TABLE IF not EXISTS responded_applicants
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    resume_id    INT NOT NULL REFERENCES resumes(id),
    vacancy_id   INT NOT NULL REFERENCES vacancies(id),
    confirmation BOOLEAN
);

INSERT INTO responded_applicants(resume_id, vacancy_id, confirmation)
VALUES
    (1, 1, true),
    (2, 2, false);



CREATE TABLE IF NOT EXISTS messages
(
    id                         INT AUTO_INCREMENT PRIMARY KEY,
    responded_applicants       INT NOT NULL REFERENCES responded_applicants(id),
    content                    VARCHAR(50) NOT NULL,
    timestamp                  TIMESTAMP
);

INSERT INTO messages(responded_applicants, content, timestamp)
VALUES
    (1, 'Здравствуйте! Я заинтересован в вакансии Юрист', '2025-12-19 10:00:00'),
    (1, 'Спасибо за отклик!', '2025-12-19 10:30:00'),
    (2, 'Добрый день! Интересуюсь вакансией Бухгалтер', '2025-12-19 11:15:00');


CREATE TABLE IF not EXISTS education_info
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    resume_id    INT NOT NULL REFERENCES resumes(id),
    institution  VARCHAR(50) NOT NULL,
    program      VARCHAR(50) NOT NULL,
    start_date   DATE NOT NULL,
    end_date     DATE,
    degree       VARCHAR(50) NOT NULL
);

INSERT INTO education_info(resume_id, institution, program, start_date, end_date, degree)
VALUES
    (1, 'Кыргызский Национальный Университет', 'Юриспруденция', '2015-09-01', '2019-06-30', 'Бакалавр'),
    (1, 'Московский Государственный Университет', 'Международное право', '2019-09-01', '2021-06-30', 'Магистр'),
    (2, 'Кыргызский Экономический Университет', 'Бухгалтерский учет', '2014-09-01', '2018-06-30', 'Бакалавр'),
    (2, 'Бишкекская Бизнес Академия', 'Финансовый менеджмент', '2020-09-01', '2022-06-30', 'Магистр');

CREATE TABLE IF not EXISTS work_experience_info
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    resume_id         INT NOT NULL REFERENCES resumes(id),
    years             INT NOT NULL,
    company_name      VARCHAR(50) NOT NULL,
    position          VARCHAR(50) NOT NULL,
    responsibilities  VARCHAR(50) NOT NULL
);

INSERT INTO work_experience_info(resume_id, years, company_name, position, responsibilities)
VALUES
    (1, 3, 'Юридическая фирма Гранат', 'Юрист', 'Консультирование клиентов'),
    (1, 2, 'Международная компания Шедевр', 'Старший юрист', 'Ведение международных контрактов'),
    (2, 4, 'Бухгалтерская фирма ЛДПР', 'Бухгалтер', 'Ведение документации'),
    (2, 1, 'Торговая компания Мерч', 'Главный бухгалтер', 'Руководство бухгалтерским отделом');