package com.mindtree.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.demo.dto.ResponseDTO;
import com.mindtree.demo.entity.Airlines;
import com.mindtree.demo.entity.Passenger;
import com.mindtree.demo.entity.Services;
import com.mindtree.demo.services.AirlineService;
import org.springframework.core.env.Environment;

@RestController
public class AirlineController {
@Autowired
private AirlineService airlineService;
@Autowired
private Environment env;
	

@GetMapping("/")
public String getMethodName() {
	return "<h1>env.getProperty("mysql_service")</h1>";
}
@PostMapping("/sheet-excel")
public ResponseEntity<ResponseDTO> read(@RequestParam("file") MultipartFile multipartFile)
{
	return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.CREATED))
	.body(new ResponseDTO("file read successfully", true, false,
			airlineService.readExcelFile(multipartFile)));	
}
@CrossOrigin(origins = "*", allowedHeaders = "*")
@GetMapping("flights")
public ResponseEntity<ResponseDTO> getFlights()
{
	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201", 
			"Sennding All Airline Details Without Passengers")
	.body(new ResponseDTO("FLIGHTS", true, false,
			airlineService.getFlights()));	
}
@CrossOrigin(origins = "*", allowedHeaders = "*",methods = RequestMethod.GET)
@GetMapping("meals")
public ResponseEntity<ResponseDTO> getMealPrefrences(@RequestParam("flightId") Long planeId)
{
	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201", 
			"Sending All Meal Prefrences Without Passengers")
	.body(new ResponseDTO("FLIGHTS", true, false,
			airlineService.getMealPrefrences(planeId)));	
}

@CrossOrigin(origins = "*", allowedHeaders = "*")
@GetMapping("passengers")
public ResponseEntity<ResponseDTO> getPassengers(@RequestParam("planeId") int planeId)
{
	
	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201",
			"Sending Passengers Of Respective Flight")
	.body(new ResponseDTO("file read successfully", true, false,
			airlineService.getPassengers(planeId)));
	
}

@CrossOrigin(origins = "*", allowedHeaders = "*")
@GetMapping("filter-passengers")
public ResponseEntity<ResponseDTO> getFilterPassengers(@RequestParam("planeId") int planeId,
		@RequestParam("filterArray") boolean[] filterBool)
{
	
	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201",
			"Sending Passengers Of Respective Flight")
	.body(new ResponseDTO("file read successfully", true, false,
			airlineService.getFilterPassengers(planeId,filterBool)));
}
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PutMapping("passengers-check")
public ResponseEntity<ResponseDTO> updatePassengerCheckIn(@RequestParam("passengerId") int passengerId)
{
//	System.out.println(passenger.getPassengerId()+"===============");
	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201","Passenger Checked In")
	.body(new ResponseDTO("Passenger Status Updated Successfully", true, false,
			airlineService.updatePassengerCheckIn(passengerId)));

}
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PutMapping("passengers-swap")
public ResponseEntity<ResponseDTO> swapPassengerSeats(@RequestBody int[] passengersId)
{
	
	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201","Passenger Seat Updation")
	.body(new ResponseDTO("Passenger Status Updated Successfully", true, false,
			airlineService.swapSeat(passengersId)));

}
@CrossOrigin(origins = "*", allowedHeaders = "*")
@GetMapping("passengers-service-list")
public ResponseEntity<ResponseDTO> getPassengerServiceList(@RequestParam("passengerId") int id)
{

	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201","Passenger Service List")
	.body(new ResponseDTO("Passenger Service List", true, false,
			airlineService.passengerServiceList(id)));

}
@CrossOrigin(origins = "*", allowedHeaders = "*")
@GetMapping("service-list")
public ResponseEntity<ResponseDTO> getServiceList(@RequestParam("planeId") long planeId)
{

	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201","Passenger Service List")
	.body(new ResponseDTO("All Service List", true, false,
			airlineService.ServiceList(planeId)));

}
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PutMapping("service-update")
public ResponseEntity<ResponseDTO> updateServiceList(@RequestParam("passengerId") int passengerId,@RequestParam("serviceId") int serviceId)
{

	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201","Update Service List")
	.body(new ResponseDTO("update Service List", true, false,
			airlineService.updateServiceList(passengerId,serviceId)));

}
@CrossOrigin(origins = "*", allowedHeaders = "*")
@GetMapping("getId")
public ResponseEntity<ResponseDTO> getPassengerId(@RequestParam("passengerId") String passengerId)
{

	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201","Get Passenger Id Status")
	.body(new ResponseDTO("get passenger id status", true, false,
			airlineService.getPassengerId(passengerId)));
}
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PutMapping("update-meal")
public ResponseEntity<ResponseDTO> updatePassengerMealPrefer(@RequestParam("passengerId") Long passengerId,
															@RequestParam("mealId") Long mealId)
{

	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201","Get Meal Prefer")
	.body(new ResponseDTO("get passenger id status", true, false,
			airlineService.updateMealPrefrences(passengerId,mealId)));
}
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PutMapping("passengers-flight-in-shopping")
public ResponseEntity<ResponseDTO> updatePassengerFlightShopping(@RequestParam("passengerId") int passengerId)
{
//	System.out.println(passenger.getPassengerId()+"===============");
	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201","Passenger Flight In Shopping")
	.body(new ResponseDTO("Passenger Flight In Shopping", true, false,
			airlineService.updatePassengerFlightInShopping(passengerId)));

}
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PostMapping("new-service")
public ResponseEntity<ResponseDTO> addNewService(@RequestParam("flightId") Long flightId,@RequestParam("newService") String newService)
{
//	System.out.println(passenger.getPassengerId()+"===============");
	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201","Passenger Flight In Shopping")
	.body(new ResponseDTO("Passenger Flight In Shopping", true, false,
			airlineService.addNewService(flightId,newService)));

}
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PostMapping("new-meal")
public ResponseEntity<ResponseDTO> addNewMeal(@RequestParam("flightId") Long flightId,
		@RequestParam("newMeal") String newMeal)
{

	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201","Passenger Flight In Shopping")
	.body(new ResponseDTO("Passenger Flight In Shopping", true, false,
			airlineService.addNewMeal(flightId,newMeal)));

}
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PostMapping("new-shopping-item")
public ResponseEntity<ResponseDTO> addNewShoppingItem(@RequestParam("flightId") Long flightId,
		@RequestParam("newItem") String newItem)
{

	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201","Passenger Flight In Shopping")
	.body(new ResponseDTO("Passenger Flight In Shopping", true, false,
			airlineService.addNewItem(flightId,newItem)));

}

@CrossOrigin(origins = "*", allowedHeaders = "*")
@DeleteMapping("delete-service")
public ResponseEntity<ResponseDTO> deleteService(@RequestParam("flightId") Long flightId,@RequestParam("serviceId") Long serviceId)
{
	System.out.println(flightId+"===============");
	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201","Passenger Flight In Shopping")
	.body(new ResponseDTO("Passenger Flight In Shopping", true, false,
			airlineService.deleteService(flightId,serviceId)));

}
@CrossOrigin(origins = "*", allowedHeaders = "*")
@DeleteMapping("delete-meal")
public ResponseEntity<ResponseDTO> deleteMeal(@RequestParam("flightId") Long flightId,
		@RequestParam("mealId") Long mealId)
{
	System.out.println(flightId+"===============");
	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201","Passenger Flight In Shopping")
	.body(new ResponseDTO("Passenger Flight In Shopping", true, false,
			airlineService.deleteMeal(flightId,mealId)));

}
//
@CrossOrigin(origins = "*", allowedHeaders = "*")
@DeleteMapping("delete-shopping")
public ResponseEntity<ResponseDTO> deleteShoppingItem(@RequestParam("flightId") Long flightId,
		@RequestParam("shoppingItem") String shoppingItem)
{
	System.out.println(flightId+"===============");
	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201","Passenger Flight In Shopping")
	.body(new ResponseDTO("Passenger Flight In Shopping", true, false,
			airlineService.deleteShoppingItem(flightId,shoppingItem)));

}

@CrossOrigin(origins = "*", allowedHeaders = "*")
@PutMapping("update-service")
public ResponseEntity<ResponseDTO> updateService(@RequestParam("serviceId") Long serviceId,
		@RequestParam("serviceType") String serviceType)
{
//	System.out.println(flightId+"===============");
	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201","Passenger Flight In Shopping")
	.body(new ResponseDTO("Passenger Flight In Shopping", true, false,
			airlineService.updateService(serviceType,serviceId)));

}
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PutMapping("update-specific-meal")
public ResponseEntity<ResponseDTO> updatemeal(@RequestParam("mealId") Long mealId,
		@RequestParam("mealType") String mealType)
{
//	System.out.println(flightId+"===============");
	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201","Passenger Flight In Shopping")
	.body(new ResponseDTO("Passenger Flight In Shopping", true, false,
			airlineService.updateMeal(mealType,mealId)));

}
@CrossOrigin(origins = "*", allowedHeaders = "*")
@GetMapping("shoppingItems")
public ResponseEntity<ResponseDTO> getShoppingList(@RequestParam("flightId") Long flightId)
{

	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201","Passenger Service List")
	.body(new ResponseDTO("Passenger Service List", true, false,
			airlineService.getShoppingList(flightId)));

}
@CrossOrigin(origins = "*", allowedHeaders = "*")
@GetMapping("generate-postal")
public ResponseEntity<ResponseDTO> generatePostal()
{

	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201","Passenger Service List")
	.body(new ResponseDTO("Passenger Service List", true, false,
			airlineService.generatePostal()));
}
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PutMapping("update-passenger-address")
public ResponseEntity<ResponseDTO> updatePassengerAddress(@RequestBody Passenger passenger)
{
	System.out.println("================");
	return ResponseEntity.status(HttpStatus.OK).header("Angular-Flight-Assignment-201","Passenger Service List")
	.body(new ResponseDTO("Passenger Service List", true, false,
			airlineService.updatePassenger(passenger)));
}

//

}
