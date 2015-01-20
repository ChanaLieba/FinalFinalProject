/*
 * An exception thrown when attempting to deal a card from an empty pool.
 */
package goFish;

@SuppressWarnings("serial")
public class EmptyPoolException extends Exception {
	public EmptyPoolException() {
		super("Pool is empty");
	}
}
