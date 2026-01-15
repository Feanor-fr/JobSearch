
INSERT INTO users (name, surname, age, email, password, phone_number, avatar, role)
VALUES

('Ivan', 'Petrov', 35, 'employer1@test.com', '$2a$12$y3tL0FVRSoUINVSZjDbGkestyXkvEjVx2jZo2Pfm3x6wiBuyWAvDC', '+996555111111', null,
 (SELECT id FROM roles WHERE role_name = 'Employer')),

('Anna', 'Sidorova', 32, 'employer2@test.com', '$2a$12$y3tL0FVRSoUINVSZjDbGkestyXkvEjVx2jZo2Pfm3x6wiBuyWAvDC', '+996555222222', null,
 (SELECT id FROM roles WHERE role_name = 'Employer')),


('Sergey', 'Ivanov', 25, 'applicant1@test.com', '$2a$12$y3tL0FVRSoUINVSZjDbGkestyXkvEjVx2jZo2Pfm3x6wiBuyWAvDC', '+996555333333', null,
 (SELECT id FROM roles WHERE role_name = 'Applicant')),

('Olga', 'Smirnova', 28, 'applicant2@test.com', '$2a$12$y3tL0FVRSoUINVSZjDbGkestyXkvEjVx2jZo2Pfm3x6wiBuyWAvDC', '+996555444444', null,
 (SELECT id FROM roles WHERE role_name = 'Applicant'));


INSERT INTO resumes (applicant, name, category_id, salary, is_active, created_date, update_date)
VALUES
    (
        'applicant1@test.com',
        'Junior Lawyer',
        (SELECT id FROM categories WHERE name = 'Юриспруденция'),
        60000,
        true,
        '2026-01-15',
        '2026-01-15'
    ),
    (
        'applicant2@test.com',
        'Accountant',
        (SELECT id FROM categories WHERE name = 'Бухгалтерия'),
        55000,
        true,
        '2026-01-15',
        '2026-01-15'
    );

INSERT INTO vacancies
(name, description, category_id, salary, exp_from, exp_to, is_active, author, created_date, update_date)
VALUES
    (
        'Junior Lawyer',
        'Looking for junior lawyer to work with contracts',
        (SELECT id FROM categories WHERE name = 'Юриспруденция'),
        70000,
        0,
        2,
        true,
        'employer1@test.com',
        '2026-01-15',
        '2026-01-15'
    ),
    (
        'Senior Accountant',
        'Accountant with experience in reporting and taxes',
        (SELECT id FROM categories WHERE name = 'Бухгалтерия'),
        90000,
        3,
        10,
        true,
        'employer2@test.com',
        '2026-01-15',
        '2026-01-15'
    );

