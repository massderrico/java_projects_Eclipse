import java.util.Scanner;

public class Driver {

	static Scanner scanner = new Scanner(System.in);
	static boolean running = true;
	static Ticketbooth[] ticketbooths;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tickets t0 = new Tickets(5,1,0,1,1);
		OPUSCard o01 = new OPUSCard("STL","M. Cola", 8, 2024);
		OPUSCard o02 = new OPUSCard("RTL","C. Venus", 3,2025);
		OPUSCard[] op0 = {o01,o02};
		
		Ticketbooth tb0 = new Ticketbooth(t0, op0);
		
		Tickets t1 = new Tickets(t0);
		OPUSCard o11 = new OPUSCard(o01);
		OPUSCard o12 = new OPUSCard("STM", "Z.Poker", 10,2022);
		OPUSCard[] op1 = {o11,o12};
		
		Ticketbooth tb1 = new Ticketbooth(t1, op1);
		
		Tickets t2 = new Tickets(5,5,5,5,5);
		OPUSCard o21 = new OPUSCard("RTL","F. Max", 12, 2021);
		OPUSCard o22 = new OPUSCard("REM","T. Flona", 4, 2023);
		OPUSCard o23 = new OPUSCard("TRAMREM","S. EaFL", 11, 2021);
		OPUSCard[] op2 = {o21,o22,o23};
		
		Ticketbooth tb2 = new Ticketbooth(t2,op2);
		
		Tickets t3 = new Tickets(0,1,1,1,1);
		
		Ticketbooth tb3 = new Ticketbooth(t3, null);
		
		Tickets t4 = new Tickets(0,1,1,1,1);
		Ticketbooth tb4 = new Ticketbooth(t4, null);
		
		Ticketbooth[] ticketbooths1 =  {tb0,tb1,tb2,tb3,tb4};
		ticketbooths = ticketbooths1;
		System.out.println("=================================================================\n");
		System.out.println("  Welcome to Concordia 2021 Fall Geek's Ticketbooth application");
		System.out.println("\n=================================================================\n");
		
		while (running == true) {
			printChoices();
			scanner.reset();
			int c = scanner.nextInt();
			
			boolean valid = false;
			
			while(valid == false) {
			if(c >9 || c<0) {
				System.out.println("Sorry that choice is not valid. Try Again.");
				printChoices();
				c = scanner.nextInt();
			}
			else {
				valid = true;	
				break;
			}
			}
			goodChoice(c);
		}
		
		
	}
	
	
	public static void printChoices() {
		
	String choice = 
	"What would you like to do" +
	"\n\t 1. See the content of all Ticketbooths" +
	"\n\t 2. See the content of one Ticketbooth" +
	"\n\t 3. List Ticketbooths with same amout of tickets' values" +
	"\n\t 4. List Ticketbooths with same Tickets amount" +
	"\n\t 5. List Ticketbooths with the same amout of tickets values and same number of OPUS cards" +
	"\n\t 6. Add a OPUS card to an existing Ticketbooth" +
	"\n\t 7. Remove an existing OPUS card from a Ticketbooth" +
	"\n\t 8. Update the expiry date of an existing OPUS card" +
	"\n\t 9. Add Tickets to a Ticketbooth" +
	"\n\t 0. To quit"+
	"\nPlease enter your choice and press <Enter>";
	System.out.println(choice);
	

	}
		
	public static void goodChoice(int c) {
		
		switch (c) {
		case 1: {
			choice1();
			break;
		}
		case 2:{
			choice2();
			break;
		}
		case 3:{
			choice3();
			break;
		}
		case 4:{
			choice4();
			break;
		}
		case 5:{
			choice5();
			break;
		}
		case 6:{
			choice6();
			break;
		}
		case 7:{
			choice7();
			break;
		}
		case 8:{
			choice8();
			break;
		}
		case 9:{
			choice9();
			break;
		}
		case 0:{
			choice0();
			break;
		}
		}
		
	}
	
	public static void choice1() {
		scanner.reset();
		System.out.println("Content of each Ticketbooth");
		
		for (int i = 0; i < ticketbooths.length; i++) {
			System.out.println("Ticketbooth #"+i );
			System.out.println(ticketbooths[i].toString() );
			System.out.println("");
		}
		return;
	}
	
	public static void choice2() {
		scanner.reset();
		System.out.println("Which Ticketbooth do you want to see the content of? Enter a number 0 to "+(ticketbooths.length-1));
		int c = scanner.nextInt();
		boolean valid = false;
		
		while(valid == false) {
		if (c<0 || c>= ticketbooths.length) {
			System.out.println("Sorry but there is no Ticketbooth number "+ c);	
			System.out.println("--> Try again. Enter a number 0 to "+(ticketbooths.length-1));
			c = scanner.nextInt();
		}
		else {
			valid = true;
			
		}
		}
		System.out.println(ticketbooths[c].toString());
		
		
		
	}
	
	public static void choice3() {
		scanner.reset();
		int[][] a = sameValue();
		for (int i = 0; i < a.length; i++) {
			if(a[i][0] != -1) {
				System.out.println("Ticketbooths "+ a[i][0]+ " and " + a[i][1]+ " both have " + ticketbooths[a[i][0]].getTicketValue()+ "\n");
			}
		}
	}
	
	public static void choice4() {
		scanner.reset();
		int[][] a = sameNum();
		for (int i = 0; i < a.length; i++) {
			if(a[i][0] != -1) {
				System.out.println("Ticketbooths "+ a[i][0]+ " and "+ a[i][1]+ " both have " + ticketbooths[a[i][0]].toStringTickets() + "\n");
			}
		}
	}
	
	public static void choice5() {
		scanner.reset();
		int[][] a = sameNumAndOpus();
		for (int i = 0; i < a.length; i++) {
			if(a[i][0] != -1) {
				System.out.println("\nTicketbooths "+ a[i][0]+ " and "+ a[i][1]);
			}
		}
	}
	
	public static void choice6() {
		scanner.reset();
		System.out.println("Which Ticketbooth do you want to add an OPUS card to? Enter a number 0 to "+(ticketbooths.length-1));
		int c = scanner.nextInt();
		boolean valid = false;
		
		while(valid == false) {
		if (c<0 || c>= ticketbooths.length) {
			System.out.println("Sorry but there is no Ticketbooth number "+ c);	
			c = scanner.nextInt();
		}
		else {
			valid = true;
			
		}
		}
		System.out.println("--> Type of OPUS card (STL, RTL, etc..): ");
		String type = scanner.next();
		System.out.println("--> Full name on OPUS card: ");
		String name = scanner.next();
		String name2 = scanner.next();
		System.out.println("Expiry month number and year (seperate by a space)");
		int m = scanner.nextInt();
		int y = scanner.nextInt();
		
		OPUSCard opusCard =new OPUSCard(type, name+" "+name2, m, y);
		int n = ticketbooths[c].addOpus(opusCard);
		System.out.println("You now have "+ n + " OPUS cards");
		return;
		
	}
	
	public static void choice7() {
		scanner.reset();
		System.out.println("\nWhich Ticketbooth do you want to remove an OPUS card to? Enter a number 0 to "+(ticketbooths.length-1));
		int c = scanner.nextInt();
		boolean valid = false;
		
		while(valid == false) {
		if (c<0 || c>= ticketbooths.length) {
			System.out.println("\nSorry but there is no Ticketbooth number "+ c);	
			c = scanner.nextInt();
		}
		else {
			valid = true;
		}
		}
		if(ticketbooths[c].getNumOpus() == 0 ) {
			System.out.println("\nSorry that Ticketbooth has no cards");
			return;
		}
		System.out.println("\nEnter card number 0 to "+ (ticketbooths[c].getNumOpus()-1));
		int o = scanner.nextInt();
		boolean valid2 = false;
		
		while(valid2 == false) {
		if (o<0 || o> ticketbooths[c].getNumOpus()-1) {
			System.out.println("\nSorry but there is no card number "+ o);	
			o = scanner.nextInt();
		}
		else {
			valid2 = true;
		}
		
		}
		boolean removed = ticketbooths[c].removeOpus(o);
		if(removed == true) {
			System.out.println("Card sucessfully removed");
		}
	}
	
	public static void choice8() {
		scanner.reset();
		System.out.println("\n Which Ticketbooth do you want to remove an OPUS card to? Enter a number 0 to "+(ticketbooths.length-1));
		int c = scanner.nextInt();
		boolean valid = false;
		
		while(valid == false) {
		if (c<0 || c>= ticketbooths.length) {
			System.out.println("\n Sorry but there is no Ticketbooth number "+ c);	
			c = scanner.nextInt();
		}
		else {
			valid = true;
		}}
		
		if(ticketbooths[c].getNumOpus() == 0 ) {
			System.out.println("\n Sorry that Ticketbooth has no cards");
			return;
		}
		System.out.println("\n Enter card number 0 to "+ (ticketbooths[c].getNumOpus()-1));
		int o = scanner.nextInt();
		boolean valid2 = false;
		
		while(valid2 == false) {
		if (o<0 || o> ticketbooths[c].getNumOpus()-1) {
			System.out.println("\n Sorry but there is no card number "+ o);	
			o = scanner.nextInt();
		}
		else {
			valid2 = true;
		}}
		
		System.out.println("\n--> Enter new expiry month number and year(seperate by a space)");
		int m = scanner.nextInt();
		int y = scanner.nextInt();
		
		ticketbooths[c].updateOpus(o, m, y);
		System.out.println("\nExpiry date updated");
		
	}
	
	public static void choice9() {
		scanner.reset();
		System.out.println("\n Which Ticketbooth do you want to add tickets to? Enter a number 0 to "+(ticketbooths.length-1));
		int c = scanner.nextInt();
		boolean valid = false;
		
		while(valid == false) {
		if (c<0 || c>= ticketbooths.length) {
			System.out.println("\n Sorry but there is no Ticketbooth number "+ c);	
			c = scanner.nextInt();
		}
		else {
			valid = true;
		}
		}
		
	System.out.println("\n How many regular,junior, senior. daily and weekly tickets do you want to add?");
	System.out.println("\n Enter 5 numbers seperated by a space");
	int r = scanner.nextInt();
	int j = scanner.nextInt();
	int s = scanner.nextInt();
	int d = scanner.nextInt();
	int w = scanner.nextInt();
	
	double v = ticketbooths[c].addTickets(r, j, s, d, w);
	System.out.println("You know have "+ v);
	
		
	}
	
	public static void choice0() {
		scanner.reset();
		running = false;
		System.out.println("\nThank you for using Concordia Fall Geek's Ticketbooth application");
		scanner.close();
	}
	
	public static int[][] sameValue() {
		int[][] sameValue= new int[ticketbooths.length*ticketbooths.length][2];  
		int count = 0;
		
		for (int i = 0; i < ticketbooths.length; i++) {
			for (int j = 0; j < ticketbooths.length; j++) {
				if(ticketbooths[i].equalValue(ticketbooths[j]) && i != j) {
					sameValue[count][0] = i;
					sameValue[count][1] = j;
					count += 1;
				}
			}
		}
		
		System.out.println();
		
		for (int i = 0; i < sameValue.length; i++) {
			for (int j = 0; j < sameValue.length; j++) {
				if(sameValue[i][0] == sameValue[j][1] && sameValue[i][1] == sameValue[j][0]) {
					sameValue[j][0] = -1;
					
				}
			}
		}
		
		return sameValue;
		
	}
	
	public static int[][] sameNum() {
		int[][] sameNum= new int[ticketbooths.length*ticketbooths.length][2];  
		int count = 0;
		
		for (int i = 0; i < ticketbooths.length; i++) {
			for (int j = 0; j < ticketbooths.length; j++) {
				if(ticketbooths[i].equalTicketNum(ticketbooths[j]) && i !=j) {
					sameNum[count][0] = i;
					sameNum[count][1] = j;
					count++;
				}
			}
		}
		
		for (int i = 0; i < sameNum.length; i++) {
			for (int j = 0; j < sameNum.length; j++) {
				if(sameNum[i][0] == sameNum[j][1] && sameNum[i][1] == sameNum[j][0]) {
					sameNum[j][0] = -1;
					
				}
			}
		}
		
		return sameNum;
		
	}
	
	public static int[][] sameNumAndOpus() {
		int[][] sameValue= new int[ticketbooths.length*ticketbooths.length][2];  
		int count = 0;
		
		for (int i = 0; i < ticketbooths.length; i++) {
			for (int j = 0; j < ticketbooths.length; j++) {
				if(ticketbooths[i].equalTicketNum(ticketbooths[j]) && ticketbooths[i].getNumOpus() == ticketbooths[j].getNumOpus() && i !=j) {
					sameValue[count][0] = i;
					sameValue[count][1] = j;
					count++;
				}
			}
		}
		
		for (int i = 0; i < sameValue.length; i++) {
			for (int j = 0; j < sameValue.length; j++) {
				if(sameValue[i][0] == sameValue[j][1] && sameValue[i][1] == sameValue[j][0]) {
					sameValue[j][0] = -1;
					
				}
			}
		}
		
		return sameValue;
		
	}
}
