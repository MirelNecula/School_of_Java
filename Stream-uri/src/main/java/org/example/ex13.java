package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class ex13 {
    public static Map<String, Long> wordFrequency(List<String> words) {
        return words.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("ana", "ion", "ana", "mihai", "ion", "ana");
        System.out.println(wordFrequency(list));
    }
}
