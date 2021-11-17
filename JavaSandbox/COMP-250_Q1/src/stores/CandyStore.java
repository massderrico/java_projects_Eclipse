package stores;

import java.util.Arrays;

public class CandyStore {
	
	private Candy[] candies;
	public int numOfCandies;
	

	
	public CandyStore(Candy[] candyStock) {
		this.candies = candyStock;
		this.numOfCandies = candyStock.length;
	}
	
	public Candy[] getCandies() {
		return candies;
	}
	
	public void restock(Candy[] moreCadies) {
		Candy[] candies = new Candy[moreCadies.length + numOfCandies];
		for (int i=0; i < candies.length; i++) {
			if (i < moreCadies.length) {
				candies[i]= moreCadies[i];
			}
			else {
				candies[i] = getCandies()[i-moreCadies.length];
			}
			
		}
	numOfCandies = candies.length;
	this.candies = candies;
	}
	
	public static void main(String[] args) {
		Candy p1 = new Candy("p1", "pink");
		Candy p2 = new Candy("p2", "blue");
		Candy p3 = new Candy("p3", "g");
		Candy p4 = new Candy("p4", "g");
		Candy p5 = new Candy("p5", "g");
		Candy[] a = {p1,p2};
		
		CandyStore c1 = new CandyStore(a);
		a[0]= p5;
		System.out.println(Arrays.deepToString(c1.getCandies()));
	}
	
	public int countCandies(String c) {
		int count = 0;
		for (int i=0; i < candies.length; i++) {
			if (candies[i].getColor().equals(c))
				count ++;
		}
		return count;		
	}
	
	
	}
	
	
	

