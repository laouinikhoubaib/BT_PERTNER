package tn.esprit.spring.inteface;

import java.util.List;

import tn.esprit.spring.entities.Accommodation;


public interface IAccomodationService {
	public Accommodation addAccommodation( Accommodation accommodation);
	public void deleteAccommodation(Integer id);
	public void updateAccommodation( Accommodation newAccommodation, int idAccommodation);
	public List<Accommodation> retrieveAllAccommodations();
	

}
