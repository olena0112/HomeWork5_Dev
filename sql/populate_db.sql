INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY)
VALUES
    ('John Doe', '1990-01-01', 'Trainee', 800),
    ('Jane Smith', '1985-05-15', 'Junior', 1200),
    ('Mike Johnson', '1982-09-30', 'Middle', 3000),
    ('Emily Davis', '1978-03-10', 'Senior', 6000),
    ('Robert Wilson', '1992-07-20', 'Middle', 2500),
    ('Sarah Brown', '1995-12-05', 'Junior', 1500),
    ('David Taylor', '1989-06-18', 'Middle', 2800),
    ('Laura Anderson', '1987-09-22', 'Senior', 5500),
    ('Mark Thompson', '1991-02-14', 'Junior', 1400),
    ('Amy Roberts', '1993-11-08', 'Trainee', 900);

INSERT INTO client (NAME)
VALUES
    ('ABC Company'),
    ('XYZ Corporation'),
    ('123 Industries'),
    ('Tech Solutions'),
    ('Global Enterprises');

INSERT INTO project (CLIENT_ID, START_DATE, FINISH_DATE)
VALUES
    (1, '2022-01-01', '2022-05-31'),
    (1, '2022-03-15', '2023-06-30'),
    (2, '2022-06-01', '2023-02-28'),
    (3, '2022-09-01', '2023-04-30'),
    (4, '2022-12-01', '2023-12-31'),
    (2, '2023-03-01', '2023-05-31'),
    (3, '2023-06-15', '2024-03-31'),
    (4, '2023-09-01', '2024-08-31'),
    (5, '2024-01-01', '2024-04-30'),
    (5, '2024-03-15', '2024-07-31');

INSERT INTO project_worker (PROJECT_ID, WORKER_ID)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 2),
    (2, 3),
    (2, 4),
    (2, 5),
    (3, 3),
    (3, 4),
    (3, 5),
    (4, 4),
    (4, 5),
    (5, 5),
    (6, 1),
    (6, 2),
    (7, 2),
    (7, 3),
    (7, 4),
    (8, 3),
    (8, 4),
    (9, 4),
    (9, 5),
    (10, 5);

COMMIT;
