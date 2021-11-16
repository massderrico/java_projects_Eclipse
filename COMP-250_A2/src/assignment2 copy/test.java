package assignment2;



import assignment2.Deck.Card;
import assignment2.Deck.PlayingCard;


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		Deck.gen.setSeed(10);
		Deck d1 = new Deck(5,2);
		d1.printDeck("Start Deck");
		d1.shuffle();
		System.out.println("");
		System.out.println(d1.generateNextKeystreamValue());
		System.out.println("");
		System.out.println(d1.generateNextKeystreamValue());
		System.out.println("");
		System.out.println(d1.generateNextKeystreamValue());
		
		
		
		}
	
		
	}


