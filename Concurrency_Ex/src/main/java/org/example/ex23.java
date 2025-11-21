package org.example;

import java.util.concurrent.Semaphore;

public class ex23 {
    static Semaphore parking = new Semaphore(3);
    static class Car extends Thread {
        public Car(String name){ super(name); }
        public void run(){
            try {
                System.out.println(getName() + " waiting for parking...");
                parking.acquire();
                System.out.println(getName() + " parked.");
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {}
            finally {
                System.out.println(getName() + " leaving.");
                parking.release();
            }
        }
    }
    public static void main(String[] args){
        for(int i=1;i<=6;i++) new Car("Car-"+i).start();
    }
}
