package tn.esprit.spring.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Accommodation;
import tn.esprit.spring.entities.Reservation;
import tn.esprit.spring.entities.Transport;
import tn.esprit.spring.inteface.IReservationService;
import tn.esprit.spring.repository.AccommodationRepository;
import tn.esprit.spring.repository.ReservationRepository;
import tn.esprit.spring.repository.TransportRepository;

@Service
public class ReservationService implements IReservationService {
	@Autowired
	ReservationRepository Rrepo;
	@Autowired
	AccommodationRepository Arepo;
	@Autowired
	TransportRepository Trepo;
	@Override
	public Reservation addReservation(Reservation R) {
		// TODO Auto-generated method stub
		Reservation reservation = Rrepo.save(R);
		return reservation;
	}

	@Override
	public void deleteReservation(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateReservation(Reservation newReservation, int idComplaint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reservation> retrieveAllComplaints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float TotalReservation(Integer idReservation) throws Exception {
		//return Rrepo.TotalReservation(idReservation);
		float totalPrice=0;
		int a = Arepo.GetIdAccomodationByReservation(idReservation);
		Optional<Accommodation> acc=Arepo.findById(a);
		
		int t=Trepo.GetIdTransportByReservation(idReservation);
		Optional<Transport> tr = Trepo.findById(t);
		float priceAccomodation = 0;
		float priceTransport = 0;
       //float priceA= Rrepo.AccomodationByIdReservation(idReservation);
       //float priceT=Rrepo.PriceTransportByIdReservation(idReservation);
       //Totalprice=priceA+priceT;
		if(!Objects.isNull(acc))
		{

			priceAccomodation = acc.get().getAccommodationPrice();
		}
		else throw new Exception("this accomodation doesn't exist");
		
		if(!Objects.isNull(tr)) {
			priceTransport = tr.get().getTransportPrice();
		}
		else throw new Exception("this Transport doesn't exist");
		
		totalPrice = priceTransport+priceAccomodation;
		
	
		
			return totalPrice;
	
	 	
	}

	

}
