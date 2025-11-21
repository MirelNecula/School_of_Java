package org.example;

import java.util.concurrent.CompletableFuture;

public class ex17 {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> "Step 1: fetch data")
                .thenApply(s -> s + " -> Step 2: transform")
                .thenAccept(s -> System.out.println(s + " -> Step 3: print"))
                .join();
    }
}
