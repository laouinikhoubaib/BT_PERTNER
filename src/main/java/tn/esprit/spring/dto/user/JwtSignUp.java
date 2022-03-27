package tn.esprit.spring.dto.user;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtSignUp {

    private String email;
    private Set<String> role;
    private String password;
   private String username;
	private String firstname;
	private String lastname;
	private String address;
	private String passportid;
	private int cin;

}