package ca.mcgill.ecse.carshop.features;

import ca.mcgill.ecse.carshop.controller.CarShopController;
import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.model.*;
import ca.mcgill.ecse.carshop.controller.ErrorTransfer;
import ca.mcgill.ecse.carshop.controller.ToService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.After;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definition for UpdateService.feature
 * 
 * --shares some step definitions with AddServiceStepDefinition.java--
 * 
 * @author Nathanael Lemma
 *
 */

public class UpdateServiceStepDefinition {

	private CarShop carshop = CommonStepDefinitions.carshop;

	@When("{string} initiates the update of the service {string} to name {string}, duration {string}, belonging to the garage of {string} technician")
	public void initiates_the_update_of_the_service_to_name_duration_belonging_to_the_garage_of_technician(
			String string, String string2, String string3, String string4, String string5) {
		try {
			CarShopController.updateService(string2, string3, Integer.parseInt(string4), string5,
					CarShopApplication.getRecentlyLogedinUser());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			CommonStepDefinitions.Error = e.getMessage();
			ErrorTransfer.setMessage(e.getMessage());
		}
	}

	@Then("the service {string} shall be updated to name {string}, duration {string}")
	public void the_service_shall_be_updated_to_name_duration(String string, String string2, String string3) {
		ToService s = CarShopController.searchServiceByName(string2);
		String actualName = s.getName();
		int actualDuration = s.getDuration();

		assertEquals(string2, actualName);
		assertEquals(Integer.parseInt(string3), actualDuration);
	}

	@After
	public void tearDown() {
		CarShopApplication.logoutRecentlyLogedinUser();
		if (carshop != null)
			carshop.delete();
		CommonStepDefinitions.Error = null;
		CommonStepDefinitions.carshop = CarShopApplication.getCarShop();
	}
}
