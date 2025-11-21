package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class ex17b {
    public static List<String> extractUniqueWords(List<String> sentences) {
        return sentences.stream()
                .flatMap(s -> Arrays.stream(s.split("\\s+")))
                .map(String::toLowerCase)
                .distinct()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> sentences = Arrays.asList("Ana are mere", "Ion are pere", "Ana si Ion");
        System.out.println(extractUniqueWords(sentences));
    }
}
