package ca.mcgill.ecse.carshop.features;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

import java.util.List;

import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.controller.CarShopController;
import ca.mcgill.ecse.carshop.model.CarShop;
import ca.mcgill.ecse.carshop.model.Customer;
import ca.mcgill.ecse.carshop.model.Garage;
import ca.mcgill.ecse.carshop.model.Owner;
import ca.mcgill.ecse.carshop.model.Technician;
import ca.mcgill.ecse.carshop.model.User;

public class UpdateAccountStepDefinition {
	
	CarShop carShop = CommonStepDefinitions.carshop;
	boolean updated = false;
	
	
//	@Given("an owner account exists in the system with username {string} and password {string}")
//	public void an_owner_account_exists_in_the_system_with_username_and_password(String string, String string2) {
//		if (carShop.getOwner() == null) {
//			new Owner(string, string2, carShop);
//	}
//	}

//	@Given("the following customers exist in the system:")
//	public void the_following_customers_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
//		List<List<String>> valueMaps = dataTable.asLists();
//		for (int i = 1; i < valueMaps.size(); i++) {
//			if (User.hasWithUsername(valueMaps.get(i).get(0)) == false) {
//			new Customer(valueMaps.get(i).get(0), valueMaps.get(i).get(1),carShop);
//		}
//		}
//		
//	}

//	@Given("the following technicians exist in the system:")
//	public void the_following_technicians_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
//	   List<List<String>> valueMaps = dataTable.asLists();
//		for (int i = 1; i < valueMaps.size(); i++) {
//			if (User.hasWithUsername(valueMaps.get(i).get(0)) == false) {
//			new Technician(valueMaps.get(i).get(0), valueMaps.get(i).get(1),
//					CarShopController.StringToTechnicianType(valueMaps.get(i).get(2)),carShop);
//		}
//	}
//	}

//	@Given("each technician has their own garage")
//	public void each_technician_has_their_own_garage() {
//	    for (int i = 0; i < carShop.numberOfTechnicians();i++ ) {
//	    	if (carShop.getTechnician(i).hasGarage()== false) {
//	    		new Garage(carShop, carShop.getTechnician(i));
//	    	}
//	    	
//	    }
//	}

	@When("the user tries to update account with a new username {string} and password {string}")
	public void the_user_tries_to_update_account_with_a_new_username_and_password(String string, String string2) {
	    try {
	    	CarShopController.updateAccount(string,string2);
	    	updated = true;
	    }
	    catch (Exception e){
	    	SignUpCustomerStepDefinition.error= e.getMessage();
	    	CommonStepDefinitions.Error = e.getMessage();
	    	updated = false;
	    }
	    
	}

	@Then("the account shall not be updated")
	public void the_account_shall_not_be_updated() {
	    assertTrue(updated == false && SignUpCustomerStepDefinition.error.trim().length()>0);
	}
	@After
	public void tearDown() {
		carShop.delete();
		updated= false;
		SignUpCustomerStepDefinition.error=null;
		CommonStepDefinitions.Error = null;
		CommonStepDefinitions.carshop = CarShopApplication.getCarShop();
	}
}
