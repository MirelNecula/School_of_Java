package org.example;

import java.util.Scanner;

public class VehicleSpeedMonitor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        while (true) {
            System.out.print("Enter vehicle type: ( car,bike or bus) ");
            String vehicleType = sc.nextLine().toLowerCase();
            System.out.print("Enter speed of the vehicle: ");
            int speed = sc.nextInt();
            if (speed < 0 ){
                System.out.println("Existing the program.");
                break;
            }

            int speedLimit = switch (vehicleType){
                case "car" -> 100;
                case "bike" -> 60;
                case "bus" -> 80;
                default -> -1;
            };

            if (speedLimit == -1) {
                System.out.println("Invalid vehicle type. Please enter car, bike, or bus.");
            } else if (speed > speedLimit) {
                System.out.println("Speeding! The speed limit for a " + vehicleType + " is " + speedLimit + " km/h.");
            } else {
                System.out.println("Within speed limit. The speed limit for a " + vehicleType + " is " + speedLimit + " km/h.");
            }
            sc.nextLine(); // Consume the newline character
        }
    }
}