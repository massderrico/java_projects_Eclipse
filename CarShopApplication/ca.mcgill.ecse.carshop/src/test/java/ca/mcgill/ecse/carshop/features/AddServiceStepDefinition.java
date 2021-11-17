package ca.mcgill.ecse.carshop.features;

import ca.mcgill.ecse.carshop.controller.CarShopController;

import ca.mcgill.ecse.carshop.controller.ErrorTransfer;
import ca.mcgill.ecse.carshop.controller.InvalidInputException;
import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.model.*;
import ca.mcgill.ecse.carshop.model.Technician.TechnicianType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step Definition AddService.feature
 * 
 * @author Nathanael Lemma
 *
 */

public class AddServiceStepDefinition {

	private CarShop carshop = CommonStepDefinitions.carshop;
	

//	@Given("a Carshop system exists")
//	public void a_carshop_system_exists() {
//		carshop = CarShopApplication.getCarShop();
//	}

//	@Given("an owner account exists in the system")
//	public void an_owner_account_exists_in_the_system() {
//		if (carshop.getOwner() == null)
//			new Owner("dummyName", "dymmyPass", carshop);
//	}
//
//	@Given("a business exists in the system")
//	public void a_business_exists_in_the_system() {
//		if (carshop.getBusiness() == null)
//			new Business("dummyName", "dummyAddress", "dummyPhoneNumber", "dymmyEmail", carshop);
//	}
//
//	@Given("the following technicians exist in the system:")
//	public void the_following_technicians_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
//
//		List<Technician> t = new ArrayList<Technician>();
//		List<List<String>> valueMaps = dataTable.asLists();
//
//		for (int i = 1; i < valueMaps.size(); i++) {
//			t.add(carshop.addTechnician(valueMaps.get(i).get(0), valueMaps.get(i).get(1),
//					CarShopController.StringToTechnicianType(valueMaps.get(i).get(2))));
//		}
//	}
//
//	@Given("each technician has their own garage")
//	public void each_technician_has_their_own_garage() throws Exception {
//
//		List<Technician> technicians = carshop.getTechnicians();
//
//		int size = technicians.size();
//
//		// -- debugging
//		String message = "Error: Number of technicians is" + size;
//		if (size != 5)
//			throw new Exception(message);
//
//		for (Technician t : technicians) {
//			new Garage(carshop, t);
//		}
//	}
//
//	@Given("the Owner with username {string} is logged in")
//	public void the_owner_with_username_is_logged_in(String string) {
//		if (carshop.getOwner() == null)
//			carshop.setOwner(new Owner(string, "dummyPass", carshop));
//		else
//			carshop.getOwner().setUsername(string);
//		try {
//			CarShopController.login(string, "dymmyPass");
//		} catch (InvalidInputException e) {
//			e.printStackTrace();
//		}
//	}

	@When("{string} initiates the addition of the service {string} with duration {string} belonging to the garage of {string} technician")
	public void initiates_the_addition_of_the_service_with_duration_belonging_to_the_garage_of_technician(String string,
			String string2, String string3, String string4) {
		try {
			CarShopController.addService(string2, Integer.parseInt(string3), string4,
					CarShopApplication.getRecentlyLogedinUser());

		} catch (Exception e) {
			System.err.println(e.getMessage());
			CommonStepDefinitions.Error = e.getMessage();
		}
	}

	@Then("the service {string} shall exist in the system")
	public void the_service_shall_exist_in_the_system(String string) {
		boolean exists = false;

		for (BookableService s : carshop.getBookableServices()) {
			if (s.getName().equals(string)) {
				exists = true;
			}
		}

		assertTrue(exists);
	}

	@Then("the service {string} shall belong to the garage of {string} technician")
	public void the_service_shall_belong_to_the_garage_of_technician(String string, String string2) {

		Service service = null;

		for (Garage g : carshop.getGarages()) {
			for (Service s : g.getServices()) {
				if (s.getName().equals(string))
					service = s;
			}
		}

		TechnicianType type1 = service.getGarage().getTechnician().getType(); // actual
		TechnicianType type2 = CarShopController.StringToTechnicianType(string2); // expected

		assertEquals(type2, type1);
	}

	@Then("the number of services in the system shall be {string}")
	public void the_number_of_services_in_the_system_shall_be(String string) {
		int size = 0; // actual
		int expected = Integer.parseInt(string); // expected

		for (Garage g : carshop.getGarages()) {
			size += g.getServices().size();
		}

		assertEquals(expected, size);
	}

//	@Then("an error message with content {string} shall be raised")
//	public void an_error_message_with_content_shall_be_raised(String string) {
//		assertEquals(string, ErrorTransfer.getErrorMessage());
//	}

	@Then("the service {string} shall not exist in the system")
	public void the_service_shall_not_exist_in_the_system(String string) {
		boolean exists = false;

		for (BookableService s : carshop.getBookableServices()) {
			if (s.getName().equals(string))
				exists = true;
		}
		assertFalse(exists);
	}

//	@Given("the following services exist in the system:")
//	public void the_following_services_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) throws Exception {
//
//		List<List<String>> valueMaps = dataTable.asLists();
//
//		for (int i = 1; i < valueMaps.size(); i++) {
//			try {
//				CarShopController.quickServiceAdd(valueMaps.get(i).get(0), Integer.parseInt(valueMaps.get(i).get(1)),
//						valueMaps.get(i).get(2));
//			} catch (Exception e) {
//				System.err.println(e.getMessage());
//			}
//		}
//	}

	@Then("the service {string} shall still preserve the following properties:")
	public void the_service_shall_still_preserve_the_following_properties(String string,
			io.cucumber.datatable.DataTable dataTable) {

		List<List<String>> valueMaps = dataTable.asLists();

		// expected values
		String nameExpected = valueMaps.get(1).get(0);
		TechnicianType garageTypeExpected = CarShopController.StringToTechnicianType(valueMaps.get(1).get(2));
		int durationExpected = Integer.parseInt(valueMaps.get(1).get(1));

		List<Garage> garages = new ArrayList<Garage>();
		garages = carshop.getGarages();

		Service target = null;

		for (Garage g : garages) {
			List<Service> services = new ArrayList<Service>();
			services = g.getServices();
			for (Service s : services) {
				if (s.getName().equals(nameExpected))
					target = s;
			}
		}

		// Actual values
		String nameActual = target.getName();
		TechnicianType garageTypeActual = target.getGarage().getTechnician().getType();
		int durationActual = target.getDuration();

		assertEquals(nameExpected, nameActual);
		assertEquals(garageTypeExpected, garageTypeActual);
		assertEquals(durationExpected, durationActual);
	}

//	@Given("the following customers exist in the system:")
//	public void the_following_customers_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
//		List<List<String>> valueMaps = dataTable.asLists();
//
//		Customer c1, c2;
//
//		c1 = carshop.addCustomer(valueMaps.get(1).get(0), valueMaps.get(1).get(1));
//		c2 = carshop.addCustomer(valueMaps.get(2).get(0), valueMaps.get(2).get(1));
//
//		carshop.addCustomer(c1);
//		carshop.addCustomer(c2);
//	}

//	@Given("the user with username {string} is logged in")
//	public void the_user_with_username_is_logged_in(String string) {
//		List<Customer> customers = new ArrayList<Customer>();
//
//		customers = carshop.getCustomers();
//
//		for (Customer c : customers) {
//			if (c.getUsername().equals(string))
//				CarShopApplication.add(c); // add to signed it list
//		}
//	}

	@After
	public void tearDown() {
		CarShopApplication.logoutRecentlyLogedinUser();
		if (carshop != null)
			carshop.delete();
		ErrorTransfer.reset();
		CommonStepDefinitions.Error = null;
		CommonStepDefinitions.carshop = CarShopApplication.getCarShop();
	}
}
