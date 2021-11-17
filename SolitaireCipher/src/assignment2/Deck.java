package assignment2;

import java.util.Random;

import assignment2.Deck.Card;

public class Deck {
 public static String[] suitsInOrder = {"clubs", "diamonds", "hearts", "spades"};
 public static Random gen = new Random();

 public int numOfCards; // contains the total number of cards in the deck
 public Card head; // contains a pointer to the card on the top of the deck

 /* 
  * TODO: Initializes a Deck object using the inputs provided
  */
 public Deck(int numOfCardsPerSuit, int numOfSuits) {
  if(numOfCardsPerSuit < 1 || numOfCardsPerSuit > 13 || numOfSuits <1 || numOfSuits > suitsInOrder.length) {
	  throw new IllegalArgumentException("Number of cards per suit must be between 1 and 13 and number of suits must be between 1 and "+ suitsInOrder.length );
  }
  else {
	  this.numOfCards = 0;
	  this.head = null;
	  for(int i = 1; i <= numOfSuits; i++) {
		  for(int j = 1; j <= numOfCardsPerSuit; j++) {
			  Card newCard = new PlayingCard(suitsInOrder[i-1], j);
			  this.addCard(newCard);
			  
	  }
  }
	  Card redJoker = new Joker("red");
	  Card blackJoker = new Joker("black");
	  addCard(redJoker);
	  addCard(blackJoker);
 }

  
  
 }
private void printDeck(String title) {
	System.out.println("");
	
	System.out.println("Head: "+this.head.toString() );
	System.out.println("Head.Next: "+this.head.next.toString() );
	System.out.println("Head.Prev: "+this.head.prev.toString() );
	
	System.out.println(title+" (forward)");
	Card testnext = this.head;
	for(int i=0; i<this.numOfCards;i++) {
		 System.out.println(testnext.toString());
		 testnext = testnext.next; 
	 }
	this.printDeckBack(title);
}

private void printDeckBack(String title) {
	System.out.println("");
	System.out.println("Head: "+this.head.toString() );
	System.out.println("Head.Next: "+this.head.next.toString() );
	System.out.println("Head.Prev: "+this.head.prev.toString() );
	System.out.println(title+" Backwards");
	Card testlast = this.head.prev;
	for(int i=0; i<this.numOfCards;i++) {
		 System.out.println(testlast.toString());
		 testlast = testlast.prev; 
	 }
	System.out.println("");
}
 /* 
  * TODO: Implements a copy constructor for Deck using Card.getCopy().
  * This method runs in O(n), where n is the number of cards in d.
  */
 public Deck(Deck d) {
	 Card cur = d.head;
		for(int i=0; i < d.numOfCards; i++) {
			this.addCard(cur.getCopy());
			cur = cur.next;
		}
	
 }

 /*
  * For testing purposes we need a default constructor.
  */
 public Deck() {}

 /* 
  * TODO: Adds the specified card at the bottom of the deck. This 
  * method runs in $O(1)$. 
  */
 public void addCard(Card c) { 

if(this.head == null && this.numOfCards == 0) {
	 this.head = c;
	 this.head.next = c;
	 this.head.prev = c;
  }
  if(this.head != null && this.numOfCards == 1) {
	c.prev = this.head;
	c.next = this.head;
	this.head.next = c;
	this.head.prev = c;
  }
  if(this.head != null && this.numOfCards >1) {
	  Card lastCard = this.head.prev;
	  c.prev = lastCard;
	  lastCard.next = c;
	  c.next = this.head;
	  this.head.prev = c;
  }
 numOfCards++;

 }

 /*
  * TODO: Shuffles the deck using the algorithm described in the pdf. 
  * This method runs in O(n) and uses O(n) space, where n is the total 
  * number of cards in the deck.
  */
 public void shuffle() {
	 if (this.head == null)return;
	 Card cur = this.head;
	 Card[] cardArray = new Card[this.numOfCards];
		
	 for(int i=0; i < this.numOfCards; i++) {
			cardArray[i] = cur;
			cur = cur.next;
			
		}
	 
	for (int i = (this.numOfCards-1); i >= 1;i-- ) {
		int j = Deck.gen.nextInt(i+1);
		Card tmp = cardArray[j];
		cardArray[j] = cardArray[i];
		cardArray[i] = tmp; 
		
	}
	
	this.head = null;//essentially sets the deck to empty
	this.numOfCards = 0;
	for(int i=0; i < cardArray.length; i++) { //sets the deck be same as the array
		Card newCard = cardArray[i];
		this.addCard(newCard);
	}
	
	//this.printDeck("After Shuffle");
 }

 /*
  * TODO: Returns a reference to the joker with the specified color in 
  * the deck. This method runs in O(n), where n is the total number of 
  * cards in the deck. 
  */
 public Joker locateJoker(String color) {
  Card cur = this.head;
  Joker jokerCard = null;
  for(int i=0; i<this.numOfCards;i++) {
	  
	  if(cur instanceof Joker) {
		  if((((Joker)cur).getColor()).equalsIgnoreCase(color)) {
			  jokerCard = (Joker)cur;
			  break;
		  }
	  }
	  cur=cur.next;
 }
  return jokerCard;
 }

 /*
  * TODO: Moved the specified Card, p positions down the deck. You can 
  * assume that the input Card does belong to the deck (hence the deck is
  * not empty). This method runs in O(p).
  */
 public void moveCard(Card c, int p) {
	 
	 //this.printDeck("before Move card");
	//#############################
  Card saveCard = c;
  Card prevCard = c.prev;
  Card nextCard = c.next;
  
  prevCard.next = nextCard;
  nextCard.prev = prevCard;
  Card newNextCard = prevCard;
  
  for (int i = 0; i < p; i++) {
	newNextCard = newNextCard.next;	 
	} 
  
  Card newPositionNext = newNextCard.next;
  newPositionNext.prev = c;
  c.next = newPositionNext;
  newNextCard.next = c;
  c.prev = newNextCard;
	//#############################  
	 //this.printDeck("After Move card");

}

 
 public void tripleCut(Card firstCard, Card secondCard) {  // this is the only method that is not working as expected
	 // this.printDeck("Before TriCut");
	 if((firstCard == this.head) && (secondCard == this.head.prev)) {
		// this.printDeck("After TriCut");
		 return;
	 }
	 
	 if ((firstCard != this.head) && (secondCard != this.head.prev)) { //case where there are cards in both sections
	 
	  Card firstSectionFirst = this.head;
	  Card firstSectionLast = firstCard.prev;
	  Card secondSectionFirst = secondCard.next;
	  Card secondSectionLast = this.head.prev;

	  firstSectionFirst.prev = secondCard;//link first card of first section to second card
	  secondCard.next = firstSectionFirst;
	  
	  secondSectionLast.next = firstCard; // links last card of second to the front of first card
	  firstCard.prev = secondSectionLast;
	 
	  firstSectionLast.next = secondSectionFirst;
	  secondSectionFirst.prev = firstSectionLast;
	  
	  this.head = secondSectionFirst;
	
	  //this.printDeck("After TriCut");
	  return;
	  
	}
	 
	if((firstCard == this.head) && (secondCard != this.head.prev)) {// case where the is no cards in first section
		 
		Card secondSectionFirst = secondCard.next;
		Card secondSectionLast = this.head.prev;
		
		
		secondSectionLast.next = firstCard;
		firstCard.prev = secondSectionLast;
		
		secondCard.next = secondSectionFirst;
		secondSectionFirst.prev= secondCard;
		
		this.head = secondSectionFirst;
		//this.printDeck("After TriCut");
		return;
	}
	
	if((firstCard != this.head) && (secondCard == this.head.prev)) {// case where the is no cards in second section
		
		Card firstSectionFirst = this.head;
		Card firstSectionLast = firstCard.prev;
		 
		
		firstSectionFirst.prev = secondCard;
		secondCard.next = firstSectionFirst;
		
		firstCard.prev = firstSectionLast;
		firstSectionLast.next = firstCard;
		
	
		this.head = firstCard;
		//this.printDeck("After TriCut");
		return;
 }
	}

 /*
  * TODO: Performs a count cut on the deck. Note that if the value of the 
  * bottom card is equal to a multiple of the number of cards in the deck, 
  * then the method should not do anything. This method runs in O(n).
  */
 public void countCut() {
	 //this.printDeck("Before countcut");
	 
	 int value = (head.prev.getValue())%(this.numOfCards);
	 if(value == 0 || value == this.numOfCards-1) {
		 return;
	 }
  if (value != 0) {
	  Card initalHead = this.head;
	  Card initialLast = this.head.prev;
	  Card initialBeforeLast = this.head.prev.prev;
	  
	  Card lastCardCut = this.head;
	  for (int i = 1; i< value; i++) {
		  lastCardCut = lastCardCut.next;
	  }
	  Card afterLastCardCut = lastCardCut.next;
	  
	  initialBeforeLast.next = initalHead;
	  initalHead.prev = initialBeforeLast;
	  
	  
	  lastCardCut.next = initialLast;
	  initialLast.prev = lastCardCut;
	  
	  this.head = afterLastCardCut;
	  initialLast.next = afterLastCardCut;
	  afterLastCardCut.prev = initialLast; 
	  
	  
  }

  //this.printDeck("After countcut");
 }

 /*
  * TODO: Returns the card that can be found by looking at the value of the 
  * card on the top of the deck, and counting down that many cards. If the 
  * card found is a Joker, then the method returns null, otherwise it returns
  * the Card found. This method runs in O(n).
  */
 public Card lookUpCard() {
  Card cardref = this.head;
  int value = this.head.getValue();
  for (int i = 0 ; i< value;i++) {
	  cardref = cardref.next;
  }
  if (cardref instanceof Joker)cardref= null;
  return cardref;
  
 }

 /*
  * TODO: Uses the Solitaire algorithm to generate one value for the keystream 
  * using this deck. This method runs in O(n).
  */
 public int generateNextKeystreamValue() {
  //this.printDeck("start");
	 
	 Card card = null;
	 
while(card == null) {
	Card rJ = this.locateJoker("red");
  
	 moveCard(rJ, 1);
	 //this.printDeck("after move red");
	 
	Card bJ = this.locateJoker("black");
  moveCard(bJ, 2);
  //this.printDeck("after move black");
 
  Card first = null;
  Card second = null;
  Card current = this.head;
  
  for(int i=0; i<this.numOfCards;i++) {
	 if (current == rJ) {
		  first = rJ;
		  second = bJ;
		  break;
	  }
	 else if (current == bJ) {
		  first = bJ;
		  second = rJ;
		  break;
	  }
	 current = current.next; 
	 }
  
  //System.out.println("");
 // System.out.println("First joker "+first.toString());
  //System.out.println("second joker "+second.toString());
  this.tripleCut(first, second);
 // this.printDeck("after triplecut");
  
  this.countCut();
  //this.printDeck("after countcut");
  card = this.lookUpCard();
 //System.out.println("\n"+lookUpCard().toString());

  
}
  return card.getValue();
 }


 public abstract class Card { 
  public Card next;
  public Card prev;

  public abstract Card getCopy();
  public abstract int getValue();

 }

 public class PlayingCard extends Card {
  public String suit;
  public int rank;

  public PlayingCard(String s, int r) {
   this.suit = s.toLowerCase();
   this.rank = r;
  }

  public String toString() {
   String info = "";
   if (this.rank == 1) {
    //info += "Ace";
    info += "A";
   } else if (this.rank > 10) {
    String[] cards = {"Jack", "Queen", "King"};
    //info += cards[this.rank - 11];
    info += cards[this.rank - 11].charAt(0);
   } else {
    info += this.rank;
   }
   //info += " of " + this.suit;
   info = (info + this.suit.charAt(0)).toUpperCase();
   return info;
  }

  public PlayingCard getCopy() {
   return new PlayingCard(this.suit, this.rank);   
  }

  public int getValue() {
   int i;
   for (i = 0; i < suitsInOrder.length; i++) {
    if (this.suit.equals(suitsInOrder[i]))
     break;
   }

   return this.rank + 13*i;
  }

 }

 public class Joker extends Card{
  public String redOrBlack;

  public Joker(String c) {
   if (!c.equalsIgnoreCase("red") && !c.equalsIgnoreCase("black")) 
    throw new IllegalArgumentException("Jokers can only be red or black"); 

   this.redOrBlack = c.toLowerCase();
  }

  public String toString() {
   //return this.redOrBlack + " Joker";
   return (this.redOrBlack.charAt(0) + "J").toUpperCase();
  }

  public Joker getCopy() {
   return new Joker(this.redOrBlack);
  }

  public int getValue() {
   return numOfCards - 1;
  }

  public String getColor() {
   return this.redOrBlack;
  }
 }

}
