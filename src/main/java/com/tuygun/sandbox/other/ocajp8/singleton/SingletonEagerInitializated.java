package com.tuygun.sandbox.other.ocajp8.singleton;

public class SingletonEagerInitializated {
    private static final SingletonEagerInitializated INSTANCE = new SingletonEagerInitializated();

    private SingletonEagerInitializated(){}

    static int b = 1;
    static final int c;
    static {
        int a =11;
        System.out.println("a: " + a);
        System.out.println("b: " + b);
    }
    public static SingletonEagerInitializated getInstance(){

        return INSTANCE;
    }

    static {
        System.out.println("ASDAS");
        c =5;
    }

    static {
        int b = 55;
        System.out.println("b: " + b);
    }
}
