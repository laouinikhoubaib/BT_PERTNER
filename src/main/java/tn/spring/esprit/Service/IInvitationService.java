package tn.spring.esprit.Service;

import java.time.LocalDateTime;
import java.util.List;

import tn.spring.esprit.entities.Invitation;
import tn.spring.esprit.entities.Invitationstatus;




public interface IInvitationService {
	public void sendTripInvitation(Long idInvit, Long idemployee);	
	public void deleteInvitation(Long Invitation_id);
	public List<Invitation> retrieveAllInvitations();
	public Invitation retrieveInvitation(Long IdInvi);
	public Invitation updateInvitation(Long IdInvi, Invitation I);
	public List<Invitation> findBetweenDate(LocalDateTime start, LocalDateTime end);
	public List<Invitation> filter(String filter);
	public void TraiterInvitation(String status,Long id);
	public boolean  addInvitations(List<Invitation> invs) ;
	public List<Invitation> getAllByStatus(Invitationstatus ins);
	public List<Invitation>getAllNonAccepted();
	public float tauxDeReussite();	
	public float tauxDeRefu();
	public void affecterUser(long idInvit, long User_id);
}
