package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ex8 {
    public static List<String> distinctWords(List<String> sentences) {
        return sentences.stream()
                .flatMap(s -> Arrays.stream(s.split("\\s+")))
                .filter(w -> !w.isEmpty())
                .distinct()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> sentences = Arrays.asList("ana are mere", "mere are ion", "ion are pere");
        System.out.println(distinctWords(sentences));
    }
}
