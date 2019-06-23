package com.tuygun.sandbox.other.ocajp8.nested;

public class AnotherNestedClass {
    public AnotherNestedClass(){
        NestedClass.A a = new NestedClass().new A();
        NestedClass.B b = new NestedClass.B();
        NestedClass.C c = new NestedClass.C();
        NestedClass.D d = new NestedClass.D();
    }
}
