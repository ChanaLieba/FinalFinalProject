package goFish;

@SuppressWarnings("serial")
public class NotFoundException extends Exception {
	public NotFoundException() {
		super("data not found");
	}

}