package goFish;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameStatusPanel extends JPanel {

	// Data fields

	// JLabels to display information about game status
	private JLabel statCurrentPlayer;
	private JLabel statTurnChoices;
	private JLabel statTurnResult;
	private JLabel statDeal;
	private JLabel statBook;
	private JLabel statBookResult;
	private JLabel statTurnConclusion;
	private AnimatedBackground ab;

	// Dropdown menus for user choices
	private DropdownsPanel drops;

	// Buttons to submit user choices and continue to next turn
	private JButton beginTurn;
	private JButton nextTurn;

	// Game object
	private Game game;

	// Main frame
	private GameFrame frame;

	// Stores a reference to itself.
	// Necessary in order to pass itself as a reference in the private classes,
	// which won't recognize
	// "this" keyword as referring to this object itself.
	private GameStatusPanel me;

	// Constructor
	public GameStatusPanel(GameFrame frame, Game game) {

		this.frame = frame;
		this.game = game;

		// Set all the status labels
		this.statCurrentPlayer = new JLabel("The current player is: "
				+ this.game.getCurrentPlayer().toString() + ".");
		this.statTurnChoices = new JLabel(
				"<html>Choose a player to request cards from.<br/>Choose a card number to request.<br/>Then press ASK A PLAYER.</html>");
		this.statTurnResult = new JLabel("");
		this.statDeal = new JLabel("");
		this.statBook = new JLabel("");
		this.statBookResult = new JLabel("");
		this.statTurnConclusion = new JLabel("");
		
		this.ab = new AnimatedBackground();

		String location1 = "./AnimatedCards/background.gif";
		Icon icon2 = new ImageIcon(location1);
		JLabel label2 = new JLabel(icon2);
		label2.setOpaque(true);
		label2.setBackground(Color.CYAN);
		// Create a panel with all the status labels
		JPanel labels = new JPanel();
		labels.setOpaque(true);
		labels.setBackground(Color.CYAN);
		labels.setLayout(new GridLayout(7, 1));
		labels.add(this.statCurrentPlayer);
		labels.add(this.statTurnChoices);
		labels.add(this.statTurnResult);
		labels.add(this.statDeal);
		labels.add(this.statBook);
		labels.add(this.statBookResult);
		labels.add(this.statTurnConclusion);

		// Create the BEGIN TURN button
		this.beginTurn = new JButton("ASK A PLAYER");
		this.beginTurn.setOpaque(true);
		this.beginTurn.setBackground(Color.CYAN);
		this.beginTurn.addActionListener(new BeginTurnListener());

		// Create the NEXT TURN button
		this.nextTurn = new JButton("NEXT TURN");
		this.nextTurn.setOpaque(true);
		this.nextTurn.setBackground(Color.CYAN);
		this.nextTurn.addActionListener(new NextTurnListener());

		// Add the two buttons to a JPanel
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
		buttonPanel.add(this.beginTurn);
		buttonPanel.add(this.nextTurn);

		// Create the initial dropdown menus
		this.drops = new DropdownsPanel(this.game.getPlayers()
				.allPlayersExcept(this.game.getCurrentPlayer()), this.game
				.getCurrentPlayer().allCardNumbersInHandWithStringRep());

		// Add the dropdown menu and the buttons to a panel
		JPanel menuAndButtons = new JPanel();
		menuAndButtons.setOpaque(true);
		menuAndButtons.setBackground(Color.CYAN);
		menuAndButtons.setLayout(new BorderLayout());
		menuAndButtons.add(this.drops, BorderLayout.NORTH);
		menuAndButtons.add(buttonPanel, BorderLayout.SOUTH);

		// Add all components to this GameStatusPanel
		this.setLayout(new BorderLayout());
		this.add(labels, BorderLayout.NORTH);
		this.add(ab,BorderLayout.CENTER);
		this.add(menuAndButtons, BorderLayout.SOUTH);
		
		//setComponentsToRightBackgounds((JComponent[]) this.getComponents());

		// Set "me" to refer to this object
		this.me = this;
	}

	// ActionListener for BEGIN TURN button
	private class BeginTurnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			beginTurn.setEnabled(false);
			nextTurn.setEnabled(true);
			String playerToAsk = ((Player) drops.getPlayers().getSelectedItem()).getName();
			int numToAsk;
			switch((String)drops.getNumbers().getSelectedItem()){
			case "A": 
				numToAsk = 1;
				break;
			case "J":
				numToAsk = 11;
				break;
			case "Q":
				numToAsk = 12;
				break;
			case "K":
				numToAsk = 13;
				break;
			default:
				numToAsk = Integer.valueOf((String) drops.getNumbers().getSelectedItem());
			}
			TurnMessage msg = new TurnMessage(2, playerToAsk, numToAsk);
			msg.send(game.getSocket());
			
		}
	}

	// ActionListener for NEXT TURN button
	private class NextTurnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ContinueMessage msg = new ContinueMessage(1);
			msg.send(game.getSocket());
			
		}
	}

	// Method to clear all labels
	public void clearLabels() {
		this.statCurrentPlayer.setText("");
		this.statTurnChoices.setText("");
		this.statTurnResult.setText("");
		this.statDeal.setText("");
		this.statBook.setText("");
		this.statBookResult.setText("");
		this.statTurnConclusion.setText("");
	}

	public JLabel getStatCurrentPlayer() {
		return statCurrentPlayer;
	}

	public JLabel getStatTurnChoices() {
		return statTurnChoices;
	}

	public JLabel getStatTurnResult() {
		return statTurnResult;
	}

	public JLabel getStatDeal() {
		return statDeal;
	}

	public JLabel getStatBook() {
		return statBook;
	}

	public JLabel getStatBookResult() {
		return statBookResult;
	}

	public JLabel getStatTurnConclusion() {
		return statTurnConclusion;
	}

	public DropdownsPanel getDrops() {
		return drops;
	}

	public JButton getBeginTurn() {
		return beginTurn;
	}

	public JButton getNextTurn() {
		return nextTurn;
	}

	public Game getGame() {
		return game;
	}

	public GameFrame getFrame() {
		return frame;
	}

	public GameStatusPanel getMe() {
		return me;
	}
	
	
}
