//This object is the graphic representation of the book, symbolizing four cards of a specific number.
package goFish;

import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class BookStack extends JLabel implements Serializable{

	// Data field
	private int cardNum;

	// Constructor
	public BookStack(int num) {
		this.cardNum = num;
		ImageIcon ii = new ImageIcon("./AnimatedCards/" + this.cardNum
				+ ".gif");
		this.setIcon(ii);
	}

}
