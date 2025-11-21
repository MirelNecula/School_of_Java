package org.example;

import java.util.Arrays;
import java.util.List;

public class ex10 {
    public static double averageWordLength(List<String> words) {
        return words.stream()
                .mapToInt(String::length)
                .average()
                .orElse(0.0);
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList("java", "este", "tare");
        System.out.println(averageWordLength(words));
    }
}
