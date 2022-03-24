package tn.spring.esprit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.esprit.ServiceInterface.CommentInterface;
import tn.spring.esprit.entities.CommentReaction;
import tn.spring.esprit.entities.Comments;
import tn.spring.esprit.entities.Posts;
import tn.spring.esprit.entities.User;
import tn.spring.esprit.repository.CommentRepository;
import tn.spring.esprit.repository.PostsRepository;
import tn.spring.esprit.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CommentService implements CommentInterface {
    @Autowired
    CommentRepository comr;
    @Autowired
    PostsRepository postr;
    @Autowired
    UserRepository userRepository;

    @Override
    public void addComment(Comments com, int post_id,int user_id){

        comr.save(com);
        affecterCommentpost(com.getComment_id(), post_id);
        affecterUserComments(com.getComment_id(),user_id);
    }


    @Override
    public void deleteComment(int id) {
        comr.deleteById(id);


    }
    @Override
    public List<Comments> retrieveAllComments() {
        return (List<Comments>) comr.findAll();

    }

    @Override
    public int nombreReactionComment (int comment_id){
        Comments commenter = comr.findById(comment_id).orElse(null);
        //int size=poster.getListPostsReactions().size();
        return commenter.getListCommentReactions().size();

    }
    @Override
    public void affecterCommentpost(int commentId,int post_id){
        Posts post = postr.findById(post_id).get();
        Comments comment=comr.findById(commentId).orElse(null);

        if (post.getComments()== null){
            Set<Comments> listPostComments=new HashSet<Comments>();
            listPostComments.add(comment);
            post.setComments(listPostComments);
        }else
        {
            post.getComments().add(comment);
            postr.save(post);
        }
        comment.setPosts(post);
        comr.save(comment);

    }

    @Override
    public void affecterUserComments(int comment_id,int user_id) {
        User user = userRepository.findById(user_id).orElse(null);
        Comments comments = comr.findById(comment_id).orElse(null);
        if (comments != null) {
            comments.setUser(user);
            comr.save(comments);
        }else System.out.println("comment n'existe pas");
    }


}
