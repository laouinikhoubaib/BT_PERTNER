package tn.esprit.spring.entities;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
//import org.hibernate.validator.constraints.NotBlank;
//import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(	name = "user", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})

public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@NotBlank
	@Size(max = 20)
	private String username;
	//@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	//@NotBlank
	@Size(max = 120)
	private String password;
	private String firstname;
	private String lastname;
	private String address;
	private int phonenumber;
	private String passportid;
	private int cin;
	private boolean isEnabled;
	private int active;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles")
	@JoinColumn(name = "user_id") 
	@JoinColumn(name = "role_id")
	private Set<Role> roles = new HashSet<>();
	
   @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
   @JoinColumn(name = "code_id")
   private Code code;

   @OneToMany(cascade = CascadeType.ALL) 
   private Set<File> file;
   
   @OneToMany(cascade = CascadeType.ALL) 
   private Set<CSV> csv;
    
    public User(String username,String email,
			String password) {
	
		this.username = username;
		this.email = email;
		this.password = password;
	
	}


	
}


