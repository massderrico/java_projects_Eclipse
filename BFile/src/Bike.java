/*	COPYRIGHT OF CONCORDIA UNIVERSITY
DEPARTMENT OF ENGINEERING AND COMPUTER SCIENCE

	COMP248 Fall 2021

	Topic: Simple Class
	PROF: Nora Houari
	 
 */

import java.util.Scanner;

public class Bike
{
	private String brand;
	private int wheels;
	private boolean isElectric;
	
	
public Bike() {
	this.brand = null;
	this. wheels = 0;
	// TODO Auto-generated constructor stub
}
public Bike(String brand, int wheels, boolean isElectric) {
	this.brand = brand;
	this.wheels = wheels;
	this.isElectric = isElectric;
	
}



public String getBrand() {
	return brand;
}

public int getWheels() {
	return wheels;
}

public boolean getIsElectric(){
	return isElectric;
}

public void setbrand(String brand) {
	this.brand = brand;
}

public void setwheels(int wheels) {
	this.wheels = wheels;
}

public void setIsElectric(boolean isElectric) {
	this.isElectric = isElectric;
}


public boolean equals(Bike bikeObject) {
	if(this.brand.equals(bikeObject.brand)&& this.wheels == bikeObject.wheels && this.isElectric == bikeObject.isElectric) {
		return true;
	}
	else{
		return false;
	}
}

public boolean bothElectric(Bike bike) {
	if (this.isElectric == true &&  bike.getIsElectric() == true) {
		return true;
	}
	else {
		return false;
	}
}

@Override
public String toString() {
	return "The Bike of brand " + this.brand + " has " + this.wheels + " inch wheels and is electric is "+ this.isElectric;
}

	// ------------------------
	// CODE TO COMPLETE
	// -------------------------
	// Declare instance variables
	
	// Implement default constructor
	
	// Implement 2nd constructor
	
	// Implement Get/Set methods
	
	// Implement equals methods
	
	// Implement bothElectric methods
	
	// Implement toString

	

	/*================================================================================================== */
	/* =====*****-----+++++!!!!! DO NOT ALTER, CHANGE, OR MODIFY THE CODE BELOW !!!!!+++++-----*****===== */
	/* ================================================================================================== */

	public static void main(String[] args) {

		Scanner keyIn = new Scanner (System.in);
		String brand;
		int wheels;
		boolean isElectric;
		System.out.print("what is the brand of this Bike? ");
		brand = keyIn.nextLine();
		System.out.print("what is the size of the wheels ? ");
		wheels = keyIn.nextInt();
		System.out.print("Is it an electric Bike? (true or false) ");
		isElectric = keyIn.nextBoolean();
		Bike t1 = new Bike();
		Bike t2 = new Bike(brand,wheels,isElectric);

		System.out.println("The two Bikes are:");
		System.out.print(t1 + "\n" + t2);

		//Let's set up the 2nd Bike

		System.out.print("\n\nLet's set up the 1st Bike ...\n\tWhat brand is it? ");
		brand = keyIn.nextLine();
		brand = keyIn.nextLine();

		System.out.print("\tWhat is the size of the wheels? ");
		wheels = keyIn.nextInt();
		System.out.print("\tIs it an electric Bike (true or false)? ");
		isElectric = keyIn.nextBoolean();
		t1.setbrand(brand);
		t1.setwheels(wheels);
		t1.setIsElectric(isElectric);
		System.out.println("Bike 1: " + t1);

		System.out.println("Are the 2 Bike objects equal?" 
				+ (t1.equals(t2)?" Yes":" No"));
		System.out.println("Are they both electric Bikes? " + t1.bothElectric(t2));
		t1.setIsElectric((!t1.getIsElectric()));
		
		System.out.print("Now are they both electric Bikes? " + t1.bothElectric(t2));
		keyIn.close();
	}
}

