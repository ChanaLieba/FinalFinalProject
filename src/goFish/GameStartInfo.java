package goFish;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;

//This object packages all the information that must be sent from the server
//to the clients at the start of the game.

public class GameStartInfo implements Serializable {

	// Fields
	private Pool pool;
	private Players players;

	public GameStartInfo(Pool pool, Players players) {
		this.pool = pool;
		this.players = players;
	}

	// Getters
	public Pool getPool() {
		return this.pool;
	}

	public Players getPlayers() {
		return this.players;
	}

	// Send the gamestartinfo
	public void sendInfo(Socket socket) throws Exception {
		OutputStream out = socket.getOutputStream();
		ObjectOutputStream stream = new ObjectOutputStream(out);
		stream.writeObject(this);
		stream.flush();
	}

}
