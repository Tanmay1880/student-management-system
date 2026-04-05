package model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Course other = (Course) obj;

         return this.name.equalsIgnoreCase(other.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name.toLowerCase());
    }
}
