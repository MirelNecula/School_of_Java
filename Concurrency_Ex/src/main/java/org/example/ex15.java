package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ex15 {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        for (int i = 1; i <= 10; i++) {
            int id = i;
            pool.submit(() -> {
                System.out.println("Task " + id + " started by " + Thread.currentThread().getName());
                try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
                System.out.println("Task " + id + " finished by " + Thread.currentThread().getName());
            });
        }
        pool.shutdown();
    }
}
