package main;

import org.example.sqch2ex4.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context =
                new AnnotationConfigApplicationContext(ProjectConfig.class);

        Parrot p = context.getBean("Papagal2" ,Parrot.class);
        System.out.println(p.getName());
    }
}
