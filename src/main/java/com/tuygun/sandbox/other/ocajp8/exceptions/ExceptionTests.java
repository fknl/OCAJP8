package com.tuygun.sandbox.other.ocajp8.exceptions;

public class ExceptionTests {
    public static void main(String[] args) {
        try {
            testTryWithResources();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void testTryWithResources() {
        try(AResource resourceA = new AResource("A");
            AResource resourceB = new AResource("B");
            AResource resourceC = new AResource("C")){

            resourceA.use();
            resourceB.use();
            resourceC.use();

            throw new Exception("Exception thrown in try block");
        } catch (Exception e){
            boolean a = true;
            if(a){
                throw new RuntimeException("RuntimeException thrown in catch block", e);
            }
        }
        System.out.println("After try with resources");
    }
    /*
    Resource(A) has opened.
    Resource(B) has opened.
    Resource(C) has opened.
    A is being used.
    B is being used.
    C is being used.
    Resource(C) has been closed.
    Resource(B) has been closed.
    Resource(A) has been closed.
    java.lang.RuntimeException: RuntimeException thrown in catch block
    at com.tuygun.sandbox.other.exceptions.ExceptionTests.testTryWithResources(ExceptionTests.java:25)
    at com.tuygun.sandbox.other.exceptions.ExceptionTests.main(ExceptionTests.java:6)
    Caused by: java.lang.Exception: Exception thrown in try block
    at com.tuygun.sandbox.other.exceptions.ExceptionTests.testTryWithResources(ExceptionTests.java:21)
            ... 1 more
    Suppressed: java.lang.Exception: Exception thrown in resource(C) close method.
    at com.tuygun.sandbox.other.exceptions.AResource.close(AResource.java:22)
    at com.tuygun.sandbox.other.exceptions.ExceptionTests.testTryWithResources(ExceptionTests.java:22)
            ... 1 more
    Suppressed: java.lang.Exception: Exception thrown in resource(B) close method.
    at com.tuygun.sandbox.other.exceptions.AResource.close(AResource.java:22)
    at com.tuygun.sandbox.other.exceptions.ExceptionTests.testTryWithResources(ExceptionTests.java:22)
            ... 1 more
    Suppressed: java.lang.Exception: Exception thrown in resource(A) close method.
    at com.tuygun.sandbox.other.exceptions.AResource.close(AResource.java:22)
    at com.tuygun.sandbox.other.exceptions.ExceptionTests.testTryWithResources(ExceptionTests.java:22)
            ... 1 more
    */
}
