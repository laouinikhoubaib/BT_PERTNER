package tn.spring.esprit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.spring.esprit.ServiceInterface.CommentReactionInterface;
import tn.spring.esprit.entities.CommentReaction;
import tn.spring.esprit.entities.PostReaction;

import java.util.List;

@RestController
@RequestMapping("/commentReaction")
public class CommentReactionController {

    @Autowired
    CommentReactionInterface commentreactionservice;

    @PostMapping("/ajouter-commentReaction/{comment_id}")
    public void addCommentReaction(@RequestBody CommentReaction preac , @PathVariable("comment_id") int id)
    {commentreactionservice.addCommentReaction(preac, id);}


    @DeleteMapping("/remove-commentReaction/{reaction_id}/{comment_id}")
    public void deleteCommentReaction(@PathVariable("reaction_id") int id,@PathVariable("comment_id")int comment_id  ){commentreactionservice.deleteCommentReaction(id,comment_id);};

    @GetMapping("/retrieveAllCommentReaction")
    public List<CommentReaction> getCommentReactions(){ return commentreactionservice.retrieveAllCommentReaction() ;};

    @PutMapping("/update-commentReaction/{comment-id}")
    public void updateCommentReaction(@RequestBody CommentReaction preac,@PathVariable("comment-id") int id)
    {
        commentreactionservice.updateCommentReaction(preac,id);
    };

}
