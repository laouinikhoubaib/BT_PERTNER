package tn.spring.esprit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.spring.esprit.entities.Posts;


@Repository
public interface PostsRepository extends CrudRepository<Posts, Integer> {

}
