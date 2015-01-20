package goFish;

import javax.swing.JButton;
import javax.swing.JLabel;

public class NextTurnThread extends Thread {

	// Data fields
	private Game game;
	private DropdownsPanel drops;
	private JLabel statCurrentPlayer;
	private JLabel statTurnChoices;
	private JLabel statTurnResult;
	private JLabel statDeal;
	private JLabel statBook;
	private JLabel statBookResult;
	private JLabel statTurnConclusion;
	private GameStatusPanel frame;
	private Boolean isMe;
	private JButton nextTurnButton;
	private JButton beginTurnButton;

	// Constructor
	public NextTurnThread(Game game, DropdownsPanel drops,
			JLabel statCurrentPlayer, JLabel statTurnChoices,
			JLabel statTurnResult, JLabel statDeal, JLabel statBook,
			JLabel statBookResult, JLabel statTurnConclusion,
			GameStatusPanel frame, JButton next, JButton begin) {
		this.game = game;
		this.drops = drops;
		this.statCurrentPlayer = statCurrentPlayer;
		this.statTurnChoices = statTurnChoices;
		this.statTurnResult = statTurnResult;
		this.statDeal = statDeal;
		this.statBook = statBook;
		this.statBookResult = statBookResult;
		this.statTurnConclusion = statTurnConclusion;
		this.frame = frame;
		this.nextTurnButton = next;
		this.beginTurnButton = begin;
	}

	@Override
	public void run() {

		// Get the current Player
		Player current = game.getCurrentPlayer();
		
		if(game.getMe().equals(this.game.getCurrentPlayer().getName())){
			this.isMe = true;
		}else{
			this.isMe = false;
		}
		
		//If not isMe
		if(!isMe){
			this.drops.setEnabled(false);
			this.drops.setVisible(false);
			this.nextTurnButton.setEnabled(false);
			this.beginTurnButton.setEnabled(false);
		}else{
			this.drops.setEnabled(true);
			this.drops.setVisible(true);
			this.nextTurnButton.setEnabled(false);
			this.beginTurnButton.setEnabled(true);
		}

		
		// Set the statCurrenPlayer panel to reflect the current player and
		// clear all other panels
		this.statTurnResult.setText("");
		this.statDeal.setText("");
		this.statBook.setText("");
		this.statBookResult.setText("");
		this.statTurnConclusion.setText("");
		this.statCurrentPlayer.setText("The current player is: "
				+ current.toString() + ".");

		// First check that the player hand is not empty!
		if (current.getHand().getList().size() == 0) {
			this.drops.setVisible(false);
			statTurnChoices
					.setText("<html>You have no cards, and so you cannot make a request.<br/Press ASK A PLAYER to proceed.</html>");
		} else {
			this.statTurnChoices
					.setText("<html>Choose a player to request cards from.<br/>Choose a card number to request.<br/>Then press ASK A PLAYER.</html>");

			// Set the dropdown panels to reflect the player info.
			drops.resetDropdownsPanel(
					game.getPlayers().allPlayersExcept(current),
					current.allCardNumbersInHandWithStringRep());
			frame.revalidate();
			frame.repaint();
		}
	}
}
