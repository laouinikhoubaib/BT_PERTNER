package tn.spring.esprit.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
public class News {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private int News_id;
	private String NewsTitle;
	private String NewsDescription;
	@Temporal(TemporalType.DATE)
	private Date NewsDate;
	private float NewsPrice;

	@ManyToOne()
	@JsonIgnore
	private User user;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy="news")
	@JsonIgnore
	private Payment payment;
	
	
}
