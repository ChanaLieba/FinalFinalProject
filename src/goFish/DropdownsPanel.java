/*
 * In order to make sure that a player (1) requests cards from other players in the game, and
 * (2) only requests cards that he has in his hand, I thought to have the player make his choices
 * by selecting items from drop down menus. The menus are customized based on the player and hand.
 * The Players menu receives a list of all the players except for the current player, and the Numbers
 * menu receives a list of all the cards in the current player's hand.
 * 
 * Note that several of the GUI components contain a reset method that essentially reconstructs the component.
 * This is used when repainting the component with updated values.
 */
package goFish;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DropdownsPanel extends JPanel {

	// Data fields
	private JLabel pickPlayer;
	private Player[] playerList;
	private JComboBox<Player> players;

	private JLabel pickCardNumber;
	private String[] numberList;
	private JComboBox<String> numbers;

	// Constructor
	public DropdownsPanel(Player[] playerList, String[] numberList) {
		
		
		this.pickPlayer = new JLabel("Choose a player to request cards from.");
		this.playerList = playerList;
		this.players = new JComboBox<Player>(this.playerList);

		this.pickCardNumber = new JLabel("Choose a card number to request.");
		this.numberList = numberList;
		this.numbers = new JComboBox<String>(this.numberList);

		JPanel player = new JPanel(new BorderLayout());
		player.add(this.pickPlayer, BorderLayout.NORTH);
		player.add(this.players, BorderLayout.SOUTH);

		JPanel number = new JPanel(new BorderLayout());
		number.add(this.pickCardNumber, BorderLayout.NORTH);
		number.add(this.numbers, BorderLayout.SOUTH);

		this.setLayout(new BorderLayout());
		this.add(player, BorderLayout.NORTH);
		this.add(number, BorderLayout.SOUTH);
		setBackground(this);
	}

	private void setBackground(JComponent comp) {
		comp.setOpaque(true);
		comp.setBackground(Color.BLUE);
	}

	// Reset the dropdowns panel (essentially reconstructs the panel).
	public void resetDropdownsPanel(Player[] playerList, String[] numberList) {
		this.removeAll();
		this.pickPlayer = new JLabel("Choose a player to request cards from.");
		this.playerList = playerList;
		this.players = new JComboBox<Player>(this.playerList);

		this.pickCardNumber = new JLabel("Choose a card number to request.");
		this.numberList = numberList;
		this.numbers = new JComboBox<String>(this.numberList);

		JPanel player = new JPanel(new BorderLayout());
		player.add(this.pickPlayer, BorderLayout.NORTH);
		player.add(this.players, BorderLayout.SOUTH);

		JPanel number = new JPanel(new BorderLayout());
		number.add(this.pickCardNumber, BorderLayout.NORTH);
		number.add(this.numbers, BorderLayout.SOUTH);

		this.setLayout(new BorderLayout());
		this.add(player, BorderLayout.NORTH);
		this.add(number, BorderLayout.SOUTH);
	}

	public JLabel getPickPlayer() {
		return pickPlayer;
	}

	public void setPickPlayer(JLabel pickPlayer) {
		this.pickPlayer = pickPlayer;
	}

	public Player[] getPlayerList() {
		return playerList;
	}

	public void setPlayerList(Player[] playerList) {
		this.playerList = playerList;
	}

	public JComboBox<Player> getPlayers() {
		return players;
	}

	public void setPlayers(JComboBox<Player> players) {
		this.players = players;
	}

	public JLabel getPickCardNumber() {
		return pickCardNumber;
	}

	public void setPickCardNumber(JLabel pickCardNumber) {
		this.pickCardNumber = pickCardNumber;
	}

	public String[] getNumberList() {
		return numberList;
	}

	public void setNumberList(String[] numberList) {
		this.numberList = numberList;
	}

	public JComboBox<String> getNumbers() {
		return numbers;
	}

	public void setNumbers(JComboBox<String> numbers) {
		this.numbers = numbers;
	}

}
