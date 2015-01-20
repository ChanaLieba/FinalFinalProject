package goFish;

import java.io.Serializable;
import java.net.Socket;

public class Game implements Serializable{

	// Data fields
	private Pool pool;
	private Players players;
	private int currentTurnNumber;
	private String me;
	private Socket socket;

	// Constructor
	public Game(Pool pool, Players players, String me, Socket socket) {

		//All these fields are obtained from the server
		this.pool = pool;
		this.players = players;
		this.me = me;
		this.socket = socket;

		// Set the turn number
		this.currentTurnNumber = 1;
		
	}
	
	public Socket getSocket(){
		return this.socket;
	}

	// Get players
	public Players getPlayers() {
		return this.players;
	}

	// Get current turn number
	public int getCurrentTurnNum() {
		return this.currentTurnNumber;
	}

	// Set current turn number
	public void setCurrentTurnNum(int num) {
		this.currentTurnNumber = num;
	}

	// Get number of players
	public int getNumberOfPlayers() {
		return this.players.getNumPlayers();
	}

	public Player getCurrentPlayer() {
		return this.players.getCurrentPlayer(this.currentTurnNumber);
	}

	public Pool getPool() {
		return this.pool;
	}

	// Get game status. Game is over when there are a total of 13 books.
	public boolean getGameOver() {
		if (this.players.totalBooks() == 13) {
			return true;
		} else {
			return false;
		}
	}

	public String getMe() {
		return this.me;
	}

}
