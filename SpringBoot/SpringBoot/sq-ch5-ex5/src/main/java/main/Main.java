package main;

import configuration.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.CommentService;

public class Main {
    public static void main(String[] args) {
        var c =
                new AnnotationConfigApplicationContext(ProjectConfig.class);

        var cs1 = c.getBean("commentServices", CommentService.class);
        var cs2 = c.getBean("commentServices", CommentService.class);
        boolean b1 = cs1 == cs2;
        System.out.println(b1);

    }
}
