package main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"controllers", "service"})
public class Main {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(Main.class, args);
    }
}
