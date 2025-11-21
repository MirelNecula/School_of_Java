package org.example;

import java.util.*;
import java.util.stream.Collectors;

class Employee20 {
    String name;
    double salary;

    Employee20(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String toString() {
        return name + " (" + salary + ")";
    }
}

class Department {
    String name;
    List<Employee20> employees;

    Department(String name, List<Employee20> employees) {
        this.name = name;
        this.employees = employees;
    }
}

public class ex20b {
    public static Optional<Employee20> highestPaid(List<Department> departments) {
        return departments.stream()
                .flatMap(d -> d.employees.stream())
                .max(Comparator.comparingDouble(e -> e.salary));
    }

    public static List<Employee20> aboveAverage(List<Department> departments) {
        List<Employee20> all = departments.stream()
                .flatMap(d -> d.employees.stream())
                .collect(Collectors.toList());
        double avg = all.stream().mapToDouble(e -> e.salary).average().orElse(0);
        return all.stream().filter(e -> e.salary > avg).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Department it = new Department("IT", Arrays.asList(
                new Employee20("Ana", 5000),
                new Employee20("Ion", 7000)
        ));
        Department hr = new Department("HR", Arrays.asList(
                new Employee20("Maria", 4000),
                new Employee20("Vlad", 6000)
        ));

        List<Department> deps = Arrays.asList(it, hr);
        System.out.println(highestPaid(deps).orElse(null));
        System.out.println(aboveAverage(deps));
    }
}

