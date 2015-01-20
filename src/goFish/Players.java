package goFish;

import java.io.Serializable;
import java.util.ArrayList;

public class Players implements Serializable{

	// Data fields
	private int numPlayers;
	private Player[] playerList;

	// Constructor
	public Players(int num, String[] names, Deck deck) {
		this.numPlayers = num;
		this.playerList = new Player[this.numPlayers];
		PlayerHand[] ph = new PlayerHand[this.numPlayers];
		int numCardsInHand;

		// Determine how many cards will be in each deck
		if (this.numPlayers <= 4) {
			numCardsInHand = 7;
		} else {
			numCardsInHand = 5;
		}

		// Instantiate all the PlayerHands
		for (int i = 0; i < ph.length; i++) {
			ph[i] = new PlayerHand();
		}

		// Deal the cards
		for (int i = 0; i < this.numPlayers; i++) {
			for (int j = 0; j < numCardsInHand; j++) {
				Card c = deck.deal();
				ph[i].insert(c);
			}
		}
		// Note that the remaining cards will be used for the pool.
		// Since the Game constructor passes the Deck to the Players
		// constructor,
		// after the Players are created and the cards are dealt, the Game
		// constructor
		// will create the pool.

		// Create the players and add them to the player array
		for (int i = 0; i < this.numPlayers; i++) {
			this.playerList[i] = new Player(names[i], ph[i], i);
		}
	}

	// Provides access to playerList
	public Player[] getPlayerList() {
		return this.playerList;
	}

	// Determines who the current player is
	public Player getCurrentPlayer(int currentTurnNumber) {
		Player current;
		if (currentTurnNumber > this.numPlayers) {
			int mod = currentTurnNumber % this.numPlayers;
			if (mod == 0) {
				current = this.playerList[this.numPlayers - 1];
			} else {
				current = this.playerList[mod - 1];
			}
		} else {
			current = this.playerList[(currentTurnNumber - 1)];
		}
		return current;
	}

	// Determines who has the most 'books'. Returns an ArrayList, in the
	// instance of a tie.
	public ArrayList<Player> mostBooks() {
		ArrayList<Player> mosts = new ArrayList<Player>();
		Player most = this.playerList[0];
		int mostPos = 0;

		// Searches for the player with the most books.
		// Keeps track of the position at which that player is found,
		// so that when searching through the list of players again for a tie,
		// that player is not selected as a "tie" with himself.
		for (int i = 0; i < this.numPlayers; i++) {
			if (most.getNumBooks() < this.playerList[i].getNumBooks()) {
				most = this.playerList[i];
				mostPos = i;
			}
		}
		// Check for a tie
		for (int i = 0; i < this.numPlayers; i++) {
			if (most.getNumBooks() == this.playerList[i].getNumBooks()
					&& mostPos != i) {
				mosts.add(this.playerList[i]);
			}
		}

		mosts.add(most);
		return mosts;
	}

	// Get sum total of books from all players; used to determine if game is up
	public int totalBooks() {
		int total = 0;
		for (int i = 0; i < this.numPlayers; i++) {
			total += this.playerList[i].getNumBooks();
		}
		return total;
	}

	// trading of cards in turn
	public void cardExchange(Player asker, int askeePosition, int num)
			throws NoMatchInHandException {
		Player askee = this.playerList[askeePosition];
		askee.getHand().giveCardsOfSpecificNumberToHand(num, asker.getHand());
	}

	// Get the list of all players except for a certain player
	public Player[] allPlayersExcept(Player p) {

		ArrayList<Player> list = new ArrayList<Player>();
		int index = p.getIndexInPlayers();
		for (int i = 0; i < this.numPlayers; i++) {
			Player b = this.playerList[i];
			if (b.getIndexInPlayers() != index) {
				list.add(b);
			}
		}
		Player[] listB = new Player[list.size()];
		for (int i = 0; i < list.size(); i++) {
			listB[i] = list.get(i);
		}
		return listB;
	}

	public int getNumPlayers() {
		return this.numPlayers;
	}
	
	//Given a name, get the player
	public Player getPlayerFromName(String name){
		Player p = null;
		for(int i = 0; i<this.playerList.length; i++){
			if(playerList[i].getName().equals(name)){
				p=playerList[i];
			}
		}
		return p;
	}


}
