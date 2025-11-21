package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ex5 {
    public static Optional<String> find(List<String> input, String substring) {
        return input.stream()
                .filter(s -> s != null && s.contains(substring))
                .findFirst();
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("C#", "JavaScript", "Python", "Java");
        System.out.println(find(list, "Java").orElse("No match found"));
    }
}
