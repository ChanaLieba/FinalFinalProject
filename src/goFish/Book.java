/*
 * This object maintains the cards that consist a book. 
 * Although essentially, the book does not need to be used once it is created, 
 * (as those specific cards are removed from the player's hand, and a simple graphic representation
 * is shown on the screen instead), I sought to keep track of the entire deck. Therefore,
 * when the cards are removed from the player's hand, the player holds those cards in a book object.
 * 
 * DATA STRUCTURE: A book is simply an array of cards, as every book contains four Cards.
 */
package goFish;

import java.io.Serializable;

public class Book implements Serializable{

	// Data fields
	private Card[] cards;
	private int bookNum;

	// Constructor
	public Book(int num) {
		this.bookNum = num;
		this.cards = new Card[4];
	}

	// Access cards
	public Card[] getCards() {
		return this.cards;
	}

	// Get number
	public int getBookNum() {
		return this.bookNum;
	}
}
