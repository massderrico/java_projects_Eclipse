/**COMP250 - Assigmnent1
 * @author Massimo D'Errico - ID:260949564
 */

package assignment1;

public class Fruit extends MarketProduct{
	private double weight;
	private int pricePerKg;
	
	/**
	 * Fruit Construtor
	 * @param name- name of fruit
	 * @param weight- weight of fruit
	 * @param price-price per kg of fruit
	 */
	public Fruit(String name, double weight, int price) {
		super(name);
		this.weight = weight;
		this.pricePerKg = price;
	}
	
	// multiply the weight by price per kg to return total cost
	public int getCost() {
		double cost = this.weight*this.pricePerKg;
		return (int)cost;
	}
	
	//returns booleen of whether or not two objects are equal
	public boolean equals(Object obj) {
		if (obj instanceof Fruit) { 
			Fruit fruitObject = (Fruit)obj;	
			return (fruitObject.getName() == this.getName() 
				&& fruitObject.weight == this.weight && fruitObject.pricePerKg == this.pricePerKg);
		}
		return false;
	}
	
}