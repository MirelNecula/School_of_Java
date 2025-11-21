package org.example.ConcreteFactory;

import org.example.AbstractFactory.GUIFactory;
import org.example.Menu;
import org.example.concrete_implementation.WindowsButton;
import org.example.Button;
import org.example.concrete_implementation.WindowsMenu;


public class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
    @Override
    public Menu createMenu() {
        return new WindowsMenu();
    }
}
