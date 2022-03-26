package tn.esprit.spring.entities;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Eventparticipants {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int i;
	private String idEv;
	private String iduser;

	private String useremail;
	private String userename;
	private LocalDate inscriptiondate ;

}
