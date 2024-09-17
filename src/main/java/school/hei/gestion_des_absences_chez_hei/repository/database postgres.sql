CREATE TABLE student (
                          id VARCHAR(15) PRIMARY KEY,
                          firstName VARCHAR(255) NOT NULL,
                          lastName VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          contact VARCHAR(20),
                          universityYears VARCHAR(50),
                          status VARCHAR(50)
);

INSERT INTO student (id, firstName, lastName, email, contact, universityYears, status)
VALUES ('S001', 'Alice', 'Brown', 'alice.brown@example.com', '1234567890', '3', 'active');
INSERT INTO student (id, firstName, lastName, email, contact, universityYears, status)
VALUES ('S002', 'Bob', 'Smith', 'bob.smith@example.com', '0987654321', '2', 'graduated');
INSERT INTO student (id, firstName, lastName, email, contact, universityYears, status)
VALUES ('S003', 'Charlie', 'Johnson', 'charlie.johnson@example.com', '1122334455', '4', 'active');
INSERT INTO student (id, firstName, lastName, email, contact, universityYears, status)
VALUES ('S004', 'Diana', 'Miller', 'diana.miller@example.com', '6677889900', '5', 'suspended');
INSERT INTO student (id, firstName, lastName, email, contact, universityYears, status)
VALUES ('S005', 'Ethan', 'Davis', 'ethan.davis@example.com', '4433221100', '1', 'active');

-- pagination

Select * from student limit 5 offset 5;
