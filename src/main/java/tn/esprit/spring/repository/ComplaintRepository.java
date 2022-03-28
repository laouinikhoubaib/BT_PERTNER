package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Complaint;
import tn.esprit.spring.entities.ComplaintStatus;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
	@Query(value="count(*) from complaint c join trip t on c.trip_trip_id=t.trip_id GROUP BY trip_trip_id",nativeQuery=true)
	public int countComplaint();
	@Query(value="select (*) from complaint c join trip t on c.trip_trip_id=t.trip_id join user u on u.user_id=c.user_user_id where c.complaint_status=:Status",nativeQuery=true)
	public List<Complaint> retrieveAllComplaintsByStatus(@Param("Status")ComplaintStatus Status);
	@Query(value="SELECT COUNT(*) from complaint c join trip t on c.trip_trip_id=t.trip_id join user u on u.user_id=c.user_user_id where c.complaint_status='untreated'",nativeQuery=true)
	public int  CountUntreatedComplaint();
	
}
