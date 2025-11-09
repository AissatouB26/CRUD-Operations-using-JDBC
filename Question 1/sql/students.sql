--The initial data in the students table--
CREATE TABLE students (
	student_id INTEGER PRIMARY KEY AUTO,
	first_name TEXT NOT NULL,
	last_name TEXT NOT NULL, 
	email TEXT NOT NULL UNIQUE,
	enrollment_date DATE
)