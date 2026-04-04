package repository;

import database.StudentFileDatabase;
import model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private StudentFileDatabase db;
    private ArrayList<Student> students;

    public StudentRepository() {
        this.db = new StudentFileDatabase();
        this.students = db.loadStudents();
    }

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    public void save(Student student) {
        students.add(student);
    }

    public boolean deleteById(int id){
        Student s = findById(id);
        if (s == null) return false;

        students.remove(s);
        return true;
    }

    public Student findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public List<Student> findByName(String name) {
        List<Student> list = new ArrayList<>();

        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                list.add(s);
            }
        }

        return list;
    }

    public void saveAll() {
        db.saveStudents(students);
    }
}