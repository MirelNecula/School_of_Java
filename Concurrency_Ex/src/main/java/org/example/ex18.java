package org.example;

import java.util.concurrent.CompletableFuture;

public class ex18 {
    public static void main(String[] args) {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            return 10;
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(700); } catch (InterruptedException ignored) {}
            return 20;
        });

        CompletableFuture<Integer> combined = f1.thenCombine(f2, Integer::sum);
        System.out.println("Combined result: " + combined.join());
    }
}
