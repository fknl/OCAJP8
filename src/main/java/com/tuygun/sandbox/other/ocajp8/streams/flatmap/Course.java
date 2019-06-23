package com.tuygun.sandbox.other.ocajp8.streams.flatmap;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String name;
    private List<Student> studentList = new ArrayList<>();

    public Course(String name){
        this.name = name;
    }

    public void addStudent(Student student){
        studentList.add(student);
    }

    public List<Student> getStudentList(){
        return this.studentList;
    }

    public String getName(){
        return this.name;
    }
}
