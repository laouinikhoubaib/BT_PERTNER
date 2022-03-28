package tn.spring.esprit.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.spring.esprit.entities.User;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
//	@Query("select User u where u.Email:=email")
//    User findByEmail(String email);
}
