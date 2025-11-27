package main;

import model.Comment;
import proxies.EmailCommentNoificationProxy;
import repositories.DBCommentRepository;
import services.CommentService;

public class Main {
    public static void main(String[] args) {
        var commentRepository =
                new DBCommentRepository();
        var commentNotificationProxy =
                new EmailCommentNoificationProxy();

        var commentService = new CommentService(
                commentRepository,
                commentNotificationProxy);

        var comment = new Comment();
        comment.setAuthor("John Doe");
        comment.setText("Demo comment");

        commentService.publishComment(comment);

    }
}
