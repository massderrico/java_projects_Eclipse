
public class OPUSCard {
	private String opusType;
	private String name;
	private int expiryMonth;
	private int expiryYear;
	
	public OPUSCard(){	//default constructor
	}
	
	public OPUSCard(String t, String n, int m, int y){
		this.opusType = t;
		this.name = n;
		if(m >=1 && m<=12) { //sets expiryMonth to zero if m is not between 1 and 12
			this.expiryMonth =m;
		}
		else {
			this.expiryMonth = 0;
		}
		this.expiryYear = y;
		
	}
	
	public OPUSCard(OPUSCard opusCard) { //copy Constructor
		this.opusType = opusCard.opusType;
		this.name = opusCard.name;
		this.expiryMonth = opusCard.expiryMonth;
		this.expiryYear = opusCard.expiryYear;
	}
	
	public String getOpusType() { //getter method for opus type 
		return opusType;
	}
	public String getName() {  //getter method for name
		return name;
	}
	
	public int getExpiryMonth() { //getter method for expiry month
		return expiryMonth;
	}
	public int getExpiryYear() { //getter method for expiry year
		return expiryYear;
	}
	
	public void setExpiryMonth(int m) { //setter for expiry month
		if(m >=1 && m<=12) { //sets expiryMonth to zero if m is not between 1 and 12
			this.expiryMonth =m;
		}
		else {
			this.expiryMonth = 0;
	}
	}
	public void setExpiryYear(int expiryYear) { //setter for expiry year
		this.expiryYear = expiryYear;
	}
	
	public String toString() {
		
		String str = this.opusType + "-" +this.name + "-";
		
		if(this.expiryMonth <10) {
			str += "0"+this.expiryMonth;
		}
		else {
			str += this.expiryMonth;
		}
		str += "/";
		str += this.expiryYear;
		
		return str;
	}
	
	public boolean equals(OPUSCard opusCard) {  
		return this.name.equals(opusCard.name) && this.opusType.equals(opusType) && this.expiryMonth == opusCard.expiryMonth && this.expiryYear == opusCard.expiryYear;
	}
	
}
