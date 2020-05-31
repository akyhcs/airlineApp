package com.mindtree.demo.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"passengers"})
@Entity
public class Services {
	@Id
	private Long serviceId;
	private String serviceName;

	@ManyToMany(mappedBy = "servicesList",fetch = FetchType.LAZY)
	private List<Passenger> passengers = new ArrayList<Passenger>();
	
	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public Services() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((passengers == null) ? 0 : passengers.hashCode());
		result = prime * result + ((serviceId == null) ? 0 : serviceId.hashCode());
		result = prime * result + ((serviceName == null) ? 0 : serviceName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Services other = (Services) obj;
		if (passengers == null) {
			if (other.passengers != null)
				return false;
		} else if (!passengers.equals(other.passengers))
			return false;
		if (serviceId == null) {
			if (other.serviceId != null)
				return false;
		} else if (!serviceId.equals(other.serviceId))
			return false;
		if (serviceName == null) {
			if (other.serviceName != null)
				return false;
		} else if (!serviceName.equals(other.serviceName))
			return false;
		return true;
	}
	
	
}
