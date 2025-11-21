package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ex4 {
    public static List<String> transform(List<String> input) {
        return input.stream()
                .map(String::toUpperCase).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("java", "stream", "lambda");
        System.out.println(transform(list));
    }
}
