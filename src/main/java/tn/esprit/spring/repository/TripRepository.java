package tn.esprit.spring.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dto.TopTrip;
import tn.esprit.spring.entities.Trip;
@Repository
public interface TripRepository extends CrudRepository<Trip, Integer> {
	@Query(value="Select count(*) from trip ta where ta.trip_destination=:destination",nativeQuery=true)
	public int numberOfTripsByDestination(@Param("destination") String TripDestination);
	@Query(value="COUNT(trip_destination) from trip a JOIN trip_user fa ON a.trip_id = fa.trip_trip_id GROUP BY trip_destination HAVING COUNT(*)>= ALL (SELECT count(*) from trip GROUP by trip_destination",nativeQuery=true)
	public int TopTripsByUser();
	@Query("Select T from Trip T where T.TripDeparature between :x and :y or T.TripDestination like :z ")
	List<Trip> SearchTrips(@Param("x")@DateTimeFormat(pattern="yyyy-MM-dd")Date d1,@Param("y")@DateTimeFormat(pattern="yyyy-MM-dd")Date d2,@Param("z")String name);
	@Query("Select count (*) from Trip T where T.TripDeparature between :x and :y ")
	int GetNumberOfTripsByDate(@Param("x")@DateTimeFormat(pattern="yyyy-MM-dd")Date d1,@Param("y")@DateTimeFormat(pattern="yyyy-MM-dd")Date d2);
	@Query(value="Select * from Trip  Where trip_deparature> CURDATE()",nativeQuery=true)
	public List<Trip> ListOfTrips();
	@Query(value="SELECT trip_id,note_date,note_value ,count(*) FROM  user u JOIN trip_user fa  on fa.user_user_id=:idUser JOIN trip t on t.trip_id=fa.trip_trip_id  JOIN note n  on n.user_user_id=u.user_id GROUP by trip_id",nativeQuery=true)
	List <Trip> RetrieveAllTripsOfOneByUser(@Param("idUser")Integer iduser);
	//@Query(value="Select new tn.esprit.spring.dto.TopTrip(trip_destination as destination,COUNT(trip_destination) as count) from trip a JOIN trip_user fa ON a.trip_id = fa.trip_trip_id GROUP BY trip_destination HAVING COUNT(*)>= ALL (SELECT count(*) from trip GROUP by trip_destination)ORDER by COUNT(trip_destination)")
	//public List<TopTrip> topthreetripps();



}

