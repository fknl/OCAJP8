package com.tuygun.sandbox.other.ocajp8.interfaces;

@FunctionalInterface
public interface ASubInterface extends AnInterface {
    default void method1(){
        System.out.println("method4 is overridden by sub interface ");
    }

    void method2();
}
