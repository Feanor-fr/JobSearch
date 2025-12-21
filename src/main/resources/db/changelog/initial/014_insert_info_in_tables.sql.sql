INSERT INTO responded_applicants(resume_id, vacancy_id, confirmation)
VALUES
    (1, 1, true),
    (2, 2, false);

INSERT INTO messages(responded_applicants, content, timestamp)
VALUES
    (1, 'Здравствуйте! Я заинтересован в вакансии Юрист', '2025-12-19 10:00:00'),
    (1, 'Спасибо за отклик!', '2025-12-19 10:30:00'),
    (2, 'Добрый день! Интересуюсь вакансией Бухгалтер', '2025-12-19 11:15:00');

INSERT INTO education_info(resume_id, institution, program, start_date, end_date, degree)
VALUES
    (1, 'Кыргызский Национальный Университет', 'Юриспруденция', '2015-09-01', '2019-06-30', 'Бакалавр'),
    (1, 'Московский Государственный Университет', 'Международное право', '2019-09-01', '2021-06-30', 'Магистр'),
    (2, 'Кыргызский Экономический Университет', 'Бухгалтерский учет', '2014-09-01', '2018-06-30', 'Бакалавр'),
    (2, 'Бишкекская Бизнес Академия', 'Финансовый менеджмент', '2020-09-01', '2022-06-30', 'Магистр');

INSERT INTO work_experience_info(resume_id, years, company_name, position, responsibilities)
VALUES
    (1, 3, 'Юридическая фирма Гранат', 'Юрист', 'Консультирование клиентов'),
    (1, 2, 'Международная компания Шедевр', 'Старший юрист', 'Ведение международных контрактов'),
    (2, 4, 'Бухгалтерская фирма ЛДПР', 'Бухгалтер', 'Ведение документации'),
    (2, 1, 'Торговая компания Мерч', 'Главный бухгалтер', 'Руководство бухгалтерским отделом');

INSERT INTO contact_types(type)
VALUES
    ('gmail'),
    ('telegram');

INSERT INTO contacts_info(type_id, resume_id, contact_value)
VALUES
    (1, 1, 'lar.jurist@gmail.com'),
    (2, 1, '@larra_lawyer'),
    (1, 2, 'lar.a@gmail.com'),
    (2, 2, '@larra_ac');