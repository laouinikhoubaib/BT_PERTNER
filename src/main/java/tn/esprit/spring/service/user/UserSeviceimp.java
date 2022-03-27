package tn.esprit.spring.service.user;



import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;





@Service
public class UserSeviceimp implements UserSevice{
	
	
	
	@Autowired
	UserRepository ur ;
	
	
	@PreAuthorize("hasRole('Admin')")
	public User ajouterUser(User c) {
		ur.save(c)	;
		return c;
	}
	
	@PreAuthorize("hasRole('Admin')")
     public List<User> getallUser() {
    	List<User> users = new ArrayList<User>();  
    	ur.findAll().forEach(user -> users .add(user));  
    	return users;  
    }
	
	@PreAuthorize("hasRole('Admin')")
    public void delete(int id)   
    {  
    ur.deleteById((long) id); 
    
    } 
	 
	@PreAuthorize("hasRole('Admin')")
   public void update(User user,int id)   {
    	
	ur.save(user);
		
}
	@PreAuthorize("hasRole('Admin')")
    public User getUserById(int id){
    	
    	return ur.findById((long) id).get();
    }
	
   public Boolean ifEmailExist(String email){
	
	return  ur.existsByEmail(email);
}
   
   @Transactional
   public int getUserActive(String email){
       return ur.getActive(email);
   }
   
   @Transactional
   public String getPasswordByEmail(String email){
       return ur.getPasswordByEmail(email);
   }

   public User getUserByMail(String mail){
       return this.ur.findByEmail(mail);
   }
   
   
  @Transactional 
   public void addUser(User user){
	   
	   ur.save(user);
   }
   
   @Override
   public User getUserEmail(String email) {
	
	return this.ur.findByEmailIgnoreCase(email);
   }

   public void editUser(User user){
    this.ur.save(user);
   }

     
}
