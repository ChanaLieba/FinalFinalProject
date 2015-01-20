package goFish;

import java.io.Serializable;

public class Node<T extends Serializable & Comparable<T>> implements
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected T data;
	protected Node<T> next;

	// empty Node
	public Node() {
		data = null;
		next = null;
	}

	// Node will contain a reference to an object
	public Node(T value) {
		this.data = value;
		next = null;

	}

	public Node(T value, Node<T> next) {
		this.data = value;
		this.next = next;
	}

	// link this Node to another Node
	public void setNext(Node<T> next) {
		this.next = next;
	}

	// set data in the Node
	public void setData(T value) {
		this.data = value;
	}

	// get the value the Node contains
	// returns a reference to the data
	public T getData() {
		return data;
	}

	// get the Node that this Node is linked to
	public Node<T> getNext() {
		return next;
	}

	public int compareTo(Node<T> aNode) {
		return this.data.compareTo(aNode.getData());
	}
}
