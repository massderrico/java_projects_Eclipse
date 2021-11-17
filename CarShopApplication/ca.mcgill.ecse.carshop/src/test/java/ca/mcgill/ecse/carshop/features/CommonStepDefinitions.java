package ca.mcgill.ecse.carshop.features;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.units.qual.Time;

import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.controller.CarShopController;
import ca.mcgill.ecse.carshop.controller.InvalidInputException;
import ca.mcgill.ecse.carshop.model.BookableService;
import ca.mcgill.ecse.carshop.model.Business;
import ca.mcgill.ecse.carshop.model.CarShop;
import ca.mcgill.ecse.carshop.model.ComboItem;
import ca.mcgill.ecse.carshop.model.Customer;
import ca.mcgill.ecse.carshop.model.Owner;
import ca.mcgill.ecse.carshop.model.Service;
import ca.mcgill.ecse.carshop.model.ServiceCombo;
import ca.mcgill.ecse.carshop.model.Technician;
import ca.mcgill.ecse.carshop.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.it.Date;

public class CommonStepDefinitions {
	
	public static CarShop carshop;
	public static String Error = null;
	public static Business commonBusiness;
	public static String targetName;
	
	@Given("a Carshop system exists")
	public void a_carshop_system_exists() {
		carshop = CarShopApplication.getCarShop();
	}
	
	@Given("an owner account exists in the system")
	public void an_owner_account_exists_in_the_system() {
		if (carshop.getOwner() == null)
			new Owner("dummyName", "dymmyPass", carshop);
	}
	
	@Given("a business exists in the system")
	public void a_business_exists_in_the_system() {
		if (carshop.getBusiness() == null)
			new Business("dummyName", "dummyAddress", "dummyPhoneNumber", "dymmyEmail", carshop);
	}
	
	@Given("the following technicians exist in the system:")
	public void the_following_technicians_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {

		List<Technician> t = new ArrayList<Technician>();
		List<List<String>> valueMaps = dataTable.asLists();

		for (int i = 1; i < valueMaps.size(); i++) {
			t.add(carshop.addTechnician(valueMaps.get(i).get(0), valueMaps.get(i).get(1),
					CarShopController.StringToTechnicianType(valueMaps.get(i).get(2))));
		}
	}
	
	@Given("each technician has their own garage")
	public void each_technician_has_their_own_garage() {
	    // Write code here that turns the phrase above into concrete actions

    	// Creates a garage for each technician
    	for (int i = 0; i < carshop.getTechnicians().size(); i++) {
    		carshop.addGarage(carshop.getTechnician(i));
    	}
	    		    
	}
	
	@Given("the Owner with username {string} is logged in")
	public void the_owner_with_username_is_logged_in(String string) {
		if (carshop.getOwner() == null)
			carshop.setOwner(new Owner(string, "dummyPass", carshop));
		else
			carshop.getOwner().setUsername(string);

		// CarShopApplication.add(carshop.getOwner()); //login user
		try {
			CarShopController.login(string, "dymmyPass");
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
	}
	
	@Given("the following services exist in the system:")
	public void the_following_services_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) throws Exception {

		List<List<String>> valueMaps = dataTable.asLists();

		for (int i = 1; i < valueMaps.size(); i++) {
			try {
				CarShopController.quickServiceAdd(valueMaps.get(i).get(0), Integer.parseInt(valueMaps.get(i).get(1)),
						valueMaps.get(i).get(2));
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	@Given("the following customers exist in the system:")
	public void the_following_customers_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		for (int i = 0; i < dataTable.height(); i++) {
			carshop.addCustomer(new Customer(dataTable.cell(i, 0), dataTable.cell(i, 1), carshop));
		}
	}
	
	@Given("the user with username {string} is logged in")
	public void the_user_with_username_is_logged_in(String string) {
		List<Customer> customers = new ArrayList<Customer>();

		customers = carshop.getCustomers();

		for (Customer c : customers) {
			if (c.getUsername().equals(string))
				CarShopApplication.add(c); // add to signed it list
		}
	}
	
	@Given("the user is logged in to an account with username {string}")
	public void the_user_is_logged_in_to_an_account_with_username(String string) {
		if (CarShopApplication.getRecentlyLogedinUser() == null) {
			CarShopApplication.add(User.getWithUsername(string));
		}
		if (CarShopApplication.getRecentlyLogedinUser() != null) {
			if (CarShopApplication.getRecentlyLogedinUser().getUsername().equals(string) == false) {
				CarShopApplication.add(User.getWithUsername(string));
			}
		}
		targetName = string;
	}
	
	@Given("an owner account exists in the system with username {string} and password {string}")
	public void an_owner_account_exists_in_the_system_with_username_and_password(String string, String string2) {
		if (carshop.getOwner() == null) {
			new Owner(string, string2, carshop);
		}
	}
	
	@Then("an error message with content {string} shall be raised")
	public void an_error_message_with_content_shall_be_raised(String string) {
		assertTrue(string.equals(Error));
	}
	
	@Given("the following service combos exist in the system:")
	public void the_following_service_combos_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		
		// create list of boolean settings
		
		// TODO find a way to fill out the parameters without sparking an error
		// to do that, I need to add the existing services into the combo, not
		// new ones with the same name.
		ServiceCombo combo = null;
		for (int i = 1; i < dataTable.height(); i++) {
			combo = new ServiceCombo(dataTable.cell(i, 0), carshop);
			
			// create list of boolean settings
			ArrayList<Boolean> mandList = new ArrayList<>();
			String[] bCut = dataTable.cell(i, 3).split(",");
			for (int j = 0; j < bCut.length; j++) {
				mandList.add(Boolean.parseBoolean(bCut[j]));
			}
			
			// create list of services from the existing services
			ArrayList<Service> serviceList = new ArrayList<>();
			String[] sCut = dataTable.cell(i, 2).split(",");
			for (String str: sCut) {
				for (BookableService ser: carshop.getBookableServices()) {
					if (ser.getName().equals(str))
						serviceList.add((Service) ser);
				}
			}
			
			// turn services into comboItems and add them to the combo
			ArrayList<ComboItem> items = new ArrayList<>();
			ComboItem main = null;
			for (int j = 0; j < serviceList.size(); j++) {
				items.add(new ComboItem(mandList.get(j),serviceList.get(j),combo));
				if (items.get(j).getService().getName().equals(dataTable.cell(i, 1)))
					main = items.get(j);
			}
			
			combo.setMainService(main); // set main service
		}
	}
	
	@Given("the system's time and date is {string}")
	public void the_systems_time_and_date_is(String string) {		
		// local version
		int i=0;
		String sDate="";
		String sTime="";
		while(string.charAt(i)!='+') {
			sDate+=(char)string.charAt(i);
			i++;
		}
		while(i<string.length()) {
			sTime+=(char) string.charAt(i);
			i++;
		}
		sTime+=":00";
		CarShopApplication.setLocalDate(sDate);
		CarShopApplication.setLocalTime(sTime);
		
		CarShopApplication.setDate(java.sql.Date.valueOf(sDate));
		CarShopApplication.setTime(CarShopController.StringToTime(sTime));
				
	}
	
	@Then("the account shall have username {string} and password {string}")
	public void the_account_shall_have_username_and_password(String string, String string2) {
		String actalUsername = User.getWithUsername(string).getUsername();
		String actualPassword = User.getWithUsername(string).getPassword();
		assertTrue(string.equals(actalUsername));
		assertTrue(string2.equals(actualPassword));
	}

	
	@Given("a business exists with the following information:")
	public void a_business_exists_with_the_following_information(io.cucumber.datatable.DataTable dataTable) {
		List<List<String>> valueMaps = dataTable.asLists();
		commonBusiness = new Business(valueMaps.get(1).get(0), valueMaps.get(1).get(1), valueMaps.get(1).get(2), valueMaps.get(1).get(3), carshop);
	}
	

}
