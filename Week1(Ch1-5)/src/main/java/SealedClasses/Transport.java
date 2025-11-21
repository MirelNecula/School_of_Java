package SealedClasses;

public sealed class Transport permits Car, Bike, Bus{
    protected String type;
    protected int capacity;
    protected double speed;

    public Transport(String type, int capacity, double speed) {
        this.type = type;
        this.capacity = capacity;
        this.speed = speed;
    }

    public void displayInfo() {
        System.out.println("Type: " + type + ", Capacity: " + capacity + ", Speed: " + speed);
    }
}
