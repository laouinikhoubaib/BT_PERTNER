package tn.esprit.spring.repository;

import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.File;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface FileDBRepository extends JpaRepository<File, Integer> {
	

}
