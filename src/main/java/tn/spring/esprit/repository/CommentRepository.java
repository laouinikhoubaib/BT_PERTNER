package tn.spring.esprit.repository;

import org.springframework.data.repository.CrudRepository;
import tn.spring.esprit.entities.Comments;
import tn.spring.esprit.entities.Posts;

public interface CommentRepository extends CrudRepository<Comments, Integer> {
}
