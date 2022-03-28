package tn.esprit.spring;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tn.esprit.spring.service.EmailSenderService;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class ProjectApplication {
@Autowired
EmailSenderService senderService;
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
@EventListener(ApplicationReadyEvent.class)
public void sendMail() throws MessagingException {
	//senderService.sendSimpleMessage("douiri.arwa1998@gmail.com","Your Complaint", "your complaint is treated");
	//senderService.sendSimpleMessage("douiri.arwa1998@gmail.com","Your Complaint","your complaint is treated","");
}
}
