package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Accommodation;
@Repository
public interface AccommodationRepository extends CrudRepository<Accommodation, Integer> {
	@Query(value="SELECT a.accommodation_id FROM accommodation a join reservation r on a.reservation_reservation_id=r.reservation_id WHERE r.reservation_id=:id",nativeQuery=true)
	public int GetIdAccomodationByReservation(@Param("id")Integer idReservation);
}
