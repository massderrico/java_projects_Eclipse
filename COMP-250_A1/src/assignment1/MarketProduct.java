/**COMP250 - Assigmnent1
 * @author Massimo D'Errico - ID:260949564
 */

package assignment1;

public abstract class MarketProduct {
	private String name; //private name field 
	
	/** MarketProduct Constructor
	 * @param name - name of marketproduct
	 */
	public MarketProduct(String name) {
//		if (name.trim().length() != 0 || name != null) {
			this.name = name;
//		}
//		else {
//			
//		}
	}
	 
	// returns name of product
	public final String getName() {
		return this.name;
	}
	
	//returns cost of product(declared in every subclass of )
	public abstract int getCost();
	
	//returns a boolean of whether two objects are equal
	public abstract boolean equals(Object obj);
}
