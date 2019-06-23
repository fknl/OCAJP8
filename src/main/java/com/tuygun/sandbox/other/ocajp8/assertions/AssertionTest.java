package com.tuygun.sandbox.other.ocajp8.assertions;

import java.util.function.IntPredicate;

public class AssertionTest {
    private static final IntPredicate POSITIVE_NUM_PREDICATE = a -> a > 0;

    public static void main(String[] args) {
        System.out.println(squareRoot(-1));
    }

    private static double squareRoot(int i) {
        if(i > 0){
            return Math.sqrt(i);
        }
        //to enable assert at runtime -ea should be passed to vm arguments
        //it gives AssertionError which could not be catched.
        assert POSITIVE_NUM_PREDICATE.test(i) : "parameter i cannot be negative";


        return -1;
    }
}
