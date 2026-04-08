package service;

import model.Student;
import repository.StudentRepository;
import java.util.List;
import java.util.Optional;

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
        return repo.deleteById(id);
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Optional <Student> searchStudent(int id) {
        return repo.findById(id);
    }

    public List<Student> searchStudentByName(String name) {
        return repo.findByName(name);
    }

    public boolean updateStudent(int id, String name, int age) {
        Optional<Student> opt = repo.findById(id);
        if (opt.isEmpty()) return false;

        Student s = opt.get();
        s.updateName(name);
        s.updateAge(age);
        return true;
    }

    public void saveAll() {
        repo.saveAll();
    }
}