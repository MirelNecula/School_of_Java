package org.example.concrete_implementation;

import org.example.Menu;

public class WindowsMenu implements Menu {
    @Override
    public void render() {
        System.out.println("Rendering a menu in Windows style.");
    }
}
