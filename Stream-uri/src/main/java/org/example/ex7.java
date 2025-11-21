package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ex7 {
    public static List<String> sortNames(List<String> names) {
        return names.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Mihai", "Ana", "Bogdan", "Alex");
        System.out.println(sortNames(names));
    }
}
