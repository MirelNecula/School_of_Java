package org.example;

import java.util.*;
import java.util.stream.IntStream;

public class ex21 {
    public static void main(String[] args) {
        List<Integer> list = IntStream.rangeClosed(1, 10_000_000).boxed().toList();

        long t1 = System.currentTimeMillis();
        int s1 = list.stream().mapToInt(i -> i * 2).sum();
        long t2 = System.currentTimeMillis();

        long t3 = System.currentTimeMillis();
        int s2 = list.parallelStream().mapToInt(i -> i * 2).sum();
        long t4 = System.currentTimeMillis();

        System.out.println("Sequential: " + (t2 - t1) + " ms");
        System.out.println("Parallel: " + (t4 - t3) + " ms");
    }
}
