package com.mindtree.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Airlines {
	@Id
	private Long airlineId;
	private String airlineName;
	private String boarding; 
	private String destination;
	private String mealCode;
	private String serviceCode;
	private String duration;
	private String shoppingItem;
//	private 

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable
	private List<Passenger> passengerList = new ArrayList<Passenger>();
	
	
	public String getShoppingItem() {
		return shoppingItem;
	}
	public void setShoppingItem(String shoppingItem) {
		this.shoppingItem = shoppingItem;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Long getAirlineId() {
		return airlineId;
	}
	public void setAirlineId(Long airlineId) {
		this.airlineId = airlineId;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public Airlines() {
		super();
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public List<Passenger> getPassengerList() {
		return passengerList;
	}
	public void setPassengerList(ArrayList<Passenger> passengerList) {
		this.passengerList = passengerList;
	}
	
	public String getBoarding() {
		return boarding;
	}
	public void setBoarding(String boarding) {
		this.boarding = boarding;
	}
	
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public String getMealCode() {
		return mealCode;
	}
	public void setMealCode(String mealCode) {
		this.mealCode = mealCode;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airlineId == null) ? 0 : airlineId.hashCode());
		result = prime * result + ((airlineName == null) ? 0 : airlineName.hashCode());
		result = prime * result + ((passengerList == null) ? 0 : passengerList.hashCode());
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
		Airlines other = (Airlines) obj;
		if (airlineId == null) {
			if (other.airlineId != null)
				return false;
		} else if (!airlineId.equals(other.airlineId))
			return false;
		if (airlineName == null) {
			if (other.airlineName != null)
				return false;
		} else if (!airlineName.equals(other.airlineName))
			return false;
		if (passengerList == null) {
			if (other.passengerList != null)
				return false;
		} else if (!passengerList.equals(other.passengerList))
			return false;
		return true;
	}
	
}
