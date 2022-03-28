package tn.spring.esprit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.spring.esprit.ServiceInterface.PostsInterface;
import tn.spring.esprit.ServiceInterface.PostsReactionInterface;
import tn.spring.esprit.entities.PostReaction;
import tn.spring.esprit.repository.PostsRepository;

import java.util.List;

@RestController
@RequestMapping("/postReaction")
public class PostReactionController {

    @Autowired
    PostsReactionInterface postreactionservice;

    @PostMapping("/ajouter-postReaction/{post_id}/{user_id}")
    public void addPostReaction(@RequestBody PostReaction preac ,@PathVariable("post_id") int post_id,@PathVariable("user_id") int user_id){postreactionservice.addPostReaction(preac,post_id,user_id);};

    @DeleteMapping("/remove-postReaction/{reaction_id}/{post_id}")
    public void deletePostReaction(@PathVariable("reaction_id") int id,@PathVariable("post_id")int post_id ){postreactionservice.deletePostReaction(id , post_id);};

    @GetMapping("/retrieveAllPostReaction")
   public List<PostReaction> getPostReactions(){ return postreactionservice.retrieveAllPostReaction() ;};

    @PutMapping("/update-postReaction/{post-id}")
    public void updatePostReaction(@RequestBody PostReaction preac,@PathVariable("post-id") int id)
    {
        postreactionservice.updatePostReaction(preac,id);
    };


}
