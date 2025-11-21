package org.example;

import java.util.Arrays;
import java.util.List;

public class ex6 {
    public static int sumOfSquaresEven(List<Integer> nums) {
        return nums.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(n -> n * n)
                .sum();
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(sumOfSquaresEven(list));
    }
}
