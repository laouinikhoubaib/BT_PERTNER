package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Eventparticipants;
import tn.esprit.spring.entities.Events;
@Repository
public interface EventparticipantsRepository extends CrudRepository<Eventparticipants,Integer> {
	
	@Query("select a from Eventparticipants a where lower(a.idEv) like lower(concat('%', :filee,'%'))")
    public List<Eventparticipants> filee(@Param("filee") String id);
	

	
	@Query("select a from Eventparticipants a where lower(a.idEv) like lower(concat('%', :filee,'%')) and lower(a.iduser) like lower(concat('%', :fila,'%')) ")
    public List<Eventparticipants> fila(@Param("filee") String ide,@Param("fila") String idu);
	
	
	
}
