package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.News;


@Repository
public interface NewsRepository extends CrudRepository<tn.esprit.spring.entities.News,Integer>{
	 @Query("SELECT t FROM News t WHERE " +
	            "LOWER(t.NewsTitle) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
	            "LOWER(t.NewsDescription) LIKE LOWER(CONCAT('%',:searchTerm, '%'))")
	   public List<News> findBySearchTerm(@Param("searchTerm") String abc);
}
