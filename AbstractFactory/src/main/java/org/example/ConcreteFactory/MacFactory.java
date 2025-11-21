package org.example.ConcreteFactory;

import org.example.AbstractFactory.GUIFactory;
import org.example.Button;
import org.example.Menu;
import org.example.concrete_implementation.MacButton;
import org.example.concrete_implementation.MacMenu;

public class MacFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Menu createMenu() {
        return new MacMenu();
    }
}
