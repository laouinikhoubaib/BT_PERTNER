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
public class Invitation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private int Invitation_id;
	@Enumerated(EnumType.STRING)
	invitationstatus InvitationStatus ;
	@Temporal(TemporalType.DATE)
	private Date SendingDate;
	@Enumerated(EnumType.STRING)
	invitationtype InvitationType ;
	
	@ManyToOne()
	@JsonIgnore
	private User user;
	
	@ManyToOne()
	@JsonIgnore
	private Trip trip;
}

