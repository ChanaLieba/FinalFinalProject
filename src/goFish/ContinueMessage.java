package goFish;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ContinueMessage {
	
	//Data field
	private int num;
	
	public ContinueMessage(int num){
		this.num = num;
	}
	
	//toString
	public String toString(){
		return this.num+"\n";
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
