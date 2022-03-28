package tn.esprit.spring.service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.Payment;
import tn.esprit.spring.entities.Reservation;
import tn.esprit.spring.entities.Trip;
import tn.esprit.spring.entities.TripObject;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.Visa;
import tn.esprit.spring.entities.visastatus;
import tn.esprit.spring.inteface.ITripService;
import tn.esprit.spring.repository.PaymentRepository;
import tn.esprit.spring.repository.ReservationRepository;
import tn.esprit.spring.repository.TripRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.repository.VisaRepository;
@Service
public class TripService implements ITripService {
@Autowired
TripRepository Trepo;
@Autowired
UserRepository Urepo;
@Autowired
ReservationRepository Rrepo;
@Autowired
PaymentRepository Prepo;
@Autowired
VisaRepository Vrepo;
@Autowired
EmailSenderService email;
@Autowired
UploadFile file;


	@Override
	public Trip addTrip(Trip T) {
		
		Trip Trip = Trepo.save(T);
		
		return Trip;
	}
	@Override
	public void deleteTrip(Integer id) {
		
		Trepo.deleteById(id);
	}
	@Override
	public void updateTrip(Trip newtrip, int idTrip) {
		Trip trip = Trepo.findById(idTrip).orElse(null);
		trip.setTripArrival(newtrip.getTripArrival());
		trip.setTripDeparature(newtrip.getTripDeparature());
		trip.setTripDuration(newtrip.getTripDuration());
		trip.setTripPrice(newtrip.getTripPrice());
		trip.setTripObject(newtrip.getTripObject());
		Trepo.save(trip);				
	}
	@Override
	public List<Trip> retrieveAllTrips() {
		return (List<Trip>) Trepo.findAll();
	}
	@Override
	public List<Trip> List(Integer idUser) {
		User u = Urepo.findById(idUser).orElse(null);
		
		/*List<Trip> List=u.get().getTrip();
		List<Float> list1 = new ArrayList<Float>();
		for(Float r : list1)
		{
		list1.add(List.get(0).getTripPrice());
		}
		return list1;*/
		 List<Trip> trips = this.retrieveAllTrips();
		 List<Trip> Usertrips = trips.stream().filter(tr -> tr.getUser().contains(u)).toList();
		
		 return Usertrips ; 
			
	}
	@Override
	public List<Trip> ShowListOfTripsByDestination(String TripDestination) {
		//List<Trip> Trips = Trepo.ShowListOfTripsByDestination(TripDestination);
		return null;
		
		//return Trips;
	}
	@Override
	public int numberOfTripsByDestination(String TripDestination) {
		return Trepo.numberOfTripsByDestination(TripDestination);
		
	}
	@Override
	public int TopTripsByUser() {
		return Trepo.TopTripsByUser();
	}

	@Override
	public Integer getRevenuByTrip(Integer idTrip) {
		Trip t = Trepo.findById(idTrip).orElse(null);
		int nombreResavation =t.getReservation().size();
		
		return (int) (t.getTripPrice()*nombreResavation);
	}
	@Override
	public void affecterPartnertoTrip(Integer iduser, Integer idTrip) {
		Trip t = Trepo.findById(idTrip).orElse(null);
		User p  = Urepo.findById(iduser).orElse(null);
		int nb = t.getUser().size(); 
				
		if(nb<=t.getNbrPartenaire()) 
		{
			
			t.getUser().add(p);
			
		}
		
		else {
			System.err.println("le nombre max est atteint");
		}
		System.err.println("nb="+nb);
	}
	@Override
	public List<Trip> SearchTrips(Date d1, Date d2, String name) {
		
		return Trepo.SearchTrips(d1, d2, name);
	}
	@Override
	public List<Trip> RetrieveAllTripsOfOneByUser(Integer iduser) {
		// TODO Auto-generated method stub
		return Trepo.RetrieveAllTripsOfOneByUser(iduser);
	}
	@Override
	public int GetTripByObject(String tripObject) {
		List <Trip> trips= (List<Trip>) Trepo.findAll();
		int nb=0;
	if(tripObject.equals("Work"))
	{
		nb= (int) trips.stream().filter(t->t.getTripObject().equals(TripObject.Work)).count();
	}
	 if(tripObject.equals("Traineeship"))
	{
		 nb= (int) trips.stream().filter(t->t.getTripObject().equals(TripObject.Traineeship)).count();
		
	}
	 if(tripObject.equals("Seminar"))
	{
		nb=  (int) trips.stream().filter(t->t.getTripObject().equals(TripObject.Seminar)).count();
	}	
				return nb;
	}
//	public HashMap<String, Integer> TopTripByDestination() {
//	//Map<String, Integer> map=new HashMap<String, Integer>();
//		return null;
//				//Trepo.topthreetripps(Sort.by("trip_id").ascending());
//			
//		
//	}
	/*@Override
	public List<TopTrip> TopTripByDestination() {
		return Trepo.topthreetripps();
	}*/
	@Override
	public void Match(Integer idUser, Integer idTrip) {

		List<Reservation> reservation= (List<Reservation>) Rrepo.findAll();
		Trip trip= Trepo.findById(idTrip).get();
		User user = Urepo.findById(idUser).get();
		Reservation reservation1= new Reservation();
		
		int nbr = (int) reservation.stream().filter(e->e.getUser().getUser_id()==idUser && e.getTrip().getTrip_id()==idTrip).count();
		if(reservation.size()==0)
		{
			
		}
	}
	@Override
	public Visa UpdateVisa(Integer id) {
	Visa visa= Vrepo.getOne(id);
	Date requestDate= visa.getApplyDate();
	String Email= visa.getUser().getEmail();
	String lname= visa.getUser().getFirstName();
	String fname = visa.getUser().getLastName();
	visastatus status=visa.getVisaStatus();
	
	if(!visa.getVisaStatus().equals("Accepted"))
	{
		
		visa.setVisaStatus(visastatus.Accepted);
		 email.sendSimpleMessage2(Email, "Check in your Visa requested on "+requestDate+""+ "with id" +visa.getVisa_id(),
				 "Hello Mr/Mrs "+lname+ " " +fname+"your  was "+status+""+"now is it Accepted");
	}
	
		return Vrepo.save(visa);
	}
	@Override
	public Visa addVisa(MultipartFile uploadFile, Integer idVisa) throws IllegalStateException, IOException {
		
		Visa v = Vrepo.findById(idVisa).orElse(null);
	   String url= uploadFile( uploadFile);
		 v.setPhotos(url);
		
		return Vrepo.save(v);
	}
	@Value("${app.upload.dir:${user.home}}")
    public String uploadDirectory;

	private String uploadFile(MultipartFile uploadFile) {
	    String url = "";
	    try {
	     
	     url= Paths.get(uploadDirectory, uploadFile.getOriginalFilename()).toString();
	
	    } catch (Exception e) {
	      url = "Could not upload the file: " + uploadFile.getOriginalFilename() + "!";
	     
	    }
	    return url;
	  }
	@Override
	public int GetNumberOfTripsByDate(Date d1, Date d2) {
		return Trepo.GetNumberOfTripsByDate(d1, d2);
	}
	@Override
	public List<Trip> ListOfTrips() {
		List<Trip> rv= Trepo.ListOfTrips();
		for(Trip r : rv)
		{
		System.out.println("la liste des voyages :"+r.getTripDestination()+" : date de depart "+r.getTripDeparature()+" : date d'arriver "+r.getTripArrival()+": destination"+r.getTripDestination());
				}
		return rv;
				
			}
	@Override
	public Payment addPayment(Payment p) {
		Payment pay=Prepo.save(p);
		return pay;
	}
	@Override
	public List<Payment> listPaymentByUser(Integer iduser) {
		// TODO Auto-generated method stub
		  User uu = Urepo.findById(iduser).orElse(null);
		//Optional<User> uu = Urepo.findById(iduser);
			 List<Payment> payments = (List<Payment>) Prepo.findAll();
		 List<Payment> Userpayments = payments.stream().filter(tr -> tr.getUser().equals(uu)).toList();
		return Userpayments ;
	}
	@Override
	public List<Payment> RetrieveAllPayments() {
		// TODO Auto-generated method stub
		return (List<Payment>) Prepo.findAll();
	}

		
	
	
	
	


	
}
