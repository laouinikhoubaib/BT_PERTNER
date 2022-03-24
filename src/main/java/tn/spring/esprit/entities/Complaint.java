package tn.spring.esprit.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Complaint {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private int Complaint_id;
	private String Complaint_description;
	@Temporal(TemporalType.DATE)
	private Date Complaint_date;
	@Enumerated(EnumType.STRING)
	complainttype ComplaintType ;
	
	@ManyToOne()
	@JsonIgnore
	private User user;
	
	@ManyToOne()
	@JsonIgnore
	private Trip trip;
	
}
