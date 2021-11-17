package ca.mcgill.ecse.carshop.features;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.controller.CarShopController;
import ca.mcgill.ecse.carshop.controller.InvalidInputException;
import ca.mcgill.ecse.carshop.model.BookableService;
import ca.mcgill.ecse.carshop.model.Business;
import ca.mcgill.ecse.carshop.model.CarShop;
import ca.mcgill.ecse.carshop.model.ComboItem;
import ca.mcgill.ecse.carshop.model.Customer;
import ca.mcgill.ecse.carshop.model.Garage;
import ca.mcgill.ecse.carshop.model.Owner;
import ca.mcgill.ecse.carshop.model.Service;
import ca.mcgill.ecse.carshop.model.ServiceCombo;
import ca.mcgill.ecse.carshop.model.Technician;
import ca.mcgill.ecse.carshop.model.Technician.TechnicianType;
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
public class DefineServiceComboStepDefinition {
	
	private CarShop carShop = CommonStepDefinitions.carshop;
	public static String error;
	private int errorCt;
	
	/**
	 * creates a carshop system
	 * @author everyone
	 */
//	@Given("a Carshop system exists")
//	public void a_carshop_system_exists() {
//	    // Write code here that turns the phrase above into concrete actions
//		carShop = CarShopApplication.getCarShop();
//		error = "";
//		errorCt = 0;
//	}
	
	/**
	 * destroys the carshop so we can start anew
	 * @author Nath
	 */
	@After
	public void teardown() {
		carShop.delete();
		CommonStepDefinitions.Error = null;
		CommonStepDefinitions.carshop = CarShopApplication.getCarShop();
	}
	
	/**
	 * reconstructs the carshop
	 * @author Tiffany
	 */
//	@Before
//	public void buildup() {
//		carShop = CarShopApplication.getCarShop();
//	}

	/**
	 * creates owner account
	 * @author Tiffany
	 */
//	@Given("an owner account exists in the system")
//	public void an_owner_account_exists_in_the_system() {
//	    // Write code here that turns the phrase above into concrete actions
//		if (!(carShop.hasOwner()))
//			new Owner("dummyUser","dummyPass",carShop);
//		
//	}

	/**
	 * creates a business
	 * @author Tiffany
	 */
//	@Given("a business exists in the system")
//	public void a_business_exists_in_the_system() {
//	    // Write code here that turns the phrase above into concrete actions
//		if (!carShop.hasBusiness())
//			new Business("dummyName","dummyAddress","dummyPhone","dummyEmail",carShop);
//	}

	/**
	 * creates technicians based on the listed technician
	 * @author Tiffany
	 * @param dataTable
	 */
//	@Given("the following technicians exist in the system:")
//	public void the_following_technicians_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
//	    // Write code here that turns the phrase above into concrete actions
//	    // For automatic transformation, change DataTable to one of
//	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
//	    //
//	    // For other transformations you can register a DataTableType.
//
//    	for (int i = 1; i < dataTable.height(); i++) {
//    		// create users:
//    		TechnicianType type = null;
//    		// convert the string TechnicianType to TechnicianType
//    		for (int j = 0; j < 5; j++) {
//    			if (TechnicianType.values()[j].toString().equals(dataTable.cell(i, 2)))
//    				type = TechnicianType.values()[j];
//    		}
//    		// add that type of technician to the carshop
//    		carShop.addTechnician(dataTable.cell(i, 0), dataTable.cell(i, 1), type); // creates Technicians based on table types
//    	}
//	}

	/**
	 * gives garages to each technician
	 * @author Tiffany 
	 */
//	@Given("each technician has their own garage")
//	public void each_technician_has_their_own_garage() {
//	    // Write code here that turns the phrase above into concrete actions
//
//    	// Creates a garage for each technician
//    	for (int i = 0; i < carShop.getTechnicians().size(); i++) {
//    		carShop.addGarage(carShop.getTechnician(i));
//    	}
//	    		    
//	}

	/**
	 * creates services based on the chart
	 * @param dataTable
	 * @author Tiffany
	 */
//	@Given("the following services exist in the system:")
//	public void the_following_services_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
//	    // Write code here that turns the phrase above into concrete actions
//	    // For automatic transformation, change DataTable to one of
//	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
//	    //
//	    // For other transformations you can register a DataTableType.
//
//    	for (int i = 1; i < dataTable.height(); i++) {
//    		// create services:
//    		Service service = new Service(dataTable.cell(i, 0), carShop, Integer.parseInt(dataTable.cell(i, 1)), carShop.getTechnician(i).getGarage());
//    		carShop.addBookableService(service);
//    	}	    	
//	   
//	}

	/**
	 * logs in the owner
	 * @author Nath
	 */
//	@Given("the Owner with username {string} is logged in")
//	public void the_owner_with_username_is_logged_in(String string) {
//	    // Sets the username of the owner to {String}
//		if (carShop.getOwner() == null)
//			CarShopApplication.add(new Owner(string,"dummyPass", carShop));
//		else 
//			carShop.getOwner().setUsername(string);
//		
//	    try {
//			CarShopController.login(string, "dummyPass");
//		} catch (InvalidInputException e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * tries to create a service combo
	 * @author Tiffany
	 * @param uName the username of the logged-in person
	 * @param name the name of the combo
	 * @param main the main service of the combo (String)
	 * @param services the list of services (String)
	 * @param mand the mandatory settings of the services (String)
	 */
	@When("{string} initiates the definition of a service combo {string} with main service {string}, services {string} and mandatory setting {string}")
	public void initiates_the_definition_of_a_service_combo_with_main_service_services_and_mandatory_setting(String uName, String name, String main, String services, String mand) {
	    // Write code here that turns the phrase above into concrete actions
		
		// turn service string into Service List<String>
		List<String> serviceList = new ArrayList<>();
		String[] sCut = services.split(","); // places all service names into arrays of strings
		for (int i = 0; i < sCut.length; i++) {
			serviceList.add(sCut[i]);
		}
		
		// turn boolean string into Boolean List<Boolean>
		List<Boolean> mandList = new ArrayList<>();
		String[] bCut = mand.split(",");
		for (int i = 0; i < bCut.length; i++) {
			mandList.add(Boolean.parseBoolean(bCut[i]));
		}
		
		try {
			// try and run the defineServiceComboMethod:
			CarShopController.defineServiceCombo(uName, name, main, serviceList, mandList);
			
		} catch (Exception e) {
			//error += e.getMessage();
			CommonStepDefinitions.Error = e.getMessage();
			errorCt += 1;
		}
	}

	/**
	 * checks if the combo exists in the system after creating it
	 * @param string the created service combo
	 * @author Tiffany
	 */
	@Then("the service combo {string} shall exist in the system")
	public void the_service_combo_shall_exist_in_the_system(String string) {
	    // Write code here that turns the phrase above into concrete actions
		
		// get list of combo names in the system
		ArrayList<String> existingComboNames = new ArrayList<>();
		for (BookableService s: carShop.getBookableServices())
		{
			if (s instanceof ServiceCombo)
				existingComboNames.add(s.getName());
		}
		// check if the combo "string" exists in the system
		assertTrue(existingComboNames.contains(string));
	}

	/**
	 * checks if the service combo matches its declared settings
	 * @author Tiffany
	 * @param string name of the service combo (String)
	 * @param string2 services within the combo (String)
	 * @param string3 mandatory setting of the combo (String)
	 */
	@Then("the service combo {string} shall contain the services {string} with mandatory setting {string}")
	public void the_service_combo_shall_contain_the_services_with_mandatory_setting(String string, String string2, String string3) {
	    // Write code here that turns the phrase above into concrete actions
		
		// find service combo "string":
		// under assumption that combo "string" exists in system
		ServiceCombo combo = null;
		for (BookableService s: carShop.getBookableServices())
		{
			if (s.getName().equals(string))
				combo = (ServiceCombo) s;
		}
		
		// get services (strings)
		String[] sCut = string2.split(","); // get service names
		ArrayList<String> serviceList = new ArrayList<>();
		for (int i = 0; i < sCut.length; i++) {
			serviceList.add(sCut[i]); // add to list
		}
		
		// get mandatory settings
		String[] bCut = string3.split(","); // get mandatory values
		ArrayList<Boolean> mandList = new ArrayList<>();
		for (int i = 0; i < sCut.length; i++) {
			mandList.add(Boolean.parseBoolean(bCut[i])); // add to list
		}
		
		// check service settings
		for (ComboItem item: combo.getServices()) {
			assertTrue(serviceList.contains(item.getService().getName()));
		}
		
		// check service and boolean settings (using for loop because order matters)
		for (int i = 0; i < combo.getServices().size(); i++) {
			assertEquals(mandList.get(i), combo.getService(i).getMandatory());		
		}
		
	}
		
	/**
	 * confirms that the main service is set in the combo
	 * @author Tiffany
	 * @param string the name of the service combo
	 * @param string2 the name of the main service
	 */
	@Then("the main service of the service combo {string} shall be {string}")
	public void the_main_service_of_the_service_combo_shall_be(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    // find service combo:
		ServiceCombo combo = null;
		for (BookableService s: carShop.getBookableServices()) {
			if (s instanceof ServiceCombo && s.getName().equals(string))
				combo = (ServiceCombo) s;
		}
		
		// compare main service:
		if (combo.getMainService() == null) // if the combo is empty
			fail("Combo main service not found.");
		
		assertEquals(string2, combo.getMainService().getService().getName());
	}

	/**
	 * confirms that the main service is mandatory
	 * @author Tiffany
	 * @param string the name of the main service (String)
	 * @param string2 the service combo in question (String)
	 */
	@Then("the service {string} in service combo {string} shall be mandatory")
	public void the_service_in_service_combo_shall_be_mandatory(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
		// find service combo:
		ServiceCombo combo = null;
		for (int i = 0; i < carShop.getBookableServices().size(); i++)
		{
			if (carShop.getBookableService(i) instanceof ServiceCombo && carShop.getBookableService(i).getName().equals(string2)) {
				combo = (ServiceCombo) carShop.getBookableService(i);
				break;
			}
		}
		
		ComboItem item = null;
		// compare main service's mandatory setting:
		if (combo.getServices() == null)
			fail("Services not found");
		else
		{
			for (ComboItem i: combo.getServices())
				if (i.getService().getName().equals(string))
					item = i;
		}
		
	    assertEquals(true, item.getMandatory());
	}

	/**
	 * Confirms the number of services in the system matches the actual amount
	 * @author Tiffany
	 * @param string number of service combos in the system
	 */
	@Then("the number of service combos in the system shall be {string}")
	public void the_number_of_service_combos_in_the_system_shall_be(String string) {
	    // Write code here that turns the phrase above into concrete actions
		
		// Returns number of expected service combos:
		Integer stringToInt = Integer.parseInt(string);
		
		// Get actual number of service combos
		int actual = 0;
		for (int i = 0; i < carShop.getBookableServices().size(); i++)
		{
			if (carShop.getBookableService(i) instanceof ServiceCombo)
				actual += 1;
		}
		
		// Compare to actual amount
		assertEquals(stringToInt, actual);
		
	}
	/**
	 * Creates service combos in the system based on the data table
	 * @author Tiffany
	 * @param dataTable contains the servicecombo name, main service, services, and mandatory settings (all of these are Strings)
	 */
//	@Given("the following service combos exist in the system:")
//	public void the_following_service_combos_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
//	    // Write code here that turns the phrase above into concrete actions
//	    // For automatic transformation, change DataTable to one of
//	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
//	    //
//	    // For other transformations you can register a DataTableType.
//		
//		// create list of boolean settings
//		
//		// TODO find a way to fill out the parameters without sparking an error
//		// to do that, I need to add the existing services into the combo, not
//		// new ones with the same name.
//		ServiceCombo combo = null;
//		for (int i = 1; i < dataTable.height(); i++) {
//			combo = new ServiceCombo(dataTable.cell(i, 0), carShop);
//			
//			// create list of boolean settings
//			ArrayList<Boolean> mandList = new ArrayList<>();
//			String[] bCut = dataTable.cell(i, 3).split(",");
//			for (int j = 0; j < bCut.length; j++) {
//				mandList.add(Boolean.parseBoolean(bCut[j]));
//			}
//			
//			// create list of services from the existing services
//			ArrayList<Service> serviceList = new ArrayList<>();
//			String[] sCut = dataTable.cell(i, 2).split(",");
//			for (String str: sCut) {
//				for (BookableService ser: carShop.getBookableServices()) {
//					if (ser.getName().equals(str))
//						serviceList.add((Service) ser);
//				}
//			}
//			
//			// turn services into comboItems and add them to the combo
//			ArrayList<ComboItem> items = new ArrayList<>();
//			ComboItem main = null;
//			for (int j = 0; j < serviceList.size(); j++) {
//				items.add(new ComboItem(mandList.get(j),serviceList.get(j),combo));
//				if (items.get(j).getService().getName().equals(dataTable.cell(i, 1)))
//					main = items.get(j);
//			}
//			
//			combo.setMainService(main); // set main service
//		}
//	}
	
	/**
	 * Confirms the error message matches the error
	 * @author Tiffany
	 * @param string the expected error
	 */
//	@Then("an error message with content {string} shall be raised")
//	public void an_error_message_with_content_shall_be_raised(String string) {
//	    // Write code here that turns the phrase above into concrete actions
//	    assertEquals(string, error);
//	}

	/**
	 * Confirms that the invalid service combo is not created
	 * @author Tiffany
	 * @param string the name of the invalid service
	 */
	@Then("the service combo {string} shall not exist in the system")
	public void the_service_combo_shall_not_exist_in_the_system(String string) {
	    // Write code here that turns the phrase above into concrete actions
		
		for (int i = 0; i < carShop.getBookableServices().size();i++) {
			// check if the service with name "string" exists in the ServiceCombo list
			if (carShop.getBookableService(i) instanceof ServiceCombo && carShop.getBookableService(i).getName().equals(string))
				fail();
		}
	}

	/**
	 * [In the case that an existing combo is declared] Confirms that the existing combo is not changed.
	 * @author Tiffany
	 * @param string name of the existing service combo
	 * @param dataTable contains the expected service name, main service, services, and boolean values (all Strings)
	 */
	@Then("the service combo {string} shall preserve the following properties:")
	public void the_service_combo_shall_preserve_the_following_properties(String string, io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		
		// find declared service combo:
		ServiceCombo combo = null;
		for (int i = 0; i < carShop.getBookableServices().size(); i++)
		{
			if (carShop.getBookableService(i) instanceof ServiceCombo && carShop.getBookableService(i).getName().equals(string)) {
				combo = (ServiceCombo) carShop.getBookableService(i);
				break;
			}
		}
			
		// break up boolean table to a list of booleans
		List<Boolean> mandList = new ArrayList<>();
		String[] bCut = dataTable.cell(1, 3).split(",");
		for (int i = 0; i < bCut.length; i++) {
			mandList.add(Boolean.parseBoolean(bCut[i]));
		}
		
		
		// break up service string to a list of services
		List<String> sServList = new ArrayList<>();
		String[] sCut = dataTable.cell(1, 2).split(",");
		for (int j = 0; j < sCut.length; j++) {
			sServList.add(sCut[j]);
			System.out.print(sCut[j] + " ");
		}
		
		
		// check is the name is unchanged
		assertTrue(combo.getName().equals(dataTable.cell(1, 0)));
		
		// check if the mainservice is unchanged
		if (combo.hasMainService() == false || !combo.hasServices())
			fail("Combo has a name but is empty");
		
		assertEquals(dataTable.cell(1, 1), combo.getMainService().getService().getName());	
		
		
		// check if the mandatory settings are unchanged.
		for (int i = 0; i < mandList.size(); i++)
		{
			assertTrue(mandList.get(i).equals(combo.getService(i).getMandatory()));
		}
		
		System.out.println("Expected service list: ");
		for (int i = 0; i < sServList.size(); i++) 
			System.out.print(" " + sServList.get(i));
		
		System.out.println("Actual service list: ");
		for (int i = 0; i < combo.getServices().size(); i++)
			System.out.print(" " + combo.getService(i).getService().getName());
		
		//*****This one keeps failing. After printing, realized that the expected service list is wrong! :D*****//
		 // check if the services are unchanged
		for (ComboItem item: combo.getServices()) {
			assertTrue(sServList.contains(item.getService().getName()));
		}
		 

	}

	/**
	 * Creates declared customers in the system
	 * @author Tiffany
	 * @param dataTable contains customer username and password (Strings)
	 */
//	@Given("the following customers exist in the system:")
//	public void the_following_customers_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
//	    // Write code here that turns the phrase above into concrete actions
//	    // For automatic transformation, change DataTable to one of
//	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
//	    //
//	    // For other transformations you can register a DataTableType.
//		for (int i = 0; i < dataTable.height(); i++) {
//			carShop.addCustomer(new Customer(dataTable.cell(i, 0), dataTable.cell(i, 1), carShop));
//		}
//	}

	/**
	 * Logs in a customer with the given username
	 * @author Tiffany
	 * @param string username of the customer
	 */
//	@Given("the user with username {string} is logged in")
//	public void the_user_with_username_is_logged_in(String string) {
//	    // Write code here that turns the phrase above into concrete actions
//		// if there are no customers
//		if (carShop.getCustomers() == null)
//			CarShopApplication.add(new Customer(string, "dummyPass", carShop));
//		else
//			carShop.getCustomer(0).setUsername(string);
//		
//	    try {
//			CarShopController.login(string, "dummyPass");
//		} catch (InvalidInputException e) {
//			e.printStackTrace();
//		}
//		
//	}

}
