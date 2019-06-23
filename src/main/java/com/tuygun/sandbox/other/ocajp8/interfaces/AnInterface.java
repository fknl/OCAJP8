package com.tuygun.sandbox.other.ocajp8.interfaces;

@FunctionalInterface
public interface AnInterface {
    //interface fields are (public & static & final) by default;
    public static final int A = 5;

    public abstract void method1();

    //protected | private void method5(); // not allowed

    default void method2(){
        System.out.println("this is default method method5");
        method4();
    }

    public default void method3(){
        System.out.println("this is default method method6");
        method4();
    }

    static void method4(){
        System.out.println("this is static method method7");
    }

    //object methods are not counted
    @Override
    public String toString();
}
