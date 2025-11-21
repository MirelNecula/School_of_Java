package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class ex11 {
    public static Map<Integer, List<String>> groupByLength(List<String> words) {
        return words.stream().collect(Collectors.groupingBy(String::length));
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList("ana", "ion", "mihai", "elena", "bob", "roxana");
        System.out.println(groupByLength(words));
    }
}
