/**COMP250 - Assigmnent1
 * @author Massimo D'Errico - ID:260949564
 */

package assignment1;

public class SeasonalFruit extends Fruit {

	public SeasonalFruit(String name, double weight, int price) {
		super(name, weight, price);
	} 
		public int getCost() { //possibly a better implemetation
			return (int)(super.getCost()*0.85);	
		}
	

}
