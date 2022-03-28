package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Accommodation;
import tn.esprit.spring.entities.Reservation;
import tn.esprit.spring.entities.Transport;
import tn.esprit.spring.inteface.IAccomodationService;
import tn.esprit.spring.repository.AccommodationRepository;
import tn.esprit.spring.repository.ReservationRepository;
@Service
public class AccommodationService implements IAccomodationService {
@Autowired
AccommodationRepository ARepo;
@Autowired
ReservationRepository Rrepo;
	@Override
	public Accommodation addAccommodation(Accommodation accommodation) {
		// TODO Auto-generated method stub
		Accommodation acco = ARepo.save(accommodation);
		return acco;
	}

	@Override
	public void deleteAccommodation(Integer id) {
		// TODO Auto-generated method stub
		ARepo.deleteById(id);
		
	}

	@Override
	public void updateAccommodation(Accommodation newAccommodation, int idAccommodation) {
		// TODO Auto-generated method stub
		Accommodation acc = ARepo.findById(idAccommodation).orElse(null);
		acc.setAccommodationLocation(newAccommodation.getAccommodationLocation());
		acc.setAccommodationPrice(newAccommodation.getAccommodationPrice());
		acc.setAccommodationType(newAccommodation.getAccommodationType());
		
		ARepo.save(acc);
	}

	@Override
	public List<Accommodation> retrieveAllAccommodations() {
		// TODO Auto-generated method stub
		return (List<Accommodation>) ARepo.findAll();
	}

	

}
