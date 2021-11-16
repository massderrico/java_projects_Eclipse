package E4;


public class Sharer{
   private int    sum;
   private String  ID;
   private Sharer other;

	public Sharer(String ID, int sum){ 
		this.ID = ID; 
		this.sum = sum; 
		}
	
	void share(int n){ 
		other.sum += n/2; 
		this.sum += n - n/2; 
		} // give half, keep half
	
	public String toString(){
		return ID + " " + sum + " ";
		}
	public int getSum() {
		return this.sum;
	}
	public String getID() {
		return this.ID;
	}
	public Sharer getSharer() {
		return this.other;
	}
}


class Giver extends Sharer{
	
	public Giver(String ID, int sum) { 
		super(ID, sum); 
		}
	void share(int n) { 
		getS += n; 
		this.sum -= n; 
		}
//give to other
}

class Taker extends Sharer{
	public Taker(String ID, int sum) { 
	super(ID, sum); 
	}
	void share(int n) { 
		other.sum -= n; 
		this.sum += n; 
		} // take from other }
	}

