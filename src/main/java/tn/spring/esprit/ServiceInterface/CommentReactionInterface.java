package tn.spring.esprit.ServiceInterface;

import tn.spring.esprit.entities.CommentReaction;
import tn.spring.esprit.entities.PostReaction;
import tn.spring.esprit.entities.Posts;

import java.util.List;

public interface CommentReactionInterface {


    public void addCommentReaction(CommentReaction preac ,int id);

    public void deleteCommentReaction(int id ,int comment_id);

    public List<CommentReaction> retrieveAllCommentReaction();

    public void updateCommentReaction(CommentReaction preac, int id);

    public void affecterCommentReaction(int comment_id,int commentReact_id);

    public void disaffecterCommentReaction(int commentId,int commentReact_id);

}
