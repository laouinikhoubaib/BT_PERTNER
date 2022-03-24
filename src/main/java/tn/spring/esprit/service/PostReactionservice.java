package tn.spring.esprit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.esprit.ServiceInterface.PostsInterface;
import tn.spring.esprit.entities.CommentReaction;
import tn.spring.esprit.entities.Comments;
import tn.spring.esprit.entities.PostReaction;
import tn.spring.esprit.entities.Posts;
import tn.spring.esprit.repository.CommentRepository;
import tn.spring.esprit.repository.PostReactionReposirory;
import tn.spring.esprit.ServiceInterface.PostsReactionInterface;
import tn.spring.esprit.repository.PostsRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PostReactionservice implements PostsReactionInterface {
    @Autowired
    PostReactionReposirory postReactionReposirory;

    @Autowired
    private PostsRepository postr;


    @Override
    public void addPostReaction(PostReaction preac,int post_id)
    {

        postReactionReposirory.save(preac);
        affecterPostReaction(post_id, preac.getId());
    }

    @Override
    public void deletePostReaction(int id,int post_id) {

        disaffecterPostReaction(post_id, id);
        postReactionReposirory. deleteById(id);


    }
    @Override
    public List<PostReaction> retrieveAllPostReaction() {
        return (List<PostReaction>) postReactionReposirory.findAll();

    }
    @Override
    public void updatePostReaction(PostReaction preac, int id){
        PostReaction postReaction=postReactionReposirory.findById(id).orElse(null);
        postReaction.setTypereaction(preac.getTypereaction());
        postReactionReposirory.save(postReaction);

    }
    @Override
    public void affecterPostReaction(int post_id,int postReact_id){
        PostReaction postReaction = postReactionReposirory.findById(postReact_id).orElse(null);
        Posts post = postr.findById(post_id).orElse(null);

        if (post.getListPostsReactions() == null){
            Set<PostReaction> listPostsReactions = new HashSet<PostReaction>();
            listPostsReactions.add(postReaction);
            post.setListPostsReactions(listPostsReactions);
            postr.save(post);
        }else
        {
            post.getListPostsReactions().add(postReaction);
            postr.save(post);
        }
        postReaction.setPost(post);

        postReactionReposirory.save(postReaction);
        postr.save(post);
    }
@Override
public void disaffecterPostReaction(int post_id,int postReact_id) {
    PostReaction postReaction = postReactionReposirory.findById(postReact_id).orElse(null);
    Posts post = postr.findById(post_id).orElse(null);
    if (post.getListPostsReactions() == null){
        System.out.println("List deja null");
    }else
    {
        post.getListPostsReactions().remove(postReaction);
    }
    postReaction.setPost(post);

    postReactionReposirory.save(postReaction);
    postr.save(post);
}


}
