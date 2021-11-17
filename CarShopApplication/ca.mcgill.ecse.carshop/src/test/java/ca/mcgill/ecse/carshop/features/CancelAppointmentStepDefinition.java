package ca.mcgill.ecse.carshop.features;

import ca.mcgill.ecse.carshop.controller.CarShopController;
import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.model.*;
import ca.mcgill.ecse.carshop.model.Technician.TechnicianType;
import ca.mcgill.ecse.carshop.model.BusinessHour.DayOfWeek;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * 
 * @author vy-khahuynh
 * Step definition for CancelAppointment
 * Supposed to pass all tests 
 */
public class CancelAppointmentStepDefinition {
	private CarShop carShop = CommonStepDefinitions.carshop;


	
	@When("{string} attempts to cancel their {string} appointment on {string} at {string}")
	public void attempts_to_cancel_their_appointment_on_at(String string,String string2,String string3,String string4) throws Exception {
		List<BookableService> services=carShop.getBookableServices();
		
		User user=CarShopApplication.getRecentlyLogedinUser();
		BookableService s=null;
		Appointment app=null;
		
		// Finds the user
		for(BookableService bs:services) {
				if(bs.getName().equals(string2)){
					s=bs;
				}
	}
		// Finds the appointment
		List<Appointment> appointments=s.getAppointments();
		for(Appointment a:appointments) {
			if(a.getBookableService().getName().equals(string2)){
				for(int i=0;i<a.getServiceBookings().size();i++) {
					if(a.getServiceBooking(i).getTimeSlot().getStartDate().equals(java.sql.Date.valueOf(string3))) {
						if(a.getServiceBooking(i).getTimeSlot().getStartTime().equals(java.sql.Time.valueOf(string4+":00"))) {
							app=a;
							
						}
					}
				}
			}
		}
		// Number of Appointments before the CancelAppointment() method is called
		MakeAppointmentStepDefinition.numOfAppointments=carShop.numberOfAppointments();
		try {
		CarShopController.CancelAppointment(user, app);
		}
		catch(Exception e){
			MakeAppointmentStepDefinition.errorMessage=e.getMessage();
		}
	}
	

		@Then("{string}'s {string} appointment on {string} at {string} shall be removed from the system")
		public void s_appointment_on_at_shall_be_removed_from_the_system(String string, String string2, String string3, String string4) throws Exception {
			String nameExpected=string;
			Boolean nameA=false;
			Boolean nameE=true;
			String serviceExpected=string2;
			Boolean serviceA = false;
			Boolean serviceE=true;
			String appDateExpected=string3;
			Boolean dateA=false;
			Boolean dateE=true;
			String appStartExpected=string4;
			Boolean timeA=false;
			Boolean timeE=true;
			
			List<Customer> customers=carShop.getCustomers();
			List<BookableService> services=carShop.getBookableServices();
			
			Customer c1=null;
			
			// List of customers
			for(Customer c:customers) {
				if(c.getUsername().equals(nameExpected)) {
					c1=c;
					nameA=true; // Selects customer1
				}
			}
			
			List<Appointment> appointments=c1.getAppointments();
			if(appointments.isEmpty() && MakeAppointmentStepDefinition.numOfAppointments!=0) { 
				//If no appointments, appointment successfully deleted
				timeA=true;
				serviceA=true; 
				dateA=true; 
			}
			else if(MakeAppointmentStepDefinition.numOfAppointments==0) { //If initiallu zero
				throw new Exception("Can't delete a non-existing appointment");
			}
			
			
			else {
			for(Appointment a:appointments) {
				if(a.getBookableService().getName().equals(serviceExpected)){
					if(a.getServiceBooking(0).getTimeSlot().getStartDate().equals(java.sql.Date.valueOf(appDateExpected))) {
						if(a.getServiceBooking(0).getTimeSlot().getStartTime().equals(java.sql.Time.valueOf(appStartExpected+":00"))) {
							// If all those match, meaning appointment still does exist
							timeA=false;
							serviceA=false;
							timeA=false;
						}
						else {
							timeA=true;
							serviceA=true;
							dateA=true;
						}
					}
					else {
						timeA=true;
					serviceA=true;
					dateA=true;
					}
				}
				else {
					timeA=true;
				serviceA=true;
				dateA=true;
				}
			}
			}
			
			// All must equal true, meaning the appointment doesn't exist anymore
			Assert.assertEquals(nameE, nameA);
			Assert.assertEquals(serviceE,serviceA);
			Assert.assertEquals(dateE,dateA);
			Assert.assertEquals(timeE,timeA);
		}


		@Then("there shall be {int} less appointment in the system")
		public void there_shall_be_less_appointment_in_the_system(Integer int1) {
			int numExpected=int1;
			int numActual=Math.abs(carShop.numberOfAppointments()-MakeAppointmentStepDefinition.numOfAppointments);
			Assert.assertEquals(numExpected,numActual);
			}	




	
	@Then("{string} shall have a {string} appointment on {string} at {string} with the following properties")
	public void shall_have_a_appointment_on_at_with_the_following_properties(String string,String string2,String string3, String string4,io.cucumber.datatable.DataTable dataTable) {
		String nameExpected=string;
		String nameActual=null;
		String serviceExpected=string2;
		String serviceActual=null;
		String appDateExpected=string3;
		String appDateActual=null;
		String appStartExpected=string4;
		String appStartActual=null;
		
		
		
		// Find name for service combo
		if(string2.contains("basic")) {
			ServiceCombo sc=(ServiceCombo) ServiceCombo.getWithName(string2);
			serviceActual=sc.getName();
		}
		// Find name for the service
		else {
			Service s=null;
			List<BookableService> bookser=carShop.getBookableServices();
			for(BookableService bs:bookser) {
				if(bs.getName().equals(string2)) {
					s=(Service)bs;
					serviceActual=s.getName();
				}
			}
		}
		
		// Get the most recent appointment
		Appointment a=carShop.getAppointment(carShop.getAppointments().size()-1);
		
		// Check that it is correct customer
		User customer=a.getCustomer();
		nameActual=customer.getUsername();
		
		// Check that it is correct date
		java.sql.Date date=a.getServiceBooking(0).getTimeSlot().getStartDate();
		appDateActual=date.toLocalDate().toString();
		
		// Check that it is correct time
		java.sql.Time startTime=a.getServiceBooking(0).getTimeSlot().getStartTime();
		appStartActual=startTime.toLocalTime().toString();
		
		String tempStartActual="";
		if(appStartActual.charAt(0)=='0') {
			for(int i=1;i<appStartActual.length();i++) {
				tempStartActual+=appStartActual.charAt(i);
			}
		}
		Assert.assertEquals(nameExpected, nameActual);
		Assert.assertEquals(serviceExpected,serviceActual);
		Assert.assertEquals(appDateExpected,appDateActual);
		Assert.assertEquals(appStartExpected,tempStartActual);
	}

		@When("{string} attempts to cancel {string}'s {string} appointment on {string} at {string}")
		public void attempts_to_cancel_s_appointment_on_at(String string, String string2, String string3, String string4, String string5) {
			List<BookableService> services=carShop.getBookableServices();
			User user=null;
			
			if(string.equals("owner")) {
				user=carShop.getOwner();
			}
			else {
				user=User.getWithUsername(string);
			}
			
			BookableService s=null;
			Appointment app=null;
			
			//Finds the correct service combo
			for(BookableService bs:services) {
				if(bs instanceof ServiceCombo && ((ServiceCombo)bs).getName().equals(string3) ) {
					s=bs;
				}
				else if(bs instanceof Service && ((Service)bs).getName().equals(string3)) {
					s=bs;
				}
				else;
			}
			// Finds the correct appointment
			List<Appointment> appointments=s.getAppointments();
			for(Appointment a:appointments) {
					for(int i=0;i<a.getServiceBookings().size();i++) {
						if(a.getServiceBooking(i).getTimeSlot().getStartDate().equals(java.sql.Date.valueOf(string4))) {
							if(a.getServiceBooking(i).getTimeSlot().getStartTime().equals(java.sql.Time.valueOf(string5+":00"))) {
								app=a;
							}
						}
					}
				}
			// Number of Appointments before the CancelAppointment() method is called
			MakeAppointmentStepDefinition.numOfAppointments=carShop.numberOfAppointments();
			try {
			CarShopController.CancelAppointment(user, app);
			}
			catch(Exception e){
				MakeAppointmentStepDefinition.errorMessage=e.getMessage();
			}
		}
		
		@After
		public void tearDown() {
			carShop.delete();
			CommonStepDefinitions.Error = null;
			CommonStepDefinitions.carshop = CarShopApplication.getCarShop();
		}
}

