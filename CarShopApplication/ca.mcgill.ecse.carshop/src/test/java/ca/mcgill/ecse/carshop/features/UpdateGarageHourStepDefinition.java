package ca.mcgill.ecse.carshop.features;

import static org.junit.Assert.assertTrue;

import java.sql.Time;
import java.util.List;

import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.controller.BusinessHourTO;
import ca.mcgill.ecse.carshop.controller.CarShopController;
import ca.mcgill.ecse.carshop.controller.InvalidInputException;
import ca.mcgill.ecse.carshop.model.*;
import ca.mcgill.ecse.carshop.model.Technician.TechnicianType;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * 
 * @author Ben Mwaniki
 *
 */
public class UpdateGarageHourStepDefinition {

	private CarShop carShop = CommonStepDefinitions.carshop;
	private Business business = CommonStepDefinitions.commonBusiness;

//	@Given("an owner account exists in the system with username {string} and password {string}")
//	public void an_owner_account_exists_in_the_system_with_username_and_password(String string, String string2) {
//		carShop = CarShopApplication.getCarShop();
//		new Owner(string, string2, carShop);
//	}

//	@Given("the following technicians exist in the system:")
//	public void the_following_technicians_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
//		List<List<String>> valueMaps = dataTable.asLists();
//
//		for (int i=1; i<valueMaps.size(); i++) {
//
//			new Technician(valueMaps.get(i).get(0), valueMaps.get(i).get(1), CarShopController.StringToTechnicianType(valueMaps.get(i).get(2)), carShop);
//
//		}
//	}

//	@Given("each technician has their own garage")
//	public void each_technician_has_their_own_garage() {
//		List<Technician> technicians = carShop.getTechnicians();
//
//		for(Technician t: technicians) {
//			new Garage(carShop, t);		
//		}
//	}

//	@Given("a business exists with the following information:")
//	public void a_business_exists_with_the_following_information(io.cucumber.datatable.DataTable dataTable) {
//		List<List<String>> valueMaps = dataTable.asLists();
//		business = new Business(valueMaps.get(1).get(0), valueMaps.get(1).get(1), valueMaps.get(1).get(2), valueMaps.get(1).get(3), carShop);
//	}

	@Given("the business has the following opening hours:")
	public void the_business_has_the_following_opening_hours(io.cucumber.datatable.DataTable dataTable) {
		List<List<String>> valueMaps = dataTable.asLists();

		for(int i=1; i<valueMaps.size(); i++) {
			business.addBusinessHour(new BusinessHour(CarShopController.parseStringToDayOfWeek(valueMaps.get(i).get(0)), Time.valueOf(valueMaps.get(i).get(1)+":00"), Time.valueOf(valueMaps.get(i).get(2)+":00"), carShop));
		}
	}

//	@Given("the user is logged in to an account with username {string}")
//	public void the_user_is_logged_in_to_an_account_with_username(String string) {
//		List <Technician> technicians = carShop.getTechnicians();
//		List <Customer> customers = carShop.getCustomers();
//		String password;
//		targetName = string;
//
//		for(Technician t: technicians) {
//			if(t.getUsername().equals(string)) {
//				password = t.getPassword();
//
//				try {
//					CarShopController.login(string, password);
//				}catch (InvalidInputException e) {
//					e.getMessage();
//				}
//			}
//
//		}
//		
//		for(Customer c: customers) {
//			if(c.getUsername().equals(string)) {
//				password = c.getPassword();
//
//				try {
//					CarShopController.login(string, password);
//				}catch (InvalidInputException e) {
//					e.getMessage();
//				}
//			}
//
//		}
//		
//		if(string.equals("owner")) {
//			try {
//				CarShopController.login(string, "ownerPass");
//			} catch (InvalidInputException e) {
//				e.getMessage();
//			}
//			
//		}
//		
//		
//		User recentUser = CarShopApplication.getRecentlyLogedinUser();
//		assertTrue(recentUser.getUsername().equals(string));
//	}

	@When("the user tries to add new business hours on {string} from {string} to {string} to garage belonging to the technician with type {string}")
	public void the_user_tries_to_add_new_business_hours_on_from_to_to_garage_belonging_to_the_technician_with_type(String string, String string2, String string3, String string4) {
		//BusinessHour businessHour = new BusinessHour(CarShopController.parseStringToDayOfWeek(string), Time.valueOf(string2+":00"), Time.valueOf(string3+":00"), carShop);
		BusinessHourTO hourTO = new BusinessHourTO(string,string2, string3);
		
		User recentUser = CarShopApplication.getRecentlyLogedinUser();
		if(recentUser.getUsername().equals(CommonStepDefinitions.targetName)) {
			
			//assertTrue(false);
			try {
				CarShopController.addGarageHours(hourTO, recentUser.getUsername(), string4);
			} catch (InvalidInputException e) {
				e.getMessage();
				CommonStepDefinitions.Error = e.getMessage();
			}		
		}	
	}

	@Then("the garage belonging to the technician with type {string} should have opening hours on {string} from {string} to {string}")
	public void the_garage_belonging_to_the_technician_with_type_should_have_opening_hours_on_from_to(String string, String string2, String string3, String string4) {
		User recentUser = CarShopApplication.getRecentlyLogedinUser();

		if(recentUser.getUsername().contains("Technician")) {
			TechnicianType targetType = CarShopController.StringToTechnicianType(string);
			recentUser = (Technician) recentUser;
			BusinessHour businessHour = new BusinessHour(CarShopController.parseStringToDayOfWeek(string2), Time.valueOf(string3+":00"), Time.valueOf(string4+":00"), carShop);

			List<Technician> technicians = carShop.getTechnicians();
			boolean exist = false;
			for(Technician t: technicians) {
				if(t.getType() == targetType) {
					List<BusinessHour> businessHours = t.getGarage().getBusinessHours();
					for(BusinessHour hr: businessHours) {
						if(hr.getDayOfWeek().equals(businessHour.getDayOfWeek()) && hr.getStartTime().equals(businessHour.getStartTime()) && hr.getEndTime().equals(businessHour.getEndTime())) {
							exist = true;
						}	
					}
				}

			}
			assertTrue(exist);
		}
	}

	@Given("there are opening hours on {string} from {string} to {string} for garage belonging to the technician with type {string}")
	public void there_are_opening_hours_on_from_to_for_garage_belonging_to_the_technician_with_type(String string, String string2, String string3, String string4) {

		List<Technician> technicians = carShop.getTechnicians();
		BusinessHour businessHour = new BusinessHour(CarShopController.parseStringToDayOfWeek(string), Time.valueOf(string2+":00"), Time.valueOf(string3+":00"), carShop);
		TechnicianType type = CarShopController.StringToTechnicianType(string4);

		for(Technician t: technicians) {
			if(t.getType()==type) {
				t.getGarage().addBusinessHour(businessHour);
			}

		}
	}

	@When("the user tries to remove opening hours on {string} from {string} to {string} to garage belonging to the technician with type {string}")
	public void the_user_tries_to_remove_opening_hours_on_from_to_to_garage_belonging_to_the_technician_with_type(String string, String string2, String string3, String string4) {
		BusinessHourTO hourTO = new BusinessHourTO(string, string2+":00", string3+":00");
		User recentUser = CarShopApplication.getRecentlyLogedinUser();

		try {
			CarShopController.removeGarageHours(hourTO, recentUser.getUsername(), string4);
		} catch (InvalidInputException e) {
			e.getMessage();
			CommonStepDefinitions.Error = e.getMessage();
		}	

	}

	@Then("the garage belonging to the technician with type {string} should not have opening hours on {string} from {string} to {string}")
	public void the_garage_belonging_to_the_technician_with_type_should_not_have_opening_hours_on_from_to(String string, String string2, String string3, String string4) {

		List<Technician> technicians = carShop.getTechnicians();
		boolean exist=false;
		for(Technician t: technicians) {
			if(t.getType()==CarShopController.StringToTechnicianType(string)) {
				List<BusinessHour> businessHours = t.getGarage().getBusinessHours();
				BusinessHour businessHour = new BusinessHour(CarShopController.parseStringToDayOfWeek(string2), Time.valueOf(string3+":00"), Time.valueOf(string4+":00"), carShop);
	
				for(BusinessHour hr: businessHours) {
					if(hr.getDayOfWeek().equals(businessHour.getDayOfWeek()) && hr.getStartTime().equals(businessHour.getStartTime()) && hr.getEndTime().equals(businessHour.getEndTime())) {
						exist = true;
					}	
				}
				
			}

		}
		
		assertTrue(exist==false);
	}

	@After
	public void tearDown() {

		if(carShop != null) {
			carShop.delete();		
		}
		CarShopApplication.logoutRecentlyLogedinUser();
		CommonStepDefinitions.Error = null;
		CommonStepDefinitions.carshop = CarShopApplication.getCarShop();

	}
}
