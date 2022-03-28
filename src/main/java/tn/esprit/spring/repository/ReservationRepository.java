package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Reservation;
@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
	@Query(value="SELECT r.reservation_id, (t.transport_price + a.accommodation_price ) as total  FROM reservation r INNER JOIN transport t on r.reservation_id=t.reservation_reservation_id INNER join accommodation a on a.reservation_reservation_id=t.reservation_reservation_id  where  r.reservation_id=:id",nativeQuery=true)
	public float TotalReservation(@Param("id")Integer idReservation);
	@Query(value="SELECT t.transport_price FROM transport t join reservation r on t.reservation_reservation_id=r.reservation_id WHERE r.reservation_id=:id",nativeQuery=true)
	public float PriceTransportByIdReservation(@Param("id")Integer idReservation);
	@Query(value="SELECT a.accommodation_price FROM accommodation a join reservation r on a.reservation_reservation_id=r.reservation_id WHERE r.reservation_id=:id",nativeQuery=true)
	public float AccomodationByIdReservation(@Param("id")Integer idReservation);

}
