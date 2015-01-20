package goFish;

import java.net.Socket;

public class GameServer {

	/*
	 * The GameServer has the following responsibilites:
	 * 1. Waits for clients to connect.
	 * 2. When a client connects, GS stores the clients name in one array, and socket in another.
	 * 3. When three clients are connected, the GS begins the game as follows:
	 * 		A. Creates a deck
	 * 		B. Creates the Players object
	 * 		C. Creates the Pool
	 * 4. Passes the Players and the Pool to the clients.
	 * 
	 * Then, the GS waits for messages from the clients. 
	 * When the GS receives a message, GS sends that message out to all clients.
	 * 
	 * GS also keeps track of permissions. 
	 * GS knows who the current player is. 
	 * GS sends TRUE to the current player, and FALSE to the other players.
	 */

	//Fields 
	private Deck deck;
	private Players players;
	private Pool pool;
	private String[]names;
	private Socket[]sockets;
	
	//Constructor
	public GameServer(){
		
	}
	
	//Set up the game
	public void startGame(){
		//Initialize the deck
		this.deck = new Deck();
		//Pass the deck to create the Players
		this.players = new Players(3, this.names, this.deck);
		//Create the pool with the remaining cards
		this.pool = deck.createPool();
	}
	
	//Send the game
	public void sendGameToClients() throws Exception{
		GameStartInfo info = new GameStartInfo(this.pool, this.players);
		for(Socket s: sockets){
			info.sendInfo(s);
		}
	}
	
			
}
