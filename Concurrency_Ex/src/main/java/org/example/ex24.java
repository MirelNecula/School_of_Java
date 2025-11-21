package org.example;

import java.util.concurrent.CyclicBarrier;

public class ex24 {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("All ready. Go!"));
        for (int i = 1; i <= 3; i++) {
            int id = i;
            new Thread(() -> {
                try {
                    System.out.println("Runner " + id + " ready.");
                    Thread.sleep(1000 * id);
                    barrier.await();
                    System.out.println("Runner " + id + " started running!");
                } catch (Exception ignored) {}
            }).start();
        }
    }
}
