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
        System.out.println("\n<----------- STUDENT MANAGEMENT SYSTEM ----------->");

        int choice;
        do {
            showMenu();
            choice = getValidMenuChoice();

            try {
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> viewStudents();
                    case 3 -> deleteStudent();
                    case 4 -> searchStudent();
                    case 5 -> updateStudent();
                    case 0 -> System.out.println("Thank you!");
                }
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

        } while (choice != 0);

        sc.close();
    }

    // ─────────────────────── MENU ───────────────────────

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

    // ─────────────────────── FEATURES ───────────────────────

    private void addStudent() {
        String name = getValidString("Enter name: ");
        int age  = getValidAge("Enter age: ");

        while (true) {
            try {
                service.addStudent(name, age);
                System.out.println("Student added successfully.");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                name = getValidString("Enter name: ");
                age = getValidAge("Enter age: ");
            }
        }
    }

    private void viewStudents() {
        ArrayList<Student> list = service.getAllStudents();
        printStudentList(list);
    }

    private void deleteStudent() {
        int id      = getValidId("Enter student ID: ");
        boolean deleted = service.deleteStudent(id);

        System.out.println(deleted ? "Student deleted successfully." : "Student not found.");
    }

    private void searchStudent() {
        int searchChoice = getValidInt(" 1 : Search by ID\n 2 : Search by Name\nEnter choice: ");

        switch (searchChoice) {
            case 1 -> searchById();
            case 2 -> searchByName();
            default -> System.out.println("Invalid option.");
        }
    }

    private void searchById() {
        int id    = getValidId("Enter student ID: ");
        Student s = service.searchStudent(id);

        if (s != null) printStudent(s);
        else           System.out.println("Student not found.");
    }

    private void searchByName() {
        String name            = getValidString("Enter student name: ");
        ArrayList<Student> list = service.searchStudentByName(name);
        printStudentList(list);
    }

    private void updateStudent() {
        int    id   = getValidId("Enter student ID: ");
        String name = getValidString("Enter new name: ");
        int    age  = getValidAge("Enter new age: ");

        boolean updated = service.updateStudent(id, name, age);
        System.out.println(updated ? "Updated successfully." : "Student not found.");
    }

    // ─────────────────────── PRINT HELPERS ───────────────────────

    private void printStudent(Student s) {
        System.out.println("ID: " + s.getId()
                + " | Name: " + s.getName()
                + " | Age:  " + s.getAge());
    }

    private void printStudentList(ArrayList<Student> list) {
        if (list.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        list.forEach(this::printStudent);
    }

    // ─────────────────────── INPUT HELPERS ───────────────────────


    private String getValidString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            if (!input.isEmpty()) return input;

            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private int getValidInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty.");
                continue;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private int getValidId(String prompt) {
        while (true) {
            int id = getValidInt(prompt);
            if (id > 0) return id;
            System.out.println("ID must be a positive number.");
        }
    }


    private int getValidAge(String prompt) {
        while (true) {
            int age = getValidInt(prompt);
            if (age > 0) return age;
            System.out.println("Age must be a positive number.");
        }
    }

    private int getValidMenuChoice() {
        while (true) {
            int choice = getValidInt("Enter choice: ");

            if (choice >= 0 && choice <= 5) return choice;

            System.out.println("Invalid option. Please enter a number between 0 and 5.");
        }
    }
}