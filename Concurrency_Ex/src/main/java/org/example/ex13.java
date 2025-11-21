package org.example;

import java.util.concurrent.*;
import java.util.*;

public class ex13 {
    static class Work implements Callable<String> {
        private final int id;
        Work(int id){ this.id=id; }
        public String call() throws Exception { Thread.sleep(200); return "R"+id+" from "+Thread.currentThread().getName(); }
    }
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        List<Callable<String>> jobs = new ArrayList<>();
        for (int i=1;i<=5;i++) jobs.add(new Work(i));
        List<Future<String>> res = pool.invokeAll(jobs);
        for (Future<String> f : res) System.out.println(f.get());
        pool.shutdown();
    }
}
