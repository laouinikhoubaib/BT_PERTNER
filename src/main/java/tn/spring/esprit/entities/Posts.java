package tn.spring.esprit.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Posts {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private int Post_id;
	@Temporal(TemporalType.DATE)
	private Date PostDate;
	private String PostContent;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int CountLike;
	@Enumerated(EnumType.STRING)
	private Dictionary dictionary ;
	
	@ManyToOne()
	@JsonIgnore
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="posts")
	@JsonIgnore
	private Set<Comments>comments;
	
	
}
