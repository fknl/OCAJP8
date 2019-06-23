package com.tuygun.sandbox.other.ocajp8.concurency;

public class StarvationTask {
    public synchronized void printThreadName(){
        int count = 1000;
        while (count > 0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            count--;
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        //One of the thread will take the task and not release for a long time.
        //Hence, the other thread will be starving
        StarvationTask task = new StarvationTask();
        new Thread(()->task.printThreadName(), "Thread-1").start();
        new Thread(()->task.printThreadName(), "Thread-2").start();
    }
}
