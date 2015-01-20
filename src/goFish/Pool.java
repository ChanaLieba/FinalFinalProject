package goFish;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("serial")
public class Pool extends ArrayList<Card> implements Serializable{

	// Data fields
	private Random random;

	// Constructor
	public Pool() {
		this.random = new Random();
	}

	// Deal a card from the pool
	public Card deal() throws EmptyPoolException {
		Card aCard = null;
		if (this.size() > 0) {
			int position = this.random.nextInt(this.size());
			aCard = this.get(position);
			this.remove(position);
		} else {
			throw new EmptyPoolException();
		}
		return aCard;
	}

}
