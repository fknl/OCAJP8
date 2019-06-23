package com.tuygun.sandbox.other.ocajp8.enums;
/*
    - All instance methods except toString are final, they cannot be overridden
 */
public enum GenderEnum {
    MALE {
        void print() {
            anotherPrint("I am a male!");
        }
    },
    FEMALE {
        void print() {
            anotherPrint("I am a female!");
        }
    };

    GenderEnum(){}

    private static void anotherPrint(String message) {
        System.out.println(message);
    }

    abstract void print();

    public void otherPrint(String message) {
        System.out.println(message);
    }
}
