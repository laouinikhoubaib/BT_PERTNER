package tn.esprit.spring.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/ROLE_EMPLOYEE")
	@PreAuthorize("hasRole('Employee') or hasRole('Company') or hasRole('Admin')")
	public String employeeAccess() {
		return "Employee Content.";
	}
	@GetMapping("/ROLE_COMPANY")
	@PreAuthorize("hasRole('Company')")
	public String companyAccess() {
		return "Company Board.";
	}
	@GetMapping("/ROLE_ADMIN")
	@PreAuthorize("hasRole('Admin')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
