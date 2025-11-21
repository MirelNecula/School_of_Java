package org.example;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ex18b {
    public static Collector<String, StringJoiner, String> customJoiner() {
        return Collector.of(
                () -> new StringJoiner(" | "),
                StringJoiner::add,
                StringJoiner::merge,
                StringJoiner::toString
        );
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java", "Streams", "Collectors", "API");
        String result = list.stream().collect(customJoiner());
        System.out.println(result);
    }
}
