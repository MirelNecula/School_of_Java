package org.example.AbstractFactory;

import org.example.Button;
import org.example.Menu;

public interface GUIFactory {
    Button createButton();
    Menu createMenu();
}
