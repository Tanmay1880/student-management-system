package model;

public class Course {
    private String name;

    public Course(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid course name");
        }
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }
}
