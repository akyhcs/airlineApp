package com.mindtree.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.demo.entity.Airlines;
import com.mindtree.demo.entity.MealPrefrence;
import com.mindtree.demo.entity.Passenger;
import com.mindtree.demo.entity.Services;


public interface AirlineService {
	ArrayList<Airlines> readExcelFile(MultipartFile multipartFile);

	List<Airlines> getFlights();

	List<MealPrefrence> getMealPrefrences(Long flightId);

	List<Passenger> getPassengers(int planeId);

	String updatePassengerCheckIn(int passengerId);

	String swapSeat(int[] passengersId);

	List<Services> passengerServiceList(int id);

	List<Services> ServiceList(Long planeId);

	String updateServiceList(int passengerId, int serviceId);

	int getPassengerId(String passengerId);

	List<Passenger> getFilterPassengers(int planeId, boolean[] filterBool);

	String updateMealPrefrences(Long passengerId, Long mealId);

	String updatePassengerFlightInShopping(int passengerId);

	String addNewService(Long flightId, String newService);

	String addNewMeal(Long flightId, String newMeal);
	
	String deleteService(Long flightId, Long serviceId);

	String updateService(String serviceType, Long serviceId);

	String deleteMeal(Long flightId, Long mealId);

	String updateMeal(String mealType, Long mealId);

	String addNewItem(Long flightId, String newItem);

	String[] getShoppingList(Long flightId);

	String deleteShoppingItem(Long flightId, String shoppingItem);

	String[] generatePostal();

	String updatePassenger(Passenger passenger);

}
