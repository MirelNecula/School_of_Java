package org.example;


import org.example.AbstractFactory.GUIFactory;
import org.example.ConcreteFactory.MacFactory;
import org.example.ConcreteFactory.WindowsFactory;

public class Main {
    private final Button button;
    private final Menu menu;


    public Main(GUIFactory factory) {
        button = factory.createButton();
        menu = factory.createMenu();
    }

    public void renderUI() {
        button.paint();
        menu.render();
    }

    public static void main(String[] args) {
        GUIFactory factory;
        String os = "Mac";

        if (os.equalsIgnoreCase("Windows")) {
            factory = new WindowsFactory();
        } else {
            factory = new MacFactory();
        }

        Main app = new Main(factory);
        app.renderUI();
    }
}
