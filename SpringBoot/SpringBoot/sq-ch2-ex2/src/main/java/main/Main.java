package main;

import org.example.sqch2ex2.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
    var context =
            new AnnotationConfigApplicationContext(ProjectConfig.class);

    Parrot p = context.getBean(Parrot.class);
        System.out.println(p.getName());
    String s = context.getBean(String.class);
        System.out.println(s);
        Integer i = context.getBean(Integer.class);
        System.out.println(i);
    }
}
