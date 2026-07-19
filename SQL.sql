show databases;

create database student_mgmt;

use student_mgmt;

show tables;

CREATE TABLE students(
id INT primary key auto_increment,
first_name VARCHAR(50),
last_name VARCHAR(50),
email VARCHAR(100) UNIQUE,
phone VARCHAR(10),
gender VARCHAR(6),
dob VARCHAR(30),
course VARCHAR(100),
year INT,
address VARCHAR(100)
    );
    
INSERT INTO students (first_name, last_name, email, phone, gender, dob, course, year, address) 
VALUES ("Soumya", "Tripathy", "soumyatripathy977@gmail.com", "9692229679", "Male", "29-5-2002", "Java Full Stack course", 2026, "Brahmeswar, Bhubaneswar, Odisha, 751018");

SELECT * FROM students;

CREATE TABLE admin(
id INT primary key auto_increment,
username VARCHAR(30) UNIQUE,
password VARCHAR(100)
    );
    
INSERT INTO admin(username, password) VALUES ("admin", "admin123");

SELECT * FROM admin;
