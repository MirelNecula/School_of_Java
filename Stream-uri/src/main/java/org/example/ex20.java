package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class ex20 {
    public static List<Integer> findDuplicates(List<Integer> numbers) {
        return numbers.stream()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 2, 3, 5, 6, 3);
        System.out.println(findDuplicates(list));
    }
}
