
package ca.mcgill.ecse.carshop.features;

import ca.mcgill.ecse.carshop.controller.CarShopController;
import ca.mcgill.ecse.carshop.controller.InvalidInputException;
import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.model.*;
import ca.mcgill.ecse.carshop.model.Technician.TechnicianType;
import ca.mcgill.ecse.carshop.model.BusinessHour.DayOfWeek;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MakeAppointmentStepDefinition {
	
	private CarShop carShop = CommonStepDefinitions.carshop; 
	static int numOfAppointments=0;
	static String errorMessage="";
	DayOfWeek[] dow= BusinessHour.DayOfWeek.values();
	

//	@Given("a Carshop system exists")
//	public void a_carshop_system_exists() {
//		if (carShop != null) // remove later
//			carShop.delete();
//		carShop = CarShopApplication.getCarShop();
//	}
	
//	@Given("the system's time and date is {string}")
//	public void the_systems_time_and_date_is(String string) {
//		int i=0;
//		String sDate="";
//		String sTime="";
//		while(string.charAt(i)!='+') {
//			sDate+=(char)string.charAt(i);
//			i++;
//		}
//		while(i<string.length()) {
//			sTime+=(char) string.charAt(i);
//			i++;
//		}
//		sTime+=":00";
//		CarShopApplication.setLocalDate(sDate);
//		CarShopApplication.setLocalTime(sTime);
//	}

//	@Given("an owner account exists in the system")
//	public void an_owner_account_exists_in_the_system() {
//		if (carShop.getOwner() == null)
//			new Owner("dummyName", "dymmyPass", carShop);
//	}

//	@Given("a business exists in the system")
//	public void a_business_exists_in_the_system() {
//		if (carShop.getBusiness() == null)
//			new Business("dummyName", "dummyAddress", "dummyPhoneNumber", "dymmyEmail", carShop);
//	}

//	@Given("the following customers exist in the system:")
//	public void the_following_customers_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
//		List<List<String>> valueMaps = dataTable.asLists();
//
//		for(int i=0;i<valueMaps.size();i++) {
//		Customer c1 = carShop.addCustomer(valueMaps.get(i).get(0), valueMaps.get(i).get(1));
//		carShop.addCustomer(c1);
//		}
//	}
	
//	@Given("the following technicians exist in the system:")
//	public void the_following_technicians_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
//
//		List<Technician> t = new ArrayList<Technician>();
//		List<List<String>> valueMaps = dataTable.asLists();
//
//		for (int i = 1; i < valueMaps.size(); i++) {
//			t.add(carShop.addTechnician(valueMaps.get(i).get(0), valueMaps.get(i).get(1),
//					CarShopController.StringToTechnicianType(valueMaps.get(i).get(2))));
//		}
//	}

//	@Given("each technician has their own garage")
//	public void each_technician_has_their_own_garage() throws Exception {
//
//		List<Technician> technicians = carShop.getTechnicians();
//
//		int size = technicians.size();
//		
//		// -- debugging
//		String message = "Error: Number of technicians is" + size;
//		if (size != 5)
//			throw new Exception(message);
//		
//		for (Technician t : technicians) {
//			new Garage(carShop, t);
//		}
//	}
	
//	@Given("the following services exist in the system:")
//	public void the_following_services_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
//		List<List<String>> valueMaps=dataTable.asLists();
//		List<Garage> garages=carShop.getGarages(); 
//		Garage garage2=null;
//		
//		
//		for(int i=1;i<valueMaps.size();i++) {
//			for(Garage g:garages) {
//				if(g.getTechnician().getType().toString().equals(valueMaps.get(i).get(2))) {
//					garage2=g;
//				}
//			}
//			
//			carShop.addBookableService(new Service(valueMaps.get(i).get(0), carShop, Integer.parseInt(valueMaps.get(i).get(1)), garage2));
//		}
//	}
	
//	@Given("the following service combos exist in the system:")
//	public void the_following_service_combo_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
//		List<List<String>> valueMaps=dataTable.asLists();
//		List<BookableService> services=carShop.getBookableServices();
//		Service service=null;
//		ServiceCombo sc=null;
//
//		for(int i=1;i<valueMaps.size();i++) {
//			String serv=valueMaps.get(i).get(2);
//			String bool=valueMaps.get(i).get(3);
//			
//			String[] servs=serv.split(","); // Separate service
//			String[] bools=bool.split(","); // Separate mandatory
//			
//			sc=new ServiceCombo(valueMaps.get(i).get(0), carShop);
//			for(int j=0;j<servs.length;j++) {
//				for(BookableService s:services) {
//					if(s.getName().equals(servs[j]))
//					service=(Service) s;
//				}
//				sc.addService(new ComboItem(Boolean.parseBoolean(bools[j]), service , sc));
//			}
//			carShop.addBookableService(sc);
//		}
//		sc.setMainService(sc.getService(0));
//	}
	
	
	@Given("the business has the following opening hours")
	public void the_business_has_the_following_opening_hours(io.cucumber.datatable.DataTable dataTable) {
		List<List<String>> valueMaps=dataTable.asLists();
		if(carShop.getBusiness().hasBusinessHours()) {
			carShop.getBusiness().getBusinessHours().clear();
			for(int i=1;i<valueMaps.size();i++) {
				carShop.getBusiness().addBusinessHour(new BusinessHour(dow[i-1], java.sql.Time.valueOf(valueMaps.get(i).get(1)+":00"), java.sql.Time.valueOf(valueMaps.get(i).get(2)+":00"), carShop));
				}
			}
		else {
		for(int i=1;i<valueMaps.size();i++) {
			carShop.getBusiness().addBusinessHour(new BusinessHour(dow[i-1], java.sql.Time.valueOf(valueMaps.get(i).get(1)+":00"), java.sql.Time.valueOf(valueMaps.get(i).get(2)+":00"), carShop));
			}
		}
	}
	
	@Given("all garages has the following opening hours")
	public void all_garages_has_the_following_opening_hours(io.cucumber.datatable.DataTable dataTable) {
		List<List<String>> valueMaps=dataTable.asLists();
		List<Garage> garages=carShop.getGarages();
		
		for(int i=1;i<valueMaps.size();i++) {
			for(Garage g:garages) {
			g.addBusinessHour(new BusinessHour(dow[i-1], java.sql.Time.valueOf(valueMaps.get(i).get(1)+":00"), java.sql.Time.valueOf(valueMaps.get(i).get(2)+":00"), carShop));
			}
			
		}
	}
	
	@Given("the business has the following holidays")
	public void the_business_has_the_following_holidays(io.cucumber.datatable.DataTable dataTable){
		List<List<String>> valueMaps=dataTable.asLists();
		
		for(int i=1;i<valueMaps.size();i++){	
			carShop.getBusiness().addHoliday(new TimeSlot(java.sql.Date.valueOf(valueMaps.get(i).get(0)), java.sql.Time.valueOf(valueMaps.get(i).get(2)+":00"), java.sql.Date.valueOf(valueMaps.get(i).get(1)), java.sql.Time.valueOf(valueMaps.get(i).get(3)+":00"), carShop));
		}
	}
	
	
	// Need to figure out this
	@Given("the following appointments exist in the system:")
	public void AppointmentsExistence(io.cucumber.datatable.DataTable dataTable) {
		List<List<String>> valueMaps=dataTable.asLists();
		List<BookableService> services=carShop.getBookableServices();
		List<Customer> customers=carShop.getCustomers();
		ServiceCombo service=null;
		Service optServ=null;
		Customer c1=null;
		
		for(BookableService bs:services) {
			if(bs instanceof ServiceCombo && bs.getName().equals(valueMaps.get(1).get(1))) {
				service=(ServiceCombo)bs;
			}
		}
		
		for(Customer c:customers) {
			if(c.getUsername().equals(valueMaps.get(1).get(0))){
				c1=c;
			}
		}
		
		String options = valueMaps.get(1).get(2);
		String[] optSer = options.split(",");
		List<BookableService> optServices = new ArrayList<BookableService>();
		optServices.add(service.getMainService().getService());
		for(int i = 0; i<optSer.length; i++) {
			optServices.add(Service.getWithName(optSer[i]));
		}
		Appointment a=new Appointment(c1, service, carShop);
		
		String timeslots = valueMaps.get(1).get(4);
		String date = valueMaps.get(1).get(3);
		String[] seperateTimes = timeslots.split(",");
		
		
		for(int i = 0; i <  seperateTimes.length; i++) {
			String[] timeElements = seperateTimes[i].split("-");
			Time start = Time.valueOf(timeElements[0]+":00");
			Time end = Time.valueOf(timeElements[1]+ ":00");
			TimeSlot timeslot=new TimeSlot(java.sql.Date.valueOf(date),start,java.sql.Date.valueOf(date),end,carShop);
			ServiceBooking sb=new ServiceBooking((Service)optServices.get(i), timeslot, a);
			a.addServiceBooking(sb);
		}
		
		carShop.addAppointment(a);
		
		/*String string=valueMaps.get(1).get(4);
		String[] tempA=string.split(",");
		String tempB=Arrays.toString(tempA);
		tempB=tempB.replace("[", "");
		tempB=tempB.replace("]", "");
		String[] tempC=tempB.split("-");
		
		// HOW TO USE DATATABLE FOR THIS??
		Appointment a=new Appointment(c1, service, carShop);
		TimeSlot timeslot1=new TimeSlot(java.sql.Date.valueOf(valueMaps.get(1).get(3)),java.sql.Time.valueOf("09:00:00"),java.sql.Date.valueOf(valueMaps.get(1).get(3)),java.sql.Time.valueOf("10:00:00"),carShop);
		TimeSlot timeslot2=new TimeSlot(java.sql.Date.valueOf(valueMaps.get(1).get(3)),java.sql.Time.valueOf("10:10:00"),java.sql.Date.valueOf(valueMaps.get(1).get(3)),java.sql.Time.valueOf("11:25:00"),carShop);
		ServiceBooking sb1=new ServiceBooking(service.getService(0).getService(), timeslot1, a);
		ServiceBooking sb2=new ServiceBooking(service.getService(1).getService(), timeslot2, a);
		a.addServiceBooking(sb1);
		a.addServiceBooking(sb2);
		carShop.addAppointment(a);*/
		
					
	}
	
	// Ok
	@Given("{string} is logged in to their account")
	public void is_logged_in_to_their_account1(String string) {
		Boolean exists=false;
		List<Customer> customers=carShop.getCustomers();
		User user=null;
		if(string.equals("owner")) {
			if(carShop.getOwner()==null) {
				user=new Owner(string,"ownerpass", carShop);
				try {
					CarShopController.login(string, user.getPassword());
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				user=carShop.getOwner();
				
				try {
					CarShopController.login(carShop.getOwner().getUsername(), user.getPassword());
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		else if(string.contains("Technician")){
			List<Technician> techs=carShop.getTechnicians();
			for(Technician t:techs) {
				if(t.getUsername().equals(string)) {
					user=t;
				}
			}
			try {
				CarShopController.login(user.getUsername(),user.getPassword());
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else{
			if ((carShop.getCustomers()).isEmpty()) {
				carShop.addCustomer(string,"12345678");
				exists=true;
			}
			else {
				for(Customer c:customers) {
					if(c.getUsername().equalsIgnoreCase(string)) {
						user=c;
						exists=true;
						break;
					}
					else exists=false;
				}
				// Create the customer if it doesn't exist
				if(!exists) {
					user=new Customer(string,"12345678", carShop);
					carShop.addCustomer((Customer)user);
					exists=true;
				}
			//CarShopApplication.add(carshop.getOwner());  //login user
				try {
					CarShopController.login(string, "12345678");
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
	
	// Should be okay
	@When("{string} schedules an appointment on {string} for {string} at {string}")
	public void schedules_an_appointment_on_for_at(String string,String string2, String string3,String string4) throws Exception {
		List<Customer> customers=carShop.getCustomers();
		List<BookableService> services=carShop.getBookableServices();
		Customer c1=null;
		Service s=null;
		
		for(Customer c:customers) {
			if(c.getUsername().equals(string)) {
				c1=c;
				break;
			}
		}
		
		for(BookableService bs:services) {
			if(bs instanceof Service) {
				if(bs.getName().equals(string3)){
					s=(Service)bs;
				}
			}
			
		}
		
		java.sql.Date appointmentDate=java.sql.Date.valueOf(string2);
		java.sql.Time startTime=java.sql.Time.valueOf(string4+=":00");
		LocalTime temp=startTime.toLocalTime();
		temp=temp.plusMinutes(s.getDuration());
		java.sql.Time endTime=java.sql.Time.valueOf(temp);
		TimeSlot timeslot=new TimeSlot(appointmentDate, startTime,appointmentDate,endTime, carShop);
		ArrayList<TimeSlot> timeslots=new ArrayList<TimeSlot>();
		ArrayList<BookableService> ser=new ArrayList<BookableService>();
		ser.add(s);
		timeslots.add(timeslot);
		numOfAppointments=carShop.numberOfAppointments();
		try {
			CarShopController.MakeAppointment(c1, s, timeslots,ser);
		}
		catch(Exception e) {
			errorMessage=e.getMessage();
		}
	}
	
	// Need to figure out Service Combo
	@When("{string} schedules an appointment on {string} for {string} with {string} at {string}")
	public void schedules_an_appointment_on_for_with_at(String string,String string2, String string3,String string4,String string5) throws Exception {
		List<Customer> customers=carShop.getCustomers();
		List<BookableService> services=carShop.getBookableServices();
		Customer c1=(Customer) User.getWithUsername(string);
		ServiceCombo sc=(ServiceCombo) BookableService.getWithName(string3);
		ArrayList<TimeSlot> timeslots=new ArrayList<TimeSlot>();
		ArrayList<BookableService> ser=new ArrayList<BookableService>();
		

		
		String[] tempTime=string5.split(",");
		
		java.sql.Date appointmentDate=java.sql.Date.valueOf(string2);
		
		for(ComboItem ci:sc.getServices()) {
			if(ci.getMandatory()) {
				ser.add(ci.getService());
			}
			else {
				if(string4.contains(ci.getService().getName())) {
					ser.add(ci.getService());
				}
			}
		}
		
		for(int i=0;i<tempTime.length;i++) {
			Service currS=(Service) ser.get(i);
			
			java.sql.Time startTime=java.sql.Time.valueOf(tempTime[i]+":00");
			
			java.sql.Time endTime=java.sql.Time.valueOf(startTime.toLocalTime().plusMinutes(currS.getDuration()));
			
			TimeSlot timeslot=new TimeSlot(appointmentDate, startTime, appointmentDate, endTime, carShop);
			
			timeslots.add(timeslot);
		}	
		

			numOfAppointments=carShop.numberOfAppointments();
			try {
			CarShopController.MakeAppointment(c1, sc, timeslots,ser);
			}
			catch (Exception e) {
				errorMessage=e.getMessage();
			}
	}
	
	
	// To RECHECK
	@Then("{string} shall have a {string} appointment on {string} from {string} to {string}")
	public void shall_have_a_appointment_on_from_to(String string,String string2, String string3,String string4,String string5) {
		
		String nameActual=null;
		String nameExpected=string;
		String serviceActual=null;
		String serviceExpected=string2;
		String appDateActual=null;
		String appDateExpected=string3;
		String appStartActual=null;
		String appStartExpected=string4;
		String appEndActual=null;
		String appEndExpected=string5;
		String tempEndActual="";
		String tempStartActual="";
		
		User customer=CarShopApplication.getRecentlyLogedinUser();
		nameActual=customer.getUsername();
		
		Appointment a=carShop.getAppointment(carShop.getAppointments().size()-1);

		if(a.getServiceBookings().size()>1) {
			serviceActual=a.getBookableService().getName();
		}
		else {
			serviceActual=a.getServiceBooking(0).getService().getName();
		}
		
		java.sql.Date date=a.getServiceBooking(0).getTimeSlot().getStartDate();
		appDateActual=date.toLocalDate().toString();
		
		if(a.getServiceBookings().size()>1) {
			for(int i=0;i<a.getServiceBookings().size();i++) {
				
				java.sql.Time startTime=a.getServiceBooking(i).getTimeSlot().getStartTime();
				appStartActual=startTime.toLocalTime().toString();
				
				java.sql.Time endTime=a.getServiceBooking(i).getTimeSlot().getEndTime();
				appEndActual=endTime.toLocalTime().toString();
			
			if(appStartActual.charAt(0)=='0') {
				for(int j=1;j<appStartActual.length();j++) {
					tempStartActual+=appStartActual.charAt(j);
				}
				}
			
			else if(tempStartActual.isEmpty()){
				tempStartActual+=appStartActual;
			}
			
			else tempStartActual+=","+appStartActual; 
				
			
			if(appEndActual.charAt(0)=='0') {
				for(int j=1;j<appEndActual.length();j++) {
					tempEndActual+=appEndActual.charAt(j);
				}
				}
			else if(tempEndActual.isEmpty()){
				tempEndActual+=appEndActual;
			}
			else {
				tempEndActual+=","+appEndActual;
			}
			}
		}
		
		else {
			java.sql.Time startTime=a.getServiceBooking(0).getTimeSlot().getStartTime();
			appStartActual=startTime.toLocalTime().toString();
			
			java.sql.Time endTime=a.getServiceBooking(a.numberOfServiceBookings()-1).getTimeSlot().getEndTime();
			appEndActual=endTime.toLocalTime().toString();
	
			if(appStartActual.charAt(0)=='0') {
				for(int i=1;i<appStartActual.length();i++) {
					tempStartActual+=appStartActual.charAt(i);
				}
				}
			else {
				tempEndActual+=appEndActual;
			}
		
		if(appEndActual.charAt(0)=='0') {
			for(int i=1;i<appEndActual.length();i++) {
				tempEndActual+=appEndActual.charAt(i);
			}
			}
		else {
			tempEndActual=appEndActual;
		}
		}
		for(ServiceBooking b:a.getServiceBookings()) {
			System.err.println(b.getService().toString()+" at "+b.getTimeSlot().toString());
		}
		Assert.assertEquals(nameExpected, nameActual);
		Assert.assertEquals(serviceExpected,serviceActual);
		Assert.assertEquals(appDateExpected,appDateActual);
		Assert.assertEquals(appStartExpected,tempStartActual);
		Assert.assertEquals(appEndExpected,tempEndActual);
	}
	
	// Ok
	@Then("there shall be {int} more appointment in the system")
	public void there_shall_be_more_appointment_in_the_system(int int1) {
		int numExpected=int1;
		int numActual=carShop.getAppointments().size()-numOfAppointments;
		Assert.assertEquals(numExpected,numActual);
	}	


	// Ok
	@Then("the system shall report {string}")
	public void the_system_shall_report(String string) {
			Assert.assertEquals(string,errorMessage);
	}

	
	@After
	public void tearDown() {
		carShop.delete();
		errorMessage="";
		numOfAppointments=0;
		CommonStepDefinitions.Error = null;
		CommonStepDefinitions.carshop = CarShopApplication.getCarShop();
	}
}
