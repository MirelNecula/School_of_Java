package org.example;

import java.util.*;
import java.util.stream.LongStream;

public class ex19b {
    public static void main(String[] args) {
        List<Long> numbers = LongStream.rangeClosed(1, 10_000_000).boxed().toList();

        long start1 = System.currentTimeMillis();
        long sum1 = numbers.stream().mapToLong(n -> n * n).sum();
        long end1 = System.currentTimeMillis();

        long start2 = System.currentTimeMillis();
        long sum2 = numbers.parallelStream().mapToLong(n -> n * n).sum();
        long end2 = System.currentTimeMillis();

        System.out.println("Normal Stream sum: " + sum1 + " time: " + (end1 - start1) + "ms");
        System.out.println("Parallel Stream sum: " + sum2 + " time: " + (end2 - start2) + "ms");
    }
}
