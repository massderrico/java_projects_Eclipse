package stores;

public class items  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
	
}
public static class Candy {
	private String name;
	public String color;
	
	public Candy(String n, String c) {
		this.name = n;
		this.color = c;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public void setColor(String c) {
		this.color = c;
	}
	
	public boolean isChewier(Candy c) {
		if(c.color == "g") {
			return true;
		}
		else return false;
	}
	public static Candy[] getSourCandies(String c,int i) {
		Candy[] g = new Candy[10];
		return g;
	}
	
}
}
