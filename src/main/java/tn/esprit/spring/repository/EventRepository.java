package tn.esprit.spring.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Events;
import tn.esprit.spring.entities.News;
import tn.esprit.spring.entities.SearchCriteria;

@Repository
public interface EventRepository extends CrudRepository<tn.esprit.spring.entities.Events,Integer> {
	
	
	
	
	
	@Query("select a from Events a where lower(a.EventTitle) like lower(concat('%', :fil,'%'))")
    public List<Events> fil(@Param("fil") String abc);
	

	
	
	@Query("SELECT t FROM Events t WHERE " +
            "LOWER(t.EventTitle) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
            "LOWER(t.EventDescription) LIKE LOWER(CONCAT('%',:searchTerm, '%'))  ")
   public List<Events> search(@Param("searchTerm") String abc);

	   
	
}
