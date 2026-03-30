package model;

public class Student {

    private String name;
    private int age;
    private int id;

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

}

