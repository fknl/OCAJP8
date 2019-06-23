package com.tuygun.sandbox.other.ocajp8.concurency;

public class DeadLockTask {
    private String name;

    public DeadLockTask(String name){
        this.name = name;
    }

    public synchronized void doStart(DeadLockTask task){
        System.out.printf("%s is starting.\n", this.name);
        task.doStop();
    }

    public synchronized void doStop(){
        System.out.printf("%s is stopping.\n", this.name);
    }

    public static void main(String[] args) {
        DeadLockTask lockedTask1 = new DeadLockTask("Thread-1");
        DeadLockTask lockedTask2 = new DeadLockTask("Thread-2");

        new Thread(() -> lockedTask1.doStart(lockedTask2)).start();
        new Thread(() -> lockedTask2.doStart(lockedTask1)).start();
    }
}
