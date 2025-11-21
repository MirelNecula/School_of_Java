package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class ex12 {
    public static Map<Boolean, List<Integer>> partitionEvenOdd(List<Integer> numbers) {
        return numbers.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println(partitionEvenOdd(list));
    }
}
