package org.example.sqch2ex7;

public class Main {
    public static void main(String[] args) {
        var context =
                new org.springframework.context.annotation.AnnotationConfigApplicationContext(ProjectConfig.class);

        Parrot p = context.getBean(Parrot.class);
        System.out.println(p.getName());
    }
}
