package tn.spring.esprit.entities;

import java.util.Date;

import javax.persistence.Entity;
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
public class Comments {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private int Comment_id;
	@Temporal(TemporalType.DATE)
	private Date CommentDate;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Comment_count_Like;
	private String CommentContent;

	
	@ManyToOne()
	@JsonIgnore
	private User user;
	
	@ManyToOne()
	@JsonIgnore
	private Posts posts;
}
