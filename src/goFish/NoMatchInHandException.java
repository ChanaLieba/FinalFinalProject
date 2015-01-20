package goFish;

@SuppressWarnings("serial")
public class NoMatchInHandException extends Exception {
	public NoMatchInHandException() {
		super("No matching cards in hand.");
	}
}
