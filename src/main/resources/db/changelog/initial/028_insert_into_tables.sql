INSERT INTO resumes (applicant, name, category_id, salary, is_active, created_date, update_date)
VALUES
    ('applicant1@test.com', 'Senior Contract Lawyer',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'), 135000, true, '2026-02-01', '2026-02-01'),

    ('applicant2@test.com', 'Legal Consultant',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'), 110000, true, '2026-02-02', '2026-02-02'),

    ('applicant3@test.com', 'Head Lawyer',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'), 160000, true, '2026-02-03', '2026-02-03'),

    ('applicant4@test.com', 'Junior Legal Specialist',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'), 52000, true, '2026-02-04', '2026-02-04'),

    ('applicant5@test.com', 'Financial Accountant',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 88000, true, '2026-02-05', '2026-02-05'),

    ('applicant6@test.com', 'Payroll Accountant',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 76000, true, '2026-02-06', '2026-02-06'),

    ('applicant7@test.com', 'Audit Manager',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 145000, true, '2026-02-07', '2026-02-07'),

    ('applicant8@test.com', 'Chief Financial Officer',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 180000, true, '2026-02-08', '2026-02-08'),

    ('applicant1@test.com', 'Tax Lawyer',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'), 125000, true, '2026-02-09', '2026-02-09'),

    ('applicant2@test.com', 'Compliance Lawyer',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'), 118000, true, '2026-02-10', '2026-02-10'),

    ('applicant3@test.com', 'Corporate Legal Director',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'), 175000, true, '2026-02-11', '2026-02-11'),

    ('applicant4@test.com', 'Law Office Assistant',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'), 48000, true, '2026-02-12', '2026-02-12'),

    ('applicant5@test.com', 'Senior Accountant',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 130000, true, '2026-02-13', '2026-02-13'),

    ('applicant6@test.com', 'Tax Reporting Specialist',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 102000, true, '2026-02-14', '2026-02-14'),

    ('applicant7@test.com', 'Finance Director',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 190000, true, '2026-02-15', '2026-02-15');

INSERT INTO vacancies
(name, description, category_id, salary, exp_from, exp_to, is_active, author, created_date, update_date)
VALUES
    ('Junior Legal Consultant', 'Client support and document preparation',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'), 60000, 0, 1, true, 'employer1@test.com', '2026-02-01', '2026-02-01'),

    ('Middle Corporate Lawyer', 'Corporate contracts and compliance',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'), 115000, 3, 6, true, 'employer2@test.com', '2026-02-02', '2026-02-02'),

    ('Senior Litigation Lawyer', 'Court cases and arbitration',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'), 155000, 5, 10, true, 'employer3@test.com', '2026-02-03', '2026-02-03'),

    ('Legal Department Head', 'Manage legal team',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'), 175000, 7, 12, true, 'employer4@test.com', '2026-02-04', '2026-02-04'),

    ('Junior Accountant', 'Primary accounting documents',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 62000, 0, 1, true, 'employer5@test.com', '2026-02-05', '2026-02-05'),

    ('Middle Accountant', 'Monthly reports and balance',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 92000, 2, 4, true, 'employer6@test.com', '2026-02-06', '2026-02-06'),

    ('Chief Accountant', 'Financial control and audits',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 145000, 6, 10, true, 'employer1@test.com', '2026-02-07', '2026-02-07'),

    ('Financial Analyst', 'Budgeting and forecasting',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 135000, 3, 6, true, 'employer2@test.com', '2026-02-08', '2026-02-08'),

    ('Tax Consultant', 'Tax optimization and reporting',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 120000, 4, 7, true, 'employer3@test.com', '2026-02-09', '2026-02-09'),

    ('Audit Supervisor', 'Audit planning and control',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 140000, 5, 9, true, 'employer4@test.com', '2026-02-10', '2026-02-10'),

    ('Contract Lawyer', 'Draft and review contracts',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'), 110000, 3, 5, true, 'employer5@test.com', '2026-02-11', '2026-02-11'),

    ('Compliance Officer', 'Internal legal compliance',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'), 125000, 4, 7, true, 'employer6@test.com', '2026-02-12', '2026-02-12'),

    ('Risk Management Lawyer', 'Legal risk assessment',
     (SELECT id FROM categories WHERE name = 'Юриспруденция'), 145000, 5, 8, true, 'employer1@test.com', '2026-02-13', '2026-02-13'),

    ('Treasury Accountant', 'Cash flow and banking operations',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 115000, 3, 6, true, 'employer2@test.com', '2026-02-14', '2026-02-14'),

    ('Internal Control Manager', 'Process audits and controls',
     (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 150000, 6, 10, true, 'employer3@test.com', '2026-02-15', '2026-02-15');
