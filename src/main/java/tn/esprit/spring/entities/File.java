package tn.esprit.spring.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

 


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "files")
public class File {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idfile ;
    private String name;
    private String type;
    
    
    @Lob
    private byte[] data;
 


 
    @ManyToOne
    User user;

    

public File(String name,  String type,byte[] data) {
	
	this.name = name;
	this.data = data;
	this.type = type;
}



public File(User user) {
	super();
	this.user = user;
}


}