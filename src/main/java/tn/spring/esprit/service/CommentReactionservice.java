package tn.spring.esprit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.esprit.ServiceInterface.CommentReactionInterface;
import tn.spring.esprit.entities.CommentReaction;
import tn.spring.esprit.entities.Comments;
import tn.spring.esprit.entities.PostReaction;
import tn.spring.esprit.repository.CommentReactionRepository;
import tn.spring.esprit.repository.CommentRepository;
import tn.spring.esprit.repository.PostsRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

@Service
public class CommentReactionservice implements CommentReactionInterface {
    @Autowired
   private CommentReactionRepository commentReacRep;

    @Autowired
    private CommentRepository comr;

    @Override
    public void addCommentReaction(CommentReaction preac,int comment_id) {
        commentReacRep.save(preac);
        affecterCommentReaction(comment_id, preac.getId());
    }


    @Override
    public void deleteCommentReaction(int id,int comment_id) {
        disaffecterCommentReaction(id,comment_id);
        commentReacRep.deleteById(id);

    }

    @Override
    public List<CommentReaction> retrieveAllCommentReaction() {
        return (List<CommentReaction>) commentReacRep.findAll();
    }

    @Override
    public void updateCommentReaction(CommentReaction preac, int id){
        CommentReaction commentReaction=commentReacRep.findById(id).orElse(null);
        commentReaction.setTypereaction(preac.getTypereaction());
        commentReacRep.save(commentReaction);

    }
    @Override
    public void affecterCommentReaction(int commentId,int commentReact_id){
        CommentReaction commentReaction=commentReacRep.findById(commentReact_id).get();
        Comments comment=comr.findById(commentId).orElse(null);

        if (comment.getListCommentReactions()== null){
            Set<CommentReaction> listCommentReactions=new HashSet<CommentReaction>();
            listCommentReactions.add(commentReaction);
            comment.setListCommentReactions(listCommentReactions);
            comr.save(comment);
        }else
        {
            comment.getListCommentReactions().add(commentReaction);
            comr.save(comment);
        }
        commentReaction.setComments(comment);
        commentReacRep.save(commentReaction);


    }

    @Override
    public void disaffecterCommentReaction(int commentId,int commentReact_id){
        CommentReaction commentReaction=commentReacRep.findById(commentReact_id).orElse(null);
        Comments comment=comr.findById(commentId).orElse(null);

        if (comment.getListCommentReactions()== null){
            System.out.println("List deja null");
        }else
        {
            comment.getListCommentReactions().remove(commentReaction);
            comr.save(comment);
        }
        commentReaction.setComments(comment);
    }
}