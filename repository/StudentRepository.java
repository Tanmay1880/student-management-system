package repository;

import database.StudentFileDatabase;
import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository {

    private StudentFileDatabase db;
    private List<Student> students;

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
        return findById(id).map(students::remove).orElse(false);
    }


    public Optional<Student> findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return Optional.of(s);
        }
        return Optional.empty();
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