package tn.spring.esprit.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class Trip {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private int Trip_id;
	private String TripDestination;
	private int TripDuration;
	@Temporal(TemporalType.DATE)
	private Date TripDeparature;
	@Temporal(TemporalType.DATE)
	private Date TripArrival;
	private String TripTitle;
	private float TripPrice;
	
	@ManyToMany()
	@JsonIgnore
	private Set<User> user;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="trip")
	@JsonIgnore
	private Set<Reservation>reservation;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="trip")
	@JsonIgnore
	private Set<Complaint>complaint;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="trip")
	@JsonIgnore
	private Set<Invitation>invitation;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy="trip")
	@JsonIgnore
	private Note note;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="trip")
	@JsonIgnore
	private Set<Notice>notice;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy="trip")
	@JsonIgnore
	private Visa visa;
	
	
	
}
