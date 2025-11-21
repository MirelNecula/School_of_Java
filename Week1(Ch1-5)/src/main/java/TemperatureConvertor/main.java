package TemperatureConvertor;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose conversion type:");
        System.out.println("1. Celsius to Fahrenheit");
        System.out.println("2. Fahrenheit to Celsius");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.print("Enter temperature in Celsius: ");
            double celsius = scanner.nextDouble();
            double fahrenheit = TemperatureConverter.celsiusToFahrenheit(celsius);
            System.out.printf("%.2f Celsius is %.2f Fahrenheit%n", celsius, fahrenheit);
        } else if (choice == 2) {
            System.out.print("Enter temperature in Fahrenheit: ");
            double fahrenheit = scanner.nextDouble();
            double celsius = TemperatureConverter.fahrenheitToCelsius(fahrenheit);
            System.out.printf("%.2f Fahrenheit is %.2f Celsius%n", fahrenheit, celsius);
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}
