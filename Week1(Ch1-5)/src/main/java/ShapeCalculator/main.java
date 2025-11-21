package ShapeCalculator;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(5));
        shapes.add(new Rectangle(4, 6));
        shapes.add(new Triangle(3, 4, 5));
        for (Shape shape : shapes) {
            System.out.println("Area: " + shape.area());
            System.out.println("Perimeter: " + shape.perimeter());
        }
    }
}

class Circle extends Shape {

    private double radius;  // <--- variabila declarată aici

    public Circle(double radius) {
        System.out.println("Circle created with radius: " + radius);
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }

    @Override
    double perimeter() {
        return 2 * Math.PI * radius;
    }
}

class Rectangle extends Shape {

    private double length;
    private double width;

    public Rectangle(double length, double width) {
        System.out.println("Rectangle created with length: " + length + " and width: " + width);
        this.length = length;
        this.width = width;
    }

    @Override
    double area() {
        return length * width;
    }

    @Override
    double perimeter() {
        return 2 * (length + width);
    }
}

class Triangle extends Shape {

    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(double sideA, double sideB, double sideC) {
        System.out.println("Triangle created with sides: " + sideA + ", " + sideB + ", " + sideC);
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    @Override
    double area() {
        double s = (sideA + sideB + sideC) / 2;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));

    }
    @Override
    double perimeter() {
        return sideA + sideB + sideC;
    }
}
