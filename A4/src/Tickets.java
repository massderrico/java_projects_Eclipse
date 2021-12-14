
public class Tickets {
	private int numRegularTicket;
	private int numJuniorTicket;
	private int numSeniorTicket;
	private int numDailyTicket;
	private int numWeeklyTicket;

	final static double regularTicket = 3.50;
	final static double juniorTicket = 2.50;
	final static double seniorTicket = 1.00;
	final static double dailyTicket = 10.00;
	final static double weeklyTicket = 40;
	
	public Tickets() {
		
	}
	
	public Tickets(int r, int j, int s, int d, int w) {
		this.numRegularTicket = r;
		this.numJuniorTicket = j;
		this.numSeniorTicket = s;
		this.numDailyTicket =d;
		this.numWeeklyTicket = w;
	
}
	public Tickets(Tickets ticket) {
		this.numRegularTicket = ticket.numRegularTicket;
		this.numJuniorTicket = ticket.numJuniorTicket;
		this.numSeniorTicket = ticket.numSeniorTicket;
		this.numDailyTicket =ticket.numDailyTicket;
		this.numWeeklyTicket = ticket.numWeeklyTicket;
	}
	
	public int getNumRegularTicket() {
		return numRegularTicket;
	}
	public int getNumJuniorTicket() {
		return numJuniorTicket;
	}
	public int getNumSeniorTicket() {
		return numSeniorTicket;
	}
	public int getNumDailyTicket() {
		return numDailyTicket;
	}
	public int getNumWeeklyTicket() {
		return numWeeklyTicket;
	}
	public void setNumRegularTicket(int numRegularTicket) {
		this.numRegularTicket = numRegularTicket;
	}
	public void setNumJuniorTicket(int numJuniorTicket) {
		this.numJuniorTicket = numJuniorTicket;
	}
	public void setNumSeniorTicket(int numSeniorTicket) {
		this.numSeniorTicket = numSeniorTicket;
	}
	public void setNumDailyTicket(int numDailyTicket) {
		this.numDailyTicket = numDailyTicket;
	}
	public void setNumWeeklyTicket(int numWeeklyTicket) {
		this.numWeeklyTicket = numWeeklyTicket;
	}
	
	public void addTickets(int r, int j, int s, int d, int w) {
		this.numRegularTicket += r;
		this.numJuniorTicket += j;
		this.numSeniorTicket += s;
		this.numDailyTicket += d;
		this.numWeeklyTicket += w;
	
}
	public double ticketsTotal() {
		return Tickets.regularTicket*this.numRegularTicket+ Tickets.juniorTicket*this.numJuniorTicket+ Tickets.seniorTicket*this.numSeniorTicket + Tickets.dailyTicket*this.numDailyTicket+Tickets.weeklyTicket*this.numWeeklyTicket;
	}
	
	public String toString(){
		return this.numRegularTicket + " x " + Tickets.regularTicket + " + " + this.numJuniorTicket + " x " + Tickets.juniorTicket + " + " + this.numSeniorTicket + " x " + Tickets.seniorTicket + " + " + this.numDailyTicket + " x " + Tickets.dailyTicket + " + " + this.numWeeklyTicket + " x " + Tickets.weeklyTicket;
		
	}
	
	public boolean equals(Tickets ticket) {
		return this.numRegularTicket == ticket.numRegularTicket && this.numJuniorTicket == ticket.numJuniorTicket && this.numSeniorTicket == ticket.numSeniorTicket && this.numDailyTicket == ticket.numDailyTicket && this.numWeeklyTicket == ticket.numWeeklyTicket;
	}
	
	
}
