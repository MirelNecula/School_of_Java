package org.example;

import java.util.concurrent.*;
import java.util.*;

public class ex12 {
    static class Fact implements Callable<Long> {
        private final int n;
        Fact(int n){ this.n=n; }
        public Long call(){ long r=1; for(int i=2;i<=n;i++) r*=i; return r; }
    }
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        List<Future<Long>> fs = new ArrayList<>();
        for (int n : new int[]{5,6,7,8}) fs.add(pool.submit(new Fact(n)));
        for (Future<Long> f : fs) System.out.println(f.get());
        pool.shutdown();
    }
}
