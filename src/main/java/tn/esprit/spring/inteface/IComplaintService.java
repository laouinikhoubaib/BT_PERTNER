package tn.esprit.spring.inteface;

import java.util.List;

import javax.mail.MessagingException;

import tn.esprit.spring.entities.Complaint;
import tn.esprit.spring.entities.ComplaintStatus;


public interface IComplaintService {
	public Complaint addComplaint( Complaint T);
	public void deleteComplaint(Integer id);
	public void updateComplaint( Complaint newcomplaint, int idComplaint);
	public Complaint  updateComplaint2(Integer id) throws MessagingException ;
	public List<Complaint> retrieveAllComplaints();
	public int countComplaint();
	public List<Complaint> retrieveAllComplaintsByStatus(ComplaintStatus status);
	public int  CountUntreatedComplaint();
	
}
