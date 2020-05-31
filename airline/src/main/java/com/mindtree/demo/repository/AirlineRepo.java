package com.mindtree.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.demo.entity.Airlines;

@Repository
public interface AirlineRepo extends JpaRepository<Airlines,Long> {

	@Transactional
	@Modifying
	@Query("update Airlines a set a.serviceCode = a.serviceCode + :newServiceCode where a.airlineId=:flightId")
	int updateServiceCode(@Param("flightId") Long flightId, @Param("newServiceCode") String newServiceCode);

}

