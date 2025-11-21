package org.example;

public class ex3 {
    public static void main(String[] args) {
        for (int i = 1; i <= 3; i++) {
            Thread t = new Thread(() -> {
                System.out.println("Running thread: " + Thread.currentThread().getName());
            });
            t.setName("CustomThread-" + i);
            t.start();
        }
    }
}
