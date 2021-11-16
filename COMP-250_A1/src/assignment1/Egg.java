/**COMP250 - Assigmnent1
 * @author Massimo D'Errico - ID:260949564
 */

package assignment1;

public class Egg extends MarketProduct {
	
	private int numOfEggs; //quantity of eggs
	private int pricePerDozen; //price per dozen of eggs
	
	
	/**
	 * 
	 * @param name-name of the eggs
	 * @param num- number of eggs
	 * @param price- price per dozen
	 */
	public Egg(String name, int num, int price) {
		super(name);
		this.numOfEggs = num;
		this.pricePerDozen = price;
	}	
	
	//calculates the cost of eggs
	public int getCost() {
		double cost = (this.pricePerDozen/12.0 *numOfEggs);
		return (int)cost;
	}

	//returns booleen of whether or not two objects are equal
	public boolean equals(Object obj) {
		if (obj instanceof Egg) { 
			Egg eggObject = (Egg)obj;	
			return (eggObject.getName() == this.getName() 
				&& eggObject.numOfEggs == this.numOfEggs && eggObject.pricePerDozen == this.pricePerDozen);
		}
		return false;
	}

	
	
}
