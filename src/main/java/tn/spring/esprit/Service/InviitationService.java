package tn.spring.esprit.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import tn.spring.esprit.Repository.InvitatioRepository;
import tn.spring.esprit.Repository.UserRepository;
import tn.spring.esprit.entities.Invitation;
import tn.spring.esprit.entities.Invitationstatus;
import tn.spring.esprit.entities.Invitationtype;
import tn.spring.esprit.entities.User;

@Service
public class InviitationService implements IInvitationService {
	@Autowired
	InvitatioRepository invr;
	@Autowired
	UserRepository repositoryuser;
	@Autowired
	private Email_Sender_Service emailSenderService;

	public void saveInvitation(Invitation invitation,int user_id) {
		invr.save(invitation);
		affecterUser(invitation.getInvitation_id(), user_id);
		
	

	}

	@Override
	public void deleteInvitation(Long Invitation_id) {
		invr.deleteById(Invitation_id);

	}

	@Override
	public List<Invitation> retrieveAllInvitations() {

		return invr.findAll();
	}

	@Override
	public Invitation retrieveInvitation(Long IdInvi) {
		// TODO Auto-generated method stub

		return invr.findById(IdInvi).orElse(null);
	}

	@Override
	public Invitation updateInvitation(Long IdInvi, Invitation inv) {
		// TODO Auto-generated method stub

		Invitation invitToupdate = invr.findById(IdInvi).orElse(null);
	
		invitToupdate.setSendingDate(inv.getSendingDate());
		invitToupdate.setInvitationBody(inv.getInvitationBody());
		invitToupdate.setInvitationTitle(inv.getInvitationTitle());
		invitToupdate.setInvitationType(inv.getInvitationType());

		return invr.save(invitToupdate);
	}

	@Override
	public void sendTripInvitation(Long idInvit, Long idemployer) {
		 
	
		
		// TODO Auto-generated method stub
		Invitation invitTosend = invr.findById(idInvit).orElse(null);

		User user = repositoryuser.findById(idemployer).get();
		invitTosend.getUsers().add(user);
		invr.save(invitTosend);
		if (invitTosend.getInvitationType().equals(Invitationtype.Trip_invitation)) {

			

				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(user.getEmail());
				mailMessage.setSubject(invitTosend.getInvitationTitle());
				mailMessage.setFrom("spring.boot.from1@gmail.com");
				mailMessage.setText(invitTosend.getInvitationBody());

				emailSenderService.sendEmail(mailMessage);
				


		}

	}

	@Override
	public List<Invitation> findBetweenDate(LocalDateTime start, LocalDateTime end) {
		

		return invr.findBetweenDate(start, end).stream().sorted(Comparator.comparing(Invitation::getInvitation_id))
				.collect(Collectors.toList());
	}

	@Override
	public List<Invitation> filter(String filter) {
		

		return invr.findByFilter(filter).stream().sorted(Comparator.comparing(Invitation::getInvitation_id))
				.collect(Collectors.toList());
	}

	@Override
	public void TraiterInvitation(String status, Long id) {
		
		Invitation invitTotraiter = invr.findById(id).orElse(null);

		invitTotraiter.setInvitationStatus(Invitationstatus.valueOf(status));
		invr.save(invitTotraiter);
		

	}

	@Override
	public boolean addInvitations(List<Invitation> invs) {
		
		invr.saveAll(invs);
		return true;
	}
	@Override
	public List<Invitation> getAllByStatus(Invitationstatus ins)
	{
		return invr.findByInvitationStatus(Invitationstatus.Accepted);
	}
	
	@Override
	public List<Invitation>getAllNonAccepted()
	{
		
		return invr.findByInvitationStatus(Invitationstatus.Refused);
	}
	
	
	@Override
	public float tauxDeReussite()
	{
		List<Invitation> total = invr.findAll();
		List<Invitation> reussit = invr.findByInvitationStatus(Invitationstatus.Accepted);
		float sizeR=reussit.size();
		float sizeT=total.size();
		float taux = (sizeR/sizeT)*100;
		
		
		return taux;
	}
	@Override
	public float tauxDeRefu()
	{
		List<Invitation> total = invr.findAll();
		List<Invitation> reussit = invr.findByInvitationStatus(Invitationstatus.Refused);
		float sizeR=reussit.size();
		float sizeT=total.size();
		float taux = (sizeR/sizeT)*100;
		
		
		return taux;
	}
	public void affecterUser(long idInvit, long user_id) {
		User user = repositoryuser.findById( user_id).orElse(null);
		Invitation invit =invr.findById( idInvit).orElse(null);
		if(user.getInvitation()==null)
		{
			Set<Invitation> invits =new HashSet<>();
			invits.add(invit);
			user.setInvitation(invits);
			repositoryuser.save(user);
			
			
		}else
			user.getInvitation().add(invit);	}
			
	}

	






