package tn.spring.esprit.entities;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter  
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Invitation {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long invitation_id;
	
	private String invitationTitle;
	private String invitationBody;
	
	@Enumerated(EnumType.STRING)
	Invitationstatus invitationStatus ;
	
	private LocalDateTime sendingDate=LocalDateTime.now();
	@Enumerated(EnumType.STRING)
	Invitationtype invitationType ;
	
	@ManyToMany
	@JsonIgnore
	private List<User> users;
	
	@ManyToOne
	@JsonIgnore
	private Trip trip;
}

