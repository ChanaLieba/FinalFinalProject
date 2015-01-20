/*
 * A deck consists of fifty two cards; four of each thirteen values.
 * DATA STRUCTURE: A deck can essentially behave as a stack, in the sense that
 * only the top card needs to be removed (for dealing purposes). However, I did not implement
 * the actual java stack, as I wanted to include several other methods and found it easier to just
 * create an object that contains an array. Still, note that the main behaviors of the deck are that of a stack.
 */
package goFish;

import java.io.Serializable;
import java.util.Random;

public class Deck implements Serializable{

	private Card[] deck;
	private int numInDeck;
	private char[] suites;

	public Deck() {
		this.deck = new Card[52];
		this.suites = new char[4];
		suites[0] = 'h';
		suites[1] = 'c';
		suites[2] = 'd';
		suites[3] = 's';
		this.numInDeck = 0;
		for (int i = 1; i < 14; i++) {
			for (int j = 0; j < 4; j++) {
				Card aCard = new Card(i, suites[j]);
				deck[numInDeck] = aCard;
				numInDeck++;
			}
		}
		shuffle();
		shuffle();
	}

	// Shuffle deck
	public void shuffle() {
		Random numGenerator = new Random();
		for (int i = 0; i < this.numInDeck; i++) {
			int newPosition = numGenerator.nextInt(this.numInDeck);
			Card tempCard = deck[i];
			deck[i] = deck[newPosition];
			deck[newPosition] = tempCard;
		}
	}

	// "Pop" -- returns the top card from the deck
	public Card deal() {
		Card aCard = this.deck[numInDeck - 1];
		this.deck[numInDeck - 1] = null;
		numInDeck--;
		return aCard;
	}

	// Access cards
	public Card[] getCards() {
		return this.deck;
	}

	// Get num in deck
	public int getNumInDeck() {
		return this.numInDeck;
	}

	// Create pool (from remaining cards in deck)
	public Pool createPool() {
		Pool pool = new Pool();
		for (int i = 0; i < this.numInDeck; i++) {
			pool.add(this.deck[i]);
		}
		return pool;
	}

}
