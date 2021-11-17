package Barkery;

public class BakeryTest {
	public static void main(String[] args) {
		String[] ing = {"hi"};
		Topping top = new Topping();
		CupCake cup = new CupCake(ing,top);
		System.out.println(cup.getPrepTime());
	}
}
