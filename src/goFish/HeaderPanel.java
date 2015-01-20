/*
 * A jlabel with a customized GoFish logo, for the header of the setup frame.
 */
package goFish;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class HeaderPanel extends JLabel {

	// Constructor
	public HeaderPanel() {
		this.setSize(1500, 300);
		ImageIcon ii = new ImageIcon("./GoFishCards/header.png");
		this.setIcon(ii);
	}

}
