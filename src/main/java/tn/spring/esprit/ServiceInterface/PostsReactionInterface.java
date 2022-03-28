package tn.spring.esprit.ServiceInterface;

import tn.spring.esprit.entities.CommentReaction;
import tn.spring.esprit.entities.PostReaction;
import tn.spring.esprit.entities.Posts;

import java.util.List;

public interface PostsReactionInterface {

    public String addPostReaction(PostReaction preac ,int post_id,int id);

    void deletePostReaction(int id ,int post_id);

    public void updatePostReaction(PostReaction preac, int id);

    public void affecterPostReaction(int post_id,int postReact_id);

    public void disaffecterPostReaction(int post_id,int postReact_id);

    List<PostReaction> retrieveAllPostReaction();

    public void affecterUserreaction(int postReact_id,int user_id);

    public void affecterUserPostReaction(int postReact_id,int user_id);

}
