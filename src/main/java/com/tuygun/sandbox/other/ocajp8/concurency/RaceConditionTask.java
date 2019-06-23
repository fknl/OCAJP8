package com.tuygun.sandbox.other.ocajp8.concurency;

public class RaceConditionTask {
    private int count = 0;

    //it can be resolve by adding synchronized to increaseCount method.
    public void increaseCount(){
        for(int i = 0; i < 1000000; i++){
            count = count + 1;
        }
    }

    public int getCount(){
        return count;
    }

    public static void main(String[] args) {
        RaceConditionTask task = new RaceConditionTask();

        new Thread(()->task.increaseCount()).start();
        new Thread(()->task.increaseCount()).start();

        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){}
        System.out.println("Count: " + task.getCount());
    }
}
