package tn.spring.esprit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.spring.esprit.entities.Chat;

@Repository 
public interface ChatRepository extends JpaRepository<Chat, Integer> {
	

}
