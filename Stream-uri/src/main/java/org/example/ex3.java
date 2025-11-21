package main.java.org.example;

import java.util.Arrays;
import java.util.List;

public class ex3 {
    public static long count(List<String> names, char letter) {
        String p = String.valueOf(letter);
        return names
                .stream()
                .filter(s -> s != null && s.startsWith(p))
                .count();
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ana", "Alex", "Bob", "Alice", "Mihai");
        System.out.println(count(names, 'A'));
    }
}
