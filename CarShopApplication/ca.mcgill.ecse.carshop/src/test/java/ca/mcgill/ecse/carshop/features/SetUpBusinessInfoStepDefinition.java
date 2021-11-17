package ca.mcgill.ecse.carshop.features;

import ca.mcgill.ecse.carshop.controller.CarShopController;
import ca.mcgill.ecse.carshop.controller.InvalidInputException;
import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.model.*;
import java.util.List;
import java.util.ArrayList;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.lang.String;
import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetUpBusinessInfoStepDefinition {
	private CarShop carshop = CommonStepDefinitions.carshop;
	public static String errorMessage = ""; //will be either empty or the error message string
	public static String resultError = "not be"; //will be either "not be" or "be"
	public static String result = "be"; //will be either "be" or "not be"
	public List<String> viewedInfo = new ArrayList<String>(); 
	
	/**
	 * @author Ramin Akhavan-Sarraf
	 * To reset after each scenario
	 */
	@After
	public void tearDown() {
		//the following restores everything back before each scenario
		carshop.delete();
		errorMessage = "";
		resultError = "not be";
		result = "be";
		CommonStepDefinitions.Error = null;
		CommonStepDefinitions.carshop = CarShopApplication.getCarShop();
	}
	
	/**
	 * @author group 7
	 * Sets carshop using application method
	 */
//	@Given("a Carshop system exists")
//	public void a_carshop_system_exists() {
//		carshop = CarShopApplication.getCarShop();
//	}
	
	/**
	 * @author Ramin Akhavan-Sarraf
	 * Creates owner in the system
	 * @param string: String - username
	 * @param string2: String - password
	 */
//	@Given("an owner account exists in the system with username {string} and password {string}")
//	public void an_owner_account_exists_in_the_system_with_username_and_password(String string, String string2) {
//		if (carshop.getOwner() == null) {
//			new Owner(string, string2, carshop);
//		}
//	}
	
	/**
	 * @author Ramin Akhavan-Sarraf
	 * Creates the customers in the system
	 * @param dataTable: io.cucumber.datatable.DataTable dataTable - table including customer info
	 */
//	@Given("the following customers exist in the system:")
//	public void the_following_customers_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
//		List<List<String>> values = dataTable.asLists();
//
//		for (int i = 1; i < values.size(); i++) {
//			carshop.addCustomer(values.get(i).get(0), values.get(i).get(1));
//		}
//	}
	/**
	 * @author Ramin Akhavan-Sarraf
	 * Technicians are created and become part of the carshop
	 * @param dataTable
	 */
//	@Given("the following technicians exist in the system:")
//	public void the_following_technicians_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
//		List<List<String>> valueMaps = dataTable.asLists();
//		
//		for (int i = 1; i < valueMaps.size(); i++) {
//			carshop.addTechnician(valueMaps.get(i).get(0), valueMaps.get(i).get(1),
//					CarShopController.StringToTechnicianType(valueMaps.get(i).get(2)));
//			
//		}
//	}
	
	/**
	 * @author Ramin Akhavan-Sarraf
	 * All technicians are made to have their own garage
	 */
//	@Given("each technician has their own garage")
//	public void each_technician_has_their_own_garage() throws Exception {
//		List<Technician> technicians = carshop.getTechnicians();
//		//goes through list of technicians
//		for(int i = 0; i < technicians.size(); i++) {
//			carshop.addGarage(technicians.get(i));
//		}
//	}
	
	/**
	 * @author Ramin Akhavan-Sarraf
	 * Makes sure there is no business in the carshop
	 */
	@Given("no business exists")
	public void no_business_exists() {
		if(carshop.getBusiness() != null) {
			carshop.getBusiness().delete();;
		}
	}
	
	/**
	 * @author Ramin Akhavan-Sarraf
	 * To set system date and time
	 * @param string: String - system time and date as one string
	 */
//	@Given("the system's time and date is {string}")
//	public void the_system_s_time_and_date_is(String string) {
//		String date = "";
//		String time = "";
//		boolean middle = false;
//		//the following goes through the string to divide into date and time strings
//		for(int i = 0; i<string.length(); i++) {
//			if(string.charAt(i) != '+' && !middle) { //if before '+' sign it is part of date
//				date += string.charAt(i);
//			}
//			if (string.charAt(i) != '+' && middle) { //if after '+' sign it is part of time
//				time += string.charAt(i);
//			}
//			if(string.charAt(i) == '+') { //if reaches divider of date and time
//				middle = true;
//			}
//		}
//		
//		//creates date and time objects using controller methods
//		Date systemDate = CarShopController.StringToDate(date);
//		Time systemTime = CarShopController.StringToTime(time);
//		
//		//sets the carshop's time and date
//		CarShopApplication.setDate(systemDate);
//		CarShopApplication.setTime(systemTime);
//	
//	}
	/**
	 * @author Ramin Akhavan-Sarraf
	 * @param string: String - used to login a user with this username
	 */
//	@Given("the user is logged in to an account with username {string}")
//	public void the_user_is_logged_in_to_an_account_with_username(String string) {
//	    try {
//			CarShopController.login(string, User.getWithUsername(string).getPassword());
//		} catch (InvalidInputException e) {
//			e.printStackTrace();
//		}
//	}
	/**
	 * @author Ramin Akhavan-Sarraf
	 * The user trying to set up the business info 
	 * @param string: String - name
	 * @param string2: String- address
	 * @param string3: String - phone number
	 * @param string4: String - email
	 */
	@When("the user tries to set up the business information with new {string} and {string} and {string} and {string}")
	public void the_user_tries_to_set_up_the_business_information_with_new_and_and_and(String string, String string2, String string3, String string4) throws Exception {
	    try{
	    	//calls controller method to set up business info
	    	CarShopController.setUpBusinessInfo(string, string2, string3, string4);
	    }
	    catch (Exception e) {
	    	//sets error message and corresponding strings
	    	errorMessage = e.getMessage();
	    	CommonStepDefinitions.Error = e.getMessage();
	    	resultError = "be";
	    	result = "not be";
	    }
	}
	/**
	 * @author Ramin Akhavan-Sarraf
	 * Checks to see if new business has been added with proper information
	 * @param string: String - name
	 * @param string2: String - address
	 * @param string3: String - phone number
	 * @param string4: String - email
	 * @param string5: String - result
	 */
	@Then("a new business with new {string} and {string} and {string} and {string} shall {string} created")
	public void a_new_business_with_new_and_and_and_shall_created(String string, String string2, String string3, String string4, String string5) {
		if(carshop.getBusiness() != null) { //if there is a business
			assertEquals(string, carshop.getBusiness().getName());
			assertEquals(string2 ,carshop.getBusiness().getAddress());
			assertEquals(string3, carshop.getBusiness().getPhoneNumber());
			assertEquals(string4, carshop.getBusiness().getEmail());
			assertEquals(string5, result);
		}
	}
	/**
	 * Ramin Akhavan-Sarraf
	 * Checks to see if there is an error message and if that message/result is correct
	 * @param string: String - error message
	 * @param string2: String - result error
	 */
	@Then("an error message {string} shall {string} raised")
	public void an_error_message_shall_raised(String string, String string2) {
		assertEquals(string, errorMessage);
		assertEquals(string2, resultError);
	}
	
	
	/**
	 * @author Ramin Akhavan-Sarraf
	 * Set's up a business with the appropriate info
	 * @param dataTable: io.cucumber.datatable.DataTable - table including business info
	 */
//	@Given("a business exists with the following information:")
//	public void a_business_exists_with_the_following_information(io.cucumber.datatable.DataTable dataTable) {
//		List<List<String>> businessInfo = dataTable.asLists();
//		Business business = new Business(businessInfo.get(1).get(0), businessInfo.get(1).get(1), businessInfo.get(1).get(2), businessInfo.get(1).get(3), carshop);
//	}
	
	/**
	 * @author Ramin Akhavan-Sarraf
	 * A business hour is created and set up
	 * @param string: String - day
	 * @param string2: String - start time
	 * @param string3: String - end time
	 */
	@Given("the business has a business hour on {string} with start time {string} and end time {string}")
	public void the_business_has_a_business_hour_on_with_start_time_and_end_time(String string, String string2, String string3) {
		
		String[] startTimeElements = string2.split(":");
		String[] endTimeElements = string3.split(":");
		Time startTime = new Time(Integer.valueOf(startTimeElements[0]), Integer.valueOf(startTimeElements[1]), 0);
		Time endTime = new Time(Integer.valueOf(endTimeElements[0]), Integer.valueOf(endTimeElements[1]), 0);
		BusinessHour businessHour = new BusinessHour(CarShopController.parseStringToDayOfWeek(string),startTime, endTime, carshop);
		carshop.getBusiness().addBusinessHour(businessHour);
		
	}
	/**
	 * @author Ramin Akhavan-Sarraf
	 * controller method is used to satisfy user's request of adding a new business hour
	 * @param string: String - day
	 * @param string2: String - start time
	 * @param string3: String - end time
	 */
	@When("the user tries to add a new business hour on {string} with start time {string} and end time {string}")
	public void the_user_tries_to_add_a_new_business_hour_on_with_start_time_and_end_time(String string, String string2, String string3) {
		try {
			//first, the start and end Time objects are created to be passed as inputs
			String[] startTimeElements = string2.split(":");
			String[] endTimeElements = string3.split(":");
			Time startTime = new Time(Integer.valueOf(startTimeElements[0]), Integer.valueOf(startTimeElements[1]), 0);
			Time endTime = new Time(Integer.valueOf(endTimeElements[0]), Integer.valueOf(endTimeElements[1]), 0);
			//calls appropriate controller method
			CarShopController.addBusinessHours(string, startTime, endTime);
			
		}
		catch(Exception e) {
			//sets error message and corresponding strings
	    	System.err.println(e.getMessage());
	    	errorMessage = e.getMessage();
	    	result = "not be";
	    	resultError = "be";
		}
	}
	
	/**
	 * @author Ramin Akhavan-Sarraf
	 * Checks to see if business hour was/was not created
	 * @param string: String - result
	 */
	@Then("a new business hour shall {string} created")
	public void a_new_business_hour_shall_created(String string) {
		assertEquals(string, result);
	}

	/**
	 * @author Ramin Akhavan-Sarraf
	 * Controller method is called to satisfy user's request of getting business info
	 */
	@When("the user tries to access the business information")
	public void the_user_tries_to_access_the_business_information() {
		try {
			//calls controller method and stores businessInfo as list of strings
			viewedInfo = CarShopController.viewBusinessInfo();
		}
		catch(Exception e) {
			//sets error message and corresponding strings
			errorMessage = e.getMessage();
			result = "not be";
			resultError = "be";
		}
	}
	/**
	 * @author Ramin Akhavan-Sarraf
	 * Checks to make sure the information given to the user was given and was correct
	 * @param string: String - name
	 * @param string2: String - address
	 * @param string3: String - phone number
	 * @param string4: String - email
	 */
	@Then("the {string} and {string} and {string} and {string} shall be provided to the user")
	public void the_and_and_and_shall_be_provided_to_the_user(String string, String string2, String string3, String string4) {
		assertEquals(string, viewedInfo.get(0));
		assertEquals(string2, viewedInfo.get(1));
		assertEquals(string3, viewedInfo.get(2));
		assertEquals(string4, viewedInfo.get(3));
	}
	
	/**
	 * @author Ramin Akhavan-Sarraf
	 * Adds the given vacation and/or holiday time slots to set up scenario
	 * @param string: String - type
	 * @param string2: String - start date
	 * @param string3: String - start time
	 * @param string4: String - end date
	 * @param string5: String - end time
	 */
	@Given("a {string} time slot exists with start time {string} at {string} and end time {string} at {string}")
	public void a_time_slot_exists_with_start_time_at_and_end_time_at(String string, String string2, String string3, String string4, String string5) {
		//creates time slot object with help of controller helper methods
		TimeSlot timeSlot = new TimeSlot(java.sql.Date.valueOf(string2), CarShopController.StringToTime(string3), java.sql.Date.valueOf(string4), CarShopController.StringToTime(string5), carshop);
		
		//checks to see if it is a holiday or vacation
		if(string.equalsIgnoreCase("vacation")) {
			carshop.getBusiness().addVacation(timeSlot);
		}
		if(string.equalsIgnoreCase("holiday")) {
			carshop.getBusiness().addHoliday(timeSlot);
		}
	}
	
	/**
	 * @author Ramin Akhavan-Sarraf
	 * Tries to add time slot when user requests to do so
	 * @param string: String - type
	 * @param string2: String - start date
	 * @param string3: String - start time
	 * @param string4: String - end date
	 * @param string5: String - end time
	 */
	@When("the user tries to add a new {string} with start date {string} at {string} and end date {string} at {string}")
	public void the_user_tries_to_add_a_new_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5) {
		try {
			//calls controller method
			CarShopController.addTimeSlot(string, java.sql.Date.valueOf(string2), CarShopController.StringToTime(string3), java.sql.Date.valueOf(string4), CarShopController.StringToTime(string5));
		}
		catch(Exception e){
			//sets error message and corresponding strings
			errorMessage = e.getMessage();
			System.err.println(errorMessage);
			result = "not be";
			resultError = "be";
		}
	}
	/**
	 * @author Ramin Akhavan-Sarraf
	 * Makes sure that the time slot was added properly
	 * @param string: String - type
	 * @param string2: String- result
	 * @param string3: String - start date
	 * @param string4: String - start time
	 * @param string5: String - end date
	 * @param string6: String - end time
	 */
	@Then("a new {string} shall {string} be added with start date {string} at {string} and end date {string} at {string}")
	public void a_new_shall_be_added_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5, String string6) {
		assertEquals(string2, result);
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		//makes sure to either check vacation list or holiday list
		if(string.equalsIgnoreCase("vacation")){
			timeSlots = carshop.getBusiness().getVacations();
		}
		if(string.equalsIgnoreCase("holiday")) {
			timeSlots = carshop.getBusiness().getHolidays();
		}
		//checks most recently added time slot
		TimeSlot recentTimeSlot = timeSlots.get(timeSlots.size()-1);
		//checks whether each of these conditions are true
		boolean startDate = recentTimeSlot.getStartDate().compareTo(java.sql.Date.valueOf(string3)) == 0;
		boolean startTime = recentTimeSlot.getStartTime().compareTo(CarShopController.StringToTime(string4)) == 0;
		boolean endDate = recentTimeSlot.getEndDate().compareTo(java.sql.Date.valueOf(string5)) == 0;
		boolean endTime = recentTimeSlot.getEndTime().compareTo(CarShopController.StringToTime(string6)) == 0;
		//if there was an error
		if(resultError.equalsIgnoreCase("be")) {
			//verifies that the time slot was not added, as it does not match the last time slot
			assertFalse(startDate && startTime && endDate && endTime);
		}
		//if there was no error
		else {
			//makes sure the time slot was added by checking the info
			assertTrue(startDate);
			assertTrue(startTime);
			assertTrue(endDate);
			assertTrue(endTime);
		}
	}
}
