package tn.spring.esprit.entities;



import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import tn.spring.esprit.entities.Comments;
import tn.spring.esprit.entities.Complaint;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private Long User_id;
	private String FirstName;
	private String LastName;
	private String Login;
	private String Password;
	private String Email;
	private String Address;
	private String PhoneNumber;
	private String Pictures;
	private String Passport_id;
	private int cin;
	@Enumerated(EnumType.STRING)
	domain Domain ;
	@Enumerated(EnumType.STRING)
	role Role ;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="user")
	@JsonIgnore
	private Set<Complaint>complaint;
	
	@ManyToOne()
	@JsonIgnore
	private Chat chat;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="user")
	@JsonIgnore
	private Set<Posts>posts;

	@OneToMany(cascade = CascadeType.ALL,mappedBy="user")
	@JsonIgnore
	private Set<Comments>comments;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="user")
	@JsonIgnore
	private Set<Payment>payment;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="user")
	@JsonIgnore
	private Set<News>news;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="user")
	@JsonIgnore
	private Set<Events>events;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="user")
	@JsonIgnore
	private Set<Reservation>reservation;
	
	@ManyToMany()
	@JsonIgnore
	private Set<Trip> trip;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="user")
	@JsonIgnore
	private Set<Visa>visa;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="user")
	@JsonIgnore
	private Set<Note>note;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="user")
	@JsonIgnore
	private Set<Notice>notice;
	
	@ManyToMany(cascade = CascadeType.ALL,mappedBy="users")
	@JsonIgnore
	private Set<Invitation> invitation;
}
