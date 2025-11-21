package org.example;

public class ex1 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.print(i + " ");
            }
        });

        Thread t2 = new Thread(() -> {
            for (char c = 'A'; c <= 'J'; c++) {
                System.out.print(c + " ");
            }
        });

        t1.start();
        t2.start();
    }
}
