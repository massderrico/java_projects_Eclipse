package ca.mcgill.ecse.carshop.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.model.*;
import ca.mcgill.ecse.carshop.model.Technician.TechnicianType;
import ca.mcgill.ecse.carshop.model.CarShop;
import ca.mcgill.ecse.carshop.model.Customer;
import ca.mcgill.ecse.carshop.model.Owner;
import ca.mcgill.ecse.carshop.model.Technician;
import ca.mcgill.ecse.carshop.model.User;
import ca.mcgill.ecse.carshop.persistence.CarShopPersistence;
import ca.mcgill.ecse.carshop.model.Appointment.AppointmentStatus;

import java.lang.String;
import java.sql.Time;
import java.sql.Date;
import ca.mcgill.ecse.carshop.model.BusinessHour.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import ca.mcgill.ecse.carshop.model.ComboItem;
import ca.mcgill.ecse.carshop.model.Appointment;
import ca.mcgill.ecse.carshop.model.TimeSlot;
import ca.mcgill.ecse.carshop.model.BookableService;
import ca.mcgill.ecse.carshop.model.ServiceBooking;
import ca.mcgill.ecse.carshop.model.ServiceCombo;
import ca.mcgill.ecse.carshop.model.Business;
import ca.mcgill.ecse.carshop.model.BusinessHour;
import ca.mcgill.ecse.carshop.model.Service;


public class CarShopController {

	/**
	 * 
	 * @author Ben
	 * @param username
	 * @param password
	 * @return boolean
	 * @throws InvalidInputException
	 */
	public static boolean login(String username, String password) throws InvalidInputException {
		CarShop carShop = CarShopApplication.getCarShop();

		//get all users
		Owner owner = carShop.getOwner();
		List<Technician> technicians = carShop.getTechnicians();
		List<Customer> customers = carShop.getCustomers();




		User user = null;   //target

		// creates owner account if none exists
		if(owner == null) {
			if(username.equals("owner")) {
				owner = new Owner(username, password ,carShop);
			}	
		}

		// Creates a technician if it doesn't exist
		// Assigns the correct garage to technician 
		// Assigns businessHours to garage
		if(username.equals("Tire-Technician") || username.equals("Engine-Technician" ) || username.equals("Transmission-Technician") || username.equals("Fluids-Technician")  || username.equals("Electronics-Technician")) {
			boolean exist = false;
			if(technicians != null) {
				for(Technician t: technicians) {
					if(t.getUsername().equals(username)) {
						exist = true;
					}
				}

			}
			if(!exist) {
				if(username.equals("Tire-Technician")){

					if(carShop.getBusiness()!= null) {
						List<BusinessHour> businessHours  = carShop.getBusiness().getBusinessHours();
						Technician tireTechnician = new Technician(username, password, StringToTechnicianType("Tire"), carShop);
						Garage tireGarage = new Garage(carShop, tireTechnician);
						for(BusinessHour hour: businessHours) {
							tireGarage.addBusinessHour(hour);
						}
					}
					else {
						Technician tireTechnician = new Technician(username, password, StringToTechnicianType("Tire"), carShop);
						Garage tireGarage = new Garage(carShop, tireTechnician);
					}				
				}

				if(username.equals("Engine-Technician")){
					if(carShop.getBusiness()!=null) {
						List<BusinessHour> businessHours  = carShop.getBusiness().getBusinessHours();
						Technician engineTechnician = new Technician(username, password, StringToTechnicianType("Engine"), carShop); 
						Garage engineGarage = new Garage(carShop,engineTechnician);
						for(BusinessHour hour: businessHours) {
							engineGarage.addBusinessHour(hour);
						}
					}else {
						Technician engineTechnician = new Technician(username, password, StringToTechnicianType("Engine"), carShop); 
						Garage engineGarage = new Garage(carShop,engineTechnician);
					}

				}

				if(username.equals("Transmission-Technician")){
					if(carShop.getBusiness()!=null) {
						List<BusinessHour> businessHours  = carShop.getBusiness().getBusinessHours();
						Technician transmissionTechnician = new Technician(username, password, StringToTechnicianType("Transmission"), carShop);
						Garage transmissionGarage = new Garage(carShop, transmissionTechnician);
						for(BusinessHour hour: businessHours) {
							transmissionGarage.addBusinessHour(hour);
						}
					}else {
						Technician transmissionTechnician = new Technician(username, password, StringToTechnicianType("Transmission"), carShop);
						Garage transmissionGarage = new Garage(carShop, transmissionTechnician);
					}
				}

				if(username.equals("Electronics-Technician")){
					if(carShop.getBusiness()!=null) {
						List<BusinessHour> businessHours  = carShop.getBusiness().getBusinessHours();

						Technician electronicsTechnician = new Technician(username, password, StringToTechnicianType("Electronics"), carShop);
						Garage electronicsGarage = new Garage(carShop, electronicsTechnician);
						for(BusinessHour hour: businessHours) {
							electronicsGarage.addBusinessHour(hour);
						}
					}else {
						Technician electronicsTechnician = new Technician(username, password, StringToTechnicianType("Electronics"), carShop);
						Garage electronicsGarage = new Garage(carShop, electronicsTechnician);
					}

				}

				if(username.equals("Fluids-Technician")){
					if(carShop.getBusiness()!=null) {
						List<BusinessHour> businessHours  = carShop.getBusiness().getBusinessHours();
						Technician fluidsTechnician = new Technician(username, password, StringToTechnicianType("Fluids"), carShop);
						Garage fluidsGarage = new Garage(carShop,fluidsTechnician);
						for(BusinessHour hour: businessHours) {
							fluidsGarage.addBusinessHour(hour);
						}
					}else {
						Technician fluidsTechnician = new Technician(username, password, StringToTechnicianType("Fluids"), carShop);
						Garage fluidsGarage = new Garage(carShop,fluidsTechnician);
					}

				}	
			}
		}

		// authenticate user
		if(owner != null) {
			if(owner.getUsername().equals(username) && owner.getPassword().equals(password)) {
				user = owner;	
			}
		}

		for(Technician t: technicians) {
			if(t.getUsername().equals(username) && t.getPassword().equals(password)) user=t;
		}

		for(Customer c: customers) {
			if(c.getUsername().equals(username) && c.getPassword().equals(password)) user=c;
		}


		if(user == null) {
			//unable to authenticate user
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new InvalidInputException("Username/password not found");   
		}
		else {
			// User successfully logged in
			CarShopApplication.add(user);
			CarShopPersistence.save(CarShopApplication.getCarShop());
			return true; 
		}

	}

	/**
	 * * Methods returns if a user is logged into the CarShopApplication
	 * @param username
	 * @return boolean
	 */
	public static boolean isLoggedIn(String username) {
		return CarShopApplication.isLoggedin(username);
	}

	// Method adds new businessHours to garage if they are within business days and don't overlap
	// Only a technician can add hours to their garage
	/**
	 * 
	 * @param businessHour
	 * @param user
	 * @param type
	 * @return boolean
	 * @throws InvalidInputException
	 */
	public static boolean addGarageHours(BusinessHourTO hourTO, String username, String garageType) throws InvalidInputException{
		CarShop carShop = CarShopApplication.getCarShop();
		BusinessHour businessHour = new BusinessHour(parseStringToDayOfWeek(hourTO.getDayOfWeek()), Time.valueOf(hourTO.getStartTime()+":00"), Time.valueOf(hourTO.getEndTime()+":00"), carShop);
		User user = User.getWithUsername(username);
		List<BusinessHour> bigbusinessHours = carShop.getBusiness().getBusinessHours();
		boolean isOpenOnDay = false;

		/**
		 * Check whether the business is open on new business day
		 */

		for(BusinessHour bh: bigbusinessHours) {
			if(bh.getDayOfWeek().equals(businessHour.getDayOfWeek())) {
				isOpenOnDay = true;
			}
		}

		if(carShop.getBusiness() == null) {
			businessHour.delete();
			throw new InvalidInputException("The Business does not exist!");
		}
		else if(!(user instanceof Technician)) {
			businessHour.delete();
			throw new InvalidInputException("You are not authorized to perform this operation");
		}
		else if(!(username.contains(garageType))) {
			businessHour.delete();
			throw new InvalidInputException("You are not authorized to perform this operation");
		}

		else if(!isOpenOnDay){
			businessHour.delete();
			throw new InvalidInputException("The opening hours are not within the opening hours of the business");
		}

		else if(businessHour.getStartTime().after(businessHour.getEndTime())) {
			businessHour.delete();
			throw new InvalidInputException("Start time must be before end time");	
		}

		List<BusinessHour> bigbusinessHoursOnDay = new ArrayList<BusinessHour>();
		for(BusinessHour bh: bigbusinessHours) {
			if(bh.getDayOfWeek().equals(businessHour.getDayOfWeek())) {
				bigbusinessHoursOnDay.add(bh);
			}


		}

		if(bigbusinessHoursOnDay.isEmpty()) {
			throw new InvalidInputException("There are no garage days on this day");

		}

		for(BusinessHour bh : bigbusinessHoursOnDay) {
			// Confirm new hours are within the opening hours of the business
			if(businessHour.getEndTime().after(bh.getEndTime())) {
				businessHour.delete();
				throw new InvalidInputException("The opening hours are not within the opening hours of the business");
			}
			if(businessHour.getStartTime().before(bh.getStartTime())) {
				businessHour.delete();
				throw new InvalidInputException("The opening hours are not within the opening hours of the business");
			}
		}

		//Confirm the new hour do not collide with hours within the garage
		if(((Technician) user).getGarage() != null) {


			List<BusinessHour> garageBusinessHours = ((Technician) user).getGarage().getBusinessHours();
			if(garageBusinessHours.isEmpty()) {
				((Technician) user).getGarage().addBusinessHour(businessHour);
				CarShopPersistence.save(CarShopApplication.getCarShop());
				return true;
			}
			else {
				List<BusinessHour> garageBusinessHoursOnDay = new ArrayList<BusinessHour>();

				for(BusinessHour hr: garageBusinessHours) {
					if(hr.getDayOfWeek().equals(businessHour.getDayOfWeek())) {
						garageBusinessHoursOnDay.add(hr);
					}
				}
				if(garageBusinessHoursOnDay.size() == 0) {
					((Technician) user).getGarage().addBusinessHour(businessHour);
					return true;
				}

				boolean collide = false;
				for(BusinessHour hr: garageBusinessHoursOnDay) {
					if(validateBusinessHour(hr,businessHour)) {
						//Check whether the hours already exist
						if(hr.getStartTime().equals(businessHour.getStartTime()) && hr.getEndTime().equals(businessHour.getEndTime())) {
							businessHour.delete();
							CarShopPersistence.save(CarShopApplication.getCarShop());
							throw new InvalidInputException("Business hours already exist!");
						}
					}
					else {
						collide = true;
					}
				}

				if(!collide) {
					((Technician) user).getGarage().addBusinessHour(businessHour);
					CarShopPersistence.save(CarShopApplication.getCarShop());
					return true;
				}
				else {
					businessHour.delete();
					CarShopPersistence.save(CarShopApplication.getCarShop());
					throw new InvalidInputException("New hours could not be added");
				}


			}
		}
		else {
			businessHour.delete();
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new InvalidInputException("New hours could not be added");
		}



		//		businessHour.delete();
		//		CarShopPersistence.save(CarShopApplication.getCarShop());
		//		//throw new InvalidInputException("New hours could not be added");
		//		return false;
	}





	/**
	 * Method removes business hour from garage hours if they exist
	 * Only a technician can remove hours from their own garage
	 * @param businessHour
	 * @param user
	 * @param type
	 * @return boolean
	 * @throws InvalidInputException
	 */
	public static boolean removeGarageHours(BusinessHourTO hourTO, String username, String garageType) throws InvalidInputException{
		CarShop carShop = CarShopApplication.getCarShop();

		BusinessHour businessHour = new BusinessHour(parseStringToDayOfWeek(hourTO.getDayOfWeek()), Time.valueOf(hourTO.getStartTime()), Time.valueOf(hourTO.getEndTime()), carShop);
		User user = User.getWithUsername(username);

		if(!(user instanceof Technician)) {
			businessHour.delete();
			throw new InvalidInputException("You are not authorized to perform this operation");
		}
		if(!(username.contains(garageType))) {
			businessHour.delete();
			throw new InvalidInputException("You are not authorized to perform this operation");
		}

		else {
			if(((Technician) user).getGarage() != null) {
				List<BusinessHour> garageBusinessHours = ((Technician) user).getGarage().getBusinessHours();
				if(garageBusinessHours.isEmpty()) {
					businessHour.delete();
					throw new InvalidInputException("The garage has no businessHours");
				}

				List<BusinessHour> garageBusinessHoursOnDay = new ArrayList<BusinessHour>();
				for(BusinessHour hr: garageBusinessHours) {
					if(hr.getDayOfWeek().equals(businessHour.getDayOfWeek())) {
						garageBusinessHoursOnDay.add(hr);
					}
				}

				for(BusinessHour hr: garageBusinessHoursOnDay) {
					if(hr.getStartTime().equals(businessHour.getStartTime()) && hr.getEndTime().equals(businessHour.getEndTime())) {
						((Technician) user).getGarage().removeBusinessHour(hr);
						hr.delete();
						CarShopPersistence.save(CarShopApplication.getCarShop());
						return true;
					}
					else {
						CarShopPersistence.save(CarShopApplication.getCarShop());
						throw new InvalidInputException("Business hour(s) could not be removed");
					}
				}			

			}
			else {
				CarShopPersistence.save(CarShopApplication.getCarShop());
				businessHour.delete();
				throw new InvalidInputException("Business hour(s) could not be removed");
			}
		}
		businessHour.delete();
		CarShopPersistence.save(CarShopApplication.getCarShop());
		throw new InvalidInputException("Business hour(s) could not be removed");
	}


	/**
	 * 
	 * @author Ben
	 *  Helper to convert string to day of week
	 * @param day
	 * @return
	 */
	public static DayOfWeek parseStringToDayOfWeek(String day) {
		if (day.equals("Monday")) {
			return DayOfWeek.Monday;}
		else if (day.equals("Tuesday")) {
			return DayOfWeek.Tuesday;}
		else if (day.equals("Wednesday")) {
			return DayOfWeek.Wednesday;}
		else if (day.equals("Thursday")) {
			return DayOfWeek.Thursday;}
		else if (day.equals("Friday")) {
			return DayOfWeek.Friday;}
		else if (day.equals("Saturday")) {
			return DayOfWeek.Saturday;}
		else if (day.equals("Sunday")) {
			return DayOfWeek.Sunday;}
		else {
			return null;}
	}

	/**
	 * Methods checks that new business hours are valid
	 * 
	 * @author Ben
	 * @param businessHour
	 * @param newBusinessHour
	 * @return boolean
	 */
	private static boolean validateBusinessHour(BusinessHour businessHour, BusinessHour newBusinessHour) {
		if((newBusinessHour.getStartTime().after(businessHour.getStartTime()) || newBusinessHour.getStartTime().equals(businessHour.getStartTime())) &&(newBusinessHour.getEndTime().before(businessHour.getEndTime()) || newBusinessHour.getEndTime().equals(businessHour.getEndTime()))){
			return false;
		}
		if((newBusinessHour.getEndTime().after(businessHour.getStartTime()) && newBusinessHour.getEndTime().before(businessHour.getEndTime()))) {
			return false;
		}
		if((newBusinessHour.getStartTime().after(businessHour.getStartTime()) && newBusinessHour.getEndTime().after(businessHour.getEndTime()))) {
			return false;
		}

		else {
			return true;}
	}

	/**
	 * Signs up a customer user account
	 * 
	 * @author Massimo D'Errico
	 *
	 */

	public static void signUp(String username, String password) throws Exception {
		if (username == null || username.trim().length() == 0) { // check the inputed username to see if valid
			throw new Exception("The user name cannot be empty");// throw error if input is invalid
		}
		if (password == null || password.trim().length() == 0) {// check the inputed password to see if valid
			throw new Exception("The password cannot be empty");// throw error if input is invalid
		}
		CarShop carShop = CarShopApplication.getCarShop();
		if (CarShopApplication.getRecentlyLogedinUser() != null // check to see if owner is logged in
				&& CarShopApplication.getRecentlyLogedinUser() instanceof Owner) {
			if (CarShopApplication.getRecentlyLogedinUser().getUsername().equals(carShop.getOwner().getUsername())) {
				CarShopPersistence.save(CarShopApplication.getCarShop());
				throw new Exception("You must log out of the owner account before creating a customer account"); // throws
				// error
				// if
				// owner
				// tried
				// to
				// make
				// an
				// account
				// while
				// logged
				// in
			}
		}
		if (CarShopApplication.getRecentlyLogedinUser() != null // check to see if technician is logged in
				&& CarShopApplication.getRecentlyLogedinUser() instanceof Technician) {
			for (int i = 0; i < carShop.numberOfTechnicians(); i++) {
				if (CarShopApplication.getRecentlyLogedinUser().getUsername()
						.equals(carShop.getTechnician(i).getUsername())) {
					CarShopPersistence.save(CarShopApplication.getCarShop());
					throw new Exception(
							"You must log out of the technician account before creating a customer account"); // throws
					// error
					// if
					// technician
					// is
					// logged
					// in
				}
			}
		}

		if (User.hasWithUsername(username) == true) { // checks for duplicate username
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("The username already exists");

		}

		new Customer(username, password, carShop); // creates new Customer account
		CarShopPersistence.save(CarShopApplication.getCarShop());
	}

	// Nath
	public static void updateService(String serviceName, String newName, String newDuration,String type)
			throws Exception {

		User requestingUser = CarShopApplication.getRecentlyLogedinUser();

		try {
			updateService(serviceName,newName, Integer.parseInt(newDuration),type, requestingUser);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	//Nath
	public static String[] getServiceList() {
		CarShop carshop = CarShopApplication.getCarShop();

		List<String> services = new ArrayList<String>();

		for(BookableService s: carshop.getBookableServices()) {
			if(s instanceof Service) {
				services.add(s.getName());
			}
		}

		String[] result = new String[services.size()];
		return services.toArray(result);
	}


	/**
	 * Updates user account
	 * 
	 * @author Massimo D'Errico
	 *
	 */

	public static void updateAccount(String newUser, String newPassword) throws Exception {
		if (newUser == null || newUser.trim().length() == 0) {// check the inputed username to see if valid
			throw new Exception("The user name cannot be empty"); // throw error if input is invalid
		}
		if (newPassword == null || newPassword.trim().length() == 0) {// check the inputed password to see if valid
			throw new Exception("The password cannot be empty");/// throw error if input is invalid
		}

		if (CarShopApplication.getRecentlyLogedinUser() != null
				&& CarShopApplication.getRecentlyLogedinUser() instanceof Owner // checks to see if owner is logged in
				&& CarShopApplication.getRecentlyLogedinUser().getUsername().equals(newUser) == false) {
			throw new Exception("Changing username of owner is not allowed"); // throws error if owner tries to change
			// username
		}
		if (CarShopApplication.getRecentlyLogedinUser() != null
				&& CarShopApplication.getRecentlyLogedinUser() instanceof Technician // checks to see if technician is
				// logged in
				&& CarShopApplication.getRecentlyLogedinUser().getUsername().equals(newUser) == false) {
			throw new Exception("Changing username of technician is not allowed"); // throws error if technician tries
			// to change username

		}
		if (CarShopApplication.getRecentlyLogedinUser() != null && User.hasWithUsername(newUser) // checks to see if new
				// username is the
				// same as an
				// existing user
				&& CarShopApplication.getRecentlyLogedinUser() instanceof Customer && (CarShopApplication.getRecentlyLogedinUser().getUsername().equals(newUser) == false)) {
			throw new Exception("Username not available"); // throws error if username is already taken
		}
		CarShopApplication.getRecentlyLogedinUser().setUsername(newUser); // updates username
		CarShopApplication.getRecentlyLogedinUser().setPassword(newPassword);// updates password
		CarShopPersistence.save(CarShopApplication.getCarShop());
	}

	/**
	 * Adds a service to the model
	 * 
	 * @author Nathanael Lemma
	 * @param serviceName
	 * @param duration
	 * @param garageName
	 * @param requestingUser
	 * @throws Exception
	 */
	public static void addService(String serviceName, int duration, String garageName, User requestingUser)
			throws Exception {

		// only owner is allowed to add services to the system
		if (!(requestingUser instanceof Owner)) {
			throw new Exception("You are not authorized to perform this operation");}

		if (duration < 1) {
			throw new Exception("Duration must be positive");}

		else {
			try {
				CarShop carshop = CarShopApplication.getCarShop();
				Technician target = null;

				TechnicianType type = StringToTechnicianType(garageName);
				List<Technician> technicians = carshop.getTechnicians();

				// find technician with TechnicianType type
				for (Technician tech : technicians) {
					if (tech.getType() == type) {
						target = tech;
					}
				}

				new Service(serviceName, carshop, duration, target.getGarage());
				CarShopPersistence.save(CarShopApplication.getCarShop());
			} catch (Exception e) {
				String message = "Service " + serviceName + " already exists";
				CarShopPersistence.save(CarShopApplication.getCarShop());
				throw new Exception(message);
			}
		}
		CarShopPersistence.save(CarShopApplication.getCarShop());
	}

	/**
	 * Updates a service
	 * 
	 * @author Nathanael Lemma
	 * @param serviceNameToBeChanged
	 * @param newName
	 * @param newDuration
	 * @param newGarageName
	 * @param requestingUser
	 * @throws Exception
	 */
	public static void updateService(String serviceNameToBeChanged, String newName, int newDuration,
			String newGarageName, User requestingUser) throws Exception {

		// only owner is allowed to add services to the system
		if (!(requestingUser instanceof Owner)) {
			throw new Exception("You are not authorized to perform this operation");
		}

		if (newDuration < 1) {
			throw new Exception("Duration must be positive");
		}

		else {
			try {
				CarShop carshop = CarShopApplication.getCarShop();
				Service target = null;

				TechnicianType type = StringToTechnicianType(newGarageName);
				List<Technician> technicians = carshop.getTechnicians();

				for (Technician tech : technicians) {
					Garage g = tech.getGarage();
					if (g != null) {
						List<Service> services = g.getServices();

						if (services != null) {
							for (Service s : services) {

								// target service found
								if (s.getName().equals(serviceNameToBeChanged))
									target = s;

								// true when the newName to be set is different from the current Name
								// & and that the New Name to be set is already taken
								if (!newName.equals(serviceNameToBeChanged) && s.getName().equals(newName)) {
									String message = "Service " + newName + " already exists";
									CarShopPersistence.save(CarShopApplication.getCarShop());
									throw new Exception(message);
								}
							}
						}
					}
				}

				// update changes to target service
				target.setDuration(newDuration);
				target.setName(newName);
				CarShopPersistence.save(CarShopApplication.getCarShop());
				// change garage
				if (!target.getGarage().getTechnician().getType().equals(type)) {
					for (Technician t : technicians) {
						if (t.getType() == type) {
							target.getGarage().removeService(target);
							t.getGarage().addService(target);
							CarShopPersistence.save(CarShopApplication.getCarShop());
						}
					}
				}
			} catch (Exception e) {
				CarShopPersistence.save(CarShopApplication.getCarShop());
				throw new Exception(e.getMessage());
			}
		}
		CarShopPersistence.save(CarShopApplication.getCarShop());
	}

	/**
	 * Search for a service by name and return a corresponding ToService object
	 * 
	 * @author Nathanael Lemma
	 * @param name
	 * @return ToService
	 */
	public static ToService searchServiceByName(String name) {
		CarShop carshop = CarShopApplication.getCarShop();
		Service target = null;

		List<Technician> technicians = carshop.getTechnicians();

		for (Technician t : technicians) {
			Garage g = t.getGarage();
			if (g != null) {
				List<Service> services = g.getServices();
				for (Service s : services) {
					if (s.getName().equals(name))
						target = s;
				}
			}
		}
		if (target == null) {
			return null;}

		ToService s = new ToService(target.getName(), TechnicianTypeToString(target.getGarage().getTechnician().getType()),
				target.getDuration());
		return s;
	}


	public static ToServiceCombo searchServiceComboByName(String name) {
		CarShop carshop = CarShopApplication.getCarShop();
		ServiceCombo target = (ServiceCombo) ServiceCombo.getWithName(name);
		ToServiceCombo serviceCombo = new ToServiceCombo(target.getMainService().getService().getName());
		serviceCombo.addMandatory(target.getMainService().getService().getName());
		for(ComboItem c: target.getServices()) {
			serviceCombo.addService(c.getService().getName());
			serviceCombo.addMandatory(Boolean.toString(c.getMandatory()));
		}

		return serviceCombo;
	}

	public static void addService(String serviceName, String duration, String type)
			throws Exception {

		User requestingUser = CarShopApplication.getRecentlyLogedinUser();

		try {
			addService(serviceName,Integer.parseInt(duration),type, requestingUser);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static String[] getGarageList() {
		CarShop carshop = CarShopApplication.getCarShop();

		List<Garage> garages = carshop.getGarages();
		String[] garageNames = new String [garages.size()];

		// find technician with TechnicianType type
		for (int i = 0; i<garages.size();i++) {
			garageNames[i] = TechnicianTypeToString(garages.get(0).getTechnician().getType());
		}
		return garageNames;
	}
	/**
	 * -- Helper method
	 * 
	 * @author Nathanael Lemma
	 * 
	 */
	public static void quickServiceAdd(String serviceName, int duration, String garageName) throws Exception {

		CarShop carshop = CarShopApplication.getCarShop();
		Technician target = null;

		TechnicianType type = StringToTechnicianType(garageName);
		List<Technician> technicians = carshop.getTechnicians();

		for (Technician tech : technicians) {
			if (tech.getType() == type) {
				target = tech;
			}
		}

		new Service(serviceName, carshop, duration, target.getGarage());
		CarShopPersistence.save(CarShopApplication.getCarShop());
	}

	/**
	 * -- Helper method
	 * 
	 * @author Nathanael Lemma
	 * 
	 */
	public static TechnicianType StringToTechnicianType(String type) {
		if (type.equals("Tire")) {
			return TechnicianType.Tire;}
		else if (type.equals("Engine")) {
			return TechnicianType.Engine;}
		else if (type.equals("Transmission")) {
			return TechnicianType.Transmission;}
		else if (type.equals("Electronics")) {
			return TechnicianType.Electronics;}
		else if (type.equals("Fluids")) {
			return TechnicianType.Fluids;}
		else {
			return null;}
	}

	/**
	 * -- Helper method
	 * 
	 * @author Nathanael Lemma
	 * 
	 */
	public static String TechnicianTypeToString(TechnicianType type) {
		if (type == TechnicianType.Tire) {
			return "Tire";}
		else if (type == TechnicianType.Engine) {
			return "Engine";}
		else if (type == TechnicianType.Transmission) {
			return "Transmission";}
		else if (type == TechnicianType.Electronics) {
			return "Electronics";}
		else if (type == TechnicianType.Fluids) {
			return "Fluids";}
		else {
			return null;}
	}

	/**
	 * @author Tiffany
	 * @param uName        The username of the currently-logged-in user
	 * @param name         What you want to name the service combo
	 * @param SmainService The main service of the combo (String)
	 * @param sServices    The list of services of the combo (String)
	 * @param mandatory    The mandatory settings of the service list (Boolean)
	 * @throws Exception thrown when an invalid input is detected
	 */
	// Assumes that a carshop, an owner, and each type of technician + garage exist
	public static void defineServiceCombo(String uName, String name, String SmainService, List<String> sServices,
			List<Boolean> mandatory) throws Exception {
		CarShop carShop = CarShopApplication.getCarShop();
		String error = "";

		List<Service> Nservices = new ArrayList<>();
		CarShopPersistence.save(CarShopApplication.getCarShop());
		List<ComboItem> cServices = new ArrayList<>();
		CarShopPersistence.save(CarShopApplication.getCarShop());

		// if the user is a customer or technician
		for (Customer c : carShop.getCustomers()) {
			if (c.getUsername().equals(uName)) {
				error = "You are not authorized to perform this operation";
				CarShopPersistence.save(CarShopApplication.getCarShop());
				throw new Exception(error);
			}
		}
		for (Technician t : carShop.getTechnicians()) {
			if (t.getUsername().equals(uName)) {
				error = "You are not authorized to perform this operation";
				CarShopPersistence.save(CarShopApplication.getCarShop());
				throw new Exception(error);
			}
		}

		ServiceCombo combo = new ServiceCombo("", carShop); // create combo
		CarShopPersistence.save(CarShopApplication.getCarShop());

		// Checks if the services exist in the business with strings
		List<String> carShopServiceNames = new ArrayList<>();
		CarShopPersistence.save(CarShopApplication.getCarShop());
		for (BookableService s : carShop.getBookableServices())
			if (s instanceof Service) {
				carShopServiceNames.add(s.getName());
				CarShopPersistence.save(CarShopApplication.getCarShop());} // gets names of all services in the carshop

		for (int j = 0; j < sServices.size(); j++) {
			if (!carShopServiceNames.contains(sServices.get(j))) { // checks if declared service exists in business
				combo.delete();
				CarShopPersistence.save(CarShopApplication.getCarShop());
				error = "Service " + sServices.get(j) + " does not exist";
				CarShopPersistence.save(CarShopApplication.getCarShop());
				throw new Exception(error);
			}

			if (!sServices.contains(SmainService)) { // checks if main service is included in service list
				combo.delete();
				CarShopPersistence.save(CarShopApplication.getCarShop());
				error = "Main service must be included in the services";
				CarShopPersistence.save(CarShopApplication.getCarShop());
				throw new Exception(error);
			}
		}

		// convert strings to actual services

		for (String s : sServices) {
			for (BookableService b : carShop.getBookableServices()) {
				if (s.equals(b.getName())) {
					Nservices.add((Service) b);
					CarShopPersistence.save(CarShopApplication.getCarShop());
				}
			}
		}

		// If mandatory settings don't match number of services
		if (Nservices.size() != mandatory.size()) {
			combo.delete();
			CarShopPersistence.save(CarShopApplication.getCarShop());
			error = "Mandatory settings do not match number of services";
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception(error);
		}

		// convert services to comboItems
		for (int j = 0; j < Nservices.size(); j++) {
			cServices.add(new ComboItem(mandatory.get(j), Nservices.get(j), combo));
			CarShopPersistence.save(CarShopApplication.getCarShop());
		}

		// find the main service comboItem
		ComboItem mainService = null;
		for (ComboItem c : cServices) {
			if (c.getService().getName().equals(SmainService)) {
				mainService = c;
			}
		}

		combo.setMainService(mainService);

		// Owner gives a non-mandatory main service
		if (mainService.getMandatory() == false) {
			combo.delete();
			CarShopPersistence.save(CarShopApplication.getCarShop());
			error = "Main service must be mandatory";
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception(error);
		}
		// set main service
		combo.setMainService(mainService);

		// Checks if combo has at least 2 services
		if (cServices.size() < 2) { // There is only one (or less) service in the combo
			combo.delete();
			CarShopPersistence.save(CarShopApplication.getCarShop());
			error = "A service Combo must contain at least 2 services";
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception(error);
		}

		for (BookableService c : carShop.getBookableServices()) { // Checks if combo name already exists
			if (name.equals(c.getName())) {
				combo.delete();
				CarShopPersistence.save(CarShopApplication.getCarShop());
				error = "Service combo " + name + " already exists";
				CarShopPersistence.save(CarShopApplication.getCarShop());
				throw new Exception(error);
			}
		}

		// set name
		combo.setName(name);
		// If no errors exist, combo is complete.
		CarShopPersistence.save(CarShopApplication.getCarShop());
	}

	/**
	 * @author Tiffany
	 * @param uName        The username of the currently-logged-in-user
	 * @param oldName      The name of the existing service-combo you want to change
	 * @param name         The updated of the service-combo
	 * @param SmainService The updated main service of the service combo (String)
	 * @param sServices    The updated service list of the service combo (String)
	 * @param mandatory    The updated mandatory settings of the service combo
	 *                     (Boolean)
	 * @throws Exception thrown when an invalid input is detected
	 */

	// Same assumptions to addServiceCombo
	public static void updateServiceCombo(String uName, String oldName, String name, String SmainService,
			List<String> sServices, List<Boolean> mandatory) throws Exception {
		CarShop carShop = CarShopApplication.getCarShop();
		ServiceCombo oldCombo = null;

		// find servicecombo we want to change:
		for (BookableService b : carShop.getBookableServices()) {
			if (b instanceof ServiceCombo && b.getName().equals(oldName))
				oldCombo = (ServiceCombo) b;
		}

		String holdName = oldName;

		try {
			if (!oldName.equals(name)) {
				defineServiceCombo(uName, name, SmainService, sServices, mandatory);
			} else {
				// use a dummy name to avoid error
				if (name.equals("dummyName"))
					oldCombo.setName("dummyName2");
				else
					oldCombo.setName("dummyName");

				defineServiceCombo(uName, name, SmainService, sServices, mandatory);
			}
		} catch (Exception e) {
			oldCombo.setName(holdName);
			// set name back to original
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception(e.getMessage());
		}

		oldCombo.delete();
		CarShopPersistence.save(CarShopApplication.getCarShop());
		// effectively replaces the original combo with the new combo
	}

	/**
	 * @author Ramin Akhavan-Sarraf
	 * 
	 *         This method is responsible for setting up the business info when
	 *         there is no business that exists
	 * @param name:     String
	 * @param address:  String
	 * @param phoneNum: String
	 * @param email:    String
	 * @throws Exception - when an invalid input is detected
	 */
	public static void setUpBusinessInfo(String name, String address, String phoneNum, String email) throws Exception {
		User currentUser = CarShopApplication.getRecentlyLogedinUser(); // gets last logged in user

		// if the user is not an owner
		if (!(currentUser instanceof Owner)) {
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("No permission to set up business information");
		}
		if(!(CarShopApplication.getCarShop().getBusiness() == null)) {
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("Business already set up");
		}
		// if the email is not a valid email
		if (!isValidEmail(email)) { // calls helper method to check this
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("Invalid email");
		}
		Business newBusiness = new Business(name, address, phoneNum, email, CarShopApplication.getCarShop());
		// new business is created if no error is thrown
		CarShopPersistence.save(CarShopApplication.getCarShop());
	}

	/**
	 * @author Ramin Akhavan-Sarraf
	 * 
	 *         The following is a helper method that is used to create a time object
	 *         from an inputted string
	 * @param time: String
	 */
	public static Time StringToTime(String time) {
		String hour = "";
		String minute = "";
		boolean colon = false; // colon is used to seperate each element of the time

		for (int i = 0; i < time.length(); i++) { // goes through string characters
			if (time.charAt(i) != ':' && !colon) { // before the colon is the hour value
				hour += time.charAt(i); // concatenates character to hour string
			}
			if (time.charAt(i) != ':' && colon) { // after colon is the minute value
				minute += time.charAt(i); // concatenates character to minute string
			}
			if (time.charAt(i) == ':') { // if reaches separation point of hour and minute
				colon = true;
			}
		}
		// the following converts the respective strings to integers and passes them as
		// arguments for the time object

		@SuppressWarnings("deprecation")
		Time t = new Time(Integer.valueOf(hour), Integer.valueOf(minute), 0);
		CarShopPersistence.save(CarShopApplication.getCarShop());
		return t;
	}


	/**
	 * @author Ramin Akhavan-Sarraf
	 * 
	 *         This is a helper method used to convert strings to the enumerated
	 *         type DayOfWeek
	 * @param type: String
	 */
	public static DayOfWeek StringToDay(String type) {

		// the following conditions check to see what day of the week the string is
		// afterwards the respective enumerated type value is returned
		if (type.equals("Monday")) {
			return DayOfWeek.Monday;
		} else if (type.equals("Tuesday")) {
			return DayOfWeek.Tuesday;
		} else if (type.equals("Wednesday")) {
			return DayOfWeek.Wednesday;
		} else if (type.equals("Thursday")) {
			return DayOfWeek.Thursday;

		} else if (type.equals("Friday")) {
			return DayOfWeek.Friday;
		} else if (type.equals("Saturday")) {
			return DayOfWeek.Saturday;
		} else if (type.equals("Sunday")) {
			return DayOfWeek.Sunday;
		}
		// if the string is not a day
		else {
			return null;}
	}

	/**
	 * @author Ramin Akhavan-Sarraf
	 * 
	 *         This is a helper method that is used to check whether an email is
	 *         valid. (Contains an @ character and a domain)
	 * @param email: String
	 */
	private static boolean isValidEmail(String email) {
		boolean validEmail = false; // starts off by saying the email is not valid (guilty until proven innocent)

		String[] emailComponents = email.split("@"); // splits string, where there is an @, into two different string
		// stored into an array
		if (emailComponents.length == 2) { // if only one @
			// the following splits the domain where there is a period (\\ is used as an
			// escape sequence)
			String[] domain = emailComponents[1].split("\\.");
			// if there is at least one period
			if (domain.length != 1) {
				validEmail = true; // email is valid at this point
			}
		}
		CarShopPersistence.save(CarShopApplication.getCarShop());
		return validEmail; // returns validity of email

	}

	/**
	 * @author Ramin Akhavan-Sarraf
	 * 
	 *         This is a method used to add business hours, given the say, start
	 *         time and end time
	 * @param day:       String
	 * @param startTime: Time
	 * @param endTime:   Time
	 * @throws Exception - when an invalid input is detected
	 */
	public static void addBusinessHours(String day, Time startTime, Time endTime) throws Exception {
		User currentUser = CarShopApplication.getRecentlyLogedinUser();

		// if user is not an owner
		if (!(currentUser instanceof Owner)) {
			throw new Exception("No permission to update business information");
		}

		// if start time is not before the end time (does not make sense)
		if (!startTime.before(endTime)) {
			throw new Exception("Start time must be before end time");
		}
		// if the business hours overlap
		if (businessOverlap(day, startTime, endTime)) { // calls helper method to check
			throw new Exception("The business hours cannot overlap");
		}
		if(CarShopApplication.getCarShop().getBusiness() == null) {
			throw new Exception("No business is set up");
		}
		DayOfWeek dayOfWeek = StringToDay(day); //converts the day string to a DayOfWeek
		//a new business hour is created
		BusinessHour businessHour = new BusinessHour(dayOfWeek, startTime, endTime, CarShopApplication.getCarShop());
		CarShopApplication.getCarShop().getBusiness().addBusinessHour(businessHour);
		CarShopPersistence.save(CarShopApplication.getCarShop());
	}

	/**
	 * @author Ramin Akhavan-Sarraf
	 * 
	 *         This is a helper method that checks if the given business hour
	 *         details will overlap with already existing business hours (modified)
	 * @param day:       String
	 * @param startTime: Time
	 * @param endTime:   Time
	 * @return: boolean
	 */
	private static boolean businessOverlap(String day, Time startTime, Time endTime) {
		CarShop carshop = CarShopApplication.getCarShop();
		boolean overlap = false; // no overlap to start (innocent until proven guilty)
		for (BusinessHour b : carshop.getBusiness().getBusinessHours()) { // goes through all business hours
			if (b.getDayOfWeek().toString().equalsIgnoreCase(day)) { // if they are on the same day
				// If the following conditions are true, there is overlap
				// if the start time is before the new start time and the end time is after the
				// new start time
				if (b.getStartTime().toLocalTime().isBefore(startTime.toLocalTime()) && b.getEndTime().toLocalTime().isAfter(startTime.toLocalTime())) {
					overlap = true;
				}
				// if the end time is after the new end time and the start time is before the
				// new end time
				if (b.getEndTime().toLocalTime().isAfter(endTime.toLocalTime()) && b.getStartTime().toLocalTime().isBefore(endTime.toLocalTime())) {
					overlap = true;
				}
				// if the start time and end time are between the new start time and new end
				// time
				if (b.getStartTime().toLocalTime().isAfter(startTime.toLocalTime()) && b.getEndTime().toLocalTime().isBefore(endTime.toLocalTime())) {
					overlap = true;
				}
			}
		}
		return overlap; // returns whether there is overlap
	}

	private static boolean openBusinessOverlap(String day, Time startTime, Time endTime) {
		CarShop carshop = CarShopApplication.getCarShop();
		boolean overlap = false; // no overlap to start (innocent until proven guilty)
		for (BusinessHour b : carshop.getBusiness().getBusinessHours()) { // goes through all business hours
			if (b.getDayOfWeek().toString().equalsIgnoreCase(day)) { // if they are on the same day
				if (b.getStartTime().toLocalTime().isBefore(startTime.toLocalTime()) && b.getEndTime().toLocalTime().isAfter(endTime.toLocalTime())) {
					overlap = true;
				}
			}
		}
		return overlap; // returns whether there is overlap
	}

	private static boolean openGarageOverlap(String day, Time startTime, Time endTime) {
		CarShop carshop = CarShopApplication.getCarShop();
		boolean overlap = false; // no overlap to start (innocent until proven guilty)
		for (int i = 0; i<carshop.getGarages().get(0).getBusinessHours().size(); i++) { // goes through all business hours
			BusinessHour garHour = carshop.getGarages().get(0).getBusinessHour(i);
			if (garHour.getDayOfWeek().toString().equalsIgnoreCase(day)) { // if they are on the same day
				if (garHour.getStartTime().toLocalTime().isBefore(startTime.toLocalTime()) && garHour.getEndTime().toLocalTime().isAfter(endTime.toLocalTime())) {
					overlap = true;
				}
			}
		}
		return overlap; // returns whether there is overlap
	}

	/**
	 * @author Ramin Akhavan-Sarraf
	 * 
	 *         This is a method that gives the user a list of strings to give them
	 *         the business info
	 * @return: List<String>
	 */
	public static List<String> viewBusinessInfo() {
		CarShop carshop = CarShopApplication.getCarShop();
		Business business = carshop.getBusiness();
		List<String> businessInfo = new ArrayList<String>();
		CarShopPersistence.save(CarShopApplication.getCarShop());
		// adds each part of the business info to the list in a correct order
		businessInfo.add(business.getName());
		businessInfo.add(business.getAddress());
		businessInfo.add(business.getPhoneNumber());
		businessInfo.add(business.getEmail());
		CarShopPersistence.save(CarShopApplication.getCarShop());
		return businessInfo; // returns the business info as a list of strings
	}

	/**
	 * @author Ramin Akhavan-Sarraf
	 * 
	 *         This is a method used to add a time slot to the carshop, given the
	 *         type of time slot, as well as the start and end dates and times
	 * @param type:      String
	 * @param startDate: Date
	 * @param startTime: Time
	 * @param endDate:   Date
	 * @param endTime:   Time
	 * @throws Exception - when an invalid input is detected
	 */
	public static void addTimeSlot(String type, Date startDate, Time startTime, Date endDate, Time endTime)
			throws Exception {
		CarShop carshop = CarShopApplication.getCarShop();
		User currentUser = CarShopApplication.getRecentlyLogedinUser();

		// if the user is not an owner
		if (!(currentUser instanceof Owner)) {
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("No permission to update business information");
		}
		// if the start date is not before the end date
		if (!(startDate.before(endDate))) {
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("Start time must be before end time");
		}
		// if the start date is the same as the end date, but the start time is still
		// not before the end time
		else if (startDate.compareTo(endDate) == 0 && !(startTime.before(endTime))) {
			throw new Exception("Start time must be before end time");
		}

		// if the start date is before the current date of the system
		// OR they are on the same date, but the start time is still before the current
		// time of the system
		if (startDate.toLocalDate().isBefore(CarShopApplication.getLocalDate()) || (startDate.toLocalDate().isEqual(CarShopApplication.getLocalDate())
				&& startTime.toLocalTime().isBefore(CarShopApplication.getLocalTime()))) {

			if (type.equalsIgnoreCase("vacation")) { // if a vacation is being inputed
				throw new Exception("Vacation cannot start in the past");
			} else if (type.equalsIgnoreCase("holiday")) { // if a holiday is being inputed
				throw new Exception("Holiday cannot start in the past");
			}
		}
		// checks to see if there is overlap with other time slots
		checkTimeSlotOverlap(type, startDate, startTime, endDate, endTime);

		// if reaches this point, a new time slot is created
		TimeSlot timeSlot = new TimeSlot(startDate, startTime, endDate, endTime, carshop);
		CarShopPersistence.save(CarShopApplication.getCarShop());
		if (type.equalsIgnoreCase("vacation")) { // if it is a vacation
			carshop.getBusiness().addVacation(timeSlot);// added as vacation
			CarShopPersistence.save(CarShopApplication.getCarShop());
		} else if (type.equalsIgnoreCase("holiday")) { // if it is a holiday
			carshop.getBusiness().addHoliday(timeSlot); // added as holiday
			CarShopPersistence.save(CarShopApplication.getCarShop());
		}

	}

	/**
	 * @author Ramin Akhavan-Sarraf
	 * 
	 *         This is a method that is used to update existing business hours,
	 *         given the day and time info to find the business hour and the new day
	 *         and times to changed the appropriate fields
	 * @param currentDay:       String
	 * @param currentStartTime: Time
	 * @param newDay:           String
	 * @param newStartTime:     Time
	 * @param newEndTime:       Time
	 * @throws Exception - when an invalid input is detected
	 */
	public static void updateBusinessHours(String currentDay, Time currentStartTime, String newDay, Time newStartTime,
			Time newEndTime) throws Exception {
		User currentUser = CarShopApplication.getRecentlyLogedinUser();
		if(CarShopApplication.getCarShop().getBusiness() == null) {
			throw new Exception("No business is set up");
		}
		// if the user is not an owner
		if (!(currentUser instanceof Owner)) {
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("No permission to update business information");
		}

		// if the new start time is not before the new end time
		if (!newStartTime.before(newEndTime)) {
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("Start time must be before end time");
		}

		// if there is business hour overlap with the new information that is being
		// inputed
		if (businessOverlap(newDay, newStartTime, newEndTime) && !StringToDay(currentDay).equals(StringToDay(newDay))) {
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("The business hours cannot overlap");

		}
		// finds business hour needed to update
		BusinessHour businessHourToChange = findBusinessHour(currentDay, currentStartTime);

		// if no business hour is found
		if (businessHourToChange == null) {
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("No business hour found"); // error is displayed (optional component that I added)
		}

		// the following set methods change all the attributes of the business hour
		// accordingly

		CarShopApplication.getCarShop().getBusiness().removeBusinessHour(businessHourToChange);
		businessHourToChange.setDayOfWeek(StringToDay(newDay));
		businessHourToChange.setStartTime(newStartTime);
		businessHourToChange.setEndTime(newEndTime);
		CarShopApplication.getCarShop().getBusiness().addBusinessHour(businessHourToChange);

		CarShopPersistence.save(CarShopApplication.getCarShop());
	}

	/**
	 * @author Ramin Akhavan-Sarraf
	 * 
	 *         This is a method that is used to update existing business info, given
	 *         the new information that would like to be used instead
	 * @param name:     String
	 * @param address:  String
	 * @param phoneNum: String
	 * @param email:    String
	 * @throws Exception - when an invalid input is detected
	 */
	public static void updateBusinessInfo(String name, String address, String phoneNum, String email) throws Exception {
		CarShop carshop = CarShopApplication.getCarShop();
		User currentUser = CarShopApplication.getRecentlyLogedinUser();

		String newName = name;
		String newAddress = address;
		String newPhone = phoneNum;
		String newEmail = email;

		if(name.isEmpty()) {
			if(carshop.getBusiness() != null) {
				newName = carshop.getBusiness().getName();
			}
		}
		if(address.isEmpty()) {
			if(carshop.getBusiness() != null) {
				newAddress = carshop.getBusiness().getAddress();
			}
		}
		if(phoneNum.isEmpty()) {
			if(carshop.getBusiness() != null) {
				newPhone = carshop.getBusiness().getPhoneNumber();
			}
		}
		if(email.isEmpty()) {
			if(carshop.getBusiness() != null) {
				newEmail = carshop.getBusiness().getEmail();
			}
		}

		// if the user is not an owner
		if (!(currentUser instanceof Owner)) {
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("No permission to update business information");
		}

		// if a valid email is not being inputed
		if (!isValidEmail(newEmail)) {
			throw new Exception("Invalid email");
		}

		// if there is a business in the carshop
		if (carshop.getBusiness() != null) {
			// all business info is changed accordingly
			carshop.getBusiness().setName(newName);
			carshop.getBusiness().setAddress(newAddress);
			carshop.getBusiness().setPhoneNumber(newPhone);
			carshop.getBusiness().setEmail(newEmail);
		}
		// no business can be found
		else {
			throw new Exception("No business has been set up"); // error is displayed (optional component i added)
		}

	}

	/**
	 * @author Ramin Akhavan-Sarraf
	 * 
	 *         This method is responsible for removing business hours, given the day
	 *         and start time of the business hour that needs to be removed
	 * @param currentDay:       String
	 * @param currentStartTime: Time
	 * @throws Exception - when an invalid input is detected
	 */

	public static void removeBusinessHours(String currentDay, Time currentStartTime) throws Exception {
		Object currentUser = CarShopApplication.getRecentlyLogedinUser();

		if(CarShopApplication.getCarShop().getBusiness() == null) {
			throw new Exception("No business is set up");
		}
		// if the user is not an owner
		if (!(currentUser instanceof Owner)) {
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("No permission to update business information");
		}
		// finds business hour needed to remove
		BusinessHour businessHourToRemove = findBusinessHour(currentDay, currentStartTime);
		// if no business hour is found
		if (businessHourToRemove == null) {
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("No business hour found"); // error is displayed (optional component that I added)
		}

		CarShopApplication.getCarShop().getBusiness().removeBusinessHour(businessHourToRemove);
		businessHourToRemove.delete();
		CarShopPersistence.save(CarShopApplication.getCarShop());
	}

	/**
	 * @author Ramin Akhavan-Sarraf
	 * 
	 *         This is a helper method that is responsible for finding the
	 *         appropriate business hour, given the day and the start time
	 * @param currentDay:       String
	 * @param currentStartTime: Time
	 * @return: BusinessHour
	 */

	private static BusinessHour findBusinessHour(String currentDay, Time currentStartTime) {
		CarShop carshop = CarShopApplication.getCarShop();
		List<BusinessHour> businessHours = carshop.getBusiness().getBusinessHours();
		BusinessHour businessHour = null;
		DayOfWeek curDay = StringToDay(currentDay);
		// goes through all the business hours to retrieve the business hour that will
		// be changed
		for (BusinessHour b : businessHours) {
			// condition to see if a business hour matches the information provided
			if (b.getDayOfWeek().compareTo(curDay) == 0 && b.getStartTime().compareTo(currentStartTime) == 0) {
				businessHour = b; // stores the business hour that needs to be changed
			}
		}
		return businessHour; // returns found business hour (null if no business hour is found)
	}

	/**
	 * @author Ramin Akhavan-Sarraf
	 * 
	 *         This is a helper method used to see if the new time slot information
	 *         overlaps with any of the time slots
	 * @param type:         String
	 * @param newStartDate: Date
	 * @param newStartTime: Time
	 * @param newEndDate:   Date
	 * @param newEndTime:   Time
	 * @throws Exception - when an invalid input is detected
	 */

	public static void checkTimeSlotOverlap(String type, Date newStartDate, Time newStartTime, Date newEndDate,
			Time newEndTime) throws Exception {
		CarShop carshop = CarShopApplication.getCarShop();
		boolean overlapVac = false;
		boolean overlapHol = false;

		// goes through all of the vacations first
		for (TimeSlot t : carshop.getBusiness().getVacations()) {
			// if there is an overlap with a vacation
			if (timeSlotOverlap(t, newStartDate, newStartTime, newEndDate, newEndTime)) {
				overlapVac = true; // set to true to indicate a vacation overlap
			}
			if (overlapVac) {
				// the following if statement is used to direct specific messages depending
				// whether a holiday overlaps with a vacation or if a vacation overlaps with a
				// vacation
				if (type.equalsIgnoreCase("vacation")) {
					throw new Exception("Vacation times cannot overlap");
				} else if (type.equalsIgnoreCase("holiday")) {
					throw new Exception("Holiday and vacation times cannot overlap");
				}
			}
		}

		for (TimeSlot t : carshop.getBusiness().getHolidays()) {
			if (timeSlotOverlap(t, newStartDate, newStartTime, newEndDate, newEndTime)) {
				overlapHol = true;
			}
			if (overlapHol) {
				// the following if statements are used to direct specific messages depending
				// whether a vacation overlaps with a holiday or if a holiday overlaps with a
				// holiday
				if (type.equalsIgnoreCase("holiday")) {
					throw new Exception("Holiday times cannot overlap");
				} else if (type.equalsIgnoreCase("vacation")) {
					throw new Exception("Holiday and vacation times cannot overlap");
				}
			}
		}
	}
	/**
	 * author: Vy-Kha Huynh
	 * @return
	 * @throws Exception 
	 */
	public static ArrayList<ToAppointment> getCustomerToAppointments(ToUser cust) throws Exception {
		Customer c=null;
		if(User.getWithUsername(cust.getUsername()) instanceof Customer) {
			c=(Customer) User.getWithUsername(cust.getUsername());
		}
		else {
			throw new Exception("You are not a customer");
		}

		ArrayList<ToAppointment> appointments=new ArrayList<ToAppointment>();

		for(Appointment a:c.getAppointments()) {
			ToAppointment app=new ToAppointment(c.getUsername(), a.getServiceBooking(0).getTimeSlot().getStartTime().toString(), a.getServiceBooking(0).getTimeSlot().getStartDate().toString(), a.getServiceBooking(0).getService().toString());
			appointments.add(app);
		}
		return appointments;
	}

	/**
	 * author: Vy-Kha Huynh
	 * @param toApp 
	 * @throws Exception
	 */
	public static void CancelToAppointment(ToAppointment toApp) throws Exception {
		String date=toApp.getDay();
		String bookableService=toApp.getBookingName();
		String time=toApp.getStartTime();
		String cust=toApp.getCustomer();
		Appointment deletedApp=null;

		for(Appointment app:((Customer)CarShopApplication.getRecentlyLogedinUser()).getAppointments()) {
			if(app.getServiceBooking(0).getTimeSlot().getStartDate().toString().equals(date)) {
				if(app.getBookableService().getName().equals(bookableService)) {
					if(app.getServiceBooking(0).getTimeSlot().getStartTime().toString().equals(time.split(", ")[0])) {
						deletedApp=app;
					}
				}
			}
		}
		CancelAppointment(User.getWithUsername(cust), deletedApp);
	}

	public static ToUser getLoggedInUser() {
		ToUser toCustomer=new ToUser(CarShopApplication.getRecentlyLogedinUser().getUsername());
		return toCustomer;
	}

	/**
	 * @author Ramin Akhavan-Sarraf
	 * 
	 *         This is a helper method to check conditions of time slot overlap and
	 *         return a boolean depending on whether the conditions find overlap
	 * @param t:            TimeSlot
	 * @param newStartDate: Date
	 * @param newStartTime: Time
	 * @param newEndDate:   Date
	 * @param newEndTime:   Time
	 * @return: boolean
	 */
	private static boolean timeSlotOverlap(TimeSlot t, Date newStartDate, Time newStartTime, Date newEndDate,
			Time newEndTime) {
		boolean overlap = false;
		// the following conditions check for overlap
		// if the start date is before the new start date or (if they are the same and
		// the start time is before the new start time)
		if (t.getStartDate().toLocalDate().isBefore(newStartDate.toLocalDate())
				|| (t.getStartDate().toLocalDate().isEqual(newStartDate.toLocalDate()) && t.getStartTime().toLocalTime().isBefore(newStartTime.toLocalTime()))) {
			// if the end date is after the new start date or (if they are the same and the
			// end time is after the new start time)
			if (t.getEndDate().toLocalDate().isAfter(newStartDate.toLocalDate())
					|| (t.getEndDate().toLocalDate().isEqual(newStartDate.toLocalDate()) && t.getEndTime().toLocalTime().isAfter(newStartTime.toLocalTime()))) {
				overlap = true;
			}
		}
		// if the end date is after the new end date or (if they are the same and the
		// end time is after the new end time)
		if (t.getEndDate().toLocalDate().isAfter(newEndDate.toLocalDate())
				|| (t.getEndDate().toLocalDate().isEqual(newEndDate.toLocalDate()) && t.getEndTime().toLocalTime().isAfter(newEndTime.toLocalTime()))) {
			// if the start date is before the new end date or (if they are the same and the
			// start time is before the new end time)
			if (t.getStartDate().toLocalDate().isBefore(newEndDate.toLocalDate())
					|| (t.getStartDate().toLocalDate().isEqual(newEndDate.toLocalDate()) && t.getStartTime().toLocalTime().isBefore(newEndTime.toLocalTime()))) {
				overlap = true;
			}
		}
		// if the end date is before the new end date or (if they are the same and the
		// end time is before the new end time)
		if (t.getEndDate().toLocalDate().isBefore(newEndDate.toLocalDate())
				|| (t.getEndDate().toLocalDate().isEqual(newEndDate.toLocalDate()) && t.getEndTime().toLocalTime().isBefore(newEndTime.toLocalTime()))) {
			// if the start date is after the new start date or (if they are the same and
			// the start time is after the new start time)
			if (t.getStartDate().toLocalDate().isAfter(newStartDate.toLocalDate())
					|| (t.getStartDate().toLocalDate().isEqual(newStartDate.toLocalDate()) && t.getStartTime().toLocalTime().isAfter(newStartTime.toLocalTime()))) {
				overlap = true;
			}
		}
		if(t.getStartDate().toLocalDate().isEqual(newStartDate.toLocalDate()) && !t.getStartTime().toLocalTime().isAfter(newStartTime.toLocalTime()) && !t.getStartTime().toLocalTime().isBefore(newStartTime.toLocalTime())){
			overlap = true;
		}
		return overlap; // returns whether there is overlap in the time slots
	}

	/**
	 * @author Ramin Akhavan
	 * 
	 *         This is a method that is used to update time slot, given the current
	 *         start date and start time, and the new start date new start time, new
	 *         end date and new end time
	 * @param type:             String
	 * @param currentStartDate: Date
	 * @param currentStartTime: Time
	 * @param newStartDate:     Date
	 * @param newStartTime:     Time
	 * @param newEndDate:       Date
	 * @param newEndTime:       Time
	 * @throws Exception - when invalid input is detected
	 */
	public static void updateTimeSlot(String type, Date currentStartDate, Time currentStartTime, Date newStartDate,
			Time newStartTime, Date newEndDate, Time newEndTime) throws Exception {
		CarShop carshop = CarShopApplication.getCarShop();
		User currentUser = CarShopApplication.getRecentlyLogedinUser();
		// if the user is not the owner
		if (!(currentUser instanceof Owner)) {
			throw new Exception("No permission to update business information");
		}
		// if the new start date is before the new end date
		if (!(newStartDate.toLocalDate().isBefore(newEndDate.toLocalDate()))) {
			throw new Exception("Start time must be before end time");
		}
		// OR if the new start date is the same as the new end date, but the new start
		// time is not before the new end time
		else if (newStartDate.toLocalDate().isEqual(newEndDate.toLocalDate()) && !(newStartTime.toLocalTime().isBefore(newEndTime.toLocalTime()))) {
			throw new Exception("Start time must be before end time");
		}

		// if the new start date is before the system date
		// OR they are the same and the new start time is before the system time
		if (newStartDate.toLocalDate().isBefore(CarShopApplication.getLocalDate())
				|| (newStartDate.toLocalDate().isEqual(CarShopApplication.getLocalDate())
						&& newStartTime.toLocalTime().isBefore(CarShopApplication.getLocalTime()))) {
			if (type.equalsIgnoreCase("vacation")) {
				throw new Exception("Vacation cannot start in the past");
			} else if (type.equalsIgnoreCase("holiday")) {
				throw new Exception("Holiday cannot start in the past");
			}
		}

		TimeSlot timeSlot = null;
		// checks if there is overlap in the time slots
		checkTimeSlotOverlap(type, newStartDate, newStartTime, newEndDate, newEndTime);

		// goes through all time slots to find which one needs to be updated
		for (TimeSlot t : carshop.getTimeSlots()) {
			if (t.getStartDate().compareTo(currentStartDate) == 0
					&& t.getStartTime().compareTo(currentStartTime) == 0) {
				timeSlot = t; // stores time slot to update
				break;
			}
		}

		// if the time slot is found
		if (timeSlot != null) {
			// updates all appropriate attributes using set methods
			timeSlot.setStartDate(newStartDate);
			timeSlot.setEndDate(newEndDate);
			timeSlot.setStartTime(newStartTime);
			timeSlot.setEndTime(newEndTime);
			CarShopPersistence.save(CarShopApplication.getCarShop());
		}

	}

	/**
	 * @author Ramin Akhavan-Sarraf
	 * 
	 *         This method is responsible for removing the appropriate time slot,
	 *         given the information to find the time slot
	 * @param type:      String
	 * @param startDate: Date
	 * @param startTime: Time
	 * @param endDate:   Date
	 * @param endTime:   Time
	 * @throws Exception - when invalid input is detected
	 */
	public static void removeTimeSlot(String type, Date startDate, Time startTime, Date endDate, Time endTime)
			throws Exception {
		CarShop carshop = CarShopApplication.getCarShop();
		User currentUser = CarShopApplication.getRecentlyLogedinUser();

		// if the user is not an owner
		if (!(currentUser instanceof Owner)) {
			throw new Exception("No permission to update business information");
		}

		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		// depending on whether a vacation is being removed or a holiday is being
		// removed,
		// the timeSlots list will store the appropriate time slots
		if (type.equalsIgnoreCase("vacation")) {
			timeSlots = carshop.getBusiness().getVacations();
		} else if (type.equalsIgnoreCase("holiday")) {
			timeSlots = carshop.getBusiness().getHolidays();
		}

		// goes through all timeslots to find which one to delete
		for (TimeSlot t : timeSlots) {
			if (t.getStartDate().compareTo(startDate) == 0 && t.getStartTime().compareTo(startTime) == 0
					&& t.getEndDate().compareTo(endDate) == 0 && t.getEndTime().compareTo(endTime) == 0) {
				if (type.equalsIgnoreCase("vacation")) {
					carshop.getBusiness().removeVacation(t);
					CarShopPersistence.save(CarShopApplication.getCarShop());
				} else if (type.equalsIgnoreCase("holiday")) {
					carshop.getBusiness().removeHoliday(t);
					CarShopPersistence.save(CarShopApplication.getCarShop());
				}
				t.delete();
				CarShopPersistence.save(CarShopApplication.getCarShop());
				break;
			}
		}
	}

	/**
	 * @author Vy-kha Huynh
	 * @param user
	 * @param aService
	 * @param timeslot
	 * @throws Exception
	 * Need to implement the garage business hour condition, and some modification to  
	 */

	public static void MakeAppointment(User user,BookableService aService, List<TimeSlot> timeslots, List<BookableService> services) throws Exception{ //throws IllegalArgumentException{ // return true
		// Not a customer attempts to make an appointment 
		/*** WORKS ***/
		if(!(user instanceof Customer)) {
			throw new Exception("Only customers can make an appointment");
		}
		if(aService instanceof Service  && timeslots.size()==1) {
			MakeAppointmentService(user,aService,timeslots);
			CarShopPersistence.save(CarShopApplication.getCarShop());
		}

		else if(aService instanceof ServiceCombo){
			MakeAppointmentServiceCombo(user, aService, timeslots, services);
			CarShopPersistence.save(CarShopApplication.getCarShop());
		}
		else {
			throw new Exception("invalid bookable service!");
		}
	}

	/**
	 * @author Vy-kha Huynh
	 * @param user
	 * @param aService
	 * @param timeslot
	 * @throws Exception
	 * Need to implement the garage business hour condition, and some modification to  
	 */
	public static void MakeAppointmentService(User user,BookableService aService, List<TimeSlot> timeslot) throws Exception{ //throws IllegalArgumentException{ // return true 
		CarShop carShop=CarShopApplication.getCarShop();
		Business business=carShop.getBusiness();
		Service serv=(Service)aService;
		java.sql.Date appointDate=timeslot.get(0).getStartDate();
		LocalDate appointmentStartDate=appointDate.toLocalDate();
		java.time.DayOfWeek appointmentD=appointmentStartDate.getDayOfWeek();
		java.sql.Time appointStartTime=timeslot.get(0).getStartTime();
		java.sql.Time appointEndTime=timeslot.get(0).getEndTime();
		Customer customer=(Customer)user;

		List<Appointment> appointments=carShop.getAppointments();
		List<BusinessHour> businesshours=business.getBusinessHours();
		List<TimeSlot> holidays=carShop.getBusiness().getHolidays();
		List<TimeSlot> vacations=carShop.getBusiness().getVacations();


		int i=0;
		switch(appointmentD) {
		case MONDAY:
			i=0;
			break;
		case TUESDAY:
			i=1;
			break;
		case WEDNESDAY:
			i=2;
			break;
		case THURSDAY:
			i=3;
			break;
		case FRIDAY:
			i=4;
			break;
		case SATURDAY:
			i=5;
			break;
		case SUNDAY:
			i=6;
			break;
		}


		// Outside of business or garage opening hours
		if(i>=businesshours.size() || i>=serv.getGarage().getBusinessHours().size()) {
			String temp=""+appointStartTime.toLocalTime().toString();
			String appointTime="";
			if(temp.charAt(0)=='0') {
				for(int j=1;j<temp.length();j++) {
					appointTime+=temp.charAt(j);
				}
			}
			else appointTime=temp;
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("There are no available slots for "+serv.getName()+" on "+appointmentStartDate.toString()+" at "+appointTime);
		}


		java.sql.Time businessClosed=businesshours.get(i).getEndTime();
		java.sql.Time businessOpened=businesshours.get(i).getStartTime();
		java.sql.Time garageClosed=serv.getGarage().getBusinessHour(i).getEndTime();
		java.sql.Time garageOpened=serv.getGarage().getBusinessHour(i).getStartTime();

		LocalTime tempBClosed=businessClosed.toLocalTime();
		LocalTime tempBOp=businessOpened.toLocalTime();
		LocalTime tempGClosed=garageClosed.toLocalTime();
		LocalTime tempGOp=garageOpened.toLocalTime();


		if(tempBClosed.isBefore(appointEndTime.toLocalTime()) || tempBOp.isAfter(appointStartTime.toLocalTime()) // Add garage 
				|| tempGClosed.isBefore(appointEndTime.toLocalTime()) || tempGOp.isAfter(appointStartTime.toLocalTime())){ // Add garafe
			String temp=""+appointStartTime.toLocalTime().toString();
			String appointTime="";
			if(temp.charAt(0)=='0') {
				for(int j=1;j<temp.length();j++) {
					appointTime+=temp.charAt(j);
				}
			}
			else appointTime=temp;
			throw new Exception("There are no available slots for "+serv.getName()+" on "+appointmentStartDate.toString()+" at "+appointTime);
		}


		// Appoint made during holidays
		for(TimeSlot h:holidays) {
			LocalDate holiStart=h.getStartDate().toLocalDate();
			LocalDate holiEnd=h.getEndDate().toLocalDate();
			if(holiStart.isBefore(appointmentStartDate) && holiEnd.isAfter(appointmentStartDate)) {
				String temp=""+appointStartTime.toLocalTime().toString();
				String appointTime="";
				if(temp.charAt(0)=='0') {
					for(int j=1;j<temp.length();j++) {
						appointTime+=temp.charAt(j);
					}
				}
				throw new Exception("There are no available slots for "+serv.getName()+" on "+appointmentStartDate.toString()+" at "+appointTime);
			}
		}

		// Appoint made during vacation
		for(TimeSlot v:vacations) {
			LocalDate vacStart=v.getStartDate().toLocalDate();
			LocalDate vacEnd=v.getEndDate().toLocalDate();
			if(vacStart.isBefore(appointmentStartDate) && vacEnd.isAfter(appointmentStartDate)){
				String temp=""+appointStartTime.toLocalTime().toString();
				String appointTime="";
				if(temp.charAt(0)=='0') {
					for(int j=1;j<temp.length();j++) {
						appointTime+=temp.charAt(j);
					}
				}
				throw new Exception("There are no available slots for "+serv.getName()+" on "+appointmentStartDate.toString()+" at "+appointTime);
			}
		}

		// Appoint made in the past
		/*** WORKS ***/
		if(CarShopApplication.getLocalDate().isAfter(appointmentStartDate)) {
			String temp=""+appointStartTime.toLocalTime().toString();
			String appointTime="";
			if(temp.charAt(0)=='0') {
				for(int j=1;j<temp.length();j++) {
					appointTime+=temp.charAt(j);
				}
			}
			throw new Exception("There are no available slots for "+serv.getName()+" on "+appointmentStartDate.toString()+" at "+appointTime);
		}


		for(Appointment a:appointments) {
			if(appointmentStartDate.isEqual(a.getServiceBooking(0).getTimeSlot().getStartDate().toLocalDate())) {
				List<ServiceBooking> servicebookings=a.getServiceBookings();
				for(ServiceBooking sb:servicebookings) {
					if(sb.getService().getName().equals(aService.getName())){
						if(sb.getTimeSlot().getStartTime().before(appointEndTime) && sb.getTimeSlot().getEndTime().after(appointStartTime)) {
							String temp=""+appointStartTime.toLocalTime().toString();
							String appointTime="";
							if(temp.charAt(0)=='0') {
								for(int j=1;j<temp.length();j++) {
									appointTime+=temp.charAt(j);
								}
							}
							throw new Exception("There are no available slots for "+serv.getName()+" on "+appointmentStartDate.toString()+" at "+appointTime);
						}
					}
				}
			}
		}



		/*** WORKS ***/
		// One appointment per service.servicecombo,
		//many serviceBooking per serviceCombo
		Appointment aNew=new Appointment(customer, aService, carShop);
		CarShopPersistence.save(CarShopApplication.getCarShop());
		ServiceBooking servicebooking=new ServiceBooking(serv, timeslot.get(0), aNew);
		CarShopPersistence.save(CarShopApplication.getCarShop());
		aNew.addServiceBooking(servicebooking);
		customer.addAppointment(aNew);
		CarShopPersistence.save(CarShopApplication.getCarShop());
		carShop.addAppointment(aNew);
		CarShopPersistence.save(CarShopApplication.getCarShop());
	}



	/**
	 * @author Vy-kha Huynh
	 * @param user
	 * @param aService
	 * @param timeslot
	 * @throws Exception
	 */
	public static void MakeAppointmentServiceCombo(User user,BookableService aCombo, List<TimeSlot> timeslots,List<BookableService> ser) throws Exception{ //throws IllegalArgumentException{ // return true
		CarShop carShop=CarShopApplication.getCarShop();
		Business business=carShop.getBusiness();
		ServiceCombo sc=(ServiceCombo)aCombo;
		Customer customer=(Customer)user;

		java.sql.Date appointDate=timeslots.get(0).getStartDate();
		LocalDate appointmentStartDate=appointDate.toLocalDate();
		java.time.DayOfWeek appointmentD=appointmentStartDate.getDayOfWeek();

		List<Appointment> appointments=carShop.getAppointments();
		List<BusinessHour> businesshours=business.getBusinessHours();
		List<TimeSlot> holidays=carShop.getBusiness().getHolidays();
		List<TimeSlot> vacations=carShop.getBusiness().getVacations();

		Service mainServ=sc.getMainService().getService();
		Appointment aNew=new Appointment(customer, sc, carShop);
		CarShopPersistence.save(CarShopApplication.getCarShop());


		int i=0;
		switch(appointmentD) {
		case MONDAY:
			i=0;
			break;
		case TUESDAY:
			i=1;
			break;
		case WEDNESDAY:
			i=2;
			break;
		case THURSDAY:
			i=3;
			break;
		case FRIDAY:
			i=4;
			break;
		case SATURDAY:
			i=5;
			break;
		case SUNDAY:
			i=6;
			break;
		}
		// Check conflicting timeslots
		for(TimeSlot ts:timeslots) {
			for(int j=0;j<timeslots.size();j++) {
				if((ts.getStartTime().toLocalTime().isBefore(timeslots.get(j).getEndTime().toLocalTime()) && 
						ts.getStartTime().toLocalTime().isAfter(timeslots.get(j).getStartTime().toLocalTime())) || 
						(ts.getEndTime().toLocalTime().isAfter(timeslots.get(j).getStartTime().toLocalTime()) && 
								ts.getEndTime().toLocalTime().isBefore(timeslots.get(j).getEndTime().toLocalTime()))) {
					aNew.delete();
					CarShopPersistence.save(CarShopApplication.getCarShop());
					throw new Exception("Time slots for two services are overlapping");
				}
			}
		}

		// Outside of business or garage opening hours
		if(i>=businesshours.size() || i>=mainServ.getGarage().getBusinessHours().size()) {
			aNew.delete();
			CarShopPersistence.save(CarShopApplication.getCarShop());
			String temp=""+timeslots.get(0).getStartTime().toLocalTime().toString();
			String appointTime="";
			if(temp.charAt(0)=='0') {
				for(int j=1;j<temp.length();j++) {
					appointTime+=temp.charAt(j);
				}
			}
			else appointTime=temp;
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("There are no available slots for "+sc.getName()+" on "+appointmentStartDate.toString()+" at "+appointTime);
		}




		java.sql.Time businessClosed=businesshours.get(i).getEndTime();
		java.sql.Time businessOpened=businesshours.get(i).getStartTime();
		LocalTime tempBClosed=businessClosed.toLocalTime();
		LocalTime tempBOp=businessOpened.toLocalTime();

		LocalTime appointEndTime=timeslots.get(timeslots.size()-1).getEndTime().toLocalTime();
		LocalTime appointStartTime=timeslots.get(0).getStartTime().toLocalTime();


		// Outside Business Opening Hours
		if(tempBClosed.isBefore(appointEndTime) || tempBOp.isAfter(appointStartTime)){ 
			aNew.delete();
			CarShopPersistence.save(CarShopApplication.getCarShop());
			String temp=""+appointStartTime.toString();
			String appointTime="";
			if(temp.charAt(0)=='0') {
				for(int j=1;j<temp.length();j++) {
					appointTime+=temp.charAt(j);
				}
			}
			else appointTime=temp;
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("There are no available slots for "+sc.getName()+" on "+appointmentStartDate.toString()+" at "+appointTime);
		}



		// Appoint made during holidays
		for(TimeSlot h:holidays) {
			LocalDate holiStart=h.getStartDate().toLocalDate();
			LocalDate holiEnd=h.getEndDate().toLocalDate();
			if(holiStart.isBefore(appointmentStartDate) && holiEnd.isAfter(appointmentStartDate)) {
				aNew.delete();
				CarShopPersistence.save(CarShopApplication.getCarShop());
				String temp=""+appointStartTime.toString();
				String appointTime="";
				if(temp.charAt(0)=='0') {
					for(int j=1;j<temp.length();j++) {
						appointTime+=temp.charAt(j);
					}
				}
				CarShopPersistence.save(CarShopApplication.getCarShop());
				throw new Exception("There are no available slots for "+sc.getName()+" on "+appointmentStartDate.toString()+" at "+appointTime);
			}
		}

		// Appoint made during vacation
		for(TimeSlot v:vacations) {
			LocalDate vacStart=v.getStartDate().toLocalDate();
			LocalDate vacEnd=v.getEndDate().toLocalDate();
			if(vacStart.isBefore(appointmentStartDate) && vacEnd.isAfter(appointmentStartDate)){
				aNew.delete();
				String temp=""+appointStartTime.toString();
				String appointTime="";
				if(temp.charAt(0)=='0') {
					for(int j=1;j<temp.length();j++) {
						appointTime+=temp.charAt(j);
					}
				}
				CarShopPersistence.save(CarShopApplication.getCarShop());
				throw new Exception("There are no available slots for "+sc.getName()+" on "+appointmentStartDate.toString()+" at "+appointTime);
			}
		}

		// Appoint made in the past
		/*** WORKS ***/
		if(CarShopApplication.getLocalDate().isAfter(appointmentStartDate)) {
			aNew.delete();
			CarShopPersistence.save(CarShopApplication.getCarShop());
			String temp=""+appointStartTime.toString();
			String appointTime="";
			if(temp.charAt(0)=='0') {
				for(int j=1;j<temp.length();j++) {
					appointTime+=temp.charAt(j);
				}
			}
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("There are no available slots for "+sc.getName()+" on "+appointmentStartDate.toString()+" at "+appointTime);
		}

		// Check conmflicting appointments
		for(int l=0;l<timeslots.size();l++) {
			LocalTime servBookStartTime=timeslots.get(l).getStartTime().toLocalTime();
			LocalTime servBookEndTime=timeslots.get(l).getEndTime().toLocalTime();
			Service currServ=(Service)ser.get(l);

			java.sql.Time garageClosed=currServ.getGarage().getBusinessHour(i).getEndTime();
			java.sql.Time garageOpened=currServ.getGarage().getBusinessHour(i).getStartTime();
			LocalTime tempGClosed=garageClosed.toLocalTime();
			LocalTime tempGOp=garageOpened.toLocalTime();

			if(tempGClosed.isBefore(servBookEndTime) || tempGOp.isAfter(servBookStartTime)) {
				String temp=""+appointStartTime.toString();
				String appointTime="";
				if(temp.charAt(0)=='0') {
					for(int j=1;j<temp.length();j++) {
						appointTime+=temp.charAt(j);
					}
				}
				else appointTime=temp;
				CarShopPersistence.save(CarShopApplication.getCarShop());
				throw new Exception("There are no available slots for "+sc.getName()+" on "+appointmentStartDate.toString()+" at "+appointTime);
			}

			for(Appointment a:appointments) {
				if(a.getServiceBookings().isEmpty()) {
					break;
				}
				else if(appointmentStartDate.isEqual(a.getServiceBooking(0).getTimeSlot().getStartDate().toLocalDate())) {
					List<ServiceBooking> servicebookings=a.getServiceBookings();
					for(ServiceBooking sb:servicebookings) {
						if(sb.getService().getName().equals(currServ.getName())){
							if(sb.getTimeSlot().getStartTime().toLocalTime().isBefore(java.sql.Time.valueOf(servBookEndTime).toLocalTime()) && sb.getTimeSlot().getEndTime().toLocalTime().isAfter(java.sql.Time.valueOf(servBookStartTime).toLocalTime())) {
								aNew.delete();
								String appointTime="";
								String temp=timeslots.get(0).getStartTime().toLocalTime().toString();
								if(temp.charAt(0)=='0') {
									for(int j=1;j<temp.length();j++) {
										appointTime+=temp.charAt(j);
									}
								}
								CarShopPersistence.save(CarShopApplication.getCarShop());
								throw new Exception("There are no available slots for "+sc.getName()+" on "+appointmentStartDate.toString()+" at "+appointTime);
							}
						}
					}
				}
			}
			ServiceBooking servicebooking=new ServiceBooking(currServ, timeslots.get(l), aNew);
			CarShopPersistence.save(CarShopApplication.getCarShop());

			aNew.addServiceBooking(servicebooking);
			CarShopPersistence.save(CarShopApplication.getCarShop());
		}

		/*** WORKS ***/
		customer.addAppointment(aNew);
		CarShopPersistence.save(CarShopApplication.getCarShop());
		carShop.addAppointment(aNew);
		CarShopPersistence.save(CarShopApplication.getCarShop());
	}


	/**
	 * @author Vy-kha Huynh
	 * @param user
	 * @param anAppointment
	 * @throws Exception
	 */
	public static void CancelAppointment(User user, Appointment anAppointment) throws Exception { // Return true

		CarShop carShop=CarShopApplication.getCarShop();
		ServiceBooking servicebooking=anAppointment.getServiceBooking(0);
		Date appointmentDate=servicebooking.getTimeSlot().getStartDate();
		LocalDate appDate=appointmentDate.toLocalDate();
		LocalDate currentDate=CarShopApplication.getLocalDate();

		// User isn't the one who scheduled the appointment
		if(!user.equals(anAppointment.getCustomer())) {
			if(user instanceof Owner) {
				throw new Exception("An owner cannot cancel an appointment");
			}
			else if(user instanceof Technician) {
				throw new Exception("A technician cannot cancel an appointment");
			}
			else if(user instanceof Customer) {
				throw new Exception("A customer can only cancel their own appointments");
			}
		}

		//If cancellation date less than one day
		else if(currentDate.until(appDate).getDays()<1) {
			throw new Exception("Cannot cancel an appointment on the appointment date");
		}

		// If cancellation date is on the appointment date
		else if(currentDate.equals(appDate)) {
			throw new Exception("Cannot cancel an appointment on the appointment date");
		}

		// If cancellation date is after appointment date
		else if(currentDate.isAfter(appDate)) {
			throw new Exception("Cannot cancel an appointment on the appointment date");
		}

		else {
			// Delete appointment from system
			carShop.removeAppointment(anAppointment);
			CarShopPersistence.save(CarShopApplication.getCarShop());
			for(int i=0;i<anAppointment.getServiceBookings().size();i++) { // May be obsolete
				anAppointment.getServiceBooking(i).delete(); // delete all related service booking
				CarShopPersistence.save(CarShopApplication.getCarShop());
			}
			anAppointment.delete();
			CarShopPersistence.save(CarShopApplication.getCarShop());
		}
	}

	/**
	 * Method checks whether an appointment was missed
	 * Deletes the appointment and increments no show (missed appointments)
	 * @author Tiffany
	 * @param user
	 * @param string
	 * @param appointment
	 * @throws Exception
	 */
	public static void noShow(User user, String string, Appointment appointment) throws Exception {
		if (!(user instanceof Owner)) {
			throw new Exception("Only the owner can declare a noshow.");
		} else {

			// time concat used from Ramin's method (string.split causes an error)
			int i=0;
			String sDate="";
			String sTime="";
			while(string.charAt(i)!='+') {
				sDate+=(char)string.charAt(i);
				i++;
			}
			while(i<string.length()) {
				sTime+=(char) string.charAt(i);
				i++;
			}
			sTime+=":00";

			// convert string to appointment date + time:
			Time curTime = Time.valueOf(sTime);
			Date curDate = Date.valueOf(sDate);
			// convert to local date + time
			LocalTime curLocalTime = curTime.toLocalTime();
			LocalDate curLocalDate = curDate.toLocalDate();


			if (curLocalDate.isEqual(appointment.getServiceBooking(0).getTimeSlot().getStartDate().toLocalDate())
					&& (curLocalTime.isAfter(appointment.getServiceBooking(0).getTimeSlot().getStartTime().toLocalTime()) 
							|| curLocalTime.equals(appointment.getServiceBooking(0).getTimeSlot().getStartTime().toLocalTime()))) {
				// checks if the first service date is the same as the current date 
				// and if the service end time is before the current time
				appointment.noShow();
				CarShopPersistence.save(CarShopApplication.getCarShop());

			} else { // else owner cannot declare a noshow
				appointment.rejectStartAppointment();
				CarShopPersistence.save(CarShopApplication.getCarShop());
			} 

		}

	}
	/**
	 * @author Tiffany
	 * @param user
	 * @param string
	 * @param appointment
	 * @throws Exception
	 */
	public static void endAppointment(User user, String string, Appointment appointment) throws Exception {
		if (!(user instanceof Owner)) {
			throw new Exception("Only the owner can end the appointment.");
		} else {

			// time concat used from Ramin's method (string.split causes an error)
			int i=0;
			String sDate="";
			String sTime="";
			while(string.charAt(i)!='+') {
				sDate+=(char)string.charAt(i);
				i++;
			}
			while(i<string.length()) {
				sTime+=(char) string.charAt(i);
				i++;
			}
			sTime+=":00";

			// convert string to appointment date + time:
			Time curTime = Time.valueOf(sTime);
			Date curDate = Date.valueOf(sDate);
			// convert to local date + time
			LocalTime curLocalTime = curTime.toLocalTime();
			LocalDate curLocalDate = curDate.toLocalDate();

			if (curLocalDate.isEqual(appointment.getServiceBooking(0).getTimeSlot().getStartDate().toLocalDate())
					&& curLocalTime.isAfter(appointment.getServiceBooking(appointment.getServiceBookings().size() - 1).getTimeSlot().getStartTime().toLocalTime()) ) {
				// checks if the first service date is the same as the current date 
				// and if the service end time is before the current time
				appointment.endAppointment();

			} else { // else owner cannot declare a noshow
				appointment.rejectEndAppointment();
			} 
			CarShopPersistence.save(CarShopApplication.getCarShop());
		}
		CarShopPersistence.save(CarShopApplication.getCarShop());
	}

	/**
	 * author: Vy-Kha Huynh 
	 * @return List of Bookable Service
	 */
	public static ArrayList<ToBookableService> getToBookableService() {
		ArrayList<ToBookableService> bookableServices=new ArrayList<ToBookableService>();
		String services=null;
		String mandatory=null;
		for(BookableService bs:CarShopApplication.getCarShop().getBookableServices()) {
			if(bs instanceof ServiceCombo) {
				for(ComboItem ci:((ServiceCombo)bs).getServices()) {
					if(services.isEmpty()) {
						services+=ci.getService().toString();
						mandatory+=ci.getMandatory();
					}
					else {
						services+=","+ci.getService().toString();
						mandatory+=","+ci.getMandatory();
					}
				}
			}
			else {
				services=bs.getName();
				mandatory="true";
			}
			ToBookableService tb=new ToBookableService(bs.getName(), services,mandatory);
			bookableServices.add(tb);
		}
		System.err.println(services);
		return bookableServices;
	}

	/**
	 * author: Vy-Kha HUynh
	 * @param s
	 * @return
	 */
	public static int getToServiceDurationWithName(String s) {
		Service ser=(Service)Service.getWithName(s);
		return ser.getDuration();
	}

	/**
	 * author: Vy-Kha Huynh
	 * @param s Bookable Service name
	 * @return Bookable Service
	 */
	public static BookableService getBookableServiceWithName(String s) {
		BookableService serv=BookableService.getWithName(s);
		return serv;
	}
	public static ArrayList<String> getBookableServiceString(){
		ArrayList<String> books=new ArrayList<String>(); 
		for(BookableService bs:CarShopApplication.getCarShop().getBookableServices()) {
			books.add(bs.getName());
		}
		return books;
	}

	public static void MakeToAppointment(String date,String books,String serv, String time) throws Exception {
		String[] services=serv.split(",");
		String[] times=serv.split(",");
		ArrayList<BookableService> servs=new ArrayList<BookableService>();
		ArrayList<TimeSlot> ts=new ArrayList<TimeSlot>();
		BookableService bs=BookableService.getWithName(books);
		java.sql.Date aDate=java.sql.Date.valueOf(date);
		for(int i=0;i<services.length;i++) {
			Service s=(Service)Service.getWithName(services[i]);
			java.sql.Time startTime=java.sql.Time.valueOf(times[i]+":00");
			LocalTime aStart=startTime.toLocalTime();
			LocalTime aEnd=aStart.plusMinutes(((Service)s).getDuration());
			java.sql.Time endTime=java.sql.Time.valueOf(aEnd);
			TimeSlot t=new TimeSlot(aDate,startTime,aDate,endTime,CarShopApplication.getCarShop());
			ts.add(t);
			servs.add(s);
		}
		MakeAppointment(CarShopApplication.getRecentlyLogedinUser(), bs,ts, servs);
	}

	/**
	 * @author Massimo
	 * @param user
	 * @param string
	 * @param appointment
	 * @throws Exception
	 */
	public static void startAppointment(User user, String string, Appointment appointment) throws Exception {
		if (!(user instanceof Owner)) {
			throw new Exception("Only the owner can start the appointment.");
		} else {

			// time concat used from Ramin's method (string.split causes an error)
			int i=0;
			String sDate="";
			String sTime="";
			while(string.charAt(i)!='+') {
				sDate+=(char)string.charAt(i);
				i++;
			}
			while(i<string.length()) {
				sTime+=(char) string.charAt(i);
				i++;
			}
			sTime+=":00";

			// convert string to appointment date + time:
			Time curTime = Time.valueOf(sTime);
			Date curDate = Date.valueOf(sDate);
			// convert to local date + time
			LocalTime curLocalTime = curTime.toLocalTime();
			LocalDate curLocalDate = curDate.toLocalDate();

			if (curLocalDate.isEqual(appointment.getServiceBooking(0).getTimeSlot().getStartDate().toLocalDate())
					&& (curLocalTime.isAfter(appointment.getServiceBooking(0).getTimeSlot().getStartTime().toLocalTime()) 
							|| curLocalTime.equals(appointment.getServiceBooking(0).getTimeSlot().getStartTime().toLocalTime()))) {
				// checks if the first service date is the same as the current date 
				// and if the service end time is before the current time
				appointment.startAppointment();

			} else { // else owner cannot declare a noshow
				appointment.rejectStartAppointment();
				CarShopPersistence.save(CarShopApplication.getCarShop());
			} 

		}
		CarShopPersistence.save(CarShopApplication.getCarShop());
	}

	/**
	 * Updates a service of a recently made appointment
	 * 
	 * @param Username
	 * @param newBookableService
	 * @throws Exception
	 */

	public static void UpdateServiceofAppointment(String Username, BookableService newBookableService)
			throws Exception {
		CarShop carshop = CarShopApplication.getCarShop();
		User currentUser = User.getWithUsername(Username);

		if (!(currentUser instanceof Customer)) {
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("No permission to update appointment");}

		Customer customer = (Customer) currentUser;
		if (customer.getAppointments().size() == 0) {
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("Unable to modify: The Customer has no appointments made");}

		//get recent --appointment 
		Appointment apt = customer.getAppointments().get(customer.getAppointments().size() - 1);
		//Service service = (Service)apt.getBookableService(); -- might not need

		if (!(newBookableService instanceof Service)) {
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("invalid bookable service!");
		}

		//get recent --servicebooking
		LocalDate startDate = apt.getServiceBooking(apt.getServiceBookings().size()-1).getTimeSlot().getStartDate().toLocalDate();
		LocalDate currentDate = CarShopApplication.getLocalDate();


		if (currentDate.until(startDate).getDays()<1) {
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("Cannot cancel an appointment on the appointment date");
		}

		// If cancellation date is on the appointment date
		else if (currentDate.equals(startDate)) {
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("Cannot cancel an appointment on the appointment date");
		}

		// If cancellation date is after appointment date
		else if (currentDate.isAfter(startDate)) {
			CarShopPersistence.save(CarShopApplication.getCarShop());
			throw new Exception("Cannot cancel an appointment on the appointment date");
		}

		if (!openBusinessOverlap(apt.getServiceBooking(0).getTimeSlot().getStartDate().toLocalDate().getDayOfWeek().toString(), apt.getServiceBooking(0).getTimeSlot().getStartTime(), java.sql.Time.valueOf(apt.getServiceBooking(0).getTimeSlot().getStartTime().toLocalTime().plusMinutes(((Service) newBookableService).getDuration())))) {
			return;
		}
		if (!openGarageOverlap(apt.getServiceBooking(0).getTimeSlot().getStartDate().toLocalDate().getDayOfWeek().toString(), apt.getServiceBooking(0).getTimeSlot().getStartTime(), java.sql.Time.valueOf(apt.getServiceBooking(0).getTimeSlot().getStartTime().toLocalTime().plusMinutes(((Service) newBookableService).getDuration())))) {
			return;
		}
		//checkTimeSlotOverlap("vacation", apt.getServiceBooking(0).getTimeSlot().getStartDate(), apt.getServiceBooking(0).getTimeSlot().getStartTime(), apt.getServiceBooking(0).getTimeSlot().getEndDate(), apt.getServiceBooking(0).getTimeSlot().getEndTime());
		for(int i = 0; i<carshop.getAppointments().size()-1; i++) {
			for(ServiceBooking ser: carshop.getAppointment(i).getServiceBookings()) {
				if(timeSlotOverlap(ser.getTimeSlot(), apt.getServiceBooking(0).getTimeSlot().getStartDate(), apt.getServiceBooking(0).getTimeSlot().getStartTime(), apt.getServiceBooking(0).getTimeSlot().getEndDate(), apt.getServiceBooking(0).getTimeSlot().getEndTime())){
					throw new Exception("timeslot overlap");
				}
			}
		}

		apt.setBookableService(newBookableService);
		apt.getServiceBooking(0).getTimeSlot().setEndTime(java.sql.Time.valueOf(apt.getServiceBooking(0).getTimeSlot().getStartTime().toLocalTime().plusMinutes( ((Service) newBookableService).getDuration())));
		apt.getServiceBooking(0).setService((Service)newBookableService);
		apt.updateAppointmentService();
		CarShopPersistence.save(CarShopApplication.getCarShop());
	}

	/**
	 * @author Massimo
	 * @modified Ben
	 * @param customerUsername
	 * @param newDate
	 * @param newStartTime
	 * @param dayOfDateChange
	 * @throws Exception
	 */
	public static void updateAppointmentTime(String customerUsername, String newDate,String newStartTime, String dayOfDateChange) throws Exception {

		Customer cust =(Customer) User.getWithUsername(customerUsername);//get the customer using the username
		Appointment anAppointment = cust.getAppointment(cust.getAppointments().size()-1);//get the most recent appointment for customer
		CarShopPersistence.save(CarShopApplication.getCarShop());
		List<ServiceBooking> servicebooking= anAppointment.getServiceBookings();

		Date appointmentDate= servicebooking.get(0).getTimeSlot().getStartDate();
		LocalDate appDate=appointmentDate.toLocalDate();

		String[] dateTime = dayOfDateChange.split("\\+");
		CarShopApplication.setLocalDate(dateTime[0]);
		CarShopApplication.setLocalTime(dateTime[1]+":00");

		LocalDate currentDate= CarShopApplication.getLocalDate();
		ArrayList<TimeSlot> timeSlots = new ArrayList<TimeSlot>(); 
		CarShopPersistence.save(CarShopApplication.getCarShop());
		String[] newTimes = newStartTime.split(",");

		int i = 0;

		if(currentDate.equals(appDate)) {
			return; //no changes are made to the appointment time
		}
		else if(currentDate.until(appDate).getDays()<1) {
			return; //no changes
		}
		else if(currentDate.isAfter(appDate)) {
			return;
		}

		else if(currentDate.until(appDate).getDays()>=1) {
			for (ServiceBooking s: servicebooking) {
				if (!openBusinessOverlap(java.sql.Date.valueOf(newDate).toLocalDate().getDayOfWeek().toString(), java.sql.Time.valueOf(newTimes[i]+":00"), java.sql.Time.valueOf(java.sql.Time.valueOf(newTimes[i]+":00").toLocalTime().plusMinutes(s.getService().getDuration())))) {
					return;
				}
				if(!openGarageOverlap(java.sql.Date.valueOf(newDate).toLocalDate().getDayOfWeek().toString(), java.sql.Time.valueOf(newTimes[i]+":00"), java.sql.Time.valueOf(java.sql.Time.valueOf(newTimes[i]+":00").toLocalTime().plusMinutes(s.getService().getDuration())))) {
					return;
				}
				checkTimeSlotOverlap("vacation", java.sql.Date.valueOf(newDate), java.sql.Time.valueOf(newTimes[i]+":00"), java.sql.Date.valueOf(newDate), java.sql.Time.valueOf(java.sql.Time.valueOf(newTimes[i]+":00").toLocalTime().plusMinutes(s.getService().getDuration())));
				for(Appointment a: cust.getAppointments()) {
					for(ServiceBooking ser: a.getServiceBookings()) {
						if(timeSlotOverlap(ser.getTimeSlot(), java.sql.Date.valueOf(newDate), java.sql.Time.valueOf(newTimes[i]+":00"), java.sql.Date.valueOf(newDate),  java.sql.Time.valueOf(java.sql.Time.valueOf(newTimes[i]+":00").toLocalTime().plusMinutes(s.getService().getDuration())))){
							throw new Exception("timeslot overlap");
						}
					}
				}
				//checkTimeSlotOverlap("vacation", java.sql.Date.valueOf(newDate), java.sql.Time.valueOf(newTimes[i]+":00"), java.sql.Date.valueOf(newDate), java.sql.Time.valueOf(java.sql.Time.valueOf(newTimes[i]+":00").toLocalTime().plusMinutes(s.getService().getDuration())));
				s.getTimeSlot().setStartTime(java.sql.Time.valueOf(newTimes[i]+":00"));
				s.getTimeSlot().setEndTime(java.sql.Time.valueOf((s.getTimeSlot().getStartTime().toLocalTime().plusMinutes(s.getService().getDuration()))));
				s.getTimeSlot().setStartDate(java.sql.Date.valueOf(newDate));
				s.getTimeSlot().setEndDate(java.sql.Date.valueOf(newDate));
				i++;
				timeSlots.add(s.getTimeSlot());
				CarShopPersistence.save(CarShopApplication.getCarShop());
			}

			anAppointment.updateAppointmentTime();
			CarShopPersistence.save(CarShopApplication.getCarShop());
		}	
	}

	public static void updateAppointmentTime(String customerUsername, String newDate,String newStartTime, String newServices, String dayOfDateChange, ToAppointment appointment) throws Exception {

		Customer cust =(Customer) User.getWithUsername(customerUsername);//get the customer using the username
		Appointment anAppointment = null;//get the most recent appointment for customer
		for(Appointment a: cust.getAppointments()) {
			if(a.getServiceBookings().get(0).getTimeSlot().getStartDate().toString().equalsIgnoreCase(appointment.getDay())) {
				if(a.getServiceBooking(0).getTimeSlot().getStartTime().toString().equalsIgnoreCase(appointment.getStartTime())) {
					if(a.getServiceBooking(0).getService().getName().equalsIgnoreCase(appointment.getBookingName())) {
						anAppointment = a;
					}
				}
			}
		}
		CarShopPersistence.save(CarShopApplication.getCarShop());
		List<ServiceBooking> servicebooking= anAppointment.getServiceBookings();

		Date appointmentDate= servicebooking.get(0).getTimeSlot().getStartDate();
		LocalDate appDate=appointmentDate.toLocalDate();

		String[] dateTime = dayOfDateChange.split("\\+");
		CarShopApplication.setLocalDate(dateTime[0]);
		CarShopApplication.setLocalTime(dateTime[1]+":00");

		LocalDate currentDate= CarShopApplication.getLocalDate();
		ArrayList<TimeSlot> timeSlots = new ArrayList<TimeSlot>(); 
		CarShopPersistence.save(CarShopApplication.getCarShop());
		String[] newTimes = newStartTime.split(",");

		for(int z = 0; z<anAppointment.getServiceBookings().size(); z++ ) {
			anAppointment.removeServiceBooking(anAppointment.getServiceBooking(z));
		}
		String [] services = newServices.split(",");
		String [] times = newStartTime.split(",");
		for(int z = 0; z<anAppointment.getServiceBookings().size(); z++ ) {
			anAppointment.removeServiceBooking(anAppointment.getServiceBooking(z));
		}
		ServiceBooking se;
		Service serv; 
		TimeSlot t;
		for(int y = 0; y < services.length; y++) {
			serv = (Service)Service.getWithName(services[y]);
			t = new TimeSlot(appointmentDate, Time.valueOf(times[y] + ":00"), appointmentDate, Time.valueOf(Time.valueOf(times[y] + ":00").toLocalTime().plusMinutes(serv.getDuration())), CarShopApplication.getCarShop());
			se = new ServiceBooking((Service)Service.getWithName(services[y]), t, anAppointment);
			anAppointment.addServiceBooking(se);
		}

		int i = 0;

		if(currentDate.equals(appDate)) {
			return; //no changes are made to the appointment time
		}
		else if(currentDate.until(appDate).getDays()<1) {
			return; //no changes
		}
		else if(currentDate.isAfter(appDate)) {
			return;
		}
		else if(currentDate.until(appDate).getDays()>=1) {
			for (ServiceBooking s: servicebooking) {
				if (!openBusinessOverlap(java.sql.Date.valueOf(newDate).toLocalDate().getDayOfWeek().toString(), java.sql.Time.valueOf(newTimes[i]+":00"), java.sql.Time.valueOf(java.sql.Time.valueOf(newTimes[i]+":00").toLocalTime().plusMinutes(s.getService().getDuration())))) {
					return;
				}
				if(!openGarageOverlap(java.sql.Date.valueOf(newDate).toLocalDate().getDayOfWeek().toString(), java.sql.Time.valueOf(newTimes[i]+":00"), java.sql.Time.valueOf(java.sql.Time.valueOf(newTimes[i]+":00").toLocalTime().plusMinutes(s.getService().getDuration())))) {
					return;
				}
				checkTimeSlotOverlap("vacation", java.sql.Date.valueOf(newDate), java.sql.Time.valueOf(newTimes[i]+":00"), java.sql.Date.valueOf(newDate), java.sql.Time.valueOf(java.sql.Time.valueOf(newTimes[i]+":00").toLocalTime().plusMinutes(s.getService().getDuration())));
				for(Appointment a: cust.getAppointments()) {
					for(ServiceBooking ser: a.getServiceBookings()) {
						if(timeSlotOverlap(ser.getTimeSlot(), java.sql.Date.valueOf(newDate), java.sql.Time.valueOf(newTimes[i]+":00"), java.sql.Date.valueOf(newDate),  java.sql.Time.valueOf(java.sql.Time.valueOf(newTimes[i]+":00").toLocalTime().plusMinutes(s.getService().getDuration())))){
							throw new Exception("timeslot overlap");
						}
					}
				}
				//checkTimeSlotOverlap("vacation", java.sql.Date.valueOf(newDate), java.sql.Time.valueOf(newTimes[i]+":00"), java.sql.Date.valueOf(newDate), java.sql.Time.valueOf(java.sql.Time.valueOf(newTimes[i]+":00").toLocalTime().plusMinutes(s.getService().getDuration())));
				s.getTimeSlot().setStartTime(java.sql.Time.valueOf(newTimes[i]+":00"));
				s.getTimeSlot().setEndTime(java.sql.Time.valueOf((s.getTimeSlot().getStartTime().toLocalTime().plusMinutes(s.getService().getDuration()))));
				s.getTimeSlot().setStartDate(java.sql.Date.valueOf(newDate));
				s.getTimeSlot().setEndDate(java.sql.Date.valueOf(newDate));
				i++;
				timeSlots.add(s.getTimeSlot());
				CarShopPersistence.save(CarShopApplication.getCarShop());
			}

			anAppointment.updateAppointmentTime();
			CarShopPersistence.save(CarShopApplication.getCarShop());
		}	
	}
	/**
	 * @author Ramin
	 * @param currentUser
	 * @param appointment
	 * @param services
	 * @param timeslots
	 * @throws Exception
	 */
	public static void updateAppointmentServiceCombo(User currentUser, Appointment appointment, List<BookableService> services, List<TimeSlot> timeslots) throws Exception{
		CarShop carshop = CarShopApplication.getCarShop();

		if(!(currentUser instanceof Customer)) {
			throw new Exception("No permission to update appointment");
		}
		if(!(appointment.getBookableService() instanceof Service) && !(appointment.getBookableService() instanceof ServiceCombo)) {
			throw new Exception("Invalid bookable service");
		}
		if(appointment.getBookableService() instanceof ServiceCombo || appointment.getAppointmentStatus().equals(AppointmentStatus.Booked)) {
			if((appointment.getBookableService() instanceof ServiceCombo && appointment.getAppointmentStatus().equals(AppointmentStatus.InProgress)) || CarShopApplication.getLocalDate().until(timeslots.get(0).getStartDate().toLocalDate()).getDays()>=1) {
				for(int i = 0; i<services.size(); i++) {
					if (!openBusinessOverlap(timeslots.get(i).getStartDate().toLocalDate().getDayOfWeek().toString(), timeslots.get(i).getStartTime(), timeslots.get(i).getEndTime())) {
						return;
					}
					if(!openGarageOverlap(timeslots.get(i).getStartDate().toLocalDate().getDayOfWeek().toString(), timeslots.get(i).getStartTime(), timeslots.get(i).getEndTime())) {
						return;
					}
					checkTimeSlotOverlap("vacation", timeslots.get(i).getStartDate(), timeslots.get(i).getStartTime(), timeslots.get(i).getEndDate(), timeslots.get(i).getEndTime());
					for(int z = 0; z<carshop.getAppointments().size()-1; z++) {
						for(ServiceBooking ser: carshop.getAppointment(z).getServiceBookings()) {
							if(timeSlotOverlap(ser.getTimeSlot(), timeslots.get(i).getStartDate(), timeslots.get(i).getStartTime(), timeslots.get(i).getEndDate(), timeslots.get(i).getEndTime())){
								throw new Exception("timeslot overlap");
							}
						}
					}
					if(appointment.getBookableService() instanceof ServiceCombo) {
						for(ServiceBooking ser: carshop.getAppointment(carshop.getAppointments().size()-1).getServiceBookings()) {
							if(timeSlotOverlap(ser.getTimeSlot(), timeslots.get(timeslots.size()-1).getStartDate(), timeslots.get(timeslots.size()-1).getStartTime(), timeslots.get(timeslots.size()-1).getEndDate(), timeslots.get(timeslots.size()-1).getEndTime())){
								throw new Exception("timeslot overlap");
							}
						}
					}

				}

				while(appointment.getServiceBookings().size() != 0) {
					appointment.getServiceBooking(0).delete();
				}
				for(int i = 0; i<services.size(); i++) {
					ServiceBooking sb = new ServiceBooking((Service) services.get(i), timeslots.get(i), appointment);
					appointment.addServiceBooking(sb);
				}
				appointment.updateAppointmentService();
			}
			else if(CarShopApplication.getLocalDate().isEqual(timeslots.get(0).getStartDate().toLocalDate())) {
				return; //no changes are made to the appointment time
			}
			else if(CarShopApplication.getLocalDate().until(timeslots.get(0).getStartDate().toLocalDate()).getDays()<1) {
				return; //changes made
			}
			else if(CarShopApplication.getLocalDate().isAfter(timeslots.get(0).getStartDate().toLocalDate())) {
				return;
			}

		} else if (appointment.getAppointmentStatus().equals(AppointmentStatus.InProgress)) {
			appointment.rejectUpdateAppointment();
		}
		CarShopPersistence.save(CarShopApplication.getCarShop());

	}



	/**
	 * @author Ramin
	 * Helper method - converting string into time
	 * @param str
	 * @return
	 */
	public static String[] splitString(String str) {
		String[] dateTimeElements = new String[2];
		String systemDate = "";
		String time = "";
		boolean middle = false;
		//the following goes through the string to divide into date and time strings
		for(int i = 0; i<str.length(); i++) {
			if(str.charAt(i) != '+' && !middle) { //if before '+' sign it is part of date
				systemDate += str.charAt(i);
			}
			if (str.charAt(i) != '+' && middle) { //if after '+' sign it is part of time
				time += str.charAt(i);
			}
			if(str.charAt(i) == '+') { //if reaches divider of date and time
				middle = true;
			}
		}
		dateTimeElements[0] = systemDate;
		dateTimeElements[1] = time;	
		return null;

	}



	public static List<BusinessHourTO> getBusinessHours() {
		CarShop carshop = CarShopApplication.getCarShop();
		List<BusinessHourTO> bhTO = new ArrayList<BusinessHourTO>();
		if(carshop.getBusiness() != null) {
			for(BusinessHour bh: carshop.getBusiness().getBusinessHours()) {
				bhTO.add(new BusinessHourTO(bh.getDayOfWeek().toString(), bh.getStartTime().toString(), bh.getEndTime().toString()));

			}
		}

		return bhTO;

	}


	public static List<BusinessHourTO> getGarageBusinessHours(String User){
		String	user = CarShopApplication.getRecentlyLogedinUser().getUsername();

		CarShop carshop = CarShopApplication.getCarShop();
		List<Technician> techs = carshop.getTechnicians();

		List<BusinessHourTO> bhTO = new ArrayList<BusinessHourTO>();
		for(Technician tech : techs) {
			if(tech.getUsername().equals(user)) {
				if(tech.getGarage()!= null) {
					if(tech.getGarage().getBusinessHours()!= null) {
						for(BusinessHour bh: tech.getGarage().getBusinessHours()) {

							bhTO.add(new BusinessHourTO(bh.getDayOfWeek().toString(), bh.getStartTime().toString(), bh.getEndTime().toString()));

						}
					}

				}

			}


		}
		return bhTO;		
	}

	public static void logOut() {
		CarShopApplication.logoutRecentlyLogedinUser();
	}
	public static String getRecentlyLoggedInUser() {
		return CarShopApplication.getRecentlyLogedinUser().getUsername();
	}
	public static ca.mcgill.ecse.carshop.view.LogInView getLogInView() {
		return CarShopApplication.getLogInView();
	}


	/**
	 * Tiffany's Transfer Object Method.
	 * This method gets the currently logged in user and returns
	 * a transfer object user (which only contains
	 * the username)
	 * @return
	 */
	public static ToUser getCurrentToUser() {
		String username = CarShopApplication.getRecentlyLogedinUser().getUsername();

		ToUser user = new ToUser(username);
		return user;		
	}

	/**
	 * Tiffany's TO helper method.
	 * This method takes a transfer object, finds the corresponding appointment in carshop
	 * and returns it
	 * @param to the transfer object we want to convert
	 * @return the corresponding appointment
	 */
	public static Appointment convertTOAppointmentToModelAppointment(ToAppointment to) {
		Appointment appointment = null;

		for (Appointment a: CarShopApplication.getCarShop().getAppointments()) {
			if (a.getCustomer().getUsername().toString().equals(to.getCustomer())
					&& a.getBookableService().getName().equals(to.getBookingName())
					&& a.getServiceBooking(0).getTimeSlot().getStartTime().toString().equals(to.getStartTime())
					) {
				appointment = a;
			}
		}

		return appointment;
	}

	/**
	 * Tiffany's Transfer Object method
	 */
	public static String curDateToString() {

		LocalDate localD = CarShopApplication.getDateNow();
		LocalTime localT = CarShopApplication.getTimeNow();

		String dateTime = localD.toString() + "+" +  localT.toString();

		return dateTime;
	}

	/**
	 * Tiffany's Transfer Object method
	 * This method takes a transfer object user and
	 * returns the Model user with that username.
	 */
	public static User ToUserToModelUser(String string) {
		User user = User.getWithUsername(string);

		return user;
	}

	/**
	 * Tiffany's Transfer Object method
	 * This method gets all the appointments in the carshop
	 * and returns a list of TOAppointments. These only
	 * have the customer username, the start time, and the
	 * start date.
	 */
	public static ArrayList<ToAppointment> getAllAppointmentsToTOAppointment() {
		ArrayList<ToAppointment> newList = new ArrayList<>();
		for (Appointment a: CarShopApplication.getCarShop().getAppointments()) {
			ToAppointment appointment = new ToAppointment(a.getCustomer().getUsername(), 
					a.getServiceBooking(0).getTimeSlot().getStartTime().toString(),
					a.getServiceBooking(0).getTimeSlot().getStartDate().toString(),
					a.getBookableService().getName());
			newList.add(appointment);
		}

		return newList;

	}
	/*Ramin*/
	public static List<ToBusinessHour> getBusinessHoursR(){
		List<ToBusinessHour> bHours = new ArrayList<ToBusinessHour>();
		if(CarShopApplication.getCarShop().getBusiness() != null) {
			for(BusinessHour b: CarShopApplication.getCarShop().getBusiness().getBusinessHours()) {
				ToBusinessHour newBHour = new ToBusinessHour(b.getDayOfWeek().toString(), b.getStartTime().toString(), b.getEndTime().toString());
				bHours.add(newBHour);
			}
		}
		return bHours;
	}

}
