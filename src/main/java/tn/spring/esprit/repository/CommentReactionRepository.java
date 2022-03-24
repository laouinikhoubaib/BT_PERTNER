package tn.spring.esprit.repository;

import org.springframework.data.repository.CrudRepository;
import tn.spring.esprit.entities.CommentReaction;
import tn.spring.esprit.entities.Comments;

public interface CommentReactionRepository extends CrudRepository<CommentReaction, Integer> {

}
