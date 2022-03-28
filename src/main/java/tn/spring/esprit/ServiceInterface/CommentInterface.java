package tn.spring.esprit.ServiceInterface;

import tn.spring.esprit.entities.Comments;
import tn.spring.esprit.entities.Posts;

import java.util.List;

public interface CommentInterface {

    public String addComment(Comments com, int post_id,int user_id);

    void deleteComment(int id );

    List<Comments> retrieveAllComments();

    public int nombreReactionComment (int comment_id);

    public void affecterCommentpost(int commentId,int post_id);

    public void affecterUserComments(int comment_id,int user_id);

}
