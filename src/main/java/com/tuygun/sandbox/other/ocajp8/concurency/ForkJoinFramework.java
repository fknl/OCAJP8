package com.tuygun.sandbox.other.ocajp8.concurency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinFramework {
    static class NSumTask extends RecursiveTask<Integer>{
        List<Integer> integerList;

        public NSumTask(List<Integer> integerList){
            this.integerList = integerList;
        }

        @Override
        protected Integer compute() {
            if(integerList.size() < 100){
                return integerList.stream().mapToInt(i->i).sum();
            }

            NSumTask task1 = new NSumTask(integerList.subList(0, integerList.size() / 2));
            NSumTask task2 = new NSumTask(integerList.subList(integerList.size() / 2, integerList.size()));

            task1.fork();
            task2.fork();

            return task1.join() + task2.join();
        }
    }

    public static void main(String[] args) {
        List<Integer> numList = new ArrayList<>();
        for(int i = 0; i < 100000; i++){
            numList.add(i);
        }

        NSumTask sumTask = new NSumTask(numList);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer sum = forkJoinPool.invoke(sumTask);
        System.out.println(sum);
    }
}
