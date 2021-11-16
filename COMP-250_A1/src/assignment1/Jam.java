/**COMP250 - Assigmnent1
 * @author Massimo D'Errico - ID:260949564
 */

package assignment1;

public class Jam extends MarketProduct {
	private int numOfJars;
	private int pricePerJar;
	
	/**
	 * 
	 * @param name-name of Jam
	 * @param num-number of jam jars
	 * @param price-price per jar
	 */
	public Jam(String name, int num, int price) {
		super(name);
		this.numOfJars = num;
		this.pricePerJar = price;
	}

	//multiplies number of jar by its price and returns total cost
	public int getCost() {
		int cost = numOfJars*pricePerJar;
		return cost;
	}

	//returns booleen of whether or not two objects are equal
	public boolean equals(Object obj) {
		if (obj instanceof Jam) { 
			Jam jamObject = (Jam)obj;	
			return (jamObject.getName() == this.getName() 
				&& jamObject.numOfJars == this.numOfJars && jamObject.pricePerJar == this.pricePerJar);
		}
		return false;
	}
	
	
}
