package tn.esprit.spring.service.user;

import java.util.List;
import tn.esprit.spring.entities.User;



public interface UserSevice  {

	public User ajouterUser(User c) ;
	 public void addUser(User user);
	public List<User> getallUser();
    public void delete(int id);
    public void update(User user,int id);
    public void editUser(User user);
    public User getUserById(int id);
    public User getUserEmail(String email);
    public Boolean ifEmailExist(String email);
    public String getPasswordByEmail(String email);
    public User getUserByMail(String mail);
    public int getUserActive(String email);

    

 
}
