package com.tuygun.sandbox.other.ocajp8.primitives;

//------------Funtinoal Interface------------
//-Predicate<T>
//	*boolean test(T t)
//  *default methods:
//      -default Predicate<T> and(Predicate<? super T> other)
//      -default Predicate<T> negate()
//      -default Predicate<T> or(Predicate<? super T> other)
//  *static methods:
//      -static <T> Predicate<T> isEqual(Object targetRef)
//
//-Consumer<T>
//	*void accept(T t)
//  *default methods:
//      -default Consumer<T> andThen(Consumer<? super T> after)
//
//-Supplier<T>
//	*T get()
//
//-Function<T, R>
//	*R apply(T t)
//  *default methods:
//      -default <V> Function<V, R> compose(Function<? super V, ? extends T> before)
//      -default <V> Function<T, V> andThen(Function<? super R, ? extends V> after)
//  *static methods:
//      -static <T> Function<T, T> identity()
//
//
//        ------------Primitive Functional Interface------------
//        IntPredicate
//        DoublePredicate
//        LongPredicate
//          *boolean test(x value)
//          *default methods:
//              -and
//              -or
//              -negate
//
//        IntConsumer
//        DoubleConsumer
//        LongConsumer
//          *void accept(x value)
//          *default methods:
//              -andThen
//
//        IntSupplier
//        DoubleSupplier
//        LongSupplier
//        BooleanSupplier
//          *x getAsX()
//
//        IntFunction<R>
//        DoubleFunction<R>
//        LongFunction<R>
//	        R apply(x value)
//
//        ToIntFunction<T>
//        ToDoubleFunction<T>
//        ToLongFunction<T>
//	        *x applyAsX(T value);
//
//        XToYFunction x 6
//          *Y applyAsY(x value)

import com.sun.javafx.image.IntPixelGetter;

import java.util.function.*;

public class PrimitiveFunctionalInterfaces {
    public static void main(String[] args) {
        //testPredicate(9);
        //testConsumer();
        //testSupplier();
        //testFunction();
        //testPrimitivePredicate();
        //testFunction2();
    }

    private static void testFunction2() {
        Function<Integer, Function<Integer, Integer>> sum = x -> y -> x + y;
        Function<Integer, Integer> plus10 = sum.apply(10);
        System.out.println(plus10.apply(5));
    }

    private static void testPrimitivePredicate() {
//        IntPredicate isInteger = (x) -> x instanceof Integer;
//        Integer four = new Integer(4);
//        System.out.println(isInteger.test(four));
    }

    private static void testFunction() {
        Function<Integer, String> intToStr = (x) -> x + "";
        Function<String, Integer> strToInt = (x) -> Integer.parseInt(x);
        Predicate<Object> isInt = (x) -> {
            try{
                Integer y = (Integer)x;
                return true;
            } catch (Exception e){
                return false;
            }
        };

        System.out.println("Am I still integer? " + isInt.test(intToStr.andThen(strToInt).apply(8)));
        //Am I still integer? true

        //Identity Test
        Function<Object, Object> identity = Function.identity();
        Integer five = new Integer(5);
        Object output = identity.apply(five);
        System.out.println(five == output);//true
    }

    private static void testSupplier() {
        //Supplier abstract method -> T get();
        Supplier<Integer> intSupplier = () -> (int)(Math.random() * 100);
        System.out.println("An integer: " + intSupplier.get());
        System.out.println("Another integer: " + intSupplier.get());
    }

    private static void testConsumer() {
        //Consumer abstract method -> void accept(T t);
        Consumer<Integer> getDouble = x -> System.out.println(x * 2);
        Consumer<Integer> getHalf = x -> System.out.println(x / 4);
        Integer eight = new Integer(8);
        getDouble.andThen(getHalf).accept(eight);//16 first and then 2
    }

    private static void testPredicate(int arg) {
        //Predicate abstract method -> boolean test(T t);
        //returns true if 0 <= arg >=10
        Predicate<Integer> isGreaterThenZero = (x) -> x > 0;
        System.out.println(isGreaterThenZero.test(4));//true
        Predicate<Integer> isLessThen10 = (x) -> x < 10;
        System.out.println(isGreaterThenZero.and(isLessThen10).negate().test(arg));//false
        Integer anInteger = new Integer(5);

        Predicate<Object> integerEqualityPredicate = Predicate.isEqual(anInteger);
        System.out.println(integerEqualityPredicate.test(6));//false

    }
}
