
public abstract class IncomeTaxPayer {
	private static int currentMaxID;
	private int taxID;
	private String name;
	private double income;
	
	public IncomeTaxPayer(String name) {
		this.name = name;
		this.taxID = currentMaxID +1;
	}
	
	public static int getCurrentMaxTaxID() {
		return currentMaxID;
		
	}
	
	public int getTaxID() {
		return this.taxID;
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getIncome() {
		return this.income;
		
	}
	
	public void setIncome(double income) {
		
	}
	
	public String toString() {
		String bill = "";	
		return bill;
		
	}
	
	public boolean equals(Object obj){
		if (obj instanceof IncomeTaxPayer){
			IncomeTaxPayer objj = (IncomeTaxPayer)obj;
			if (objj.taxID == this.taxID) {
				return true;
			}
		}
		
		return false;
	}
	
	abstract double calculateIncomeTax() {
		return ;
	}
}
