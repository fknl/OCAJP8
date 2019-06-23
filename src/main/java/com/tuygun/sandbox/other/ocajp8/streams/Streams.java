package com.tuygun.sandbox.other.ocajp8.streams;

import com.tuygun.sandbox.other.ocajp8.compare.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {
    public static void main(String[] args) {
        //testFilter();
        //testForeach();
        //print10RandomNumberSortedBetween0And100();
        //testMap();
        //testFindMethods();
        //testMatchMethods();
        //testMinMaxMethods();
        //testReduceMethod();
        //checkPalyndrom("abcba");
        //calculateFactorial(13);
        //testSortMethods();
        //testParallelStreams();
    }

    private static void testParallelStreams() {
        Stream<String> charStream = Stream.of("S", "E", "R", "H", "A", "N", " ", "T", "U", "Y", "G", "U", "N");
        Stream<String> parallel = charStream.parallel();
        System.out.println(parallel.reduce("", String::concat, (i, j) -> i +j));//SERHAN TUYGUN

        Stream<Integer> stream = Arrays.asList(1,2,3,4,5,6,7,8).parallelStream();
        ConcurrentMap<Boolean, List<Integer>> collect = stream.collect(Collectors.groupingByConcurrent(i -> i % 2 == 0));
        collect.forEach((i, j)->{
            System.out.println(i);
            j.forEach(a->System.out.print(a + " "));
            System.out.println();
        });
        //false
        //3 1 7 5
        //true
        //4 2 8 6

        Stream<String> stringStream = Arrays.asList("oracle", "java").parallelStream();
        ConcurrentMap<String, Integer> collect1 = stringStream.collect(Collectors.toConcurrentMap(String::toUpperCase, String::length));
        collect1.forEach((i, j)-> System.out.println(i + " " + j));
        //JAVA 4
        //ORACLE 6
    }

    private static void testSortMethods() {
        Employee e1 = new Employee("serhan", "tuygun", 38);
        Employee e2 = new Employee("ufuk", "unlu", 39);
        Employee e3 = new Employee("mustafa", "karakaya", 36);
        Employee e4 = new Employee("yetkin", "karis", 33);
        Employee e5 = new Employee("dogukan", "zengin", 31);

        List<Employee> employeeList = Stream.of(e1, e2, e3, e4, e5).collect(Collectors.toList());
        //Sort by name which is implemented in Employee class
        System.out.println("Sort by 'name'");
        employeeList.stream().sorted().forEach(System.out::println);
        System.out.println();
        //Sort by surname
        System.out.println("Sort by 'surname'");
        employeeList.stream().sorted(Comparator.comparing(Employee::getSurname)).forEach(System.out::println);
        System.out.println();
        //Sort by age
        System.out.println("Sort by 'age'");
        employeeList.stream().sorted((x, y) -> x.getAge() - y.getAge() ).forEach(System.out::println);
    }

    private static void calculateFactorial(int i) {
        IntStream stream = IntStream.rangeClosed(1, i);
        System.out.println(i + " factorial is: " + stream.reduce(1, (a, b) -> a * b));
    }

    private static void checkPalyndrom(String str) {
        String temp = str.toLowerCase();
        boolean result = IntStream.range(0, str.length() / 2)
                .allMatch(i -> temp.charAt(i) == temp.charAt(temp.length() - i - 1));
        System.out.println(str + " is " + (result ? " palyndrom" : "not palyndrom"));
    }

    private static void testReduceMethod() {
        Stream<Integer> numStream = Stream.of(3,2,5,1,4);
        //reduce returns an optional
        numStream.reduce(Integer::sum).ifPresent(System.out::println);//15

        Stream<Integer> numStream2 = Stream.of(1,2,3,4,5);
        System.out.println(numStream2.reduce(4, (x,y)->x+y));//19

        Stream<String> letters = Stream.of("N", "A", "H", "R", "E", "S");
        letters.reduce((x, y)-> y + x).ifPresent(System.out::println);//SERHAN
    }

    private static void testMinMaxMethods() {
        Supplier<Integer> supplier = () -> ((int) (Math.random() * 20));

        System.out.println("--------Test .anyMatch--------");
        List<Integer> numList1 = Stream.generate(supplier).limit(10).sorted().peek(System.out::println).collect(Collectors.toList());
        System.out.println("Number of list: " + numList1.stream().count());

        /*
            stream.min | max requires comparator
            stream.mapTo<Primitive>.min | max does not require compartor
            average only available for <Primitive>Streams
         */

        //Number of list: 10
        numList1.stream().mapToInt((x)->x.intValue()).min().ifPresent((x) -> System.out.println("Min of numbers: " + x));
        //Min of numbers: 1
        numList1.stream().max(Comparator.naturalOrder()).ifPresent((x) -> System.out.println("Max of numbers: " + x));
        //Max of numbers: 19
        numList1.stream().mapToInt((x)->x.intValue()).average().ifPresent((x) -> System.out.println("Average of numbers: " + x));
        //Average of numbers: 7.4
        System.out.println("Sum of numbers: " + numList1.stream().mapToInt((x)->x.intValue()).sum());
        //Sum of numbers: 74
    }

    private static void testMatchMethods() {
        Supplier<Integer> supplier = () -> ((int) (Math.random() * 20));

        System.out.println("--------Test .anyMatch--------");
        List<Integer> numList1 = Stream.generate(supplier).limit(10).sorted().peek(System.out::println).collect(Collectors.toList());
        Predicate<Integer> predicateFive = (x) -> x == 5;
        System.out.println("Five is found: " + numList1.stream().anyMatch(predicateFive));
        //Five is found: false

        System.out.println("--------Test .allMatch--------");
        List<Integer> numList2 = Stream.generate(supplier).limit(10).peek(System.out::println).collect(Collectors.toList());
        Predicate<Integer> between0And20 = (x) -> x >=0 && x <=20;
        System.out.println("The numbers between [0,20]: " + numList2.stream().allMatch(between0And20));
        //The numbers between [0,20]: true

        System.out.println("--------Test .noneMatch--------");
        List<Integer> numList3 = Stream.generate(supplier).limit(10).peek(System.out::println).collect(Collectors.toList());
        Predicate<Integer> outSideOf0And20 = (x) -> x > 20 || x < 0;
        System.out.println("All the numbers inside the range[0,20]: " + numList3.stream().noneMatch(outSideOf0And20));
        //All the numbers inside the range[0,20]: true
    }

    private static void testFindMethods() {
        Supplier<Integer> supplier = () -> ((int) (Math.random() * 20));

        List<Integer> numList1 = Stream.generate(supplier).limit(10).sorted().peek(System.out::println).collect(Collectors.toList());
        Optional<Integer> first = numList1.parallelStream().findFirst();
        System.out.println(first.isPresent() ? "First number is " + first.get() : "the first number is not found");
        //First number is 0

        List<Integer> numList2 = Stream.generate(supplier).limit(10).peek(System.out::println).collect(Collectors.toList());
        Optional<Integer> any = numList2.parallelStream().findAny();
        System.out.println(any.isPresent() ? "Any number is " + any.get() : "the any number is not found");
        //Any number is 9
    }

    private static void testMap() {
        Person p1 = new Person("serhan", "tuygun", 38);
        Person p2 = new Person("ufuk", "unlu", 39);
        List<Person> people = Arrays.asList(p1, p2);
        people.stream().map(p->p.getName()).forEach(System.out::print);//serhanufuk
        System.out.println();
        people.stream().mapToInt(p->p.getAge()).forEach(System.out::print);//3839
    }

    private static void print10RandomNumberSortedBetween0And100() {
        Stream.generate(() -> (int) (Math.random() * 100)).limit(10).sorted().peek(System.out::println).forEach((p) -> {
        });
    }

    private static void testForeach() {
        List<String> letters = Stream.of("e", "c", "b", "d", "a").collect(Collectors.toList());
        letters.stream().sorted().map(String::toUpperCase).forEachOrdered(System.out::print);//ABCDE

    }

    private static void testFilter() {
        List<Integer> integers = Arrays.asList(-3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> evenNumbers = integers.stream().filter((i) -> i % 2 == 0).collect(Collectors.toList());
        evenNumbers.forEach(System.out::println);//prints -2 0 4 6 8

        integers.stream().filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 0;
            }
        }).map(i -> i + "").forEach((j) -> System.out.print(j + ", "));//1, 2, 3, 4, 5, 6, 7, 8, 9,
    }

    private static class Person{
        private final String name;
        private final String surname;
        private final int age;

        public Person(String name, String surname, int age) {
            this.name = name;
            this.surname = surname;
            this.age = age;
        }

        public String getName(){
            return this.name;
        }

        public String getSurname(){
            return this.surname;
        }

        public int getAge(){
            return this.age;
        }
    }
}
