package services;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class CommentServices {

    public CommentServices() {
        System.out.println("Comment Service Bean is created");
    }
}
