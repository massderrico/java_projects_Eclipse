/**COMP250 - Assigmnent1
 * @author Massimo D'Errico - ID:260949564
 */

package assignment1;

public class Basket {
	private MarketProduct[] listMarketProducts;
	
	/**
	 * Basket constructor
	 */
	public Basket() {
		this.listMarketProducts =  new MarketProduct[0];
	}
	
	// returns a copied array of the products in the basket
	public MarketProduct[] getProducts(){
		MarketProduct[] listProductsCopy = this.listMarketProducts.clone();
		return listProductsCopy;
	
	}
	
	//adds the inputed MarketProduct in the basket
	public void add(MarketProduct mkp) {
		MarketProduct[] biggerArray = new MarketProduct[this.listMarketProducts.length+1];
		if (this.listMarketProducts.length > 0) {
			for (int i = 0;i < this.listMarketProducts.length; i++) {
				biggerArray[i] = this.listMarketProducts[i];
			}
		}
			biggerArray[this.listMarketProducts.length] = mkp;
			this.listMarketProducts = biggerArray.clone();	
	}
	
	//removes a product from the basket
	public boolean remove(MarketProduct mkp) {
		boolean removed = false;
		MarketProduct[] reducedArray = new MarketProduct[this.listMarketProducts.length-1]; //make a array that is one smaller than the original
		for (int i = 0; i < this.listMarketProducts.length;i++) { //loops through MarketProduct list to find if any products are equal to inputed product
			if(this.listMarketProducts[i]!= null) {
				if(this.listMarketProducts[i].equals(mkp)) { //if the product is equal, then its reference is set to null
					this.listMarketProducts[i] = null;
					for (int j = i; j< (this.listMarketProducts.length-1); j++) { //shifts all products after removed product down by one index
						listMarketProducts[j] = listMarketProducts[j+1];
					}
					listMarketProducts[this.listMarketProducts.length-1] = null; //sets the last index to null since an item got removed 
					removed = true;
					break;
				}
			}
		}
		for (int i = 0; i < reducedArray.length;i++) { //reduces the size of array by one so that the last null reference is removed(this insures that the number of products in the basket is the length of the basket)
			reducedArray[i] = this.listMarketProducts[i];
		}
		this.listMarketProducts = reducedArray.clone();
		return removed;
	}
	
	//empies the basket
	public void clear() {
		this.listMarketProducts = new MarketProduct[0];
	}
	
	//return the number of products in the basket
	public int getNumOfProducts(){
		int count = 0;
		for (MarketProduct product: this.listMarketProducts) {
			if (product != null) {
				count++;
			}
		}
		return count;
	}
	
	//calculates and returns the subtotal of the products in the basket
	public int getSubTotal() {
		int subtotal = 0;
		for (MarketProduct product: this.listMarketProducts) {
			if (product != null) {
				subtotal += product.getCost();
			}
		}
		return subtotal;
	}
	
	//calulates and returns the total tax of the products in the basket
	public int getTotalTax() {
		int tax = 0;
		double taxPercentage = 0.15;
		for (MarketProduct product: this.listMarketProducts) {
			if (product != null) {
				if(product instanceof Jam) {
					tax += (int)(product.getCost()*taxPercentage);
				}
			}
		}
		return tax;
	}
	
	//sums the subtotal and totaltax and returns the totalcost
	public int getTotalCost() {
		int totalCost = this.getSubTotal() + this.getTotalTax();
		return totalCost;
	}
	
	//check to see if any int are less than or equal to zero and covert it into "-"
	//if not equal or less than 0 , method converts the cost in cents into dollars 
	private String toDollars(int i) {
		String dollars = "";
		if (i <= 0) {
			dollars = "-";
		}
		else {
			dollars = String.format("%.2f",i/100.00);
		}
		return dollars;
	}
	
	//prints a receipt of all items in this basket
	public String toString() {
		String receipt = "";
		for (MarketProduct product: this.listMarketProducts) {
			if (product!= null) {
				receipt+= product.getName() + "\t" + this.toDollars(product.getCost()) + "\n";	
				
			}
		}
			receipt += "\nSubtotal\t" +this.toDollars(this.getSubTotal()) + "\n";
			receipt += "Total Tax\t" + this.toDollars(this.getTotalTax()) + "\n" + "\n";
			receipt += "Total Cost\t" + this.toDollars(this.getTotalCost());
		
		return receipt;
	}
	
	
}
