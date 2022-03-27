package tn.esprit.spring.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.security.sevices.UserDetailsImpl;



@Service
public class UserServiceAuth implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceAuth(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        System.out.println(user.getEmail() + "         " +user.getPassword());
        UserDetailsImpl userPrincipal = new UserDetailsImpl(user);
        return userPrincipal;
    }

    @Transactional
    public void addUser(User user){
        userRepository.save(user);
    }

    public boolean ifEmailExist(String email){
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public int getUserActive(String email){
        return userRepository.getActive(email);
    }

    @Transactional
    public String getPasswordByEmail(String email){
        return userRepository.getPasswordByEmail(email);
    }

    public User getUserByMail(String mail){
        return this.userRepository.findByEmail(mail);
    }
    public void editUser(User user){
        this.userRepository.save(user);
    }
}
