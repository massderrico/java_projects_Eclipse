/**COMP250 - Assigmnent1
 * @author Massimo D'Errico - ID:260949564
 */

package assignment1;

public class Customer {
	private String name;
	private int balance;
	private Basket basket;
	
	/**
	 * 
	 * @param name- name of customer 
	 * @param balance- balance of customer
	 */
	public Customer(String name, int balance) {
		this.name = name;
		this.balance = balance;
		this.basket = new Basket();
	}
	
	//return the name of customer
	public String getName() {
		return this.name;
	}
	
	//returns the balance of the customer
	public int getBalance() {
		return this.balance;
	}
	
	//returns a reference to the basket
	public Basket getBasket() {
		return this.basket;
	}
	
	//adds funds to a customers balance
	public int addFunds(int i) {
		if(i < 0) {
			throw new IllegalArgumentException("Must add positve funds");
		}
		else {
			this.balance += i;
		}
		return this.balance;
	}
	
	//adds a product to the customer's basket
	public void addToBasket(MarketProduct mkp) {
		this.basket.add(mkp);
	}
	
	//removes a product from the basket
	public boolean removeFromBasket(MarketProduct mkp) {
		return this.basket.remove(mkp);
	}
	
	//checks the balance of the customers to see if purchase is possible
	//if it possible, balance is updated and receipt is printed
	public String checkOut() {
		String receipt = "";
		if (this.basket.getTotalCost() > this.balance) {
			throw new IllegalStateException("Insufficient Funds");
		}
		else {
			this.balance -= this.basket.getTotalCost();
			receipt = this.basket.toString();
			this.basket.clear();
			
		}
		return receipt;
	}
}
