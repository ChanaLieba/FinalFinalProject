package goFish;

import java.io.Serializable;

public class LinkedList<T extends Serializable & Comparable<T>> implements
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1826782479863723174L;
	protected Node<T> head;
	public LinkedListInternalIterator iter; // internal iterator, only one per
											// class instance

	// empty linked list
	public LinkedList() {
		head = null;
		iter = new LinkedListInternalIterator();
	}

	// insert data at the beginning
	// doesn't check for duplicates
	public void insert(T data) throws Exception {
		if (head == null) {
			// this data will be entered into first node
			head = new Node<T>(data);
		} else {
			// insert new node and head of list
			// have the new node point to the previous head of list
			Node<T> aNode = new Node<T>(data);
			aNode.setNext(head);
			head = aNode;
		}
	}

	// remove Node that contains the data specified
	public void remove(T data) throws Exception {
		Node<T> currNode;
		Node<T> prevNode;
		if (head == null) {
			throw new ListEmptyException();
		} else {
			// find the node with the data in it
			// adjust the links so the Node is removed from the chain of Nodes
			currNode = head;
			prevNode = head;
			while (currNode != null) {
				if (currNode.getData().compareTo(data) == 0) {
					// found the data we are looking for
					if (currNode == head) {
						// reset head and you're done!
						head = head.getNext();
						return;

					} else {
						prevNode.setNext(currNode.getNext());
						return;

					}
				} else {
					// didn't find it yet, continue on to next node
					prevNode = currNode;
					currNode = currNode.getNext();
				}

			}
			// exhausted list, didn't find a match
			throw new NotFoundException();
		}

	}

	public void removeAll() {
		head = null;
	}

	public Node<T> getFirst() {
		// return first Node in the list
		return head;
	}

	public boolean isEmpty() {
		// is list empty
		return (head == null);
	}

	@Override
	public String toString() {
		Node<T> currNode = head.getNext();
		String info = null;
		if (head == null) {
			return " ";
		} else {
			info = head.getData().toString();
		}
		while (currNode != null) {
			info = info + "\n" + currNode.getData().toString();
			currNode = currNode.getNext();
		}
		return info;
	}

	// will return reference to external iterator, one instance can have many
	// external iterators
	public LinkedListIterator<T> iterator() {
		return new LinkedListIterator<T>(head);
	}

	// this class gives provides the internal iterator with its functionality

	class LinkedListInternalIterator implements Serializable{
		private Node<T> currentNode;

		public LinkedListInternalIterator() {
			currentNode = head;

		}

		public boolean hasNext() {
			// is there more data to peruse
			if (currentNode == null) {
				return false;
			}
			if (currentNode.getData() != null) {
				return true;
			} else {
				return false;
			}
		}

		public T next() {
			Node<T> temp = currentNode;
			currentNode = currentNode.getNext();// move further along in the
												// list
			// return the currentNode before you moved further along
			return temp.getData();
		}

		public void reset() {
			// resets from the beginning of the list , starts all over again
			currentNode = head;
		}
	}
}
