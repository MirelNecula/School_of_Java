package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ex9 {
    public static Optional<String> findLongest(List<String> items) {
        return items.stream().reduce((a, b) -> a.length() >= b.length() ? a : b);
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("cireasa", "mar", "portocala", "para");
        System.out.println(findLongest(list).orElse(""));
    }
}
