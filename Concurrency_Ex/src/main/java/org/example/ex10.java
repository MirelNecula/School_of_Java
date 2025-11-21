package org.example;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ex10 {
    static class SharedList {
        private final List<Integer> data = new ArrayList<>();
        private final ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
        public void add(int v) { rw.writeLock().lock(); try { data.add(v); } finally { rw.writeLock().unlock(); } }
        public List<Integer> snapshot() { rw.readLock().lock(); try { return new ArrayList<>(data); } finally { rw.readLock().unlock(); } }
    }

    public static void main(String[] args) {
        SharedList s = new SharedList();
        Thread writer = new Thread(() -> { for (int i=1;i<=10;i++){ s.add(i); System.out.println("Wrote "+i); try{Thread.sleep(100);}catch(Exception ignored){} }});
        Thread reader = new Thread(() -> { for (int i=0;i<10;i++){ System.out.println("Read "+s.snapshot()); try{Thread.sleep(150);}catch(Exception ignored){} }});
        writer.start(); reader.start();
    }
}
