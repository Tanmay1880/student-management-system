package repository;

import database.StudentFileDatabase;
import model.Student;

import java.util.ArrayList;

public class StudentRepository {

    private StudentFileDatabase db;
    private ArrayList<Student> students;

    public StudentRepository() {
        this.db = new StudentFileDatabase();
        this.students = db.loadStudents();
    }

    public ArrayList<Student> findAll() {
        return new ArrayList<>(students);
    }

    public void save(Student student) {
        students.add(student);
        db.saveStudents(students);
    }

    public void delete(Student student) {
        students.remove(student);
        db.saveStudents(students);
    }

    public Student findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public ArrayList<Student> findByName(String name) {
        ArrayList<Student> list = new ArrayList<>();

        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                list.add(s);
            }
        }

        return list;
    }

    public void update() {
        db.saveStudents(students);
    }
}