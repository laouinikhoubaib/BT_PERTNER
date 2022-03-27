package tn.esprit.spring.service.email;

import  org.springframework.beans.factory.annotation.Autowired ;
import  org.springframework.mail.SimpleMailMessage ;
import  org.springframework.mail.javamail.JavaMailSender ;
import  org.springframework.scheduling.annotation.Async ;
import  org.springframework.stereotype.Service ;

import tn.esprit.spring.dto.user.Mail;

@Service
public class EmailServiceImpl implements EmailService {

	private JavaMailSender javaMailSender;
	


    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

	@Override
	@Async
	public void sendCodeByEmail(Mail mail) {
		  SimpleMailMessage mailMessage = new SimpleMailMessage();
		    mailMessage.setTo(mail.getTo());
		    mailMessage.setSubject("Code Active");
		    mailMessage.setFrom("bttravel2022@gmail.com");
		    mailMessage.setText(mail.getCode());
		    javaMailSender.send(mailMessage);
		
	}
   
}
