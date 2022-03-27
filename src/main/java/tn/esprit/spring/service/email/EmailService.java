package tn.esprit.spring.service.email;

import org.springframework.mail.SimpleMailMessage;

import tn.esprit.spring.dto.user.Mail;



public interface EmailService{
	
	
	 public void sendCodeByEmail(Mail mail);
	 public void sendEmail(SimpleMailMessage email);


}
