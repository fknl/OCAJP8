package com.tuygun.sandbox.other.ocajp8.concurency;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ConcurencyTests {
    public static void main(String[] args) throws Exception {
        //testRunnableWithExecutors();
        //testCallableWithExecutorService();
        //testInvokeAnyCallable();//working with Callables only not Runnables
        //testInvokeAllCallable();//working with Callables only not Runnables
        //testAtomicValues();
        //testCopyOnArrayList();
    }

    private static void testCopyOnArrayList() {
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("value-1");
        list.add("value-2");
        Iterator<String> iterator = list.iterator();
        list.add("value-3");
        list.remove(0);
        while(iterator.hasNext()){
            //prints value-1 and value-2
            System.out.println(iterator.next());//if it was ArrayList, this line would throw ConcurrentModificationException
        }
        Iterator<String> iterator2 = list.iterator();
        while(iterator2.hasNext()){
            //prints value-2 and value-3
            System.out.println(iterator2.next());
        }
    }

    private static void testAtomicValues() {
        AtomicReference<String> atomicReference = new AtomicReference<>("INITIAL_VALUE");
        atomicReference.compareAndSet("INIT_VALUE", "NEW_VALUE");
        System.out.println(atomicReference.get());
        atomicReference.compareAndSet("INITIAL_VALUE", "NEW_VALUE");
        System.out.println(atomicReference.get());

        AtomicInteger integer = new AtomicInteger(10);
        int prevValue = integer.getAndSet(100);
        System.out.println("prevValue: " + prevValue + ", currValue: "+ integer.get());

        AtomicInteger anotherInteger = new AtomicInteger(0);
        int int1 = anotherInteger.updateAndGet(i -> i + 10);
        System.out.println(int1);//10
        //15 will be assigned to i2, on the other hand current value will be assinged to i1
        int int2 = anotherInteger.accumulateAndGet(15, (i1, i2) -> i1 + i2);
        System.out.println(int2);//25
    }

    private static void testInvokeAllCallable() throws InterruptedException {
        Callable<String> c1 = () -> "Callable-1";
        Callable<String> c2 = () -> "Callable-2";

        List<Callable<String>> tasks = Arrays.asList(c1, c2);

        ExecutorService executorService = Executors.newCachedThreadPool();
        //the order of the future result will be same with the task list
        List<Future<String>> futures = executorService.invokeAll(tasks);
        futures.forEach(i-> {
            try {
                System.out.println(i.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }

    private static void testInvokeAnyCallable() throws ExecutionException, InterruptedException {
        Callable<String> c1 = () -> "Callable-1";
        Callable<String> c2 = () -> "Callable-2";

        List<Callable<String>> tasks = Arrays.asList(c1, c2);

        ExecutorService executorService = Executors.newCachedThreadPool();
        String s = executorService.invokeAny(tasks);
        System.out.println("InvokeAny Result: " + s);
        executorService.shutdown();
    }

    private static void testCallableWithExecutorService() throws ExecutionException, InterruptedException {
        Callable<Integer> c = () -> {
            int result = 0;
            for(int i = 1; i < 1000000; i++){
                result += i;
            }
            return result;
        };

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> submit = executorService.submit(c);
        //If get method called, the main method will wait for the result
        Integer result = submit.get();
        System.out.println("Result from callable: " + result);
        executorService.shutdown();
    }

    private static void testRunnableWithExecutors() {
        Runnable r = () -> {
            int result = 0;
            for(int i = 1; i < 1000000; i++){
                result += i;
            }
            System.out.println("Result from Runnable: " + result);
        };

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> submit = executorService.submit(r);
        executorService.shutdown();
    }
}
