package stores;

public class Candy {
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
	
	
}
