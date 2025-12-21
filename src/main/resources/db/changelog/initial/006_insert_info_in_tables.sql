INSERT INTO users(name, surname, age, email, password, phone_number, avatar,  role)
VALUES ('Baltazar', 'Black', 40, 'balt@gmail.com', 'balt', '0223344556', '', 'employer'),
       ('Larra', 'Dorren', 30, 'lar@gmail.com', 'lara', '0555123456', '', 'applicant');

INSERT INTO categories(name, parent_id)
VALUES
    ('Юриспруденция', NULL),
    ('Бухгалтерия', NULL);

INSERT INTO resumes(applicant, name, category_id, salary, is_active, created_date, update_date)
VALUES
    ('lar@gmail.com', 'Юрист', 1, 1000.0, TRUE, '2025-12-19', '2025-12-19'),
    ('lar@gmail.com', 'Бугалтер', 2, 900.0, TRUE, '2025-12-19', '2025-12-19');

INSERT INTO vacancies(name, description, category_id, salary, exp_from, exp_to, is_active, author, created_date, update_date)
VALUES
    ('Юрист', 'Работа в юристом', 1, 1200.0, 2, 5, TRUE, 'balt@gmail.com', '2025-12-19', '2025-12-19'),
    ('Бугалтер', 'Работа бугалтером', 2, 1100.0, 1, 3, TRUE, 'balt@gmail.com', '2025-12-19', '2025-12-19');