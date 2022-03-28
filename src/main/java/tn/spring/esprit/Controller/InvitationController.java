package tn.spring.esprit.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;

import tn.spring.esprit.Service.InviitationService;
import tn.spring.esprit.entities.Invitation;
import tn.spring.esprit.entities.Invitationstatus;

@RestController
@RequestMapping("/invit")
public class InvitationController {
	@Autowired
	InviitationService invr;
	
	
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;
	


	
	@PostMapping("/addlot")
	public void handle() throws Exception {

		jobLauncher.run(job, new JobParameters());
	}

	
	
	@DeleteMapping("/delete/{IdInvi}")
	@ResponseBody
	public void deleteInvitation(@PathVariable("IdInvi") Long Invitation_id) {
		invr.deleteInvitation(Invitation_id);
	}

	@PostMapping("/save/{user_id}")
	public void sendInvitation(@RequestBody Invitation invitation,@PathVariable("user_id")int user_id) {
		invr.saveInvitation(invitation, user_id);
	}

	@GetMapping("/retrieve-all")

	public List<Invitation> getInvitations() {
		return invr.retrieveAllInvitations();
	}

	@GetMapping("/get/{IdInvi}")
	public Invitation getInvitation(@PathVariable("IdInvi") Long invitationId) {
		return invr.retrieveInvitation(invitationId);
	}

	@PutMapping("/update/{IdInvi}")
	public Invitation updateInvitation(@PathVariable("IdInvi") Long id, @RequestBody Invitation I) {
		return invr.updateInvitation(id, I);
	}


	@GetMapping("/between")
	public ResponseEntity<?> findBetweenDate(@RequestParam String start, @RequestParam String end) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime s = LocalDateTime.parse(start, formatter);
		LocalDateTime e = LocalDateTime.parse(end, formatter);

		return new ResponseEntity<>(invr.findBetweenDate(s, e), HttpStatus.OK);
	}
	@GetMapping("/filter")
	public List<Invitation> filter(@RequestParam String filter)
	{
	return 	invr.filter(filter);
	}
	
	
	
	@PutMapping("/traiter/{id}")
	public void TraiterInvitation(@RequestParam String status ,@PathVariable  Long id)
	{
		 	invr.TraiterInvitation(status, id);
	}
	//@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@PutMapping("/sendTripInvitation/{idInvit}/{idemployee}")
	public void sendTripInvitation(@PathVariable Long idInvit,@PathVariable Long idemployee)
	{
		invr.sendTripInvitation(idInvit, idemployee);
	}
	@GetMapping("/allStatus")
	public List<Invitation>getAllS(Invitationstatus in){
		return invr.getAllByStatus(Invitationstatus.Accepted);
	}
	
	@RequestMapping(value="/Statistics",method = RequestMethod.GET)
	public String statistiques()
	{
	
		
		String msg="Taux de Reussite des invitations : "+invr.tauxDeReussite()+"%";
		
		
		
		return msg;
		
	}
	@RequestMapping(value="/Statistic",method = RequestMethod.GET)
	public String statistique()
	{
	
		
		String msg="Taux de Refu des invitations : "+invr.tauxDeRefu()+"%";
		
		
		
		return msg;
		
	}
	
	

}
