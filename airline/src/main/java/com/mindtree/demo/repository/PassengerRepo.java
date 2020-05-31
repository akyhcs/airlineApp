package com.mindtree.demo.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.demo.entity.Passenger;

@Repository
public interface PassengerRepo extends JpaRepository<Passenger,Long>{

	
	@Transactional
	@Modifying
	@Query("update Passenger p set p.checkedIn = :checkIn where p.passengerId=:passengerId")
	int updatePassengerCheckIn(@Param("passengerId") Long long1,@Param("checkIn") boolean checkIn);

	@Transactional
	@Modifying
	@Query("update Passenger p set p.seatNo = :seatNo where p.passengerId=:passengerId")
	int updatePassengerSeatNo(@Param("passengerId") Long passengerId,@Param("seatNo") String seatNo);

	@Transactional
	@Modifying
	@Query("update Passenger p set p.mealPrefrence.mealId = :mealId where p.passengerId=:passengerId")
	int UpdateMealPrefr(@Param("passengerId") Long passengerId, @Param("mealId") Long mealId);

	@Transactional
	@Modifying
	@Query("update Passenger p set p.flightInShopping = :flightShopping where p.passengerId=:passengerId")
	int updatePassengerFlightIn(@Param("passengerId")Long passengerId,@Param("flightShopping") boolean b);
	
	@Transactional
	@Modifying
	@Query("update Passenger p set p.city = :city,"
			+ "p.region = :region,"
			+ "p.state = :state,"
			+ "p.postal = :postal,"
			+ "p.passportNo = :passportNo  where p.passengerId=:passengerId")
	int updatePassengerAddress(
			@Param("passengerId") Long passengerId,
			@Param("city") String city,
			@Param("region") String region, 
			@Param("state") String state,
			@Param("postal") String postal,
			@Param("passportNo") String passportNo
			);
}
