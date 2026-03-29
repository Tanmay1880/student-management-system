package model;

public class Student {

    private String name;
    private int age;
    private int id;

   public Student(String name,int age ,int id){
        this.name=name;
        this.age=age;
        this.id=id;
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

    // setter


    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

