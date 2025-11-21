package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class ex16b {
    public static List<Integer> flatten(List<List<Integer>> listOfLists) {
        return listOfLists.stream().flatMap(List::stream).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<List<Integer>> data = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8)
        );
        System.out.println(flatten(data));
    }
}
