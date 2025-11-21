package org.example;

import java.util.*;
import java.util.concurrent.*;

public class ex27 {
    public static void main(String[] args) throws InterruptedException {
        Map<Integer,Integer> hashMap = new HashMap<>();
        Map<Integer,Integer> concurrentMap = new ConcurrentHashMap<>();

        Runnable task1 = () -> { for (int i=0;i<1000;i++) hashMap.put(i,i); };
        Runnable task2 = () -> { for (int i=0;i<1000;i++) concurrentMap.put(i,i); };

        Thread t1 = new Thread(task1); Thread t2 = new Thread(task1);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("HashMap size (likely inconsistent): " + hashMap.size());

        Thread c1 = new Thread(task2); Thread c2 = new Thread(task2);
        c1.start(); c2.start(); c1.join(); c2.join();
        System.out.println("ConcurrentHashMap size: " + concurrentMap.size());
    }
}
