package com.tuygun.sandbox.other.ocajp8.concurency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTests {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(2, ()->System.out.println("Threads released."));

        Runnable r1 = () -> {
            for(int i = 1; i <=2; i++){
                try{
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                }
                System.out.println("Thread-1 round: " + i);
            }
        };

        Runnable r2 = () -> {
            for(int i = 1; i <=2; i++){
                try{
                    Thread.sleep(1000);
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                }
                System.out.println("Thread-2 round: " + i);
            }
        };

        new Thread(r1).start();
        new Thread(r2).start();
    }
}
