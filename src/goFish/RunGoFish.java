package goFish;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class RunGoFish {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Socket socket;
		try {
			socket = new Socket("localhost", 3773);
			GameSetupPanel gp = new GameSetupPanel(socket);
		} catch (java.net.ConnectException e) {
			JOptionPane.showMessageDialog(null,
					"You are not connected to a server, please try again");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
