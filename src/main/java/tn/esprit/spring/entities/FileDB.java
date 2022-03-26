package tn.esprit.spring.entities;

import java.sql.Time;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

@Entity
public class FileDB {
	@Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private int id;
	  private String name;
	  private String idnews;
	  private String type;
	  @Lob
	  private byte[] data;
	  public FileDB() {
	  }
	  public FileDB(String name,String idnews ,String type, byte[] data) {
	    this.name = name;
	    this.type = type;
	    this.data = data;
	    this.idnews = idnews ;
	  }
	  
	  @ManyToMany(cascade = CascadeType.ALL)
		private  Set<News> nes;
}
