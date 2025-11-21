package org.example;

import java.util.concurrent.CompletableFuture;

public class ex16 {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            return "Hello from CompletableFuture";
        });

        future.thenAccept(System.out::println).join();
    }
}
