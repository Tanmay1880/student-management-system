package ui;

import model.Student;
import service.StudentService;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleUI {

    private StudentService service;
    private Scanner sc;

    public ConsoleUI() {
        service = new StudentService();
        sc = new Scanner(System.in);
    }

    public void start() {
        System.out.println();
        System.out.println("==============================================");
        System.out.println("         STUDENT MANAGEMENT SYSTEM");
        System.out.println("==============================================");

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
                    case 0 ->{
                        service.saveAll();
                        System.out.println("Saved. Goodbye!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

        } while (choice != 0);

        sc.close();
    }

    // ─────────────────────── MENU ───────────────────────

    private void showMenu() {
        System.out.println();
        System.out.println("--------------- MENU ---------------");
        System.out.println(" 0 : Exit (save)");
        System.out.println(" 1 : Add Student");
        System.out.println(" 2 : View Students");
        System.out.println(" 3 : Delete Student");
        System.out.println(" 4 : Search Student");
        System.out.println(" 5 : Update Student");
        System.out.println("-----------------------------------");
    }

    // ─────────────────────── FEATURES ───────────────────────

    private void addStudent() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Add Student");
                System.out.println("-----------");
                String name = getValidString("Name: ");
                int age = getValidAge("Age: ");
                service.addStudent(name, age);
                System.out.println("Student added successfully.");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private void viewStudents() {
        System.out.println();
        System.out.println("All Students");
        System.out.println("------------");
        List<Student> list = service.getAllStudents();
        printStudentList(list);
    }

    private void deleteStudent() {
        System.out.println();
        System.out.println("Delete Student");
        System.out.println("--------------");
        int id      = getValidId("Student ID: ");
        boolean deleted = service.deleteStudent(id);

        System.out.println(deleted ? "Student deleted successfully." : "Student not found.");
    }

    private void searchStudent() {
        while (true) {
            System.out.println();
            System.out.println("Search Student");
            System.out.println("--------------");
            int searchChoice = getValidInt("""
                0 : Back
                1 : Search by ID
                2 : Search by Name
                Choice: """);

            switch (searchChoice) {
                case 0 -> { return; }
                case 1 -> searchById();
                case 2 -> searchByName();
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void searchById() {
        int id    = getValidId("Student ID: ");
        Optional<Student> result = service.searchStudent(id);

        if (result.isPresent()) {
            Student s = result.get();
            System.out.println();
            printStudent(s);
        } else {
            System.out.println("Student not found.");
        }
    }

    private void searchByName() {
        String name            = getValidString("Student name: ");
        List<Student> list = service.searchStudentByName(name);
        printStudentList(list);
    }

    private void updateStudent() {
        System.out.println();
        System.out.println("Update Student");
        System.out.println("--------------");
        int    id   = getValidId("Student ID: ");
        String name = getValidString("New name: ");
        int    age  = getValidAge("New age: ");

        boolean updated = service.updateStudent(id, name, age);
        System.out.println(updated ? "Updated successfully." : "Student not found.");
    }

    // ─────────────────────── PRINT HELPERS ───────────────────────

    private void printStudent(Student s) {
        System.out.printf("ID: %d | Name: %s | Age: %d%n", s.getId(), s.getName(), s.getAge());
    }

    private void printStudentList(List<Student> list) {
        if (list.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("+----+------------------------------+-----+");
        System.out.println("| ID | Name                         | Age |");
        System.out.println("+----+------------------------------+-----+");
        for (Student s : list) {
            System.out.printf("| %2d | %-28s | %3d |%n", s.getId(), truncate(s.getName(), 28), s.getAge());
        }
        System.out.println("+----+------------------------------+-----+");
    }

    // ─────────────────────── INPUT HELPERS ───────────────────────

    private String truncate(String s, int maxLen) {
        if (s == null) return "";
        if (s.length() <= maxLen) return s;
        return s.substring(0, Math.max(0, maxLen - 3)) + "...";
    }

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