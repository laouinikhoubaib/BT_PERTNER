package tn.esprit.spring.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import tn.esprit.spring.entities.Complaint;
import tn.esprit.spring.entities.ComplaintStatus;
import tn.esprit.spring.entities.Trip;
import tn.esprit.spring.inteface.IComplaintService;
import tn.esprit.spring.repository.ComplaintRepository;

@Service
public class ComplaintService implements IComplaintService  {
@Autowired
ComplaintRepository Crepo;
@Autowired
EmailSenderService email;
	@Override
	public Complaint addComplaint(Complaint T) {
		// TODO Auto-generated method stub
         Complaint complaint = Crepo.save(T);
		
		return complaint;
	}

	@Override
	public void deleteComplaint(Integer id) {
		// TODO Auto-generated method stub
		
		Crepo.deleteById(id);
	}
	
	

	@Override
	public void updateComplaint(Complaint newcomplaint, int idComplaint) {
		// TODO Auto-generated method stub
		Complaint c = Crepo.findById(idComplaint).orElse(null);
		
		c.setComplaint_date(newcomplaint.getComplaint_date());
		c.setComplaint_description(newcomplaint.getComplaint_description());
		c.setComplaintType(newcomplaint.getComplaintType());
		Crepo.save(c);
		
	}

	@Override
	public List<Complaint> retrieveAllComplaints() {
		// TODO Auto-generated method stub
		return  (List<Complaint>) Crepo.findAll();
	}

	@Override
	public int countComplaint() {
		// TODO Auto-generated method stub
		return Crepo.countComplaint();
		
	
		
	}

	@Override
	public List<Complaint> retrieveAllComplaintsByStatus(ComplaintStatus Status) {
	
		return Crepo.retrieveAllComplaintsByStatus(Status);
	}

	@Override
	public int CountUntreatedComplaint() {
		
		return Crepo.CountUntreatedComplaint();
	}

	@Override
	public Complaint updateComplaint2(Integer id) throws MessagingException  {
     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
     LocalDateTime now = LocalDateTime.now();  
	 Complaint oldComplaint = Crepo.getOne(id);
	 
	 String Email=oldComplaint.getUser().getEmail();
	 String fname=oldComplaint.getUser().getFirstName();
	 String lname=oldComplaint.getUser().getLastName();
	 Date requestDate= (Date) oldComplaint.getComplaint_date();
	 if (oldComplaint.isEtat()==false)
	 {
	 oldComplaint.setComplaint_status(ComplaintStatus.treated);
	 oldComplaint.setEtat(true);
	// email.sendSimpleMessage(Email, "Check in your Complaint requested on "+requestDate+""+ "with id" +oldComplaint.getComplaint_id(),
			// "Hello Mr/Mrs" + "" +fname+ "" +lname+"your complaint is treated succefully on"+""+now);
	 email.sendSimpleMessage(Email, "Check in your Complaint requested on "+requestDate+""+ "with id" +oldComplaint.getComplaint_id(),
			 "Hello Mr/Mrs" + "" +fname+ "" +lname+"your complaint is treated succefully on"+""+now,"/Users/msi/Pictures/arwa.jpg");
	 }
	 
	 return Crepo.save(oldComplaint);
	}

}
