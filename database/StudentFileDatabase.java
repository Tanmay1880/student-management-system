package database;

import model.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class StudentFileDatabase {

  public  void saveStudents(ArrayList<Student> students){

      try{
          FileWriter writer = new FileWriter("students.txt");

          for(Student i : students){
              String line = i.getId()+","+ i.getName()+","+i.getAge();
              writer.write(line+"\n");

      }
          writer.close();
      }
      catch (IOException e) {
          e.printStackTrace();
      }


    }

  public ArrayList<Student> loadStudents(){
      ArrayList<Student> list = new ArrayList<>();
      try {
          BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
          String line ;
          while ((line = reader.readLine()) != null){

              String[] parts =line.split(",");
              int id = Integer.parseInt(parts[0]);
              String name = parts[1];
              int age = Integer.parseInt(parts[2]);
              Student s = new Student(name, age, id);
              list.add(s);

          }

          reader.close();

      } catch (IOException e) {
          // it means file is not created
      }
      return list;
    }
}
