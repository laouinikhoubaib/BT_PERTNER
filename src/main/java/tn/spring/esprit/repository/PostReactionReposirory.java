package tn.spring.esprit.repository;

import org.springframework.data.repository.CrudRepository;
import tn.spring.esprit.entities.Comments;
import tn.spring.esprit.entities.PostReaction;
import tn.spring.esprit.entities.Posts;

public interface PostReactionReposirory extends CrudRepository<PostReaction, Integer> {


}
