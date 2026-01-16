INSERT INTO users (name, surname, age, email, password, phone_number, avatar, role)
VALUES


('Ivan', 'Petrov', 35, 'employer1@test.com', '$2a$12$uqKy7W62KOcxPSlssCf3GO8XWxm0lYYlnhBF9OD7RbHCjr7nsJY9O', '+996555100001', null,
 (SELECT id FROM roles WHERE role_name = 'Employer')),

('Anna', 'Sidorova', 32, 'employer2@test.com', '$2a$12$uqKy7W62KOcxPSlssCf3GO8XWxm0lYYlnhBF9OD7RbHCjr7nsJY9O', '+996555100002', null,
 (SELECT id FROM roles WHERE role_name = 'Employer')),

('Oleg', 'Smirnov', 41, 'employer3@test.com', '$2a$12$uqKy7W62KOcxPSlssCf3GO8XWxm0lYYlnhBF9OD7RbHCjr7nsJY9O', '+996555100003', null,
 (SELECT id FROM roles WHERE role_name = 'Employer')),

('Elena', 'Kuznetsova', 38, 'employer4@test.com', '$2a$12$uqKy7W62KOcxPSlssCf3GO8XWxm0lYYlnhBF9OD7RbHCjr7nsJY9O', '+996555100004', null,
 (SELECT id FROM roles WHERE role_name = 'Employer')),

('Pavel', 'Orlov', 29, 'employer5@test.com', '$2a$12$uqKy7W62KOcxPSlssCf3GO8XWxm0lYYlnhBF9OD7RbHCjr7nsJY9O', '+996555100005', null,
 (SELECT id FROM roles WHERE role_name = 'Employer')),

('Marina', 'Belova', 45, 'employer6@test.com', '$2a$12$uqKy7W62KOcxPSlssCf3GO8XWxm0lYYlnhBF9OD7RbHCjr7nsJY9O', '+996555100006', null,
 (SELECT id FROM roles WHERE role_name = 'Employer')),


('Sergey', 'Ivanov', 24, 'applicant1@test.com', '$2a$12$uqKy7W62KOcxPSlssCf3GO8XWxm0lYYlnhBF9OD7RbHCjr7nsJY9O', '+996555200001', null,
 (SELECT id FROM roles WHERE role_name = 'Applicant')),

('Olga', 'Smirnova', 27, 'applicant2@test.com', '$2a$12$uqKy7W62KOcxPSlssCf3GO8XWxm0lYYlnhBF9OD7RbHCjr7nsJY9O', '+996555200002', null,
 (SELECT id FROM roles WHERE role_name = 'Applicant')),

('Nikita', 'Fedorov', 30, 'applicant3@test.com', '$2a$12$uqKy7W62KOcxPSlssCf3GO8XWxm0lYYlnhBF9OD7RbHCjr7nsJY9O', '+996555200003', null,
 (SELECT id FROM roles WHERE role_name = 'Applicant')),

('Alina', 'Volkova', 22, 'applicant4@test.com', '$2a$12$uqKy7W62KOcxPSlssCf3GO8XWxm0lYYlnhBF9OD7RbHCjr7nsJY9O', '+996555200004', null,
 (SELECT id FROM roles WHERE role_name = 'Applicant')),

('Dmitry', 'Lebedev', 34, 'applicant5@test.com', '$2a$12$uqKy7W62KOcxPSlssCf3GO8XWxm0lYYlnhBF9OD7RbHCjr7nsJY9O', '+996555200005', null,
 (SELECT id FROM roles WHERE role_name = 'Applicant')),

('Irina', 'Morozova', 28, 'applicant6@test.com', '$2a$12$uqKy7W62KOcxPSlssCf3GO8XWxm0lYYlnhBF9OD7RbHCjr7nsJY9O', '+996555200006', null,
 (SELECT id FROM roles WHERE role_name = 'Applicant')),

('Roman', 'Zakharov', 26, 'applicant7@test.com', '$2a$12$uqKy7W62KOcxPSlssCf3GO8XWxm0lYYlnhBF9OD7RbHCjr7nsJY9O', '+996555200007', null,
 (SELECT id FROM roles WHERE role_name = 'Applicant')),

('Tatyana', 'Egorova', 31, 'applicant8@test.com', '$2a$12$uqKy7W62KOcxPSlssCf3GO8XWxm0lYYlnhBF9OD7RbHCjr7nsJY9O', '+996555200008', null,
 (SELECT id FROM roles WHERE role_name = 'Applicant'));

INSERT INTO resumes (applicant, name, category_id, salary, is_active, created_date, update_date)
VALUES
    ('applicant1@test.com', 'Junior Lawyer', (SELECT id FROM categories WHERE name = 'Юриспруденция'), 55000, true, '2026-01-10', '2026-01-10'),
    ('applicant2@test.com', 'Lawyer Assistant', (SELECT id FROM categories WHERE name = 'Юриспруденция'), 60000, true, '2026-01-11', '2026-01-11'),
    ('applicant3@test.com', 'Senior Lawyer', (SELECT id FROM categories WHERE name = 'Юриспруденция'), 120000, true, '2026-01-12', '2026-01-12'),
    ('applicant4@test.com', 'Trainee Lawyer', (SELECT id FROM categories WHERE name = 'Юриспруденция'), 45000, true, '2026-01-13', '2026-01-13'),
    ('applicant5@test.com', 'Accountant', (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 70000, true, '2026-01-10', '2026-01-10'),
    ('applicant6@test.com', 'Junior Accountant', (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 55000, true, '2026-01-11', '2026-01-11'),
    ('applicant7@test.com', 'Chief Accountant', (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 130000, true, '2026-01-12', '2026-01-12'),
    ('applicant8@test.com', 'Financial Accountant', (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 90000, true, '2026-01-13', '2026-01-13'),
    ('applicant1@test.com', 'Contract Lawyer', (SELECT id FROM categories WHERE name = 'Юриспруденция'), 80000, true, '2026-01-14', '2026-01-14'),
    ('applicant2@test.com', 'Tax Accountant', (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 95000, true, '2026-01-15', '2026-01-15');

INSERT INTO vacancies
(name, description, category_id, salary, exp_from, exp_to, is_active, author, created_date, update_date)
VALUES
    ('Junior Lawyer', 'Work with contracts and documents',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'), 65000, 0, 1, true, 'employer1@test.com', '2026-01-10', '2026-01-10'),

    ('Middle Lawyer', 'Court practice and legal consulting',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'), 95000, 2, 5, true, 'employer2@test.com', '2026-01-11', '2026-01-11'),

    ('Senior Lawyer', 'Lead legal projects',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'), 140000, 5, 10, true, 'employer3@test.com', '2026-01-12', '2026-01-12'),

    ('Lawyer Assistant', 'Documentation and client support',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'), 55000, 0, 1, true, 'employer4@test.com', '2026-01-13', '2026-01-13'),

    ('Accountant', 'Accounting and reports',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 80000, 1, 3, true, 'employer5@test.com', '2026-01-10', '2026-01-10'),

    ('Senior Accountant', 'Taxes and financial statements',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 120000, 5, 10, true, 'employer6@test.com', '2026-01-11', '2026-01-11'),

    ('Junior Accountant', 'Primary documentation',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 60000, 0, 1, true, 'employer1@test.com', '2026-01-12', '2026-01-12'),

    ('Financial Analyst', 'Budget planning and analytics',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 150000, 3, 7, true, 'employer2@test.com', '2026-01-13', '2026-01-13'),

    ('Corporate Lawyer', 'Corporate law support',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'), 130000, 4, 8, true, 'employer3@test.com', '2026-01-14', '2026-01-14'),

    ('Tax Consultant', 'Tax optimization and audits',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 110000, 3, 6, true, 'employer4@test.com', '2026-01-15', '2026-01-15');


