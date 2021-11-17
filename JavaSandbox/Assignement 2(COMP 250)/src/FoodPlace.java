
public abstract class FoodPlace {

	private int currentMaxFoodPlaceID;
	private int foodPlaceID;
	private String name;
	private double fixedCosts;
	private double totalSalesTax;
	
	public FoodPlace(String name, double fixedCosts, Owner owner) {
		this.name = name;
		this.fixedCosts = fixedCosts;
	}
	
	public boolean equals(Object obj){
		if (obj instanceof FoodPlace){
			FoodPlace objj = (FoodPlace)obj;
			if (objj.foodPlaceID == this.foodPlaceID) {
				return true;
			}
		}
		
		return false;
		
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
