package ui;

import model.Student;
import service.StudentService;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleUI {


    private StudentService service;
    private Scanner sc;

    public ConsoleUI() {
        service = new StudentService();
        sc = new Scanner(System.in);
    }

    public void start() {
        int choice;

        System.out.println("\n<----------- STUDENT MANAGEMENT SYSTEM ----------->");

        do {
            showMenu();
            choice = sc.nextInt();

            switch (choice) {

                case 1 -> addStudent();

                case 2 -> viewStudents();

                case 3 -> deleteStudent();

                case 4 -> searchStudent();

                case 5 -> updateStudent();

                case 0 -> System.out.println("Thank you!");

                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);

        sc.close();
    }

// ---------------- MENU ----------------

    private void showMenu() {
        System.out.println("""
            0 : Exit
            1 : Add Student
            2 : View Students
            3 : Delete Student
            4 : Search Student
            5 : Update Student
            """);
    }

// ---------------- FEATURES ----------------

    private void addStudent() {
        sc.nextLine();

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter age: ");
        int age = sc.nextInt();

        service.addStudent(name, age);
        System.out.println("Student added successfully");
    }

    private void viewStudents() {
        ArrayList<Student> list = service.getAllStudents();
        printStudentList(list);
    }

    private void deleteStudent() {
        System.out.print("Enter student ID: ");
        int id = sc.nextInt();

        boolean deleted = service.deleteStudent(id);

        if (deleted) {
            System.out.println("Student deleted successfully");
        } else {
            System.out.println("Student not found");
        }
    }

    private void searchStudent() {
        System.out.println("""
            1 : Search Student by ID
            2 : Search Student by Name
            """);

        int searchChoice = sc.nextInt();

        if (searchChoice == 1) {
            searchById();
        } else if (searchChoice == 2) {
            searchByName();
        } else {
            System.out.println("Invalid input");
        }
    }

    private void searchById() {
        System.out.print("Enter student ID: ");
        int id = sc.nextInt();

        Student s = service.searchStudent(id);

        if (s != null) {
            printStudent(s);
        } else {
            System.out.println("Student not found");
        }
    }

    private void searchByName() {
        sc.nextLine();

        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        ArrayList<Student> list = service.searchStudentByName(name);
        printStudentList(list);
    }

    private void updateStudent() {
        System.out.print("Enter student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter age: ");
        int age = sc.nextInt();

        boolean updated = service.updateStudent(id, name, age);

        if (updated) {
            System.out.println("Updated successfully");
        } else {
            System.out.println("Student not found");
        }
    }

// ---------------- UTIL METHODS ----------------

    private void printStudent(Student s) {
        System.out.println("ID: " + s.getId() +
                " | Name: " + s.getName() +
                " | Age: " + s.getAge());
    }

    private void printStudentList(ArrayList<Student> list) {
        if (list.isEmpty()) {
            System.out.println("No students found");
            return;
        }

        for (Student s : list) {
            printStudent(s);
        }
    }


}
