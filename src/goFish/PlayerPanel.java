package goFish;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {

	// Data fields
	private Player player;
	private PlayerHand hand;
	private JLabel playerName;
	private ArrayList<Book> books;
	private String me;
	boolean myPanel;

	// Constructor
	public PlayerPanel(Player p, String name) {
		this.me = name;
		this.player = p;
		this.hand = this.player.getHand();
		this.books = this.player.getBooks();
		
		if(this.me.equals(p.getName())){
			myPanel = true;
		}else{
			myPanel = false;
		}

		this.playerName = new JLabel(p.getName());
		this.add(this.playerName);

		// Add the player's books to the panel
		BookStack bs;
		for (int i = 0; i < this.books.size(); i++) {
			bs = new BookStack(this.books.get(i).getBookNum());
			this.add(bs);
		}
		// Add the player's cards to the panel
		ArrayList<Card> cards = hand.getList();
		for (int i = 0; i < cards.size(); i++) {
			Card c = cards.get(i);
			if(!myPanel){
				c.setEnabled(false);
				c.setSelected(false);
			}
			if(myPanel){
				c.setSelected(true);
				c.setEnabled(true);
			}
			this.add(cards.get(i));
			
		}

		this.setBackground(new Color(127, 226, 255));
	}

	// Reset panel
	public void resetPlayerPanel(Player p) {
		this.removeAll();
		this.hand = this.player.getHand();
		this.books = this.player.getBooks();
		this.add(this.playerName);
		BookStack bs;
		for (int i = 0; i < this.books.size(); i++) {
			bs = new BookStack(this.books.get(i).getBookNum());
			this.add(bs);
		}
		ArrayList<Card> cards = hand.getList();
		
			for (int i = 0; i < cards.size(); i++) {
				Card c = cards.get(i);
				if(!myPanel){
					c.setSelected(false);
					c.setEnabled(false);
				}
				if(myPanel){
					c.setSelected(true);
					c.setEnabled(true);
				}
				this.add(c);
			}
		}
	}
	


