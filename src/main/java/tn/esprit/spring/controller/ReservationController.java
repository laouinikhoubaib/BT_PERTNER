package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Reservation;
import tn.esprit.spring.inteface.IReservationService;

@RestController
@RequestMapping("/ReservationController")
public class ReservationController {
	@Autowired
	IReservationService Reservationservice;
	@PostMapping("/AddReservation")
	@ResponseBody
	public Reservation addReservation( @RequestBody Reservation R) {
		return Reservationservice.addReservation(R);
		
	}
	@GetMapping("/TotalReservation/{idReservation}")
	public float TotalReservation(@PathVariable ("idReservation")Integer idReservation) throws Exception{
		return Reservationservice.TotalReservation(idReservation);
		
	}
}
