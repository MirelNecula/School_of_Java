package org.example;

import java.util.concurrent.CompletableFuture;

public class ex20 {
    public static void main(String[] args) {
        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(() -> {
            if (true) throw new RuntimeException("Something went wrong");
            return 42;
        });

        f.exceptionally(e -> {
            System.out.println("Recovered from error: " + e.getMessage());
            return -1;
        }).thenAccept(System.out::println).join();
    }
}
