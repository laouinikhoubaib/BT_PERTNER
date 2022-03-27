package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.CSV;

@Repository
public interface CsvRepository extends JpaRepository<CSV,Long>{
	
	List<CSV> findByPublished(boolean published);
	List<CSV> findByTitleContaining(String title);

}
