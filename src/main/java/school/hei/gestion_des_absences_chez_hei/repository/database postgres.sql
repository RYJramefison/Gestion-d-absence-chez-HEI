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

INSERT INTO student (id,firstName, lastName, email, contact, universityYears, genre, status)
VALUES ('STD23001', 'John', 'Doe', 'john.doe@example.com', '1234567890', 'L1', 'M', 'actif');
INSERT INTO student (id,firstName, lastName, email, contact, universityYears, genre, status)
VALUES ('STD23002', 'Jane', 'Smith', 'jane.smith@example.com', '0987654321', 'L1', 'F', 'inactif');
INSERT INTO student (id,firstName, lastName, email, contact, universityYears, genre, status)
VALUES ('STD22003', 'Alex', 'Johnson', 'alex.johnson@example.com', '1122334455', 'L1', 'M', 'actif');
INSERT INTO student (id,firstName, lastName, email, contact, universityYears, genre, status)
VALUES ('STD23004', 'Emily', 'Davis', 'emily.davis@example.com', '5566778899', 'L1', 'F', 'actif');
INSERT INTO student (id,firstName, lastName, email, contact, universityYears, genre, status)
VALUES ('STD22055', 'Michael', 'Brown', 'michael.brown@example.com', '6677889900', 'L2', 'M', 'actif');




CREATE TABLE admin (
                       id VARCHAR(15) PRIMARY KEY,
                       firstName VARCHAR(100),
                       lastName VARCHAR(100),
                       email VARCHAR(100),
                       contact VARCHAR(20)
);


INSERT INTO admin (id, firstName, lastName, email, contact)
VALUES ('001', 'Alice', 'Williams', 'alice.williams@example.com', '1234567890');
INSERT INTO admin (id, firstName, lastName, email, contact)
VALUES ('002', 'Bob', 'Johnson', 'bob.johnson@example.com', '0987654321');



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




CREATE TABLE absence (
                         id SERIAL PRIMARY KEY,
                         student_id VARCHAR(50) REFERENCES student(id),
                         course_id INT REFERENCES course(id)
);




INSERT INTO absence (course_id, student_id)
VALUES
    (1, 'STD23001'), -- John Doe est absent
    (1, 'STD23004'); -- Emily Davis est absente

INSERT INTO absence (course_id, student_id)
VALUES
    (2, 'STD23002'), -- Jane Smith est absente
    (2, 'STD22003'); -- Alex Johnson est absent
INSERT INTO absence (course_id, student_id)
VALUES
    (4, 'STD23001'), -- John Doe est absent
    (4, 'STD22003'); -- Alex Johnson est absent
INSERT INTO absence (course_id, student_id)
VALUES
    (3, 'STD23004'), -- Emily Davis est absente
    (3, 'STD22055'); -- Michael Brown est absent
INSERT INTO absence (course_id, student_id)
VALUES
    (5, 'STD23002'), -- Jane Smith est absente
    (5, 'STD22055'); -- Michael Brown est absent

-- pagination

Select * from student limit 5 offset 5;
