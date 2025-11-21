package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ex2 {
    public static List<Integer> square(List<Integer> numbers) {
        return numbers
                .stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(square(list));
    }
}
