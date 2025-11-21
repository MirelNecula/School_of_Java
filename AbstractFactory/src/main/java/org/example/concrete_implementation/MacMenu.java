package org.example.concrete_implementation;

import org.example.Menu;

public class MacMenu implements Menu {
    @Override
    public void render() {
        System.out.println("Rendering a menu in Mac style.");
    }
}
