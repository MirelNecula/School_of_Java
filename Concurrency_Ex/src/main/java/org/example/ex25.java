package org.example;

import java.util.concurrent.CountDownLatch;

public class ex25 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        for (int i = 1; i <= 3; i++) {
            int id = i;
            new Thread(() -> {
                System.out.println("Worker " + id + " doing setup...");
                try { Thread.sleep(1000 * id); } catch (InterruptedException ignored) {}
                System.out.println("Worker " + id + " done.");
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println("All setup done. Main thread continues.");
    }
}
