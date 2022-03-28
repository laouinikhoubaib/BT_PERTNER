package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Transport;
@Repository
public interface TransportRepository extends CrudRepository<Transport, Integer> {
	@Query(value="SELECT t.transport_id FROM transport t join reservation r on t.reservation_reservation_id=r.reservation_id WHERE r.reservation_id=:id",nativeQuery=true)
	public int GetIdTransportByReservation(@Param("id")Integer idReservation);
}
