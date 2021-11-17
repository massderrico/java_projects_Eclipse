package Barkery;

public class CupCake extends Cake {
	private Topping t;
	
	public CupCake(String[] i, Topping t) {
		super(i);
		this.t = t;
	}
	public int getPrepTime() {
		return super.getPrepTime() + this.t.getPrepTime() + 5;
	}
	
}

