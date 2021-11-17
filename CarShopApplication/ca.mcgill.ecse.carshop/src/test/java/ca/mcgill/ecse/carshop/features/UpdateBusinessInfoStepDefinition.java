package ca.mcgill.ecse.carshop.features;

import ca.mcgill.ecse.carshop.controller.CarShopController;
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

public class UpdateBusinessInfoStepDefinition {
	
	private CarShop carshop = CommonStepDefinitions.carshop;
	public String exist = "not"; //can be "" or "not"
	
	/**
	 * @author Ramin Akhavan-Sarraf
	 * Everything is reset before each scenario
	 */
	@After
	public void tearDown() {
		carshop = CarShopApplication.getCarShop();
		SetUpBusinessInfoStepDefinition.errorMessage = "";
		SetUpBusinessInfoStepDefinition.resultError = "not be";
		SetUpBusinessInfoStepDefinition.result = "be";
		CommonStepDefinitions.Error = null;
		CommonStepDefinitions.carshop = CarShopApplication.getCarShop();
	}
	
	/**
	 * @author Ramin Akhavan-Sarraf
	 * Tries to update the business info upon user's request
	 * @param string: String - name
	 * @param string2: String - address
	 * @param string3: String - phone number
	 * @param string4: String - email
	 */
	@When("the user tries to update the business information with new {string} and {string} and {string} and {string}")
	public void the_user_tries_to_update_the_business_information_with_new_and_and_and(String string, String string2, String string3, String string4) {
		try {
			//calls controller method to perform action
			CarShopController.updateBusinessInfo(string, string2, string3, string4);
		}
		catch(Exception e) {
			//error messages are set up if an error is thrown
			SetUpBusinessInfoStepDefinition.errorMessage = e.getMessage();
			CommonStepDefinitions.Error = e.getMessage();
			SetUpBusinessInfoStepDefinition.result = "not be";
			SetUpBusinessInfoStepDefinition.resultError ="be";
		}
	}

	/**
	 * @author Ramin Akhavan-Sarraf
	 * Checks to make sure that the business info was/was not updated
	 * @param string: String - result
	 * @param string2: String - name
	 * @param string3: String - address
	 * @param string4: String - phone number
	 * @param string5: String - email
	 */
	@Then("the business information shall {string} updated with new {string} and {string} and {string} and {string}")
	public void the_business_information_shall_updated_with_new_and_and_and(String string, String string2, String string3, String string4, String string5) {
		//if the business was set up, it check if it was properly set up
		if(SetUpBusinessInfoStepDefinition.resultError.equalsIgnoreCase("not be")) {
			assertEquals(string2, carshop.getBusiness().getName());
			assertEquals(string3 ,carshop.getBusiness().getAddress());
			assertEquals(string4, carshop.getBusiness().getPhoneNumber());
			assertEquals(string5, carshop.getBusiness().getEmail());
		}
		assertEquals(string, SetUpBusinessInfoStepDefinition.result);
	}

	/**
	 * @author Ramin Akhavan-Sarraf
	 * Tries to add business hour upon user's request
	 * @param string: String - day
	 * @param string2: String - start time
	 * @param string3: String - day
	 * @param string4: String - new start time
	 * @param string5: String - new end time
	 */
	@When("the user tries to change the business hour {string} at {string} to be on {string} starting at {string} and ending at {string}")
	public void the_user_tries_to_change_the_business_hour_at_to_be_on_starting_at_and_ending_at(String string, String string2, String string3, String string4, String string5) {
		try {
			//calls controller method to perform action
			CarShopController.updateBusinessHours(string, CarShopController.StringToTime(string2), string3, CarShopController.StringToTime(string4), CarShopController.StringToTime(string5));
		}
		
		catch(Exception e) {
			//error messages are set up if an error is thrown
			SetUpBusinessInfoStepDefinition.errorMessage = e.getMessage();
			SetUpBusinessInfoStepDefinition.resultError = "be";
			SetUpBusinessInfoStepDefinition.result = "not be";
		}
		
	}

	/**
	 * @author Ramin Akhavan-Sarraf
	 * Checks to make sure that the business hour was/was not updated
	 * @param string: String - result
	 */
	@Then("the business hour shall {string} be updated")
	public void the_business_hour_shall_be_updated(String string) {
		assertEquals(string, SetUpBusinessInfoStepDefinition.result);
	}
	
	/**
	 * @author Ramin Akhavan-Sarraf
	 * Tries to remove business hour upon user's request
	 * @param string: String - day
	 * @param string2: String - start time
	 */
	@When("the user tries to remove the business hour starting {string} at {string}")
	public void the_user_tries_to_remove_the_business_hour_starting_at(String string, String string2) {
		try {
			//calls controller method to perform action 
			CarShopController.removeBusinessHours(string, CarShopController.StringToTime(string2));
		}
		catch (Exception e){
			//error messages are set up if an error is thrown
			SetUpBusinessInfoStepDefinition.errorMessage = e.getMessage();
	    	exist = "";
		}
	}
	
	/**
	 * @author Ramin Akhavan-Sarraf
	 * Checks to see if the business hour still does or does not exist
	 * @param string: String - day
	 * @param string2: String - start time
	 * @param string3: String - exist (either "" or "not")
	 */
	@Then("the business hour starting {string} at {string} shall {string} exist")
	public void the_business_hour_starting_at_shall_exist(String string, String string2, String string3) {
		assertEquals(string3, exist); //checks to see if the business hour exists
		boolean found = false;
		//goes through all business hours to see if it is in the list
		for(BusinessHour b: carshop.getBusiness().getBusinessHours()) {
			//checks to see if business hour matches the inputed one
			if(b.getDayOfWeek().toString().equalsIgnoreCase(string) && b.getStartTime().compareTo(CarShopController.StringToTime(string2)) == 0) {
				found = true;
			}
		}
		if(string3.equalsIgnoreCase("not")) {
			assertEquals(false, found);
		}
		else {
			assertEquals(true, found);
		}
	}
	
	/**
	 * @author Ramin Akhavan-Sarraf
	 * Tries to change the time slot of the certain type upon user's request
	 * @param string: String - type
	 * @param string2: String - current start date
	 * @param string3: String - current start time
	 * @param string4: String - new start date
	 * @param string5: String - new start time
	 * @param string6: String - new end date
	 * @param string7: String - new start time
	 */
	@When("the user tries to change the {string} on {string} at {string} to be with start date {string} at {string} and end date {string} at {string}")
	public void the_user_tries_to_change_the_on_at_to_be_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5, String string6, String string7) {
		try {
			//calls controller method to perform action
			CarShopController.updateTimeSlot(string, java.sql.Date.valueOf(string2), CarShopController.StringToTime(string3), java.sql.Date.valueOf(string4), CarShopController.StringToTime(string5), java.sql.Date.valueOf(string6), CarShopController.StringToTime(string7));
		}
		catch (Exception e) {
			//error messages are set up if an error is thrown
			SetUpBusinessInfoStepDefinition.errorMessage = e.getMessage();
			SetUpBusinessInfoStepDefinition.resultError = "be";
			SetUpBusinessInfoStepDefinition.result = "not be";
		}
	}

	/**
	 * @author Ramin Akhavan-Sarraf
	 * Checks to see if the time slot was or was not updated depending on the result
	 * @param string: String - type
	 * @param string2: String - result
	 * @param string3: String - new start date
	 * @param string4: String - new start time
	 * @param string5: String - new end date
	 * @param string6: String - new start time
	 */
	@Then("the {string} shall {string} updated with start date {string} at {string} and end date {string} at {string}")
	public void the_shall_updated_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5, String string6) {
		assertEquals(string2, SetUpBusinessInfoStepDefinition.result);
		boolean found = false;
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		//depending on if it is a vacation or holiday, it retrieves the appropriate time slots
		if(string.equalsIgnoreCase("vacation")) {
			timeSlots = carshop.getBusiness().getVacations();
		}
		if(string.equalsIgnoreCase("holiday")) {
			timeSlots = carshop.getBusiness().getHolidays();
		}
		//goes through all time slots. If one of the time slots matches the same inputed info, it has been updated
		for(TimeSlot t: timeSlots) {
			//condition to check all info of time slots being compared
			if(t.getStartDate().equals(java.sql.Date.valueOf(string3)) && t.getStartTime().compareTo(CarShopController.StringToTime(string4)) == 0 && t.getEndDate().equals(java.sql.Date.valueOf(string5)) && t.getEndTime().compareTo(CarShopController.StringToTime(string6)) == 0) {
				found = true;
			}
		}
		//if it is not suppose to be updated, it should not have been found
		if(string2.equalsIgnoreCase("not be")) {
			assertFalse(found);
		}
		//if it should have been updated, it should have been found
		else {
			assertTrue(found);
		}
		
	}
	
	/**
	 * @author Ramin Akhavan-Sarraf
	 * Tries to remove an existing time slot upon user's request
	 * @param string: String - type
	 * @param string2: String - start date
	 * @param string3: String - start time
	 * @param string4: String - end date
	 * @param string5: String - end time
	 */
	@When("the user tries to remove an existing {string} with start date {string} at {string} and end date {string} at {string}")
	public void the_user_tries_to_remove_an_existing_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5) {
		try {
			//calls controller method to perform action
			CarShopController.removeTimeSlot(string, java.sql.Date.valueOf(string2),  CarShopController.StringToTime(string3), java.sql.Date.valueOf(string4), CarShopController.StringToTime(string5));
		}
		catch(Exception e) {
			//error messages are set up if an error is thrown
			SetUpBusinessInfoStepDefinition.errorMessage = e.getMessage();
			CommonStepDefinitions.Error = e.getMessage();
			exist = "";
		}
		
	}
	
	/**
	 * @author Ramin Akhavan-Sarraf
	 * Checks to see if the time slot still does or does not exist
	 * @param string: String - type
	 * @param string2: String - start date
	 * @param string3: String - start time
	 * @param string4: String - end date
	 * @param string5: String - end time
	 */
	@Then("the {string} with start date {string} at {string} shall {string} exist")
	public void the_with_start_date_at_shall_exist(String string, String string2, String string3, String string4) {
		assertEquals(string4, exist);
		boolean found = false;
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		//depending on if it is a vacation or holiday, the appropriate list is retrieved
		if(string.equalsIgnoreCase("vacation")) {
			timeSlots = carshop.getBusiness().getVacations();
		}
		if(string.equalsIgnoreCase("holiday")) {
			timeSlots = carshop.getBusiness().getHolidays();
		}
		//goes through all time slots until 
		for(TimeSlot t: timeSlots) {
			if(t.getStartDate().equals(java.sql.Date.valueOf(string2)) && t.getStartTime().compareTo(CarShopController.StringToTime(string3)) == 0) {
				found = true;
			}
		}
		
		//if it should have been removed, it should not have been found
		if(string4.equalsIgnoreCase("not")) {
			assertFalse(found);
		}
		//if it should not been removed, it should have been found
		else {
			assertTrue(found);
		}
	}
	
	/**
	 * @author Ramin Akhavan-Sarraf
	 * Checks to see if an error message is raised depending on the result (in this
	 * case the error message is known, it is about detecting whether it is being raised -
	 * known from result)
	 * @param string: String - error message
	 * @param string2: String - result
	 */
	@Then("an error message {string} shall {string} be raised")
	public void an_error_message_shall_be_raised(String string, String string2) {
		assertEquals(string2, exist); 
	}

	
}
