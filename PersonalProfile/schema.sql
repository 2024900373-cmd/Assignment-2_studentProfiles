/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Afrina Natasha
 * Created: Dec 26, 2025
 */
CREATE TABLE profiles (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    student_id VARCHAR(20) NOT NULL,
    name VARCHAR(100) NOT NULL,
    programme VARCHAR(50),
    email VARCHAR(100),
    hobbies VARCHAR(100),
    intro CLOB
);

ALTER TABLE profiles
ADD CONSTRAINT uq_student UNIQUE (student_id);
