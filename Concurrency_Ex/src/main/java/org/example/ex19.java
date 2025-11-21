package org.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ex19 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            return "API 1 result";
        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
            return "API 2 result";
        });

        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(800); } catch (InterruptedException ignored) {}
            return "API 3 result";
        });

        CompletableFuture.anyOf(f1, f2, f3).thenAccept(r -> System.out.println("First completed: " + r)).join();
        CompletableFuture.allOf(f1, f2, f3).join();
        System.out.println("All completed: " + f1.get() + ", " + f2.get() + ", " + f3.get());
    }
}
