package SealedClasses;

public class main {
    public static void main(String[] args) {
        Bus bus = new Bus(50, 60.0);
        Car car = new Car(5, 120.0);
        Bike bike = new Bike(1, 30.0);

        bus.displayInfo();
        car.displayInfo();
        bike.displayInfo();
    }
}
