package tn.esprit.spring.entities;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Events {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Event_id;
	@Temporal(TemporalType.DATE)
	private Date EventDate;
	private String EventTitle;
	private String EventDescription;
	private int nbmax;
	private int nb;
	private Time EventDuration ;
	private String lieu;
	private String type;
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<User> users;
	

}
