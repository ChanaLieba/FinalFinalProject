package goFish;

@SuppressWarnings("serial")
public class ListEmptyException extends Exception {
	public ListEmptyException() {
		super("list empty");
	}
}