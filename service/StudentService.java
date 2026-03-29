package service;

import database.StudentFileDatabase;
import model.Student;
import java.util.ArrayList;

public class StudentService {

    private ArrayList<Student> students;
    private int nextId;
    private StudentFileDatabase db;

    public StudentService() {
        this.db = new StudentFileDatabase();
        this.students = db.loadStudents();
        this.nextId = 1;

        for (Student s : students) {
            if (s.getId() >= nextId) {
                nextId = s.getId() + 1;
            }
        }
    }

    public void addStudent(String name, int age) {
        Student s = new Student(name, age, nextId);
        students.add(s);
        nextId++;
        db.saveStudents(students);
    }

    public boolean deleteStudent(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                db.saveStudents(students);
                return true;
            }
        }

        return false;
    }

    public ArrayList<Student> searchStudentByName(String name) {
        ArrayList<Student> list = new ArrayList<>();

        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                list.add(s);
            }
        }

        return list;
    }


    public ArrayList<Student> getAllStudents() {
        return new ArrayList<>(students);

    }

    public Student searchStudent(int id){
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                return students.get(i);

            }
        }
        return null;
    }

    public boolean updateStudent(int id, String name, int age){


        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {

                Student s = students.get(i);
                s.setName(name);
                s.setAge(age);
                db.saveStudents(students);
                return true;

            }
        }
        db.saveStudents(students);
        return false;
    }
}