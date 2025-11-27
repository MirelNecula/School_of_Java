package proxies;

import model.Comment;
import org.springframework.stereotype.Component;

@Component
public class EmailCommentNoificationProxy
        implements CommentNotificationProxy {

    @Override
    public void sendComment(Comment comment) {
        System.out.println("Sending email notification for comment: " + comment.getText());
    }
}
