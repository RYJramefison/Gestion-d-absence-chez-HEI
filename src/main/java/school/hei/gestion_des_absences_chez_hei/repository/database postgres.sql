
                                    -- STUDENT TABLE --

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

INSERT INTO student (id, firstName, lastName, email, contact, universityYears, genre, status)
VALUES
    ('STD23001', 'John', 'Doe', 'john.doe@example.com', '1234567890', 'L1', 'M', 'actif'),
    ('STD23002', 'Jane', 'Smith', 'jane.smith@example.com', '0987654321', 'L1', 'F', 'inactif'),
    ('STD22003', 'Alex', 'Johnson', 'alex.johnson@example.com', '1122334455', 'L1', 'M', 'actif'),
    ('STD23004', 'Emily', 'Davis', 'emily.davis@example.com', '5566778899', 'L1', 'F', 'actif'),
    ('STD22055', 'Michael', 'Brown', 'michael.brown@example.com', '6677889900', 'L2', 'M', 'actif');



                                    -- ADMIN TABLE --

CREATE TABLE admin (
                       id VARCHAR(15) PRIMARY KEY,
                       firstName VARCHAR(100),
                       lastName VARCHAR(100),
                       email VARCHAR(100),
                       contact VARCHAR(20)
);

INSERT INTO admin (id, firstName, lastName, email, contact)
VALUES
    ('001', 'Alice', 'Williams', 'alice.williams@example.com', '1234567890'),
    ('002', 'Bob', 'Johnson', 'bob.johnson@example.com', '0987654321');


                                    -- COURSE TABLE --

CREATE TABLE course (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        startCourse TIMESTAMP NOT NULL,
                        endCourse TIMESTAMP NOT NULL
);

INSERT INTO course (name, startCourse, endCourse)
VALUES
    ('Introduction to Programming', '2024-09-01 09:00:00', '2024-09-01 12:00:00'),
    ('Database Systems', '2024-09-15 13:00:00', '2024-09-15 15:00:00'),
    ('Web Development', '2024-10-01 10:00:00', '2024-10-01 14:00:00'),
    ('Data Structures', '2024-10-15 11:00:00', '2024-10-15 13:00:00'),
    ('Operating Systems', '2024-11-01 08:00:00', '2024-11-01 12:00:00');


                                    -- ABSENCE TABLE --

CREATE TABLE absence (
                         id SERIAL PRIMARY KEY,
                         studentId VARCHAR(50) REFERENCES student(id),
                         courseId INT REFERENCES course(id),
                         isJustify BOOLEAN
);

                                    INSERT INTO absence (courseId, studentId, isJustify)
                                    VALUES
                                        (1, 'STD23001', FALSE),
                                        (1, 'STD23004', TRUE),
                                        (2, 'STD23002', TRUE),
                                        (2, 'STD22003', FALSE),
                                        (4, 'STD23001', FALSE),
                                        (4, 'STD22003', TRUE),
                                        (3, 'STD23004', FALSE),
                                        (3, 'STD22055', TRUE),
                                        (3, 'STD23001', FALSE);


                                    -- JUSTIFICATION TABLE --

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


                                    -- PAGINATION --


SELECT * FROM student LIMIT 5 OFFSET 5;

                                    -- COR TABLE --

                                    CREATE TABLE cor (
                                                         id SERIAL PRIMARY KEY,
                                                         status VARCHAR(50) NOT NULL,
                                                         date DATE NOT NULL,
                                                         studentId VARCHAR(20) NOT NULL,
                                                         FOREIGN KEY (studentId) REFERENCES student(id)
                                    );

                                    INSERT INTO cor (status, date, studentId) VALUES
                                                                                ('Convocation', '2024-09-15', 'STD23001'),
                                                                                ('Observation', '2024-08-30', 'STD23002');

