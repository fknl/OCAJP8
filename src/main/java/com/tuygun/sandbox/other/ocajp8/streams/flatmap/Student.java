package com.tuygun.sandbox.other.ocajp8.streams.flatmap;

public class Student {
    private String name;
    private int mark;

    public Student(String name, int mark){
        this.name = name;
        this.mark = mark;
    }

    public String getName(){
        return this.name;
    }

    public int getMark(){
        return this.mark;
    }
}
