/*
 * A card is a JToggleButton; it shows its value when clicked.
 */

package goFish;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class Card extends JToggleButton implements Comparable<Card>,
		Serializable {

	// Data fields
	private ImageIcon coverPic;
	private ImageIcon face;
	private ImageIcon disabledPic;
	private int cardNum;
	private char suite;

	// Constructor
	public Card(int num, char suite) {
		this.suite = suite;
		this.setSize(600, 400);
		this.coverPic = new ImageIcon("./RegularCards/b1fv.gif");
		String faceLocation = "./RegularCards/" + suite + num + ".gif";
		this.face = new ImageIcon(faceLocation);
		this.disabledPic = new ImageIcon("./RegularCards/b1fv.gif");
		this.setIcon(coverPic);
		this.setSelectedIcon(face);
		this.setDisabledIcon(disabledPic);
		this.cardNum = num;
		this.setBackground(new Color(59, 194, 241));
	}

	// Getter for cardNum
	public int getCardNum() {
		return this.cardNum;
	}

	// CompareTo, based on cardNum
	@Override
	public int compareTo(Card aCard) {
		Integer a = this.cardNum;
		Integer b = aCard.cardNum;
		return a.compareTo(b);
	}

	// Eequals, based on cardNum
	@Override
	public boolean equals(Object aCard) {
		boolean eq = false;
		if (aCard instanceof Card) {
			Card c = (Card) aCard;
			if (this.cardNum == c.cardNum) {
				eq = true;
			}
		}
		return eq;
	}

	@Override
	public String toString() {
		String cardString = null;
		switch (cardNum) {
		case 1:
			cardString = "A";
			break;
		case 2:
			cardString = "" + cardNum;
			break;
		case 3:
			cardString = "" + cardNum;
			break;
		case 4:
			cardString = "" + cardNum;
			break;
		case 5:
			cardString = "" + cardNum;
			break;
		case 6:
			cardString = "" + cardNum;
			break;
		case 7:
			cardString = "" + cardNum;
			break;
		case 8:
			cardString = "" + cardNum;
			break;
		case 9:
			cardString = "" + cardNum;
			break;
		case 10:
			cardString = "" + cardNum;
			break;
		case 11:
			cardString = "J";
			break;
		case 12:
			cardString = "Q";
			break;
		case 13:
			cardString = "K";
			break;

		}
		return cardString;
	}

}
