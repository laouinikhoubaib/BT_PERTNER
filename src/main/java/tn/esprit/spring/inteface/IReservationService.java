package tn.esprit.spring.inteface;

import java.util.List;


import tn.esprit.spring.entities.Reservation;

public interface IReservationService {
	public Reservation addReservation( Reservation R);
	public void deleteReservation(Integer id);
	public void updateReservation( Reservation newReservation, int idComplaint);
	public List<Reservation> retrieveAllComplaints();
	public float TotalReservation(Integer idReservation)  throws Exception ;
}
