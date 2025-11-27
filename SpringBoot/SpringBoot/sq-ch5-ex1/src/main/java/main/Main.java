package main;

import configuration.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var c =
                new AnnotationConfigApplicationContext(ProjectConfig.class);

        var cs1 = c.getBean("commentService", Object.class);
        var cs2 = c.getBean("commentService", Object.class);

        boolean b1= cs1==cs2;
        System.out.println(b1);
    }
}
