INSERT INTO users (name, surname, age, email, password, phone_number, avatar, role)
VALUES

('Dmitry', 'Volkov', 40, 'employer3@test.com',
 '$2a$12$y3tL0FVRSoUINVSZjDbGkestyXkvEjVx2jZo2Pfm3x6wiBuyWAvDC',
 '+996555555111', null,
 (SELECT id FROM roles WHERE role_name = 'Employer')),

('Elena', 'Kuznetsova', 37, 'employer4@test.com',
 '$2a$12$y3tL0FVRSoUINVSZjDbGkestyXkvEjVx2jZo2Pfm3x6wiBuyWAvDC',
 '+996555555222', null,
 (SELECT id FROM roles WHERE role_name = 'Employer')),

('Alexey', 'Morozov', 23, 'applicant3@test.com',
 '$2a$12$y3tL0FVRSoUINVSZjDbGkestyXkvEjVx2jZo2Pfm3x6wiBuyWAvDC',
 '+996555555333', null,
 (SELECT id FROM roles WHERE role_name = 'Applicant')),

('Natalia', 'Orlova', 30, 'applicant4@test.com',
 '$2a$12$y3tL0FVRSoUINVSZjDbGkestyXkvEjVx2jZo2Pfm3x6wiBuyWAvDC',
 '+996555555444', null,
 (SELECT id FROM roles WHERE role_name = 'Applicant')),

('Pavel', 'Semenov', 27, 'applicant5@test.com',
 '$2a$12$y3tL0FVRSoUINVSZjDbGkestyXkvEjVx2jZo2Pfm3x6wiBuyWAvDC',
 '+996555555555', null,
 (SELECT id FROM roles WHERE role_name = 'Applicant')),

('Irina', 'Lebedeva', 34, 'applicant6@test.com',
 '$2a$12$y3tL0FVRSoUINVSZjDbGkestyXkvEjVx2jZo2Pfm3x6wiBuyWAvDC',
 '+996555555666', null,
 (SELECT id FROM roles WHERE role_name = 'Applicant'));

INSERT INTO resumes (applicant, name, category_id, salary, is_active, created_date, update_date)
VALUES
    ('applicant3@test.com', 'Assistant Lawyer',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'),
     50000, true, '2026-01-15', '2026-01-15'),

    ('applicant4@test.com', 'Chief Accountant',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'),
     95000, true, '2026-01-15', '2026-01-15'),

    ('applicant5@test.com', 'Legal Consultant',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'),
     72000, true, '2026-01-15', '2026-01-15'),

    ('applicant6@test.com', 'Junior Accountant',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'),
     48000, true, '2026-01-15', '2026-01-15');

INSERT INTO vacancies
(name, description, category_id, salary, exp_from, exp_to, is_active, author, created_date, update_date)
VALUES
    ('Corporate Lawyer',
     'Work with corporate contracts and clients',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'),
     120000, 5, 15, true, 'employer3@test.com',
     '2026-01-15', '2026-01-15'),

    ('Junior Accountant',
     'Primary accounting and documentation',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'),
     50000, 0, 2, true, 'employer4@test.com',
     '2026-01-15', '2026-01-15'),

    ('Legal Assistant',
     'Support legal department and prepare documents',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'),
     65000, 1, 3, true, 'employer1@test.com',
     '2026-01-15', '2026-01-15'),

    ('Financial Accountant',
     'Financial reporting and audits',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'),
     110000, 4, 10, true, 'employer2@test.com',
     '2026-01-15', '2026-01-15'),

    ('International Lawyer',
     'International contracts and negotiations',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'),
     150000, 7, 20, true, 'employer3@test.com',
     '2026-01-15', '2026-01-15'),

    ('Payroll Accountant',
     'Salary calculation and tax reporting',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'),
     80000, 2, 6, true, 'employer4@test.com',
     '2026-01-15', '2026-01-15');



