package com.tuygun.sandbox.other.ocajp8.singleton;

import com.tuygun.sandbox.other.ocajp8.lambdas.AutoPrint;
import com.tuygun.sandbox.other.ocajp8.lambdas.IntegerComparator;
import com.tuygun.sandbox.other.ocajp8.lambdas.Printable;

import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
//        SingletonEagerInitializated.getInstance();
//        NestedClass.A.Aa aa = new NestedClass().new A().new Aa();
//        NestedClass.B b = new NestedClass.B();
//
//        AbstractClass test = new AbstractClass() {
//            public void print() {
//                System.out.println("test");
//            }
//        };
//        test.print();
//        System.out.println("----------------------");

//
//        GenderEnum.MALE.print();
//        GenderEnum.MALE.otherPrint("test");
//        GenderEnum.FEMALE.print();
//        System.out.println("----------------------");


//        AnInterfaceImplementorClass clazz = new AnInterfaceImplementorClass();
//        clazz.method1();
//        clazz.method2();
//        clazz.method3();
//        System.out.println("----------------------");


        Printable printable = (s) -> System.out.println(s);
        printable.print("Printable test");
        print().autoPrint();

        IntegerComparator integerComparator = (i1, i2) -> i1 == i2;
        System.out.println(integerComparator.equals(1,1));

        Supplier<Stream<Integer>> supplier = () -> Stream.of(1, 2, 3, 4);
        supplier.get().forEach(System.out::println);
        System.out.println("Doubled");
        supplier.get().forEach(i -> System.out.println(i*2));
    }

    public static AutoPrint print(){
        return () -> System.out.println("serhan");
    }
}
