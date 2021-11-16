package assignment2;



import assignment2.Deck.Card;
//import assignment2.Deck.PlayingCard;
//import assignment2.SolitaireCipher;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		
		Deck d1 = new Deck(5,2);
		d1.printDeck("Start Deck");
		Deck.gen.setSeed(10);
		d1.shuffle();
		d1.printDeck("Shuffled Deck");
//		System.out.println("");
		//d1.countCut();
		//System.out.println("\n Gen 1\n");
		for (int i=0; i< 12;i++) {
		System.out.println(d1.generateNextKeystreamValue());
		}
		//System.out.println(d1.generateNextKeystreamValue());
		//System.out.println(d1.generateNextKeystreamValue());
		
		
		/*
		
		Card first = d1.head;
		Card second = d1.head.next;
		Card third = second.next;
		Card fourth = d1.head.next.next.next;
		Card fifth = fourth.next;
		Card last = d1.head.prev;
		
	
		d1.tripleCut(fourth,fifth);
		System.out.println("\n Cut 4\n");
		first = d1.head;
		second = d1.head.next;
		third = second.next;
		fourth = d1.head.next.next.next;
		fifth = fourth.next;
		last = d1.head.prev;
		
		d1.tripleCut(third,fourth);
		System.out.println("\n Cut 5\n");
		first = d1.head;
		second = d1.head.next;
		third = second.next;
		fourth = d1.head.next.next.next;
		fifth = fourth.next;
		last = d1.head.prev;
		d1.tripleCut(second,fifth);
		System.out.println("\n Cut 5\n");
		first = d1.head;
		second = d1.head.next;
		third = second.next;
		fourth = d1.head.next.next.next;
		fifth = fourth.next;
		last = d1.head.prev;
		d1.tripleCut(third,fifth);
		System.out.println("\n Cut 6\n");
		first = d1.head;
		second = d1.head.next;
		third = second.next;
		fourth = d1.head.next.next.next;
		fifth = fourth.next;
		last = d1.head.prev;
		d1.tripleCut(fifth,last);
		*/
//		SolitaireCipher cipher = new SolitaireCipher(d1);
//		int[] i = {4,4,15, 3, 3, 2, 1, 14, 16, 17, 17, 14};
//		System.out.println(cipher.encode("Is that you, Bob?", i));
//		System.out.println(cipher.decode("MWIKDVZCKSFP", i));
//		for (int i =0;i <5;i++) {
//			d1.tripleCut(second);
//			d1.tripleCut(fourth, last);
//		}
//		d1.tripleCut(second,fourth);
//		d1.moveCard(second, 4);
//		d1.moveCard(fourth, 55);
//		d1.moveCard(second, 10);
//		d1.moveCard(first, 17);
//		d1.moveCard(second, 1);
//		
//		d1.shuffle();
//		d1.shuffle();
//		d1.shuffle();
//		d1.shuffle();
//		d1.shuffle();
//		d1.shuffle();
		
		


		//Card joker = d1.locateJoker("black");
		//System.out.println(joker.toString());
//		System.out.println("");
//		System.out.println(d1.generateNextKeystreamValue());
//		System.out.println("");
//		System.out.println(d1.generateNextKeystreamValue());
//		System.out.println("");
//		System.out.println(d1.generateNextKeystreamValue());
		
		
		
		}
	
		
	}


