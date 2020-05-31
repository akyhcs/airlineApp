package com.mindtree.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Passenger implements Serializable {
	@Id
	private Long passengerId;
	private String passengerName;

	private String seatNo;
	private boolean checkedIn = false;
	private boolean wheelChair;
	private boolean flightInShopping = false;
	private boolean infant;
	private String region,
					state,
					city,
					postal,
					dob,
					passportNo;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "passenger_service",
	        joinColumns = @JoinColumn(name = "passenger_id"),
	        inverseJoinColumns = @JoinColumn(name = "service_id")
	    )
	private List<Services> servicesList = new ArrayList<Services>();
	
	@ManyToOne
	private MealPrefrence mealPrefrence;

	
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}


	public Long getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public boolean isCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	public boolean isWheelChair() {
		return wheelChair;
	}

	public void setWheelChair(boolean wheelChair) {
		this.wheelChair = wheelChair;
	}

	public boolean isFlightInShopping() {
		return flightInShopping;
	}

	public void setFlightInShopping(boolean flightInShopping) {
		this.flightInShopping = flightInShopping;
	}

	public boolean isInfant() {
		return infant;
	}

	public void setInfant(boolean infant) {
		this.infant = infant;
	}

	public List<Services> getServicesList() {
		return servicesList;
	}

	public void setServicesList(List<Services> servicesList) {
		this.servicesList = servicesList;
	}

	public MealPrefrence getMealPrefrence() {
		return mealPrefrence;
	}

	public void setMealPrefrence(MealPrefrence mealPrefrence) {
		this.mealPrefrence = mealPrefrence;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (checkedIn ? 1231 : 1237);
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + (flightInShopping ? 1231 : 1237);
		result = prime * result + (infant ? 1231 : 1237);
		result = prime * result + ((mealPrefrence == null) ? 0 : mealPrefrence.hashCode());
		result = prime * result + ((passengerId == null) ? 0 : passengerId.hashCode());
		result = prime * result + ((passengerName == null) ? 0 : passengerName.hashCode());
		result = prime * result + ((passportNo == null) ? 0 : passportNo.hashCode());
		result = prime * result + ((postal == null) ? 0 : postal.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((seatNo == null) ? 0 : seatNo.hashCode());
		result = prime * result + ((servicesList == null) ? 0 : servicesList.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + (wheelChair ? 1231 : 1237);
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
		Passenger other = (Passenger) obj;
		if (checkedIn != other.checkedIn)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (flightInShopping != other.flightInShopping)
			return false;
		if (infant != other.infant)
			return false;
		if (mealPrefrence == null) {
			if (other.mealPrefrence != null)
				return false;
		} else if (!mealPrefrence.equals(other.mealPrefrence))
			return false;
		if (passengerId == null) {
			if (other.passengerId != null)
				return false;
		} else if (!passengerId.equals(other.passengerId))
			return false;
		if (passengerName == null) {
			if (other.passengerName != null)
				return false;
		} else if (!passengerName.equals(other.passengerName))
			return false;
		if (passportNo == null) {
			if (other.passportNo != null)
				return false;
		} else if (!passportNo.equals(other.passportNo))
			return false;
		if (postal == null) {
			if (other.postal != null)
				return false;
		} else if (!postal.equals(other.postal))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (seatNo == null) {
			if (other.seatNo != null)
				return false;
		} else if (!seatNo.equals(other.seatNo))
			return false;
		if (servicesList == null) {
			if (other.servicesList != null)
				return false;
		} else if (!servicesList.equals(other.servicesList))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (wheelChair != other.wheelChair)
			return false;
		return true;
	}

		
}
