package model;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name;
    private int age;
    private final int id;
    private List<Course> courses;

    public Student(String name, int age, int id) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (age <= 0) {
            throw new IllegalArgumentException("Invalid age");
        }

        this.name = name.trim();
        this.age = age;
        this.id = id;
        this.courses = new ArrayList<>();
    }

    // getters

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void updateName(String name){
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name.trim();
    }

    public void updateAge(int age){
        if (age <= 0) {
            throw new IllegalArgumentException("Invalid age");
        }
        this.age = age;
    }
    public void enrollCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        if (courses.size() >= 5) {
            throw new IllegalArgumentException("Max courses reached");
        }
        if (courses.contains(course)) {
            throw new IllegalArgumentException("Already enrolled in this course");
        }
        courses.add(course);
    }

    public boolean unrollCourse(Course course){
        if (course==null){
            throw new IllegalArgumentException("Course cannot be null");
        }
        if (courses.size()==1){
            throw  new IllegalStateException("Student Should be enrolled in at least one course");
        }

        return courses.remove(course);
    }

    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }

}

