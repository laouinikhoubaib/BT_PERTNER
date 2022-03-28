package tn.spring.esprit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.esprit.ServiceInterface.PostsInterface;
import tn.spring.esprit.entities.*;
import tn.spring.esprit.repository.*;
import tn.spring.esprit.ServiceInterface.PostsReactionInterface;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PostReactionservice implements PostsReactionInterface {
    @Autowired
    PostReactionReposirory postReactionReposirory;

    @Autowired
    private PostsRepository postr;

    @Autowired
    UserRepository userRepository;
    @Autowired
    NotificationService notificationService;
    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public String addPostReaction(PostReaction preac,int post_id,int user_id)
    {

        postReactionReposirory.save(preac);
        affecterPostReaction(post_id, preac.getId());
        affecterUserreaction(preac.getId(),user_id);
        affecterUserPostReaction(preac.getId(),user_id);

        Notification notif = new Notification();
        notificationRepository.save(notif);
        String message1 = notificationService.affecterNotficationUser ( post_id,user_id,notif.getId() );
        String messageGlobale = message1 + "un nouveau postReaction sur votre post d'id :" + post_id;
        notif.setObjetNotif(messageGlobale);
        notificationRepository.save(notif);
        return (messageGlobale);
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

    @Override
    public void affecterUserreaction(int postReact_id,int user_id) {
        User user = userRepository.findById(user_id).orElse(null);
        PostReaction postReaction = postReactionReposirory.findById(postReact_id).orElse(null);

        if (user.getPostReactions()== null) {
            Set<PostReaction> postReactions = new HashSet<>();
            postReactions.add(postReaction);
            user.setPostReactions(postReactions);
            userRepository.save(user);
        } else {
            user.getPostReactions().add(postReaction);
        userRepository.save(user);}
    }
    @Override
    public void affecterUserPostReaction(int postReact_id,int user_id) {
        User user = userRepository.findById(user_id).orElse(null);
        PostReaction postReaction = postReactionReposirory.findById(postReact_id).orElse(null);
        if (postReaction != null) {
            postReaction.setUser(user);
            postReactionReposirory.save(postReaction);
        }else System.out.println("postReaction n'existe pas");
    }


}
