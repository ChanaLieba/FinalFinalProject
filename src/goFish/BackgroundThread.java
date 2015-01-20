package goFish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class BackgroundThread extends Thread {
	private Socket socket;
	private Game game;
	private GameStatusPanel stats;
	private GameFrame frame;
	
	// JLabels to display information about game status
		private JLabel statCurrentPlayer;
		private JLabel statTurnChoices;
		private JLabel statTurnResult;
		private JLabel statDeal;
		private JLabel statBook;
		private JLabel statBookResult;
		private JLabel statTurnConclusion;

		// Dropdown menus for user choices
		private DropdownsPanel drops;

		// Buttons to submit user choices and continue to next turn
		private JButton beginTurn;
		private JButton nextTurn;
		private Boolean isMe;
		private Boolean gameEnded;
		
	public BackgroundThread(Socket socket, GameStatusPanel stats, Game game, GameFrame frame) {
		this.socket = socket;
		this.game = game;
		this.stats = stats;
		this.statCurrentPlayer = this.stats.getStatCurrentPlayer();
		this.statTurnChoices=this.stats.getStatTurnChoices();
		this.statTurnResult=this.stats.getStatTurnResult();
		this.statDeal = this.stats.getStatDeal();
		this.statBook = this.stats.getStatBook();
		this.statBookResult = this.stats.getStatBookResult();
		this.statTurnConclusion = this.stats.getStatTurnConclusion();
		this.drops = this.stats.getDrops();
		this.beginTurn = this.stats.getBeginTurn();
		this.nextTurn=this.stats.getNextTurn();
		this.frame = frame;
		this.gameEnded = false;
	}
  
	@Override
	public void run() {
		InputStream inputStream;
		try {
			inputStream = socket.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			
			if(game.getMe().equals(this.game.getCurrentPlayer().getName())){
				this.isMe = true;
			}else{
				this.isMe = false;
			}
			
			//If not isMe
			if(!isMe){
				this.drops.setEnabled(false);
				this.drops.setVisible(false);
				this.nextTurn.setEnabled(false);
				this.beginTurn.setEnabled(false);
			}else{
				this.drops.setEnabled(true);
				this.drops.setVisible(true);
				this.nextTurn.setEnabled(false);
				this.beginTurn.setEnabled(true);
			}
			while ((line = bufferedReader.readLine()) != null) {
				try{
				String[]parts = line.split(" ");
				int caseNum = Integer.parseInt(parts[0]);
				
				switch (caseNum){
				case 1:
					NextTurnThread next = new NextTurnThread(game, drops,
							statCurrentPlayer, statTurnChoices, statTurnResult,
							statDeal, statBook, statBookResult, statTurnConclusion, stats, nextTurn, beginTurn);
					next.start();
					break;
				
				case 2:
					Player playerToAsk = game.getPlayers().getPlayerFromName(parts[1]);
					int numToAsk = Integer.parseInt(parts[2]);
					PlayerTurnThread turn = new PlayerTurnThread(statTurnChoices,
							statTurnResult, statDeal, statBook, statBookResult,
							statTurnConclusion, drops, frame.getGameCardsPanel()
									.getPanels(), game, frame.getGameCardsPanel(),
							nextTurn, beginTurn, stats, playerToAsk, numToAsk);
					turn.start();
				}
				
				}
				catch(java.lang.NumberFormatException s){
					if(gameEnded == false){
					JOptionPane.showMessageDialog(null, "This game has ended as a player has quit");
					gameEnded = true;
					}
					this.statCurrentPlayer.setText("GAME ENDED");
					this.statTurnChoices.setText(null);
					this.statTurnResult.setText(null);
					this.statDeal.setText(null);
					this.statBook.setText(null);
					this.statBookResult.setText(null);
					this.statTurnConclusion.setText(null);
					this.drops.setEnabled(false);
					this.drops.setVisible(false);
					this.nextTurn.setEnabled(false);
					this.beginTurn.setEnabled(false);
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}