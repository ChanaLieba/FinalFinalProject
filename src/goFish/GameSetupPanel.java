package goFish;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class GameSetupPanel extends JFrame {
	
	//Fields
	private JLabel intro;
	private JLabel nameInstructions;
	private JTextField nameInput;
	private JButton submitName;
	private Socket socket;
	private GameSetupPanel theFrame;
	
	public GameSetupPanel(Socket socket){
		theFrame = this;
		this.setSize(400,400);
		this.socket = socket;
		this.intro = new JLabel("Welcome to Go Fish!");
		this.nameInstructions = new JLabel("Please input your name. Then press SUBMIT.");
		this.nameInput = new JTextField(20);
		this.submitName = new JButton("SUBMIT");
		this.submitName.addActionListener(new SubmitNameListener());
		this.add(intro, BorderLayout.NORTH);
		JPanel nameStuff = new JPanel();
		nameStuff.add(this.nameInstructions, BorderLayout.NORTH);
		nameStuff.add(this.nameInput, BorderLayout.CENTER);
		this.add(nameStuff, BorderLayout.CENTER);
		this.add(this.submitName, BorderLayout.SOUTH);
		
		//Basic JFrame Stuff
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private class SubmitNameListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String name = nameInput.getText().toUpperCase().trim();
			if(name.length()<=0){
				nameInstructions.setText("Please enter a valid name. Then press SUBMIT");
			}else{
				name+="\n";
			try {
				OutputStream out = socket.getOutputStream();
				out.write(name.getBytes());
				out.flush();
				nameInstructions.setText("Name submitted. Waiting for response from server...");
				nameInput.setText("");
			} catch (IOException e) {
				e.printStackTrace();
			}
			submitName.setEnabled(false);
			}
			StartupReceivingThread t = new StartupReceivingThread(socket, name.trim(), theFrame);
			t.start();
			
		}
		
	}
}