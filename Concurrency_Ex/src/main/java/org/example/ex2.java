package org.example;

public class ex2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    System.out.print(i + " ");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                for (char c = 'A'; c <= 'J'; c++) {
                    System.out.print(c + " ");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nBoth threads finished.");
    }
}
