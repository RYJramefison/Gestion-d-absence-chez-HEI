CREATE TABLE student (
                         id SERIAL PRIMARY KEY,            -- Identifiant unique pour chaque étudiant
                         firstName VARCHAR(100),           -- Prénom de l'étudiant
                         lastName VARCHAR(100),            -- Nom de famille de l'étudiant
                         email VARCHAR(100),               -- Email de l'étudiant
                         contact VARCHAR(20),              -- Numéro de contact de l'étudiant
                         reference VARCHAR(50),            -- Référence unique de l'étudiant
                         universityYears VARCHAR(50),      -- Années universitaires de l'étudiant
                         status VARCHAR(50)                -- Statut de l'étudiant (ex: actif, inactif)
);

INSERT INTO student (firstName, lastName, email, contact, reference, universityYears, status)
VALUES ('John', 'Doe', 'john.doe@example.com', '1234567890', 'STD23001', '2020-2024', 'actif');
INSERT INTO student (firstName, lastName, email, contact, reference, universityYears, status)
VALUES ('Jane', 'Smith', 'jane.smith@example.com', '0987654321', 'STD23002', '2019-2023', 'inactif');
INSERT INTO student (firstName, lastName, email, contact, reference, universityYears, status)
VALUES ('Alex', 'Johnson', 'alex.johnson@example.com', '1122334455', 'STD23003', '2021-2025', 'actif');
INSERT INTO student (firstName, lastName, email, contact, reference, universityYears, status)
VALUES ('Emily', 'Davis', 'emily.davis@example.com', '5566778899', 'STD23004', '2022-2026', 'actif');
INSERT INTO student (firstName, lastName, email, contact, reference, universityYears, status)
VALUES ('Michael', 'Brown', 'michael.brown@example.com', '6677889900', 'STD23005', '2018-2022', 'actif');



CREATE TABLE admin (
                       id SERIAL PRIMARY KEY,
                       firstName VARCHAR(100),
                       lastName VARCHAR(100),
                       email VARCHAR(100),
                       contact VARCHAR(20)
);


INSERT INTO admin (firstName, lastName, email, contact)
VALUES ('Alice', 'Williams', 'alice.williams@example.com', '1234567890');
INSERT INTO admin (firstName, lastName, email, contact)
VALUES ('Bob', 'Johnson', 'bob.johnson@example.com', '0987654321');
INSERT INTO admin (firstName, lastName, email, contact)
VALUES ('Catherine', 'Smith', 'catherine.smith@example.com', '1122334455');
INSERT INTO admin (firstName, lastName, email, contact)
VALUES ('David', 'Brown', 'david.brown@example.com', '5566778899');
INSERT INTO admin (firstName, lastName, email, contact)
VALUES ('Eva', 'Davis', 'eva.davis@example.com', '6677889900');


CREATE TABLE course (
                        id SERIAL PRIMARY KEY,          -- Identifiant unique pour chaque cours
                        name VARCHAR(255) NOT NULL,    -- Nom du cours
                        startCourse TIMESTAMP NOT NULL,   -- Date et heure de début du cours
                        endCourse TIMESTAMP NOT NULL      -- Date et heure de fin du cours
);

VALUES
    ('Introduction to Programming', '2024-09-01 09:00:00', '2024-09-01 12:00:00');

INSERT INTO course (name, startCourse, endCourse)
VALUES
    ('Database Systems', '2024-09-15 13:00:00', '2024-09-15 15:00:00');

INSERT INTO course (name, startCourse, endCourse)
VALUES
    ('Web Development', '2024-10-01 10:00:00', '2024-10-01 14:00:00');

INSERT INTO course (name, startCourse, endCourse)
VALUES
    ('Data Structures', '2024-10-15 11:00:00', '2024-10-15 13:00:00');

INSERT INTO course (name, startCourse, endCourse)
VALUES
    ('Operating Systems', '2024-11-01 08:00:00', '2024-11-01 12:00:00');

CREATE TABLE courseStudent (
                               courseId INT REFERENCES course(id),
                               studentId INT REFERENCES student(id),
                               PRIMARY KEY (courseId, studentId)
);





-- Liaison entre le cours 'Introduction to Programming' et les étudiants avec id 1 et 2
INSERT INTO courseStudent (courseId, studentId)
VALUES (1, 1);

INSERT INTO courseStudent (courseId, studentId)
VALUES (1, 2);

-- Liaison entre le cours 'Database Systems' et les étudiants avec id 2 et 3
INSERT INTO courseStudent (courseId, studentId)
VALUES (2, 2);

INSERT INTO courseStudent (courseId, studentId)
VALUES (2, 3);

-- Liaison entre le cours 'Web Development' et les étudiants avec id 1, 3 et 4
INSERT INTO courseStudent (courseId, studentId)
VALUES (3, 1);

INSERT INTO courseStudent (courseId, studentId)
VALUES (3, 3);

INSERT INTO courseStudent (courseId, studentId)
VALUES (3, 4);

-- Liaison entre le cours 'Data Structures' et les étudiants avec id 2, 4 et 5
INSERT INTO courseStudent (courseId, studentId)
VALUES (4, 2);

INSERT INTO courseStudent (courseId, studentId)
VALUES (4, 4);

INSERT INTO courseStudent (courseId, studentId)
VALUES (4, 5);

-- Liaison entre le cours 'Operating Systems' et les étudiants avec id 1, 2 et 5
INSERT INTO courseStudent (courseId, studentId)
VALUES (5, 1);

INSERT INTO courseStudent (courseId, studentId)
VALUES (5, 2);

INSERT INTO courseStudent (courseId, studentId)
VALUES (5, 5);

-- pagination

Select * from student limit 5 offset 5;
