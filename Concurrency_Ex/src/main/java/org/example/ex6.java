package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class ex6 {
    static Queue<Integer> queue = new LinkedList<>();
    static final int LIMIT = 5;

    static class Producer implements Runnable {
        public void run() {
            for (int i = 1; i <= 10; i++) {
                synchronized (queue) {
                    while (queue.size() == LIMIT) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.add(i);
                    System.out.println("Produced: " + i);
                    queue.notifyAll();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        public void run() {
            for (int i = 1; i <= 10; i++) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int val = queue.poll();
                    System.out.println("Consumed: " + val);
                    queue.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread producer = new Thread(new Producer());
        Thread consumer = new Thread(new Consumer());

        producer.start();
        consumer.start();
    }
}
