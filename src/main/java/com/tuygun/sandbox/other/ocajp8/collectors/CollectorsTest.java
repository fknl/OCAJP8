package com.tuygun.sandbox.other.ocajp8.collectors;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsTest {
    public static void main(String[] args) {
        List<String> letterList = Arrays.asList("A", "B", "D", "A", "Z", "C", "D", "E", "K", "F", "G", "H", "Y");
        letterList.forEach(a-> System.out.print(a + ','));

        System.out.println("-------Collect As Set-------");
        Set<String> letterSet = letterList.stream().collect(Collectors.toSet());
        letterSet.forEach(a-> System.out.print(a + ','));

        System.out.println("-------Collect As LinkedList-------");
        LinkedList<String> letterLinkedList = letterList.stream().collect(Collectors.toCollection(() -> new LinkedList<>()));
        letterLinkedList.forEach((a)-> System.out.print(a + ","));

        System.out.println("--------------");
        Citizen c1 = new Citizen(1, "serhan", "tuygun", Citizen.Gender.MALE, 38);
        Citizen c2 = new Citizen(2, "ufuk", "unlu", Citizen.Gender.MALE, 39);
        Citizen c3 = new Citizen(3, "mehmet", "unlu", Citizen.Gender.MALE, 34);
        Citizen c4 = new Citizen(4, "hulya", "avsar", Citizen.Gender.FEMALE, 50);
        Citizen c5 = new Citizen(5, "gulben", "ergen", Citizen.Gender.FEMALE, 45);
        Stream<Citizen> citizenStream = Stream.of(c1, c2, c3, c4, c5);
        Map<Integer, Citizen> citizenMap = citizenStream.collect(Collectors.toMap(Citizen::getId, a -> a));
        citizenMap.forEach((x, y) -> System.out.println("Id: " + x + ", Full Name: " + y.getName() + " " + y.getSurname()));

        System.out.println("--------------");
        Stream<Citizen> citizenStream2 = Stream.of(c1, c2, c3, c4, c5);
        Map<Citizen.Gender, List<Citizen>> groupByGender = citizenStream2.collect(Collectors.groupingBy(Citizen::getGender));
        groupByGender.forEach((gender, list) -> {
            System.out.println(gender.name());
            list.forEach(c -> {
                System.out.println("Full Name:" + c.getName() + c.getSurname());
            });
        });

        System.out.println("--------------");
        Stream<Citizen> citizenStream3 = Stream.of(c1, c2, c3, c4, c5);
        Map<Citizen.Gender, Double> ageAvgByGender = citizenStream3.collect(Collectors.groupingBy(Citizen::getGender, Collectors.averagingDouble(Citizen::getAge)));
        ageAvgByGender.forEach((x,y) -> System.out.println(x.name() + " " + y));

        System.out.println("--------------");
        Stream<Citizen> citizenStream4 = Stream.of(c1, c2, c3, c4, c5);
        Map<Boolean, List<Citizen>> isMaleMap = citizenStream4.collect(Collectors.partitioningBy(Citizen::isMale));
        isMaleMap.forEach((isMale, list) -> {
            System.out.println(isMale ? "Male:" : "Female:");
            list.forEach(c -> {
                System.out.println("Full Name:" + c.getName() + c.getSurname());
            });
        });

        System.out.println("--------------");
        Stream<Citizen> citizenStream5 = Stream.of(c1, c2, c3, c4, c5);
        Map<Boolean, Double> isMaleAverageAgeMap = citizenStream5.collect(Collectors.partitioningBy(Citizen::isMale, Collectors.averagingDouble(Citizen::getAge)));
        isMaleAverageAgeMap.forEach((x,y) -> System.out.println((x ? "Male" : "Female") + " average age is: " + y));
    }

    static class Citizen{
        private final int id;
        private final String name;
        private final String surname;
        private final Gender gender;
        private final int age;

        public Citizen(int id, String name, String surname, Gender gender, int age){
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.gender = gender;
            this.age = age;
        }

        public int getId(){
            return this.id;
        }

        public String getName(){
            return this.name;
        }

        public String getSurname(){
            return this.surname;
        }

        public Gender getGender(){
            return this.gender;
        }

        public int getAge(){
            return this.age;
        }

        public boolean isMale(){
            return gender == Gender.MALE;
        }

        enum Gender {MALE, FEMALE};
    }
}
