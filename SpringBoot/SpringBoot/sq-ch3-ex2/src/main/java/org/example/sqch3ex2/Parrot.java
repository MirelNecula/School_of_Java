package org.example.sqch3ex2;

import ch.qos.logback.core.encoder.JsonEscapeUtil;

public class Parrot {

    private String name;
    public Parrot() {
        System.out.println("Parrot instance created");
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Parrot:" + name;
    }
}

