package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ex1 {
    public static List<Integer> filter(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(filter(list));
    }
}
