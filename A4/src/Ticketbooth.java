import java.util.Iterator;

public class Ticketbooth {
	private Tickets ticket;
	private OPUSCard[] opusCards;
 
public Ticketbooth() {
	this.ticket = new Tickets();
}

public Ticketbooth(Tickets t, OPUSCard[] o) {
	this.ticket = t;
	
	this.opusCards = o;
}

public boolean equalValue(Ticketbooth t) {
	return this.ticket.ticketsTotal() == t.ticket.ticketsTotal();
}

public boolean equalTicketNum(Ticketbooth t) {
	return this.ticket.equals(t.ticket);
}
public double getTicketValue() {
	return this.ticket.ticketsTotal();
	
}

public int getNumOpus() {
if(opusCards == null) {
	return 0;
}
	return this.opusCards.length;
}

public int addOpus(OPUSCard opusCard) {
	if(this.opusCards == null || this.opusCards.length == 0) {
		OPUSCard[] o = new OPUSCard[1];
		o[0] = opusCard;
		this.opusCards = o.clone();
	}
	else {
		OPUSCard[] o = new OPUSCard[opusCards.length+1];
		for (int i = 0; i < opusCards.length; i++) {
			o[i] = opusCards[i]; 
		}
		o[opusCards.length+1] = opusCard;
		this.opusCards = o.clone();
		
	}
	
	return this.opusCards.length; 
}

public boolean removeOpus(int index) {
	boolean removed = false;
	
	
	if(this.opusCards == null || this.opusCards.length == 0) {
		return false;
	}	
	else {	
	
	OPUSCard[] copy = new OPUSCard[this.opusCards.length - 1];

	for (int i = 0, j = 0; i < this.opusCards.length; i++) {
		  if (i != index) {
		  copy[j++] = this.opusCards[i];
		  }
	}
	this.opusCards = copy.clone();
	removed = true;

	}
	return removed;
}

public void updateOpus(int index, int newMonth, int newYear) {
	
	
	if(this.opusCards == null || this.opusCards.length == 0) {
		return;
	}	
	else {
			this.opusCards[index].setExpiryMonth(newMonth);
			this.opusCards[index].setExpiryYear(newYear);
		}
		
}


public double addTickets(int r, int j, int s, int d, int w) {
	this.ticket.addTickets(r, j, s, d, w);
	
	return this.ticket.ticketsTotal();
}

public boolean equals(Ticketbooth ticketbooth) {
	return this.ticket.ticketsTotal() == ticketbooth.ticket.ticketsTotal() && this.ticket.equals(ticketbooth.ticket);
}


public String toString() {
	String str = this.ticket.toString();
	if(this.opusCards == null || this.opusCards.length == 0) {
		str += "\n No OPUS cards";
	}
	else {
		for (int i = 0; i < opusCards.length; i++) {
			str += "\n" + opusCards[i].toString();
	}
		
	}
	return str;
}

public String toStringTickets() {

	return this.ticket.toString();
}

}
