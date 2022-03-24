package tn.spring.esprit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.spring.esprit.entities.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{
}
