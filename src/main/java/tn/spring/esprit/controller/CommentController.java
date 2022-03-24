package tn.spring.esprit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.spring.esprit.ServiceInterface.CommentInterface;
import tn.spring.esprit.entities.Comments;
import tn.spring.esprit.entities.Posts;
import tn.spring.esprit.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService coms;

    @PostMapping("/ajouter-comment/{post_id}/{user_id}")
    public void addComment(@RequestBody Comments com ,@PathVariable ("post_id") int post_id,@PathVariable("user_id") int user_id ){
        coms.addComment(com,post_id,user_id);
    }


    @DeleteMapping("/remove-comment/{comment-id}")
    public void removeComments(@PathVariable("comment-id") int comment_id) {
        coms.deleteComment(comment_id);
    }

    @GetMapping("/retrieveAllComments")
    public List<Comments> getComments() {
        return coms.retrieveAllComments();
    }

    @GetMapping("/nombreReactionComment/{comment-id}")
    public int nombreReactionComment (	 @PathVariable("comment-id") int comment_id){return coms.nombreReactionComment(comment_id); }
}
