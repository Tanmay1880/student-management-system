# Student Management System (Java - Console Based)

## 📌 Project Overview

This is a simple **console-based Student Management System** built using Java.
It allows users to manage student records with basic CRUD operations and stores data persistently using a text file.

The project follows a **layered architecture** to maintain clean code structure and separation of concerns.

---

## 🚀 Features

* Add a new student
* View all students
* Update student details
* Delete a student
* Search student by ID
* Persistent storage using file handling

---

## 🛠️ Tech Stack

* **Java**
* **File Handling** (FileWriter, BufferedReader)
* **OOP Concepts**

    * Classes & Objects
    * Encapsulation
    * Separation of Concerns

---

## 📁 Project Structure

```
StudentManagementSystem/
│
├── model/
│   └── Student.java
│
├── service/
│   └── StudentService.java
│
├── database/   
│   └── StudentFileDatabase.java
│
├── ui/
│   └── ConsoleUI.java
│
└── Main.java
```

---

## ▶️ How to Run

1. Clone the repository

```
git clone <https://github.com/Tanmay1880/student-management-system.git>
```

2. Navigate to the project folder

```
cd student-management-system
```

3. Compile the project

```
javac Main.java
```

4. Run the application

```
java Main
```

---

## 🖥️ Sample Output

```
------------ STUDENT MANAGEMENT SYSTEM ------------

0 : Exit
1 : Add Student
2 : View Students
3 : Delete Student
4 : Search Student
5 : Update Student

Enter choice: 1
Enter name: Himanshu
Enter age: 45
Student added successfully

Enter choice: 2
ID: 1 | Name: Tanmay | Age: 20
ID: 2 | Name: Varun | Age: 23
ID: 3 | Name: Sagar | Age: 25
```

---

## 💡 Future Improvements

* Add input validation and better error handling
* Replace text file with a database (MySQL / MongoDB)
* Rename `database` layer to `repository` (industry standard)
* Add sorting and filtering features
* Convert into a REST API using Spring Boot

---

## 🎯 Learning Outcomes

* Understanding of basic backend logic
* Hands-on with file handling in Java
* Experience with layered architecture
* Improved problem-solving skills

---

## 📌 Author

Tanmay Kaushik
