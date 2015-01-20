package goFish;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TurnMessage {
	
	//Data field
	private int num;
	private String player;
	private int request;
	
	public TurnMessage(int num, String p, int r){
		this.num = num;
		this.player = p;
		this.request = r;
	}
	
	//toString
	public String toString(){
		return this.num+" "+this.player+" "+this.request+"\n";
	}
	
	//Send
	public void send(Socket socket){
		try {
			OutputStream out = socket.getOutputStream();
			out.write(this.toString().getBytes());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
