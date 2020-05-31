package com.mindtree.demo.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.demo.entity.Airlines;
import com.mindtree.demo.entity.MealPrefrence;
import com.mindtree.demo.entity.Passenger;
import com.mindtree.demo.entity.Services;
import com.mindtree.demo.repository.AirlineRepo;
import com.mindtree.demo.repository.MealRepo;
import com.mindtree.demo.repository.PassengerRepo;
import com.mindtree.demo.repository.ServiceRepo;
import com.mindtree.demo.services.AirlineService;

@Service
public class AirlineServiceImpl implements AirlineService{
	
	@Autowired
	AirlineRepo airlineRepo;
	@Autowired 
	MealRepo mealRepo;
	@Autowired
	ServiceRepo serviceRepo;
	@Autowired
	PassengerRepo passengerRepo;
	private static MultipartFile multipartFile;
	private InputStream is;
	private XSSFWorkbook xssfWorkbook = null;
	private XSSFSheet xssfSheet = null;
	private XSSFRow xssfRow = null;

	@Override
	public ArrayList<Airlines> readExcelFile(MultipartFile multipartFile) {
		dropDatabase();//
		ArrayList<Airlines> airlinesList = new ArrayList<Airlines>();
		ArrayList<Services> servicesList = new ArrayList<Services>();
		String[] airline = {"air asia","air carnival","air india","go air","indigo airlines","jet airways"};
		
		String[] destination = {"Hyderabad","Itanagar","Dispur","Patna","Gandhinagar","Chandigarh"};
		String[] serv = {" onboard sales of food and beverages",
						"checking of baggage and excess baggage",
						 "assigned seats or seats with more leg room",
						"fees charged for purchases made with credit cards",
						"call center support for reservations",
						"priority check-in and screening",
						"early boarding benefits",
						"onboard entertainment systems",
						"wireless internet access"
		};
		String[] duration = new String[airline.length];
		for(int i =0;i<duration.length;i++)
		{
			duration[i] = randomNumberGenerator(2,1)+":"+randomNumberGenerator(59,10)+" hr";
		}
		for(int i = 0;i<airline.length;i++)
		{
			Airlines airlines = new Airlines();
			airlines.setAirlineId(new Long(i+1));
			airlines.setAirlineName(airline[i].trim().toUpperCase());
			airlines.setDestination(destination[i].trim().toUpperCase());
			airlines.setBoarding("BENGALURU");
			airlines.setDuration(duration[i]);
			String shoppingItem = "";
			for(int j = 0;j<5;j++)
			{
				shoppingItem = shoppingItem + "shopping-item" + randomNumberGenerator(100)+":";
			}
			airlines.setShoppingItem(shoppingItem);
			airlinesList.add(airlines);
			
		}
		for(int i=0;i<serv.length;i++)
		{
			Services ser = new Services();
			ser.setServiceId(new Long(i+1));
			ser.setServiceName(serv[i].trim().toUpperCase());
			servicesList.add(ser);
		}
		/////////////////////////////////////////////passenger//////////////////////
		try 
		{
			is = multipartFile.getInputStream();
			xssfWorkbook = new XSSFWorkbook(is);
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		xssfSheet = xssfWorkbook.getSheetAt(0);
		
		Map<Long, String> passengerMap = new HashMap<Long,String>();
		Map<Long,ArrayList<String>> passengerAddress = new HashMap<Long,ArrayList<String>>();
		for(int i=1;i<xssfSheet.getLastRowNum();i++)
		{
			if(passengerMap.size()==528)
			{
				break;
			}
			String customerName=
						xssfSheet.getRow(i).getCell((int)'G'-65).toString().toUpperCase();
			Long id = new Long((int)
					Double.parseDouble(
							xssfSheet.getRow(i).getCell((int)'F'-65).toString()));
			//region,state,city,pin
			ArrayList<String> address = new ArrayList<String>();
			String region = xssfSheet.getRow(i).getCell((int)'P'-65).toString().toUpperCase();
			String state = xssfSheet.getRow(i).getCell((int)'Q'-65).toString().toUpperCase();
			String city = xssfSheet.getRow(i).getCell((int)'R'-65).toString().toUpperCase();
			String pin = ""+ (int)Double.parseDouble(
					xssfSheet.getRow(i).getCell((int)'S'-65).toString().toUpperCase());
			address.add(region);
//			System.out.println(region);
			address.add(state);
			address.add(city);
			address.add(pin);
			passengerAddress.put(id, address);
			passengerMap.put(id,customerName);
//			System.out.println(passengerMap);
//			System.out.println(passengerAddress);
		}
		////////////////////meal preference///////////////////////////////
		String[] meal = {"Special Meal",
							"Gluten Free Meal",
							"Fruit Platter",
								"Diabetic Meal",
								"Bland Meal",
								"Raw Vegetable Meal",
								"Vegetarian Oriental Meal",
								"Seafood Meal",
								"Non-lactose Meal",
								"Low Sodium Meal",
								"Low Fat Low Cholesterol Meal",
								"Low-calorie Meal"
								};
		ArrayList<MealPrefrence> mealPres = new ArrayList<MealPrefrence>();
		for(int i = 0;i<meal.length;i++)
		{
			MealPrefrence mealPrefrence = new MealPrefrence();
			mealPrefrence.setMealId(new Long(i+1));
			mealPrefrence.setMealType(meal[i]);
			mealPres.add(mealPrefrence);
		}
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();
			for(Map.Entry<Long, String> p :passengerMap.entrySet())
			{
				Passenger passenger = new Passenger();
				passenger.setPassengerId(p.getKey());
				passenger.setPassengerName(p.getValue());
				passenger.setFlightInShopping(false);

				passenger.setWheelChair(randomNumberGenerator());
				if(!passenger.isWheelChair()) {
					passenger.setInfant(randomNumberGenerator());
				}
				
				/////////////////address//////////////////////
				boolean haveAddress = randomNumberGenerator();
				if(haveAddress)
				{
					for(int j = 0;j<passengerAddress.get(p.getKey()).size();j++)
					{
						switch (j) {
						case 0:
							passenger.setRegion(passengerAddress.get(p.getKey()).get(0));
							break;
							
						case 1:
							passenger.setState(passengerAddress.get(p.getKey()).get(1));
							break;
						case 2:
							passenger.setCity(passengerAddress.get(p.getKey()).get(2));
							break;
							
						case 3:
							passenger.setPostal(passengerAddress.get(p.getKey()).get(3));
							break;
	
						default:
							break;
						}
					}
				}
				////////////////dob//////////////////////////
				boolean haveDOB = randomNumberGenerator();
				if(haveDOB)
				{
					int day = randomNumberGenerator(28,1);
					int month = randomNumberGenerator(12, 1);
					int year = randomNumberGenerator(90,80);
					passenger.setDob(day+"-"+month+"-"+"19"+year);
					}
				///////////////passport/////////////////////
				boolean havePassport = randomNumberGenerator();
				if(havePassport)
				{
					String passportNumber = generatePassportNumber();
					passenger.setPassportNo(passportNumber);
				}
			
				passengers.add(passenger);

			}

			int x = 0;
			
			for(Airlines a:airlinesList)
			{
				String serviceCode = "";
				String mealCode = "";
				for(int i =0;i<servicesList.size();i++)
				{
					if(randomNumberGenerator())
					{
						if(i<9)
							serviceCode = serviceCode + "0"+(i+1)+":";
						else {
							serviceCode = serviceCode + (i+1)+":";
						}
					}
				}
				///setting service code
				a.setServiceCode(serviceCode);
				
				for(int i =0;i<mealPres.size();i++)
				{
					if(randomNumberGenerator())
					{
						if(i<9)
						{
							mealCode = mealCode + "0"+(i+1)+":";
						}
						else {
							mealCode = mealCode + (i+1)+":"; 	
						}
						
					}
				}
				////setting meal code
				a.setMealCode(mealCode);
				while(a.getPassengerList().size()<88)
				{
					Passenger p = passengers.get(a.getPassengerList().size()+x);
					///
					 String[] splitMealCode = a.getMealCode().split(":");
//					 System.out.println(splitMealCode.length+" Split-meal-code-length");
					
					for(int i = 0;i<splitMealCode.length;i++)
					{
						
						 if(randomNumberGenerator())
						{
//							 System.out.println(Integer.parseInt(splitMealCode[i])+"==>Meal");
							p.setMealPrefrence(mealPres.get(Integer.parseInt(splitMealCode[i])-1));
							break;
						}
						 if(i==splitMealCode.length-1&&p.getMealPrefrence()==null)///if all the cases got false
						 {
							 p.setMealPrefrence(mealPres.get(Integer.parseInt(splitMealCode[randomNumberGenerator(splitMealCode.length)])-1));
						 }
						
					}
					/////adding services///////////
					String[] splitServices = a.getServiceCode().split(":");
//					System.out.println(splitServices.length+" Split-service-length");
					for(int j=0;j<splitServices.length;j++)
					{
						if(randomNumberGenerator())
						{
//							 System.out.println(Integer.parseInt(splitMealCode[j])+"==>serv");
							p.getServicesList().add(servicesList.get(Integer.parseInt(splitServices[j])-1));
						}	
					}
					p.setSeatNo(getSeatNumber(a.getPassengerList().size()));
					a.getPassengerList().add(p);
				}
				x = x + 88;
			}
			
			serviceRepo.saveAll(servicesList);
			mealRepo.saveAll(mealPres);
			airlineRepo.saveAll(airlinesList);
			return airlinesList;
	}
	private String generatePassportNumber() {
		String pass = "";
		for(int i = 1;i<=12;i++)
		{
			pass = pass + (char)randomNumberGenerator(90,65);
		}
		return pass;
	}
	private int randomNumberGenerator(int max, int min) {
		// TODO Auto-generated method stub
		return (int)(Math.random()*(max-min+1)+min);
	}
	private void dropDatabase() {
		
		airlineRepo.deleteAll();
//		passengerRepo.deleteAll();
		mealRepo.deleteAll();
		serviceRepo.deleteAll();
		
		
	}
	public boolean randomNumberGenerator()
	{
		boolean[] boolArr = {true,false};
		return boolArr[(int)Math.floor(Math.random()*2)];
	}
	public int randomNumberGenerator(int n)
	{
		
		return (int)Math.floor(Math.random()*n);
	}
	public String getSeatNumber(int num)
	{
		 char ch =  (char)(65 + Math.floor((num) / 11) );
		  if(num%11==0)
		  {
		    num = 11;
		  }
		  else{
		    num = num%11;
		  }
		  return ch+"-"+num;
	}
	@Override
	public List<Airlines> getFlights() {
		List<Airlines> airlines =  airlineRepo.findAll();
		airlines.forEach(airline->{
			airline.setPassengerList(null);
		});
		return airlines;
	}
	@Override
	public List<MealPrefrence> getMealPrefrences(Long flightId) {
		List<MealPrefrence> mealPreList = new ArrayList<MealPrefrence>();
		String splitMealCode[] = airlineRepo.findById(flightId).get().getMealCode().split(":");
		for(int i = 0 ;i < splitMealCode.length;i++)
		{
			MealPrefrence m = mealRepo.findById(Long.parseLong(splitMealCode[i])).get();
			mealPreList.add(m);
		}
		return mealPreList;
	}
	@Override
	public List<Passenger> getPassengers(int planeId) {
		Optional<Airlines> p =   airlineRepo.findById(new Long(planeId));

		return p.get().getPassengerList();
		
	}
	@Override
	public String updatePassengerCheckIn(int passengerId) {
		Optional<Passenger> passOptional = passengerRepo.findById(new Long(passengerId));
		
		int i = passengerRepo.updatePassengerCheckIn(passOptional.get().getPassengerId(),
				!passOptional.get().isCheckedIn());
		return i==1?"updated":"failed to update";
	}
	@Override
	public String swapSeat(int[] passengersId) {
//		System.out.println("swapSeat============================");
		Optional<Passenger> optionalPassernger1 = passengerRepo.findById(new Long(passengersId[0]));
		Optional<Passenger> optionalPassernger2 = passengerRepo.findById(new Long(passengersId[1]));
		
		int i = passengerRepo.updatePassengerSeatNo(optionalPassernger1.get().getPassengerId(),
				optionalPassernger2.get().getSeatNo());
		int j = passengerRepo.updatePassengerSeatNo(optionalPassernger2.get().getPassengerId(),
				optionalPassernger1.get().getSeatNo());
		return i+j==2?"seat updated successfully":"failed to update";
	}
	@Override
	public List<Services> passengerServiceList(int id) {
		 Optional<Passenger> optionalpassenger = passengerRepo.findById(new Long(id));
		 optionalpassenger.get().getServicesList().forEach(service->{
			 service.setPassengers(null);
		 });
		return optionalpassenger.get().getServicesList();
	}
	@Override
	public List<Services> ServiceList(Long planeId) {
		List<Services> services= new ArrayList<Services>();
		Optional<Airlines> optionalAirline = airlineRepo.findById(planeId);
		String[] serviceCode = optionalAirline.get().getServiceCode().split(":");
		for(int i = 0 ;i<serviceCode.length;i++)
		{
			try {
			Optional<Services> serOptional =  serviceRepo.findById(Long.parseLong(serviceCode[i]));
			if(serOptional.get()!=null) {
				services.add(serOptional.get());
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return services;
	}
	
	@Override
	public String updateServiceList(int passengerId, int serviceId) {
		Optional<Passenger> optional = passengerRepo.findById(new Long(passengerId));
		Optional<Services> optionalService = serviceRepo.findById(new Long(serviceId));
		optional.get().getServicesList().add(optionalService.get());
		passengerRepo.save(optional.get());
		System.out.println(serviceId+"=========="+passengerId);
		return "updated";
	}
	@Override
	public int getPassengerId(String passengerId) {
		Long l = null;
		boolean flag = true;
		Optional<Passenger> optional = null;
		try 
		{
			 l = new Long(Integer.parseInt(passengerId));
			 optional = passengerRepo.findById(l);
//			 System.out.println(optional.get());
		}
		catch (Exception e) 
		{
			flag = false;
		}
		
		if(flag==false)
				return -1;
		return 1;
	}
	@Override
	public List<Passenger> getFilterPassengers(int planeId, boolean[] filterBool) {
		
		return airlineRepo.findById(new Long(planeId)).get().getPassengerList();
	}
	public static boolean predicate(ArrayList<?> l,Passenger p)
	{
		if(l.contains(p))
			return false;
		
		return true;
		
		
	}
	@Override
	public String updateMealPrefrences(Long passengerId, Long mealId) {
		int i = -1;
		boolean flag = true;
		try {
			i =  passengerRepo.UpdateMealPrefr(passengerId,mealId);
		}
		catch (Exception e) {
			
			flag = false;
			e.printStackTrace();
		}
		
		return flag==true&& i == 1?"updated successfully":"failed to update";
	}
	
	@Override
	public String updatePassengerFlightInShopping(int passengerId) 
	{
		Optional<Passenger> passOptional = passengerRepo.findById(new Long(passengerId));
		int i = passengerRepo.updatePassengerFlightIn(passOptional.get().getPassengerId(),
				!passOptional.get().isFlightInShopping());
		return i==1?"updated":"failed to update";
	}
	@Override
	public String addNewService(Long flightId, String newService) {
		Services ser = new Services();
		List<?> l = serviceRepo.findAll();
		
		String newServiceCode = "";
		
		Services s = serviceRepo.findServiceByName(newService.trim().toUpperCase());
		if(s==null)
		{
			ser.setServiceId(new Long(l.size()+1));
			ser.setServiceName(newService);
			serviceRepo.save(ser);
			if(l.size()+1<9)
			{
				newServiceCode = "0"+(l.size()+1)+"";
			}
			else
			{
				newServiceCode = l.size()+1+"";
			}
		}
		else
		{
			if(s.getServiceId()<10)
				newServiceCode = "0"+s.getServiceId()+"";
			else
			{
				newServiceCode = s.getServiceId()+"";
			}
		}
		
		boolean flag = true;
		int i = 0;
		try {
			Airlines a = airlineRepo.findById(flightId).get();
			//check if service is already present in airline
			boolean splitCodeNotFound = false;
			for(String splitCode:a.getServiceCode().split(":"))
			{
				if(newServiceCode.equalsIgnoreCase(splitCode))
				{
					splitCodeNotFound = true;
				}
			}
			 newServiceCode = newServiceCode+":"+a.getServiceCode();
			 a.setServiceCode(newServiceCode);
			 if(!splitCodeNotFound)
				 airlineRepo.save(a);
		}
		catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
	
		return flag?"service added successfully":"failed to add new service";
	}
	@Override
	public String addNewMeal(Long flightId, String newMeal) {
		
		MealPrefrence mealPrefrence = new MealPrefrence();
		List<?> l = mealRepo.findAll();
		boolean flag = true;
		String newMealCode = "";
		MealPrefrence m = mealRepo.findMealByName(newMeal.trim().toUpperCase());
		if(m==null)
		{
			mealPrefrence.setMealId(new Long(l.size()+1));
			mealPrefrence.setMealType(newMeal.trim().toUpperCase());
			mealRepo.save(mealPrefrence);
			if(l.size()+1<9)
			{
				newMealCode = "0"+(l.size()+1)+"";
			}
			else
			{
				newMealCode = l.size()+1+"";
			}
		}
		else {
			if(m.getMealId()<10)
				newMealCode = "0"+m.getMealId()+"";
			else
			{
				newMealCode = m.getMealId()+"";
			}
		}
		int i = 0;
		try {
			Airlines a = airlineRepo.findById(flightId).get();
			//check if service is already present in airline
			boolean splitCodeNotFound = false;
			for(String splitCode:a.getMealCode().split(":"))
			{
				if(newMealCode.equalsIgnoreCase(splitCode))
				{
					splitCodeNotFound = true;
				}
			}
			 newMealCode = newMealCode+":"+a.getMealCode();
			 a.setMealCode(newMealCode);
			 if(!splitCodeNotFound)
				 airlineRepo.save(a);
		}
		catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}			
		return flag?"meal added successfully":"failed to add new meal";
	}
	@Override
	public String deleteService(Long flightId, Long serviceId) {
		boolean flag = true;
		try {
			Airlines a = airlineRepo.findById(flightId).get();
			String newServiceCode = "";
			for(String splitCode:a.getServiceCode().split(":"))
			{
				if(Long.parseLong(splitCode)!=serviceId)
					newServiceCode = newServiceCode + splitCode+":";
			}
			a.setServiceCode(newServiceCode); 
			a = airlineRepo.save(a);
		}
		catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag?"DeletedSuccessFully":"failed to delete";
	}
	@Override
	public String deleteMeal(Long flightId, Long mealId) {
		boolean flag = true;
		try {
			Airlines a = airlineRepo.findById(flightId).get();
			String newMealCode = "";
			for(String splitCode:a.getMealCode().split(":"))
			{
				if(Long.parseLong(splitCode)!=mealId)
					newMealCode = newMealCode + splitCode+":";
			}
			System.out.println("newMealCode=="+newMealCode);
			a.setMealCode(newMealCode); 
			a = airlineRepo.save(a);

		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag?"Deleted SuccessFully":"failed to delete";
	}
	
	@Override
	public String updateService(String serviceType, Long serviceId) {
	boolean flag = true;
	int i = -1;
	try 
	{	if(serviceType.trim().length()>0)
		 	i = serviceRepo.updateServiceName(serviceId,serviceType);
		else {
		System.out.println("empty string");
		}
	}
	catch (Exception e) 
	{
		flag = false;
	}
		return flag&&i==1?"updated successfully":"failed to update";
	}
	@Override
	public String updateMeal(String mealType, Long mealId) {
		boolean flag = true;
		int i = -1;
		try 
		{	if(mealType.trim().length()>0)
			 	i = mealRepo.updateMealName(mealId,mealType);
			else {
			System.out.println("empty string");
			}
		}
		catch (Exception e) 
		{
			flag = false;
		}
			return flag&&i==1?"updated successfully":"failed to update";
		}
	@Override
	public String addNewItem(Long flightId, String newItem) {
		Airlines a =  airlineRepo.findById(flightId).get();
		String shoppingItem = a.getShoppingItem();
		String newShoppingItem = "";
		boolean flag = true;
		System.out.println("newItem===="+newItem);
		if(newItem.trim().length()>0) {
			if(shoppingItem==null)
			{
				newShoppingItem = newShoppingItem + newItem + ":";
				a.setShoppingItem(newShoppingItem);
			}
			else {
				
				a.setShoppingItem(a.getShoppingItem()+newItem + ":" ); 
			}
			
			try {
				airlineRepo.save(a);	
			}
			catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}

		}
		return flag?"shopping Item added successfully":"failed to add new item";
	}
	@Override
	public String[] getShoppingList(Long flightId) {
		Airlines a = airlineRepo.findById(flightId).get();
		Set<String> s = new HashSet<String>();
		if(a.getShoppingItem()!=null)
		{
			for(String s1 : a.getShoppingItem().split(":")) {
				
				s.add(s1);	
			}
		}
		String []shoppingItems = new String[s.size()];
		int i=-1;
//		System.out.println(s);
		for(String s1: s)
		{
			if(s1.trim().length()>0)
			shoppingItems[++i] = s1 ;
		}
//		System.out.println(shoppingItems[0]);
		return a.getShoppingItem().split(":");
	}
	@Override
	public String deleteShoppingItem(Long flightId, String shoppingItem) {
		System.out.println(shoppingItem);
		Airlines a = airlineRepo.findById(flightId).get();
		String newCode = "";
		if(a.getShoppingItem()!=null)
		{
			for(String s : a.getShoppingItem().split(":")) 
			{
				if(!s.equalsIgnoreCase(shoppingItem))
				{
					newCode = newCode + s+":";
				}
			}
			a.setShoppingItem(newCode);
			System.out.println(newCode);
			airlineRepo.save(a);
		}
		else 
		{
			return null;
		}
		return "deleted successfully";
	}
	@Override
	public String[] generatePostal() {
		String[] pin = new String[8];
		for(int i = 0; i<pin.length;i++ )
		{
			String postal = "";
			for(int j = 1;j<=6;j++)
			{
				postal = postal + randomNumberGenerator(10);
			}
			pin[i] = postal;
		}
		return pin;
	}
	@Override
	public String updatePassenger(Passenger passenger) {
		System.out.println("============");
		int i = passengerRepo.updatePassengerAddress(
				passenger.getPassengerId(),
				passenger.getCity(),
				passenger.getRegion(),
				passenger.getState(),
				passenger.getPostal(),
				passenger.getPassportNo().toUpperCase()
				);
		return i==1?" passenger address updated":" failed to update";
	}
	
	
	
	
}
