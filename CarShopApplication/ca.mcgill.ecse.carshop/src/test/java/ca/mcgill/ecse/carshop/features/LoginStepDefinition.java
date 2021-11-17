package ca.mcgill.ecse.carshop.features;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.controller.CarShopController;
import ca.mcgill.ecse.carshop.controller.InvalidInputException;
import ca.mcgill.ecse.carshop.model.*;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * 
 * @author Ben Mwaniki
 *
 */

public class LoginStepDefinition {
	private CarShop carShop = CommonStepDefinitions.carshop;
	private String error; 
	private String targetName; 
	private String targetPass; 


//	@Given("a Carshop system exists")
//	public void a_carshop_system_exists() {
//		if (carShop == null) {
//			carShop = CarShopApplication.getCarShop();
//		}
//	}

//	@Given("the following customers exist in the system:")
//	public void the_following_customers_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
//
//		List<List<String>> valueMaps = dataTable.asLists();	    
//		for (int i=1; i<valueMaps.size(); i++) {
//			new Customer(valueMaps.get(i).get(0), valueMaps.get(i).get(1), carShop);
//
//		}
//	}

	@When("the user tries to log in with username {string} and password {string}")
	public void the_user_tries_to_log_in_with_username_and_password(String string, String string2) {
		try {
			CarShopController.login(string, string2);
		} catch (InvalidInputException e) {
			//error += e.getMessage();
			CommonStepDefinitions.Error = e.getMessage();
		}

		targetName = string; 
		targetPass = string2;
		
	}

	@Then("the user should be successfully logged in")
	public void the_user_should_be_successfully_logged_in() {

		User recentUser = CarShopApplication.getRecentlyLogedinUser();
		String actualName = recentUser.getUsername();
		String actualPass = recentUser.getPassword();

		assertEquals(targetName, actualName);
		assertEquals(targetPass, actualPass);

	}

	@Then("the user should not be logged in")
	public void the_user_should_not_be_logged_in() {
		assertFalse(CarShopController.isLoggedIn(targetName));

	}

	@Then("an error message {string} shall be raised")
	public void an_error_message_shall_be_raised(String string) {
		if (error != null) {
			assertTrue(error.contains(string));
		}
	}

	@Then("a new account shall be created")
	public void a_new_account_shall_be_created() {
		if(targetName.equals("owner")) {
			Owner owner = carShop.getOwner();
			assertNotNull(owner);
		}
		if(targetName.contains("Technician")) {
			List<Technician> technicians = carShop.getTechnicians();
			boolean exist = false;
			for(Technician t: technicians) {
				if(t.getUsername().equals(targetName));
					exist = true;
			}
			assertTrue(exist);
		}
	
	}

//	@Then("the account shall have username {string} and password {string}")
//	public void the_account_shall_have_username_and_password(String string, String string2) {
//		
//			assertTrue(carShop.getOwner().getUsername().equals(string));
//			assertTrue(carShop.getOwner().getPassword().equals(string2));
//	}

	@Then("the user shall be successfully logged in")
	public void the_user_shall_be_successfully_logged_in() {

			User recentUser = CarShopApplication.getRecentlyLogedinUser();
			assertTrue(recentUser.getUsername().equals(targetName));
			assertTrue(recentUser.getPassword().equals(targetPass));
	}

	@Then("the account shall have username {string}, password {string} and technician type {string}")
	public void the_account_shall_have_username_password_and_technician_type(String string, String string2, String string3) {
		if(targetName.contains("Technician")) {
			List<Technician> technicians = carShop.getTechnicians();
			for(Technician t: technicians) {
				if(t.getUsername().equals(targetName)) {
					assertTrue(t.getPassword().equals(targetPass));
					assertTrue(t.getType()== CarShopController.StringToTechnicianType(string3));
				}	
			}
	
		}

	}

	@Then("the corresponding garage for the technician shall be created")
	public void the_corresponding_garage_for_the_technician_shall_be_created() {
		User recentUser = CarShopApplication.getRecentlyLogedinUser();

		assertTrue(recentUser instanceof Technician );

		assertNotNull(((Technician) recentUser).getGarage());
	}

	@Then("the garage should have the same opening hours as the business")
	public void the_garage_should_have_the_same_opening_hours_as_the_business() {
		User target = CarShopApplication.getRecentlyLogedinUser();

		List<BusinessHour> garageHours = ((Technician) target).getGarage().getBusinessHours();
		List<BusinessHour> businessHours = carShop.getHours();
		assertTrue(garageHours.equals(businessHours));
	}

	@After
	public void tearDown() {

		if(carShop != null) {
			carShop.delete();		
		}
		error = null; 
		targetName = null; 
		targetPass = null; 
		CarShopApplication.logoutRecentlyLogedinUser();
		CommonStepDefinitions.Error = null;
	}

}
