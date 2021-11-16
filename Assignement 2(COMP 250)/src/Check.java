
public class Check {
	private double menuPrice;
	private double salesTax;
	private double tip;
	
	public Check(double menuPrice) {
		this.menuPrice = menuPrice;
	}
	
	public double getMenuPrice() {
		return menuPrice;
	}
	public double getSalesTax() {
		return salesTax;
	}
	public double getTip() {
		return tip;
	}
	public void setTipByPct(double tipPct) {
		this.tip = tipPct;
	}
	
	
}
