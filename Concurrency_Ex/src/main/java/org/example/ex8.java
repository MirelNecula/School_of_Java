package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedList;
import java.util.Queue;

public class ex8 {
    static final ReentrantLock lock = new ReentrantLock();
    static final Condition notEmpty = lock.newCondition();
    static final Condition notFull = lock.newCondition();
    static final Queue<Integer> q = new LinkedList<>();
    static final int LIMIT = 5;

    static class Producer implements Runnable {
        public void run() {
            for (int i = 1; i <= 10; i++) {
                lock.lock();
                try {
                    while (q.size() == LIMIT) notFull.awaitUninterruptibly();
                    q.add(i);
                    System.out.println("Produced " + i);
                    notEmpty.signal();
                } finally { lock.unlock(); }
            }
        }
    }

    static class Consumer implements Runnable {
        public void run() {
            for (int i = 1; i <= 10; i++) {
                lock.lock();
                try {
                    while (q.isEmpty()) notEmpty.awaitUninterruptibly();
                    int v = q.poll();
                    System.out.println("Consumed " + v);
                    notFull.signal();
                } finally { lock.unlock(); }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
    }
}
