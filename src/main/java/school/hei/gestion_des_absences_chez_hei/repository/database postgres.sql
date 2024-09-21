CREATE TABLE student (
                         id VARCHAR(15) PRIMARY KEY,
                         firstName VARCHAR(100),
                         lastName VARCHAR(100),
                         email VARCHAR(100),
                         contact VARCHAR(20),
                         universityYears VARCHAR(10) CHECK (universityYears IN ('L1', 'L2', 'L3', 'M1', 'M2')),
                         genre CHAR(1) CHECK (genre IN ('M', 'F')),
                         status VARCHAR(50)
);

INSERT INTO student (firstName, lastName, email, contact, universityYears, genre, status)
VALUES ('STD23001', 'John', 'Doe', 'john.doe@example.com', '1234567890', 'L1', 'M', 'actif');
INSERT INTO student (firstName, lastName, email, contact, universityYears, genre, status)
VALUES ('STD23002', 'Jane', 'Smith', 'jane.smith@example.com', '0987654321', 'L1', 'F', 'inactif');
INSERT INTO student (firstName, lastName, email, contact, universityYears, genre, status)
VALUES ('STD22003', 'Alex', 'Johnson', 'alex.johnson@example.com', '1122334455', 'L1', 'M', 'actif');
INSERT INTO student (firstName, lastName, email, contact, universityYears, genre, status)
VALUES ('STD23004', 'Emily', 'Davis', 'emily.davis@example.com', '5566778899', 'L1', 'F', 'actif');
INSERT INTO student (firstName, lastName, email, contact, universityYears, genre, status)
VALUES ('STD22055', 'Michael', 'Brown', 'michael.brown@example.com', '6677889900', 'L2', 'M', 'actif');




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


CREATE TABLE absence (
                         course_id INT NOT NULL,              -- Référence au cours
                         student_id INT NOT NULL,             -- Référence à l'étudiant
                         date_absence DATE NOT NULL,          -- Date de l'absence
                         PRIMARY KEY (course_id, student_id, date_absence), -- Clé primaire composite
                         FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE,
                         FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE
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
