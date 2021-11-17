package ca.mcgill.ecse.carshop.persistence;

import ca.mcgill.ecse.carshop.model.CarShop;

public class CarShopPersistence {
	private static String filename = "data.carshop";
	public static void save(CarShop carShop) {
	    PersistenceObjectStream.serialize(carShop);
	}

	public static CarShop load() {
	    PersistenceObjectStream.setFilename(filename);
	    CarShop carShop = (CarShop) PersistenceObjectStream.deserialize();
	    // model cannot be loaded - create empty CarShop
	    if (carShop == null) {
	    	carShop = new CarShop();    
	    }
	    return carShop;
	}
}
