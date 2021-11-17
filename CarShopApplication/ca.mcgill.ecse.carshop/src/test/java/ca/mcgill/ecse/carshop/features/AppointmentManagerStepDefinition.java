
package ca.mcgill.ecse.carshop.features;

import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.controller.CarShopController;
import ca.mcgill.ecse.carshop.model.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.carshop.model.Appointment;
import ca.mcgill.ecse.carshop.model.Appointment.AppointmentStatus;
import ca.mcgill.ecse.carshop.model.BookableService;
import ca.mcgill.ecse.carshop.model.CarShop;
import ca.mcgill.ecse.carshop.model.ComboItem;
import ca.mcgill.ecse.carshop.model.Customer;
import ca.mcgill.ecse.carshop.model.Service;
import ca.mcgill.ecse.carshop.model.ServiceCombo;
import ca.mcgill.ecse.carshop.model.TimeSlot;
import ca.mcgill.ecse.carshop.model.User;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AppointmentManagerStepDefinition {
	
	CarShop carshop = CommonStepDefinitions.carshop;
	String errorMessage = CommonStepDefinitions.Error;
	
	@Before
	public void buildup() {
		carshop = CarShopApplication.getCarShop();
	}
	
	@Given("{string} has {int} no-show records")
	public void has_no_show_records(String string, Integer int1) {
		// Vy-Kha
		Customer c = (Customer) Customer.getWithUsername(string);
		c.setNoShows(int1);
	}

	@When("{string} makes a {string} appointment for the date {string} and time {string} at {string}")
	public void makes_a_appointment_for_the_date_and_time_at(String string, String string2, String string3, String string4, String string5) {
		// nath
         try {
        	CarShopApplication.setLocalDate(string5.split("\\+")[0]);
        	CarShopApplication.setLocalTime(string5.split("\\+")[1]+":00");
        	Service service = (Service)BookableService.getWithName(string2);
        	System.err.println("gdhshsahsd: " + service.getName());
        	
        	List<TimeSlot> timeslots = new ArrayList<TimeSlot>();
    
        	Date date = Date.valueOf(string3);
        	LocalTime start = java.sql.Time.valueOf(string4 + ":00").toLocalTime();
        	LocalTime end = start.plusMinutes(service.getDuration());
           
        	TimeSlot timeslot = new TimeSlot(date,java.sql.Time.valueOf(start), date, java.sql.Time.valueOf(end),CommonStepDefinitions.carshop);
        	timeslots.add(timeslot);
        	List<BookableService> bookableService = new ArrayList<BookableService>();
        	bookableService.add(BookableService.getWithName(string));
			CarShopController.MakeAppointment(User.getWithUsername(string), BookableService.getWithName(string2),timeslots, bookableService);
		} catch (Exception e) {
			CommonStepDefinitions.Error = e.getMessage();
		}
	}

	@When("{string} attempts to change the service in the appointment to {string} at {string}")
	public void attempts_to_change_the_service_in_the_appointment_to_at(String string, String string2, String string3) {
    	// nath
    	CarShopApplication.setLocalDate(string3.split("\\+")[0]);
    	CarShopApplication.setLocalTime(string3.split("\\+")[1]+":00");
    	Customer customer = (Customer) User.getWithUsername(string);
    	List<Appointment> appointments = customer.getAppointments();
    	Appointment mostRecentApp = appointments.get(appointments.size()-1);
    	
    	BookableService bookableService = BookableService.getWithName(string2); 
    	List<BookableService> services = new ArrayList<BookableService>();
    	services.add(bookableService);
    	List<TimeSlot> timeslots = new ArrayList<TimeSlot>();
    	Time endTime = java.sql.Time.valueOf(mostRecentApp.getServiceBooking(0).getTimeSlot().getStartTime().toLocalTime().plusMinutes(((Service) BookableService.getWithName(string2)).getDuration()));
    	TimeSlot timeslot = new TimeSlot(mostRecentApp.getServiceBooking(0).getTimeSlot().getStartDate(), mostRecentApp.getServiceBooking(0).getTimeSlot().getStartTime(), mostRecentApp.getServiceBooking(0).getTimeSlot().getEndDate(), endTime, carshop);
    	timeslots.add(timeslot);
    	
    	try {
    		CarShopController.UpdateServiceofAppointment(string, bookableService);
		} catch (Exception e) {
			CommonStepDefinitions.Error = e.getMessage();
		}
	}

	@Then("the appointment shall be booked")
	public void the_appointment_shall_be_booked() {
		// Vy-Kha
		boolean bookExpected=true;
		boolean bookActual=false;
		
		Appointment newApp=CommonStepDefinitions.carshop.getAppointment(CommonStepDefinitions.carshop.getAppointments().size()-1);
		bookActual=newApp.getAppointmentStatus().equals(AppointmentStatus.Booked);
		assertEquals(bookActual,bookExpected);
	}

	@Then("the service in the appointment shall be {string}")
	public void the_service_in_the_appointment_shall_be(String string) {
		// Vy-Kha
		String nameExpected=string;
		String nameActual="";
		Appointment a=CommonStepDefinitions.carshop.getAppointment(CommonStepDefinitions.carshop.getAppointments().size()-1);
	
		nameActual=a.getBookableService().getName();
		
		assertEquals(nameExpected, nameActual);
	}

	//the appointment shall be for the date "2021-04-11" with start time "11:00" and end time "11:20"
	@Then("the appointment shall be for the date {string} with start time {string} and end time {string}")
	public void the_appointment_shall_be_for_the_date_with_start_time_and_end_time(String string, String string2, String string3) {
		Appointment a = (CarShopApplication.getCarShop().getAppointments().get(CarShopApplication.getCarShop().getAppointments().size()-1));
		Date expectedDate = Date.valueOf(a.getServiceBookings().get(0).getTimeSlot().getStartDate().toLocalDate());
    	
		String[] startTimes = string2.split(",");
		String[] endTimes = string3.split(",");
		Appointment apt = CommonStepDefinitions.carshop.getAppointment(CommonStepDefinitions.carshop.getAppointments().size()-1);
		List<ServiceBooking> serviceBookings = new ArrayList<ServiceBooking>();
		serviceBookings = apt.getServiceBookings();
		assertEquals(string, expectedDate.toString());
		
		
		for(int i = 0; i<startTimes.length; i++) {
			assertEquals(startTimes[i], serviceBookings.get(i).getTimeSlot().getStartTime().toLocalTime().toString());
			assertEquals(endTimes[i], serviceBookings.get(i).getTimeSlot().getEndTime().toLocalTime().toString());
		}
	}

	@Then("the username associated with the appointment shall be {string}")
	public void the_username_associated_with_the_appointment_shall_be(String string) {
	    // Write code here that turns the phrase above into concrete actions
		// Vy-Kha
		String nameExpected=string;
		String nameActual="";
		Appointment a=CommonStepDefinitions.carshop.getAppointment(CommonStepDefinitions.carshop.getAppointments().size()-1);
		nameActual=a.getCustomer().getUsername();
		assertEquals(nameActual, nameExpected);
	}

	@Then("the user {string} shall have {int} no-show records")
	public void the_user_shall_have_no_show_records(String string, Integer int1) {
		// nath
		Customer c = (Customer) User.getWithUsername(string);
		int actual = c.getNoShows();
		assertEquals(int1.intValue(), actual);
	}

	@Then("the system shall have {int} appointments")
	public void the_system_shall_have_appointments(Integer int1) {
		// ben
		assertTrue(CommonStepDefinitions.carshop.getAppointments().size()==int1);
	}

	@When("{string} attempts to update the date to {string} and time to {string} at {string}")
	public void attempts_to_update_the_date_to_and_time_to_at(String string, String string2, String string3, String string4) {
		// ben
		try {
			CarShopController.updateAppointmentTime(string, string2, string3, string4);
		}
		catch (Exception e) {
			CommonStepDefinitions.Error = e.getMessage();
		}
	}

	@When("{string} attempts to cancel the appointment at {string}")
	public void attempts_to_cancel_the_appointment_at(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
		// ben
		CarShop carshop = CommonStepDefinitions.carshop;	
		User user= User.getWithUsername(string);
		
		String systemDate = "";
		String time = "";
		boolean middle = false;
		//the following goes through the string to divide into date and time strings
		for(int i = 0; i<string2.length(); i++) {
			if(string2.charAt(i) != '+' && !middle) { //if before '+' sign it is part of date
				systemDate += string2.charAt(i);
			}
			if (string2.charAt(i) != '+' && middle) { //if after '+' sign it is part of time
				time += string2.charAt(i);
			}
			if(string2.charAt(i) == '+') { //if reaches divider of date and time
				middle = true;
			}
		}
		CarShopApplication.setLocalDate(systemDate);
		CarShopApplication.setLocalTime(time + ":00");
		CarShopApplication.setDate(Date.valueOf(systemDate));
		CarShopApplication.setTime(Time.valueOf(time+":00"));
		
		// Finds the last appointment
		Appointment appointment= carshop.getAppointment(carshop.getAppointments().size()-1);
		try {
			CarShopController.CancelAppointment(user, appointment);
		} catch (Exception e) {
			
		}
	}

	/**
	 * @author: Ramin Akhavan-Sarraf
	 * Making sure the carshop has the right number of appointments in the system
	 * @param int1: int - number of appointments that should be in the system
	 */
	@Then("the system shall have {int} appointment")
	public void the_system_shall_have_appointment(Integer int1) {
		assertEquals(int1, carshop.getAppointments().size());
	}

	@When("{string} makes a {string} appointment with service {string} for the date {string} and start time {string} at {string}")
	public void makes_a_appointment_with_service_for_the_date_and_start_time_at(String string, String string2, String string3, String string4, String string5, String string6) {
		// Ramin
    	ServiceCombo serviceCombo = (ServiceCombo)BookableService.getWithName(string2);
    	List<TimeSlot> timeslots = new ArrayList<TimeSlot>();
    	List<ComboItem> comboServices = new ArrayList<ComboItem>();
    	List<BookableService> services = new ArrayList<BookableService>();
    	comboServices = serviceCombo.getServices();
    	String[] startTimes = string5.split(",");
    	Date date = Date.valueOf(string4);
    	int index = 0;
    	for(int i = 0; i<comboServices.size(); i++) {
    		if(comboServices.get(i).getMandatory()) {
    			System.err.println(comboServices.get(i).getService().getName());
	    		Time startingTime = Time.valueOf(CarShopController.StringToTime(startTimes[index]).toLocalTime());
	    		Time endingTime = Time.valueOf(startingTime.toLocalTime().plusMinutes(comboServices.get(i).getService().getDuration()));
	    		TimeSlot newTimeSlot = new TimeSlot(date, startingTime, date, endingTime, carshop);
	    		timeslots.add(newTimeSlot);
	    		services.add(comboServices.get(i).getService());
	    		index++;
	    		System.err.println(startingTime.toString());
	    		System.err.println(endingTime.toString());
    		}
    	}
    	
		try {
			String systemDate = "";
			String time = "";
			boolean middle = false;
			//the following goes through the string to divide into date and time strings
			for(int i = 0; i<string6.length(); i++) {
				if(string6.charAt(i) != '+' && !middle) { //if before '+' sign it is part of date
					systemDate += string6.charAt(i);
				}
				if (string6.charAt(i) != '+' && middle) { //if after '+' sign it is part of time
					time += string6.charAt(i);
				}
				if(string6.charAt(i) == '+') { //if reaches divider of date and time
					middle = true;
				}
			}
			
			CarShopApplication.setLocalDate(systemDate);
			CarShopApplication.setLocalTime(time + ":00");
			CarShopApplication.setDate(Date.valueOf(systemDate));
			CarShopApplication.setTime(Time.valueOf(time+":00"));
	    	
	    	CarShopController.MakeAppointment(User.getWithUsername(string), BookableService.getWithName(string2), timeslots, services);
	    	Appointment a = CarShopApplication.getCarShop().getAppointments().get(CarShopApplication.getCarShop().getAppointments().size()-1);
	
	    	for(ServiceBooking s: a.getServiceBookings()) {
	    		System.err.println(s.getService().getName());
	    	}
	    	
	    }
	    catch(Exception e) {
	    	errorMessage = e.getMessage();
	    }
	}

	@When("{string} attempts to add the optional service {string} to the service combo with start time {string} in the appointment at {string}")
	public void attempts_to_add_the_optional_service_to_the_service_combo_with_start_time_in_the_appointment_at(String string, String string2, String string3, String string4) {
		// Ramin
    	Customer currentUser = (Customer)User.getWithUsername(string);
    	List<Appointment> customerApps = currentUser.getAppointments();
    	Appointment mostRecentApp = customerApps.get(customerApps.size()-1);
    	List<BookableService> services = new ArrayList<BookableService>();
    	List<TimeSlot> timeslots = new ArrayList<TimeSlot>();
	    for(int i = 0; i<mostRecentApp.getServiceBookings().size(); i++) {
	    	services.add(mostRecentApp.getServiceBooking(i).getService());
	    	timeslots.add(mostRecentApp.getServiceBooking(i).getTimeSlot());
	    }
	    
	    try {
			String[] systemDateTimeElements = string4.split("\\+");
	    	CarShopApplication.setLocalDate(systemDateTimeElements[0]);
	    	CarShopApplication.setLocalTime(systemDateTimeElements[1]+":00");
	    	
	    	Service newService = (Service)Service.getWithName(string2);
	    	
    		Time startingTime = Time.valueOf(CarShopController.StringToTime(string3).toLocalTime());
    		Time endingTime = Time.valueOf(startingTime.toLocalTime().plusMinutes(((Service)newService).getDuration()));
    		TimeSlot newTimeSlot = new TimeSlot(mostRecentApp.getServiceBookings().get(0).getTimeSlot().getStartDate(), startingTime, mostRecentApp.getServiceBookings().get(0).getTimeSlot().getEndDate(), endingTime, carshop);
    		services.add(newService);
    		timeslots.add(newTimeSlot);
	    	CarShopController.updateAppointmentServiceCombo(User.getWithUsername(string), mostRecentApp, services, timeslots);	    	
	    }
	    catch(Exception e) {
	    	errorMessage = e.getMessage();
	    }
	}

	@Then("the service combo in the appointment shall be {string}")
	public void the_service_combo_in_the_appointment_shall_be(String string) {
		//Massimo
		Appointment appointment = CarShopApplication.getCarShop().getAppointment(CarShopApplication.getCarShop().numberOfAppointments()-1);
		assertEquals(string,appointment.getBookableService().getName());
	}
	
	@Then("the service combo shall have {string} selected services")
	public void the_service_combo_shall_have_selected_services(String string) {
		// Massimo
		String expected = "";
		Appointment appointment = CarShopApplication.getCarShop().getAppointment(CarShopApplication.getCarShop().numberOfAppointments()-1);
		List<ServiceBooking> sBookings = appointment.getServiceBookings(); 
		
		for (ServiceBooking s : sBookings) {
			if(expected.trim().length() == 0) {	
			expected += s.getService().getName();
			}
			else {
				expected += "," + s.getService().getName();
			}
		}
		assertEquals(string, expected);
	}

	@When("{string} attempts to update the date to {string} and start time to {string} at {string}")
	public void attempts_to_update_the_date_to_and_start_time_to_at(String string, String string2, String string3, String string4) {
		// Massimo
		try {
			CarShopController.updateAppointmentTime(string, string2, string3, string4);
		}
			catch(Exception e){
				errorMessage = e.getMessage();
		}
	}

	@When("the owner starts the appointment at {string}")
	public void the_owner_starts_the_appointment_at(String string) {
		// Massimo
		Appointment appointment = CarShopApplication.getCarShop().getAppointment(CarShopApplication.getCarShop().getAppointments().size() - 1);
		try {
			CarShopController.startAppointment(CarShopApplication.getCarShop().getOwner(), string, appointment);
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}
	}

	@When("the owner ends the appointment at {string}")
	public void the_owner_ends_the_appointment_at(String string) {
		// Tiff
		List<Appointment> appointment = CarShopApplication.getCarShop().getAppointments();
	    try {
	    	CarShopController.endAppointment(CarShopApplication.getCarShop().getOwner(), string, appointment.get(appointment.size() - 1));
	    } catch (Exception e) {
	    	errorMessage = e.getMessage();
	    }
	}

	@Then("the appointment shall be in progress")
	public void the_appointment_shall_be_in_progress() {
		// Tiff
		List<Appointment> appointment = CarShopApplication.getCarShop().getAppointments();
		assertEquals(AppointmentStatus.InProgress, appointment.get(appointment.size() - 1).getAppointmentStatus());
	}

	@When("the owner attempts to register a no-show for the appointment at {string}")
	public void the_owner_attempts_to_register_a_no_show_for_the_appointment_at(String string) {
		// Tiff
		List<Appointment> appointment = CarShopApplication.getCarShop().getAppointments();
		try {
			CarShopController.noShow(CarShopApplication.getCarShop().getOwner(), string, appointment.get(appointment.size() - 1));
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}
	}

	@When("the owner attempts to end the appointment at {string}")
	public void the_owner_attempts_to_end_the_appointment_at(String string) {
		// Tiff
		List<Appointment> appointment = CarShopApplication.getCarShop().getAppointments();
	    try {
	    	CarShopController.endAppointment(carshop.getOwner(),string,appointment.get(appointment.size() - 1));
	    } catch (Exception e) {
	    	errorMessage = e.getMessage();
	    }
	}
	
	@After
	public void tearDown() {
		carshop.delete();
		errorMessage = null;
	}
}