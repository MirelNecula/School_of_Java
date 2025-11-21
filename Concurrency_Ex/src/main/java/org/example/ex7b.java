package org.example;

public class ex7b {
    static final Object lock1 = new Object();
    static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1: locked lock1");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (lock2) {
                    System.out.println("Thread 1: locked lock2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock1) { // aceeași ordine ca Thread 1
                System.out.println("Thread 2: locked lock1");
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (lock2) {
                    System.out.println("Thread 2: locked lock2");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
