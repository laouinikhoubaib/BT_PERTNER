package tn.esprit.spring.inteface;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.dto.TopTrip;
import tn.esprit.spring.entities.Payment;
import tn.esprit.spring.entities.Trip;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.Visa;

public interface ITripService  {
	public Trip addTrip(Trip T);
	public void deleteTrip(Integer id);
	public void updateTrip(Trip newtrip, int idTrip);
	public List<Trip> retrieveAllTrips();
	public List<Trip> ShowListOfTripsByDestination(String TripDestination);
	public int numberOfTripsByDestination(String TripDestination);
	public int TopTripsByUser();
	public Integer getRevenuByTrip(Integer idTrip);
	public void affecterPartnertoTrip(Integer iduser, Integer idTrip);
	public List<Trip> SearchTrips(Date d1,Date d2,String name);
	int GetNumberOfTripsByDate(Date d1,Date d2);
	public List<Trip> ListOfTrips();
	public List<Trip> List(Integer idUser);
	public List <Trip> RetrieveAllTripsOfOneByUser(Integer iduser);
	public int GetTripByObject(String tripObject);
	///public List<TopTrip> TopTripByDestination();
	public void Match(Integer idUser,Integer idTrip );
	public Visa UpdateVisa(Integer id);
	public Visa addVisa(MultipartFile uploadFile, Integer idVisa) throws IllegalStateException, IOException;
	public Payment addPayment(Payment p);
	public List<Payment> RetrieveAllPayments();
	public List<Payment> listPaymentByUser(Integer iduser);
	
}
