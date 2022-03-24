package tn.spring.esprit.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

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
public class Events {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private int Event_id;
	@Temporal(TemporalType.DATE)
	private Date EventDate;
	private String EventTitle;
	private String EventDescription;
	
	@ManyToOne()
	@JsonIgnore
	private User user;




}
