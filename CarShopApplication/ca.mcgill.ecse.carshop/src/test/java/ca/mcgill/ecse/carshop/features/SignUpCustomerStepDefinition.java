package ca.mcgill.ecse.carshop.features;

import static org.junit.Assert.*;

import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.controller.CarShopController;
import ca.mcgill.ecse.carshop.model.CarShop;
import ca.mcgill.ecse.carshop.model.Customer;
import ca.mcgill.ecse.carshop.model.Owner;
import ca.mcgill.ecse.carshop.model.Technician;
import ca.mcgill.ecse.carshop.model.User;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignUpCustomerStepDefinition {

	public CarShop carShop = CommonStepDefinitions.carshop;
	public static String error = null;
	public String username;
	public boolean isCreated = false;

//	@Given("a Carshop system exists")
//	public void a_carshop_system_exists() {
//		carShop = CarShopApplication.getCarShop();
//	}

	@Given("there is no existing username {string}")
	public void there_is_no_existing_username(String string) {
		if (User.hasWithUsername(string) == true) {
			User.getWithUsername(string).delete();
		}

	}

	@When("the user provides a new username {string} and a password {string}")
	public void the_user_provides_a_new_username_and_a_password(String string, String string2) {
		username = string;
		try {
			CarShopController.signUp(string, string2);
			isCreated = true;
		} catch (Exception e) {
			isCreated = false;
			error = e.getMessage();
			CommonStepDefinitions.Error = e.getMessage();

		}

	}

	@Then("a new customer account shall be created")
	public void a_new_customer_account_shall_be_created() {

		assertTrue(User.hasWithUsername(username));
	}

//	@Then("the account shall have username {string} and password {string}")
//	public void the_account_shall_have_username_and_password(String string, String string2) {
//		String actalUsername = User.getWithUsername(string).getUsername();
//		String actualPassword = User.getWithUsername(string).getPassword();
//		assertEquals(string, actalUsername);
//		assertEquals(string2, actualPassword);
//	}

	@Then("no new account shall be created")
	public void no_new_account_shall_be_created() {

		assertFalse(isCreated);
	}

//	@Then("an error message {string} shall be raised")
//	public void an_error_message_shall_be_raised(String string) {
//		assertTrue(error.equals(string));
//	}

	@Given("there is an existing username {string}")
	public void there_is_an_existing_username(String string) {
		if (User.hasWithUsername(string) == true) {
			;
		} else {
			if (string.equals("owner")) {
				new Owner(string, "pass", carShop);

			} else if (string.length() >= 15) {
				String checkTech = string.substring(string.length() - 11, string.length() - 1) + "n"; // check to see if
																										// "-Technician
																										// is end of a
																										// username"
				String techType = string.substring(0, string.length() - 11);
				if ((checkTech.equals("-Technician")) && (CarShopController.StringToTechnicianType(techType) != null)) {
					new Technician(string, "dummyPass", CarShopController.StringToTechnicianType(techType), carShop);

				}
			} else {
				new Customer(string, "dummyPass", carShop);
			}
		}
	}

//	@Given("the user is logged in to an account with username {string}")
//	public void the_user_is_logged_in_to_an_account_with_username(String string) {
//		if (CarShopApplication.getRecentlyLogedinUser() == null) {
//			CarShopApplication.add(User.getWithUsername(string));
//		}
//		if (CarShopApplication.getRecentlyLogedinUser() != null) {
//			if (CarShopApplication.getRecentlyLogedinUser().getUsername().equals(string) == false) {
//				CarShopApplication.add(User.getWithUsername(string));
//			}
//		}
//	}

	@After
	public void tearDown() {
		if (carShop != null)
			carShop.delete();
		isCreated = false;
		error = "";
		CommonStepDefinitions.Error = null;
		CommonStepDefinitions.carshop = CarShopApplication.getCarShop();
	}

}
