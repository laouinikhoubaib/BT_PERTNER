package tn.spring.esprit.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.spring.esprit.entities.Invitation;
import tn.spring.esprit.entities.Invitationstatus;

@Repository
public interface InvitatioRepository extends JpaRepository<Invitation, Long> {
	
	
    @Query("select i from Invitation i where lower(i.invitationTitle) like lower(concat('%', :filter,'%'))")
    public List<Invitation> findByFilter(@Param("filter") String filter);
    
    
    @Query("select i from Invitation i where i.sendingDate>=:start and i.sendingDate<=:end")
    public List<Invitation> findBetweenDate(@Param("start")LocalDateTime start,@Param("end") LocalDateTime end);
    List<Invitation> findByInvitationStatus(Invitationstatus invitationStatus);

}
