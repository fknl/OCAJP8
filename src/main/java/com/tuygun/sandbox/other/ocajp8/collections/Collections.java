package com.tuygun.sandbox.other.ocajp8.collections;

import com.tuygun.sandbox.other.ocajp8.lambdas.MapEntryPrinter;

import java.util.*;

public class Collections {
    public static void main(String[] args) {
        //testArrayList();
        //testTreeSet();
        //testTreeMap();
        //testArrayDeque();
    }

    private static void testTreeSet() {
        List<Integer> numList = Arrays.asList(3, 7, 1, 5, 6, 2);
        TreeSet<Integer> treeSet = new TreeSet<>(numList);
        treeSet.forEach(System.out::println);
    }

    private static void testArrayList(){
        ArrayList<Integer> arrayList = new ArrayList<>(15);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);

        arrayList.forEach(System.out::println);
        System.out.println("---------------");

        arrayList.add(2, 33);
        arrayList.stream().forEach(System.out::println);
        System.out.println("---------------");

        System.out.println("ArrayList size: " + arrayList.size());
    }

    private static void testTreeMap() {
        TreeMap<Integer, String> intStrTreeMap = new TreeMap<>((a,b)-> a.compareTo(b));
        intStrTreeMap.put(3, "C");
        intStrTreeMap.put(2, "B");
        intStrTreeMap.entrySet().forEach((a)-> System.out.println(a.getKey() + ": " + a.getValue()));
        System.out.println("------------");

        MapEntryPrinter mePrinter = (a -> System.out.println("key: " + a.getKey() + ", value: " + a.getValue()));
        mePrinter.printMapEntry(intStrTreeMap.ceilingEntry(1));
        System.out.println("------------");

        System.out.println(intStrTreeMap.replace(2, "D") + " replaced by D");
        intStrTreeMap.entrySet().forEach(System.out::println);
        System.out.println("------------");

        if(intStrTreeMap.remove(2,"D")) {
            System.out.println("key: 2, value: D has been removed.");
            intStrTreeMap.entrySet().forEach(System.out::println);
            System.out.println("------------");
        }

        System.out.println("Removing key: 2-> removed value= " + intStrTreeMap.remove(2));


    }

    private static void testArrayDeque() {
        ArrayDeque<String> letters = new ArrayDeque<>();
        letters.add("A");
        letters.add("C");
        letters.add("B");
        letters.addFirst("0");
        letters.addLast("9");
        letters.forEach(System.out::println);

        System.out.println("-----------");
        letters.pollLast();
        letters.forEach(System.out::println);

        System.out.println("-----------");
        System.out.println(letters.element());

        System.out.println("-----------");
        letters.offerFirst("-1");
        letters.forEach(System.out::println);
    }
}
