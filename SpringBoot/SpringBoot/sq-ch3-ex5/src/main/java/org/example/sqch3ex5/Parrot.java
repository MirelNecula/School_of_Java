package org.example.sqch3ex5;

import org.springframework.stereotype.Component;

@Component
public class Parrot {

    private String name = "Koko";

    // Omitted getters and setters

    @Override
    public String toString() {
        return "Parrot : " + name;
    }
}