/*
 * This test file is meant to extend the already provided test cases.
 * 
 * There is no guarantee these tests cover all possible edge cases.
 * There is no guarantee these tests are not themselves flawed.
 * 
 * This file is poorly documented (AKA "The code is self explanatory").
 */

// Package

package assignment2;

// Tests

class CreateEmpty implements Runnable {
	
	public void run () {
		
		Deck d1 = new Deck();
		
		if (d1.head != null) {
			throw new AssertionError("Head of empty deck is not null");
		}
		
		if (d1.numOfCards != 0) {
			throw new AssertionError("Length of empty deck is not 0");
		}
		
		Deck d2 = new Deck(d1);
		
		if (d2.head != null) {
			throw new AssertionError("Head of empty deck is not null");
		}
		
		if (d2.numOfCards != 0) {
			throw new AssertionError("Length of empty deck is not 0");
		}
		
		System.out.println("Test Passed");
		
	}
	
}

class CreateWrong implements Runnable {
	
	public void run () {
		
		Deck d;
		
		try {
			
			d = new Deck(0,1);	
			throw new AssertionError("Should not create impossible deck");
			
		} catch (Exception e) {}
		
		try {
			
			d = new Deck(14,1);	
			throw new AssertionError("Should not create impossible deck");
			
		} catch (Exception e) {}
		
		try {
			
			d = new Deck(1,0);	
			throw new AssertionError("Should not create impossible deck");
			
		} catch (Exception e) {}
		
		try {
			
			d = new Deck(1,5);	
			throw new AssertionError("Should not create impossible deck");
			
		} catch (Exception e) {}
		
		System.out.println("Test Passed");
		
	}
	
}

class ModifySuits implements Runnable {
	
	public void run () {
		
		Deck.suitsInOrder = new String[] {"A", "B"};
		
		Deck d;
		
		try {
			
			d = new Deck(1,4);
			
		} catch (Exception e) {}
		
		Deck.suitsInOrder = new String[] {"clubs", "diamonds", "hearts", "spades"};
		
		System.out.println("Test Passed");
		
	}
	
}

class CreateFull implements Runnable {
	
	public void run () {
		
		Deck d = new Deck(13,4);
		
		if (d.numOfCards != 54) {
			throw new AssertionError("Wrong number of cards in deck");
		}
		
		Deck.Card c = d.head;
		
		for (int i = 0; i < 53; i++) {
			
			if (c.getValue() != i+1) {
				throw new AssertionError("Wrong card value");
			}
			
			c = c.next;
			
		}
		
		if (c.getValue() != 53 ) {
			throw new AssertionError("Wrong card value");
		}
		
		if (c.next != d.head) {
			throw new AssertionError("Loop not working properly");
		}
		
		System.out.println("Test Passed");
		
	}
	
}

class AddCardNo implements Runnable {
	
	public void run () {
		
		Deck d = new Deck(13,4);
		
		Deck.Card c = d.head;
		d.addCard(c);
		
		if (d.numOfCards != 55) {
			throw new AssertionError("Wrong number of cards in deck");
		}
		
		System.out.println("Test Passed");
		
	}
	
}

class FindNoJokers implements Runnable {
	
	public void run () {
		
		Deck d = new Deck();
		
		d.addCard(d.new PlayingCard("", 0));
		d.addCard(d.new PlayingCard("", 0));
		d.addCard(d.new PlayingCard("", 0));
		
		Deck.Joker j = d.locateJoker("red");
		
		if (j != null) {
			throw new AssertionError("No jokers in deck; should have returned null");
		}
		
		System.out.println("Test Passed");
		
	}
	
}

class FullEncrypt implements Runnable {
	
	public void run () {
		
		Deck.gen.setSeed(10);
		
		Deck d = new Deck(5,2);
		d.shuffle();
		
		SolitaireCipher c = new SolitaireCipher(d);
		
		String code = c.encode("Is that you, Bob?");
		
		if (!code.equals("MWIKDVZCKSFP")) {
			throw new AssertionError("Wrong code");
		}
		
		System.out.println("Test Passed");
		
	}
	
}

class FullDecrypt implements Runnable {
	
	public void run () {
		
		Deck.gen.setSeed(10);
		
		Deck d = new Deck(5,2);
		d.shuffle();
		
		SolitaireCipher c = new SolitaireCipher(d);
		
		String code = c.decode("MWIKDVZCKSFP");
		
		if (!code.equals("ISTHATYOUBOB")) {
			throw new AssertionError("Wrong code");
		}
		
		System.out.println("Test Passed");
		
	}
	
}

public class MegaTester {
	 static String[] tests = {
		        "assignment2.CreateEmpty",
		        "assignment2.CreateWrong",
		        "assignment2.ModifySuits",
		        "assignment2.CreateFull",
		        "assignment2.AddCardNo",
		        "assignment2.FindNoJokers",
		        "assignment2.FullEncrypt",
		        "assignment2.FullDecrypt"
		    };
	 public static void main(String[] args) {
	        int numPassed = 0;
	        for(String className: tests)    {
	            System.out.printf("%n======= %s =======%n", className);
	            System.out.flush();
	            try {
	                Runnable testCase = (Runnable) Class.forName(className).getDeclaredConstructor().newInstance();
	                testCase.run();
	                numPassed++;
	            } catch (AssertionError e) {
	                System.out.println(e);
	            } catch (Exception e) {
	                e.printStackTrace(System.out);
	            }
	        }
	        System.out.printf("%n%n%d of %d tests passed.%n", numPassed, tests.length);
	    }
}
