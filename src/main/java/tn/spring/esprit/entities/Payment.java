package tn.spring.esprit.entities;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Payment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private int Transaction_id;
	@JsonIgnore
	private int CreditCardNumber;
	@JsonIgnore
	private int Password;
	@JsonIgnore
	private float Amount;
	@JsonIgnore
	@Temporal(TemporalType.DATE)
	private Date TransactionDate;
	@JsonIgnore
	@Enumerated(EnumType.STRING)
	private CardType cardtype;
	
	@ManyToOne()
	@JsonIgnore
	private User user;
	
	@OneToOne
	@JsonIgnore
	private News news;

}
