package com.tuygun.sandbox.other.ocajp8.interfaces;

public class AnInterfaceImplementorClass implements ASubInterface {
//    @Override
//    public void method1() {
//        System.out.println("method1 concrete implementation.");
//    }

    @Override
    public void method2(){
        System.out.println("I am overriding the method2 in concrete class and A=" + A);
    }
}
