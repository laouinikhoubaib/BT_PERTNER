package tn.spring.esprit.entities;



import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class Reservation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private int Reservation_id;
	

	@ManyToOne()
	@JsonIgnore
	private User user;
	
	
	@ManyToOne()
	@JsonIgnore
	private Trip trip;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="reservation")
	@JsonIgnore
	private Set<Transport>transport;

	@OneToMany(cascade = CascadeType.ALL,mappedBy="reservation")
	@JsonIgnore
	private Set<Accommodation>accommodation;
}
