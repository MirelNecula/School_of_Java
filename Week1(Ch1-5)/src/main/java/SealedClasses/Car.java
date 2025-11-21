package SealedClasses;

public non-sealed class Car extends Transport {
    public Car(int capacity, double speed) {
        super("Car", capacity, speed);
    }
}
