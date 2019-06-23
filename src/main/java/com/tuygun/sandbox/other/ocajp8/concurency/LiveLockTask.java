package com.tuygun.sandbox.other.ocajp8.concurency;

public class LiveLockTask {
    private volatile boolean value = false;

    private boolean getValue(){
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){}

        return value;
    }

    public void setSame(LiveLockTask task){
        while(value != task.getValue()){
            value =! value;
        }
    }

    public void setOpposite(LiveLockTask task){
        while(value == task.getValue()){
            value =! value;
        }
    }

    public static void main(String[] args) {
        LiveLockTask task1 = new LiveLockTask();
        LiveLockTask task2 = new LiveLockTask();

        new Thread(()-> task1.setOpposite(task2)).start();
        try{
            Thread.sleep(500);
        } catch (InterruptedException e){
        }
        new Thread(()-> task2.setSame(task1)).start();
    }
}
