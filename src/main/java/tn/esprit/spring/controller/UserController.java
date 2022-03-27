package tn.esprit.spring.controller;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.service.user.UserSevice;




@RestController
public class UserController {

	@Autowired
	UserSevice us;
	
	@PostMapping("/ajouteruser")
	@ResponseBody
	public void ajouterUser(@RequestBody  User u){
		us.ajouterUser(u);
	}
	
    @GetMapping("/AfficherUser")  
    private List<User> getAllUser()   
    {  
    return us.getallUser();  
    } 
    
    @GetMapping("/AfficherUserById/{userid}")  
    private User getUserById(@PathVariable("userid") int id)
    {  
    return us.getUserById(id);  
    }  
    
    @PutMapping("/UpdateUser") 
    @ResponseBody
    private User update(@RequestBody User user,int id)   
    {  
    	us.update(user,id);  
    	return user;
     
    }  
    @DeleteMapping("/delete/{userid}")  
    private void deleteUser(@PathVariable("userid") int id)   
    {  
    us.delete(id);  

    }  
    
}
