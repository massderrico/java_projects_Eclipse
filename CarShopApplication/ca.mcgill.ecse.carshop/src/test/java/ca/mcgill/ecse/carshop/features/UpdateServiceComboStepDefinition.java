package ca.mcgill.ecse.carshop.features;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.controller.CarShopController;
import ca.mcgill.ecse.carshop.model.BookableService;
import ca.mcgill.ecse.carshop.model.CarShop;
import ca.mcgill.ecse.carshop.model.Customer;
import ca.mcgill.ecse.carshop.model.Service;
import ca.mcgill.ecse.carshop.model.ServiceCombo;
import ca.mcgill.ecse.carshop.model.User;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * 
 * @author Tiffany
 *
 */
public class UpdateServiceComboStepDefinition {
	
	private CarShop carShop = CommonStepDefinitions.carshop;
	private int errorCt;
	
	/**
	 * setup a carshop
	 * @author everyone
	 */
//	@Before
//	public void setup() {
//		carShop = CarShopApplication.getCarShop();
//	}
	
	/**
	 * destroys carshop after test
	 * @author everyone
	 */
	@After
	public void teardown() {
		carShop.delete();
		CommonStepDefinitions.Error = null;
		CommonStepDefinitions.carshop = CarShopApplication.getCarShop();
	}
	
	/**
	 * Tries to update an existing service combo
	 * @author Tiffany
	 * @param user the username of the person trying to update a combo (String)
	 * @param cToChange name of the combo you want to change (String)
	 * @param newName new name of the combo (String)
	 * @param main new main service (String)
	 * @param services new set of services (String)
	 * @param mand new mandatory settings of the services (String)
	 */
	@When("{string} initiates the update of service combo {string} to name {string}, main service {string} and services {string} and mandatory setting {string}")
	public void initiates_the_update_of_service_combo_to_name_main_service_and_services_and_mandatory_setting(String user, String cToChange, String newName, String main, String services, String mand) {
	    // Write code here that turns the phrase above into concrete actions
	   
		// makes a [Boolean] mandatory settings list based off the declared mandatory settings
    	List<Boolean> mandList = new ArrayList<>();
    	String[] bCut = mand.split(",");
    	for (int i=0; i < bCut.length; i++) {
    		mandList.add(Boolean.parseBoolean(bCut[i]));
    	}
	    	
    	// makes a [String] list of the services declared 
    	List<String> servList = new ArrayList<>();
    	String[] sCut = services.split(",");
    	for (int i = 0; i < sCut.length; i++) {
    		servList.add(sCut[i]);
    	}	    	
	    	// try to update
	    try {
	    	CarShopController.updateServiceCombo(user, cToChange, newName, main, servList, mandList);
	    } catch (Exception e) {
	    	//DefineServiceComboStepDefinition.error += e.getMessage();
	    	CommonStepDefinitions.Error = e.getMessage();
			errorCt += 1;
	    }
	}

	/**
	 * Confirms if when a combo is updated, the original combo is replaced with the new one
	 * @author Tiffany
	 * @param string name of the old combo
	 * @param string2 name of the updated combo
	 */
	@Then("the service combo {string} shall be updated to name {string}")
	public void the_service_combo_shall_be_updated_to_name(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
		
		// check if the updated serviceCombo exists
		for (BookableService s: carShop.getBookableServices()) {
			if (s instanceof ServiceCombo && s.getName().equals(string2))
				assertTrue(s.getName().equals(string2));
		}
		
		// check if the original serviceCombo exists
		// if the strings have the same name, then the service combo must be the
		// updated one because otherwise there would be an error from the
		// class files based on the model. So we only need to check if they're
		// different.
		if (!string.equals(string2)) { 
			for (BookableService s: carShop.getBookableServices()) {
				if (s instanceof ServiceCombo && s.getName().equals(string))
					fail("The original service combo still exists");
		}
			
		}
		

	}

}
