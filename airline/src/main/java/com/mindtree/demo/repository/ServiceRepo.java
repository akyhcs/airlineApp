package com.mindtree.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.mindtree.demo.entity.Services;

@Repository
public interface ServiceRepo extends JpaRepository<Services,Long>{

	
	@Query("select s from Services s where s.serviceName = :serviceName")
	Services findServiceByName(@Param("serviceName") String serviceName);

	@Transactional
	@Modifying
	@Query("update Services s set s.serviceName = :serviceName where s.serviceId=:serviceId")
	int updateServiceName(@Param("serviceId") Long serviceId, @Param("serviceName") String serviceType);

	
	
	
}
