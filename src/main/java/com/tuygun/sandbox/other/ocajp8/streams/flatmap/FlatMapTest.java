package com.tuygun.sandbox.other.ocajp8.streams.flatmap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FlatMapTest {
    public static void main(String[] args) {
        Student serhan = new Student("Serhan", 88);
        Student ufuk = new Student("Ufuk", 99);
        Student mehmet = new Student("Mehmet", 100);
        Student mustafa = new Student("Mustafa", 65);

        Course math = new Course("Math");
        Course bio = new Course("Biology");

        math.addStudent(mehmet);
        math.addStudent(mustafa);

        bio.addStudent(ufuk);
        bio.addStudent(serhan);

        List<Course> courses = Arrays.asList(math, bio);
        System.out.println("----Students----");
        //flatMap takes <T> returns Stream<R>
        Stream<Student> studentStream = courses.stream().flatMap(c -> c.getStudentList().stream());
        studentStream.forEach(student -> System.out.println(student.getName()));
        //Mehmet
        //Mustafa
        //Ufuk
        //Serhan

        System.out.println("----Marks----");
        //flatMapTo<Primitive> takes <T> returns <Primitive>Stream
        IntStream markStream = courses.stream().
                flatMapToInt(course -> course.getStudentList().stream().mapToInt(Student::getMark));
        markStream.forEach(System.out::println);
        //100
        //65
        //99
        //88
    }
}