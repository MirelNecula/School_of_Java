package org.example;

import java.util.concurrent.*;

public class ex11 {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i=1;i<=6;i++){
            int id=i;
            pool.submit(() -> { System.out.println("Task "+id+" on "+Thread.currentThread().getName()); try{Thread.sleep(300);}catch(Exception ignored){} });
        }
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Done");
    }
}
