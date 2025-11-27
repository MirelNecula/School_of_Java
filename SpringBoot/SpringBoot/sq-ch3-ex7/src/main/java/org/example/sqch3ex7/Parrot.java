package org.example.sqch3ex7;


import org.springframework.beans.factory.annotation.Autowired;

public class Parrot {

    private String name;

    Autowired
    public Parrot(Person person) {
        this.person=person;
    }
}
