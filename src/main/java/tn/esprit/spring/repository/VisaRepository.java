package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.Visa;

public interface VisaRepository extends JpaRepository<Visa, Integer> {

}
