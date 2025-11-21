package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Comparator;

class Employee {
    String name;
    String department;
    double salary;

    Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String toString() {
        return name + " (" + department + ", " + salary + ")";
    }
}

public class ex14 {
    public static Map<String, Double> averageSalaryPerDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(e -> e.department,
                        Collectors.averagingDouble(e -> e.salary)));
    }

    public static Optional<Employee> highestPaidEmployee(List<Employee> employees) {
        return employees.stream().max(Comparator.comparingDouble(e -> e.salary));
    }

    public static String allEmployeeNames(List<Employee> employees) {
        return employees.stream().map(e -> e.name).collect(Collectors.joining(", "));
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Ana", "HR", 3000),
                new Employee("Mihai", "IT", 5000),
                new Employee("Ioana", "HR", 3500),
                new Employee("Alex", "IT", 6000)
        );

        System.out.println(averageSalaryPerDepartment(employees));
        System.out.println(highestPaidEmployee(employees).orElse(null));
        System.out.println(allEmployeeNames(employees));
    }
}
