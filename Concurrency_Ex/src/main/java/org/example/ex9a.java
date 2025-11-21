package org.example;

public class ex9a {
    static class Singleton {
        private static Singleton instance;
        private Singleton() {}
        public static synchronized Singleton getInstance() {
            if (instance == null) instance = new Singleton();
            return instance;
        }
    }
    public static void main(String[] args) {
        Runnable r = () -> System.out.println(System.identityHashCode(Singleton.getInstance()));
        new Thread(r).start(); new Thread(r).start(); new Thread(r).start();
    }
}
