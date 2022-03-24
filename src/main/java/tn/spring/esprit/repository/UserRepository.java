package tn.spring.esprit.repository;

import org.springframework.data.repository.CrudRepository;
import tn.spring.esprit.entities.Posts;
import tn.spring.esprit.entities.User;

public interface UserRepository extends CrudRepository<User, Integer>  {
}
