import java.util.ArrayList;
import java.util.Scanner;

import model.Student;
import service.StudentService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();

        int choice;

        do {
            System.out.println("\n<----------- STUDENT MANAGEMENT SYSTEM ----------->");
            System.out.println("""
                    0 : Exit
                    1 : Add Student
                    2 : View Students
                    3 : Delete Student
                    4 : Search Student
                    5 : Update Student
                    """);

            choice = sc.nextInt();

            switch (choice) {

                case 1: {
                    sc.nextLine();
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter age: ");
                    int age = sc.nextInt();

                    service.addStudent(name, age);
                    System.out.println("Student added successfully");
                    break;
                }

                case 2: {
                    ArrayList<Student> list = service.getAllStudents();
                    if (list.isEmpty()) {
                        System.out.println("No students available");
                    } else {
                        for (Student s : list) {
                            System.out.println("ID: " + s.getId() +
                                    " Name: " + s.getName() +
                                    " Age: " + s.getAge());
                        }
                    }
                    break;
                }

                case 3:{
                    System.out.print("Enter student ID: ");
                    int id = sc.nextInt();

                    boolean deleted = service.deleteStudent(id);

                    if (deleted) {
                        System.out.println("Student deleted successfully");
                    } else {
                        System.out.println("Student not found");
                    }
                    break;
                }

                case 4:{
                    System.out.print("Enter student ID: ");
                     int id = sc.nextInt();

                    Student s = service.searchStudent(id);

                    if (s != null) {
                        System.out.println("ID: " + s.getId() +
                                " Name: " + s.getName() +
                                " Age: " + s.getAge());
                    } else {
                        System.out.println("Student not found");
                    }
                    break;
                }

                case 5:{
                    System.out.print("Enter student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter age: ");
                    int age = sc.nextInt();

                   boolean updated = service.updateStudent(id, name, age);

                   if(updated){
                       System.out.println("Updated Successfully");
                   }
                   else {
                       System.out.println("Student not found");
                   }
                }
                break;

                case 0: {
                    System.out.println("Thank you!");
                    break;
                }

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}