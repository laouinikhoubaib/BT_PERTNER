package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Accommodation;
import tn.esprit.spring.inteface.IAccomodationService;

@RestController
@RequestMapping("/AccomodationController")
public class AccomodationController {
	@Autowired
	IAccomodationService AccommodationService;
	@PostMapping("/AddAccommodation")
	@ResponseBody
	public Accommodation addAccommodation( @RequestBody Accommodation accommodation) {
		return AccommodationService.addAccommodation(accommodation);
	}
	@DeleteMapping("/deleteAccommodation/{Accommodation_id}")  
	public void deleteAccommodation(@PathVariable("Accommodation_id")Integer id) {
		AccommodationService.deleteAccommodation(id);
	}
	@PutMapping("/deleteAccommodation/{Accommodation_id}") 
	public void updateAccommodation(@RequestBody Accommodation newAccommodation,@PathVariable("Accommodation_id") int idAccommodation) {
		AccommodationService.updateAccommodation(newAccommodation, idAccommodation);
	}

    @GetMapping("/retrieveAllAccommodations")
    public List<Accommodation> retrieveAllAccommodations(){
	return AccommodationService.retrieveAllAccommodations();
}

}
