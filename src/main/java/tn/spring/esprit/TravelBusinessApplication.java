package tn.spring.esprit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import tn.spring.esprit.Service.IInvitationService;
@EnableScheduling
@SpringBootApplication
public class TravelBusinessApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(TravelBusinessApplication.class, args);
	}

}
