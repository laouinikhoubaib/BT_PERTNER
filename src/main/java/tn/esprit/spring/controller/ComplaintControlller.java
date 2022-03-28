package tn.esprit.spring.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Complaint;
import tn.esprit.spring.entities.ComplaintStatus;
import tn.esprit.spring.inteface.IComplaintService;

@RestController
@RequestMapping("/ComplaintController")
public class ComplaintControlller {
	@Autowired
	IComplaintService ComplaintService;
	@PostMapping("/AddComplaint")
	@ResponseBody
	public Complaint addComplaint( @RequestBody Complaint T) {
		return ComplaintService.addComplaint(T);
		
	}
	@DeleteMapping("/deleteComplaint/{Complaint_id}")  
	public void deleteComplaint(@PathVariable("Complaint_id") Integer id) {
		ComplaintService.deleteComplaint(id);
		
	}
	@PutMapping("/updateCompalaint/{Compalaint-id}") 
	public void updateComplaint(@RequestBody Complaint newcomplaint, @PathVariable("Compalaint-id") int idComplaint) {
		ComplaintService.updateComplaint(newcomplaint, idComplaint);
	}

	@GetMapping("/retrieveAllComplaints")
	public List<Complaint> retrieveAllComplaints()
	{
		return ComplaintService.retrieveAllComplaints();
	}
	@GetMapping("/retrieveAllComplaintsByStatus/{Status}")
	public List<Complaint> retrieveAllComplaintsByStatus(@PathVariable("Status") ComplaintStatus Status){
		return ComplaintService.retrieveAllComplaintsByStatus(Status);
	}
	@GetMapping("/countUntreatedComplaints")
	public int CountUntreatedComplaint() {
		return ComplaintService.CountUntreatedComplaint();
	}
	@PutMapping("/updateUntreatedComplaint/{Compalaint-id}")
	@Transactional
	public Complaint updateComplaint2(@PathVariable(value ="Compalaint-id")Integer id) throws MessagingException {
		return ComplaintService.updateComplaint2(id);
	}
}
