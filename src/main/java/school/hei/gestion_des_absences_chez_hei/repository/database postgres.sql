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
                         studentId VARCHAR(50) REFERENCES student(id),
                         courseId INT REFERENCES course(id),
                         isJustify BOOLEAN
);




-- John Doe est absent au cours 1 (non justifié) et Emily Davis (justifiée)
INSERT INTO absence (courseId, studentId, isJustify)
VALUES
    (1, 'STD23001', FALSE), -- John Doe est absent, absence non justifiée
    (1, 'STD23004', TRUE);  -- Emily Davis est absente, absence justifiée

-- Jane Smith est absente au cours 2 (justifiée) et Alex Johnson (non justifié)
INSERT INTO absence (courseId, studentId, isJustify)
VALUES
    (2, 'STD23002', TRUE),  -- Jane Smith est absente, absence justifiée
    (2, 'STD22003', FALSE); -- Alex Johnson est absent, absence non justifiée

-- John Doe est absent au cours 4 (non justifié) et Alex Johnson (justifiée)
INSERT INTO absence (courseId, studentId, isJustify)
VALUES
    (4, 'STD23001', FALSE), -- John Doe est absent, absence non justifiée
    (4, 'STD22003', TRUE);  -- Alex Johnson est absent, absence justifiée

-- Emily Davis est absente au cours 3 (non justifiée) et Michael Brown (justifiée)
INSERT INTO absence (courseId, studentId, isJustify)
VALUES
    (3, 'STD23004', FALSE), -- Emily Davis est absente, absence non justifiée
    (3, 'STD22055', TRUE);  -- Michael Brown est absent, absence justifiée

-- Jane Smith est absente au cours 5 (justifiée) et Michael Brown (non justifiée)



CREATE TABLE justification (
                               studentId VARCHAR(255) NOT NULL,
                               courseId INT NOT NULL,
                               type VARCHAR(100) NOT NULL,
                               description TEXT NOT NULL,
                               date DATE NOT NULL,
                               PRIMARY KEY (studentId, courseId)
);

INSERT INTO justification (studentId, courseId, type, description, date)
VALUES
    ('STD23001', 1, 'Medical', 'Absent due to illness', '2024-09-10'),
    ('STD23002', 2, 'Family', 'Family emergency', '2024-09-12'),
    ('STD23003', 3, 'Personal', 'Personal reasons', '2024-09-15'),
    ('STD23004', 4, 'Medical', 'Medical appointment', '2024-09-18'),
    ('STD23005', 5, 'Work', 'Work-related absence', '2024-09-20');


-- pagination

Select * from student limit 5 offset 5;
