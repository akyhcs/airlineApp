package com.mindtree.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"passengers"})
@Entity
public class MealPrefrence {

	@Id
	private Long mealId;
	private String mealType;
	@OneToMany(mappedBy = "mealPrefrence",fetch = FetchType.LAZY)
	private List<Passenger> passengers = new ArrayList<Passenger>();
	
	public Long getMealId() {
		return mealId;
	}

	public void setMealId(Long mealId) {
		this.mealId = mealId;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mealId == null) ? 0 : mealId.hashCode());
		result = prime * result + ((mealType == null) ? 0 : mealType.hashCode());
		result = prime * result + ((passengers == null) ? 0 : passengers.hashCode());
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
		MealPrefrence other = (MealPrefrence) obj;
		if (mealId == null) {
			if (other.mealId != null)
				return false;
		} else if (!mealId.equals(other.mealId))
			return false;
		if (mealType == null) {
			if (other.mealType != null)
				return false;
		} else if (!mealType.equals(other.mealType))
			return false;
		if (passengers == null) {
			if (other.passengers != null)
				return false;
		} else if (!passengers.equals(other.passengers))
			return false;
		return true;
	}

	public MealPrefrence() {
		super();
	}
	
}
