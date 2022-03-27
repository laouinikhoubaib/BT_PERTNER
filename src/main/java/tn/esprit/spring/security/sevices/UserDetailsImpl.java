package tn.esprit.spring.security.sevices;

import java.util.Collection;
import java.util.Objects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import tn.esprit.spring.entities.User;



public class UserDetailsImpl implements UserDetails {
	
	


	private static final long serialVersionUID = 1L;
	User user;
	private Long id;
	private String username;
	private String email;
	
	
	@JsonIgnore
	  private String password;
	
	
	  private Collection<? extends GrantedAuthority> authorities;
	  
	  
		

	public UserDetailsImpl(Long id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}



	public UserDetailsImpl(User user) {
		super();
		this.user = user;
	}



	@Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    return authorities;
	  }
	  
	  
	  @Override
	    public String getPassword() {
	        return user.getPassword();
	    }

	    @Override
	    public String getUsername() {
	        return user.getEmail();
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return user.getActive() == 1;
	    }
	
	    
	  @Override
	  public boolean equals(Object o) {
	    if (this == o)
	      return true;
	    if (o == null || getClass() != o.getClass())
	      return false;
	    UserDetailsImpl user = (UserDetailsImpl) o;
	    return Objects.equals(id, user.id);
	  }
	}