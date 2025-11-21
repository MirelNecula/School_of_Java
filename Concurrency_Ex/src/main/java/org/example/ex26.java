package org.example;

import java.util.concurrent.Phaser;

public class ex26 {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        for (int i = 1; i <= 3; i++) {
            int id = i;
            new Thread(() -> {
                System.out.println("Thread " + id + " loading data");
                try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
                phaser.arriveAndAwaitAdvance();

                System.out.println("Thread " + id + " processing data");
                try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
                phaser.arriveAndAwaitAdvance();

                System.out.println("Thread " + id + " reporting results");
                phaser.arriveAndDeregister();
            }).start();
        }
    }
}
