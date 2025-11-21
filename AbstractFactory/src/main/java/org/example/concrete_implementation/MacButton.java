package org.example.concrete_implementation;

import org.example.Button;

public class MacButton implements Button {
    @Override
    public void paint() {
        System.out.println("Rendering a button in Mac style.");
    }
}
