package goFish;

import java.awt.Dimension;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

@SuppressWarnings("serial")
public class GameFrame extends JFrame implements Serializable{

	// Data fields
	private Game game;
	private GameStatusPanel status;
	private GameCardsPanel cards;

	// Constructor
	public GameFrame(Game game) {
		this.game = game;

		// Create the two main components
		this.status = new GameStatusPanel(this, this.game);
		this.cards = new GameCardsPanel(game);

		// Add each component to a scroll pane
		JScrollPane statusScroll = new JScrollPane(this.status);
		JScrollPane cardScroll = new JScrollPane(this.cards);

		// Create a split pane with the two scroll panes in it.
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				statusScroll, cardScroll);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(.35);

		// Provide minimum sizes for the two components in the split pane
		statusScroll.setMinimumSize(new Dimension(350, 600));
		cardScroll.setMinimumSize(new Dimension(800, 600));

		// Add the split pane to the JFrame
		this.add(splitPane);
		
		//Thread
		BackgroundThread thread = new BackgroundThread(this.game.getSocket(), this.status, this.game, this);
		thread.start();

		// JFrame details...
		this.setSize(1300, 600);
		this.setTitle(this.game.getMe());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	// Getter - necessary to pass components to ActionListeners in other classes
	public GameCardsPanel getGameCardsPanel() {
		return this.cards;
	}

}
