package tn.spring.esprit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.esprit.ServiceInterface.CommentReactionInterface;
import tn.spring.esprit.entities.*;
import tn.spring.esprit.repository.*;

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
    @Autowired
    NotificationService notificationService;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public String addCommentReaction(CommentReaction preac,int comment_id,int user_id) {
        commentReacRep.save(preac);
        affecterCommentReaction(comment_id, preac.getId(),user_id);

        Notification notif = new Notification();
        notificationRepository.save(notif);
        String message1 = notificationService.affecterNotficationUser ( comment_id,user_id,notif.getId() );
        String messageGlobale = message1 + "un nouveau CommentReaction sur votre post d'id :" + comment_id;
        notif.setObjetNotif(messageGlobale);
        notificationRepository.save(notif);
        return (messageGlobale);
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
    public void affecterCommentReaction(int commentId,int commentReact_id,int user_id){
        CommentReaction commentReaction=commentReacRep.findById(commentReact_id).get();
        Comments comment=comr.findById(commentId).orElse(null);
        User user = userRepository.findById(user_id).orElse(null);
        if (user.getCommentReactions()== null) {
            Set<CommentReaction> commentReactions = new HashSet<>();
            commentReactions.add(commentReaction);
            user.setCommentReactions(commentReactions);
            userRepository.save(user);
        } else{
            user.getCommentReactions().add(commentReaction);
            userRepository.save(user);
        }

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
        commentReacRep.save(commentReaction);
    }
}