package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Events;
import tn.esprit.spring.entities.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository <FileDB, Integer> {
	@Query("select a from FileDB a where lower(a.idnews) like lower(concat('%', :fil,'%'))")
    public List<FileDB> showdoc(@Param("fil") String abc);
}
