package org.example;

public class ex9c {
    static class Singleton {
        private Singleton() {}
        private static class Holder { static final Singleton I = new Singleton(); }
        public static Singleton getInstance() { return Holder.I; }
    }
    public static void main(String[] args) {
        Runnable r = () -> System.out.println(System.identityHashCode(Singleton.getInstance()));
        new Thread(r).start(); new Thread(r).start(); new Thread(r).start();
    }
}
