package org.example;

public class ex9b {
    static class Singleton {
        private static volatile Singleton instance;
        private Singleton() {}
        public static Singleton getInstance() {
            if (instance == null) {
                synchronized (Singleton.class) {
                    if (instance == null) instance = new Singleton();
                }
            }
            return instance;
        }
    }
    public static void main(String[] args) {
        Runnable r = () -> System.out.println(System.identityHashCode(Singleton.getInstance()));
        new Thread(r).start(); new Thread(r).start(); new Thread(r).start();
    }
}
