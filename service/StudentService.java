package service;

import model.Student;
import repository.StudentRepository;

import java.util.ArrayList;

public class StudentService {

    private StudentRepository repo;
    private int nextId;

    public StudentService() {
        this.repo = new StudentRepository();
        this.nextId = 1;

        for (Student s : repo.findAll()) {
            if (s.getId() >= nextId) {
                nextId = s.getId() + 1;
            }
        }
    }

    public void addStudent(String name, int age) {
        Student s = new Student(name, age, nextId);
        repo.save(s);
        nextId++;
    }

    public boolean deleteStudent(int id) {
        Student s = repo.findById(id);
        if (s == null) return false;

        repo.delete(s);
        return true;
    }

    public ArrayList<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student searchStudent(int id) {
        return repo.findById(id);
    }

    public ArrayList<Student> searchStudentByName(String name) {
        return repo.findByName(name);
    }

    public boolean updateStudent(int id, String name, int age) {
        Student s = repo.findById(id);
        if (s == null) return false;

        s.updateName(name);
        s.updateAge(age);

        repo.update();
        return true;
    }
}