package goFish;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class PlayerHand extends LinkedList<Card> implements Serializable {

	@Override
	public void insert(Card data) {
		Node<Card> currNode;
		Node<Card> prevNode;
		Node<Card> aNode = new Node<Card>(data);

		if (head == null) {
			head = aNode;
			head.setData(data);

		} else {
			currNode = head;
			prevNode = head;
			// loop till you find the right place
			while (currNode != null && data.compareTo(currNode.getData()) >= 0) {
				if (data.compareTo(currNode.getData()) == 0) {
					// Since we allow for duplicates, if they are equal then we
					// will
					// simply break out of the loop and put it before the
					// currNode.
					break;
				} else {

					// parse further along in the list to find the right
					// position
					prevNode = currNode;
					currNode = currNode.getNext();
				}
			}// end while

			// data belongs right here
			if (currNode == head) {
				// data belongs before current head
				aNode.setNext(head);
				head = aNode;

			} else if (currNode == null) {
				// data belongs after last node
				prevNode.setNext(aNode);
			} else {
				// belongs between two nodes
				aNode.setNext(currNode);
				prevNode.setNext(aNode);

			}

		}
	}

	// Return a card and then remove it
	public Card returnAndRemove(Card data) throws Exception {
		Node<Card> currNode;
		Node<Card> prevNode;
		Card returnCard = null;
		if (head == null) {
			throw new ListEmptyException();
		} else {
			// find the node with the data in it
			// adjust the links so the Node is removed from the chain of Nodes
			currNode = head;
			prevNode = head;
			while (currNode != null && data.compareTo(currNode.getData()) >= 0) {
				if (currNode.getData().equals(data)) {
					// found the data we are looking for
					returnCard = currNode.getData();
					if (currNode == head) {
						// reset head and you're done!
						head = head.getNext();
						return returnCard; // leave the method

					} else { // node was found in the middle of the list
						prevNode.setNext(currNode.getNext());
						return returnCard; // leave the method

					}
				}
				// didn't find it yet, continue on to next node
				prevNode = currNode;
				currNode = currNode.getNext();
			}
			// exhausted list, didn't find a match
			throw new NotFoundException();
		}
	}

	// Give all cards of specific number to another hand
	public void giveCardsOfSpecificNumberToHand(int num, PlayerHand aHand)
			throws NoMatchInHandException {
		int numFound = 0;
		boolean finding = true;
		Card aCard = new Card(num, 'd');
		Card Card2 = new Card(num, 'c');
		Card Card3 = new Card(num, 's');
		Card Card4 = new Card(num, 'h');
		//while (finding) {
			try {
				Card removed = returnAndRemove(aCard);
				aHand.insert(removed);
				numFound++;
			} catch (Exception e) {
				finding = false;
			}
			try {
				Card removed = returnAndRemove(Card2);
				aHand.insert(removed);
				numFound++;
			} catch (Exception e) {
				finding = false;
			}
			try {
				Card removed = returnAndRemove(Card3);
				aHand.insert(removed);
				numFound++;
			} catch (Exception e) {
				finding = false;
			}
			try {
				Card removed = returnAndRemove(Card4);
				aHand.insert(removed);
				numFound++;
			} catch (Exception e) {
				finding = false;
			}
		//}
		if (numFound == 0) {
			throw new NoMatchInHandException();
		}
	}

	// Check if hand contains a book
	public boolean checkBook() {
		boolean hasBook = false;
		ArrayList<Card> list = getList();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < list.size(); i++) {
			int numb = list.get(i).getCardNum();
			if (!map.containsKey(numb)) {
				map.put(numb, 1);
			} else {
				int qty = map.get(numb) + 1;
				map.put(numb, qty);
			}
		}
		if (map.containsValue(4)) {
			hasBook = true;
		}
		return hasBook;
	}

	// Returns the card number of the book
	public int numOfBook() {
		ArrayList<Card> list = getList();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		Integer bookNum = null;
		for (int i = 0; i < list.size(); i++) {
			int numb = list.get(i).getCardNum();
			if (!map.containsKey(numb)) {
				map.put(numb, 1);
			} else {
				int qty = map.get(numb) + 1;
				map.put(numb, qty);
			}
		}
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			Integer key = entry.getKey();
			Integer value = entry.getValue();
			if (value == 4) {
				bookNum = key;
				break;
			}

		}
		return bookNum;
	}

	// Create book
	public Book createBook(int bookNum) {
		Book b = null;
		Card d0 = new Card(bookNum, 'h');
		Card d1 = new Card(bookNum, 'c');
		Card d2 = new Card(bookNum, 's');
		Card d3 = new Card(bookNum, 'd');
		if (checkBook()) {
			b = new Book(bookNum);
			Card[] bookCards = b.getCards();
			Card c = null;
			try {
				bookCards[0] = returnAndRemove(d0);
				bookCards[1] = returnAndRemove(d1);
				bookCards[2] = returnAndRemove(d2);
				bookCards[3] = returnAndRemove(d3);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return b;
	}

	// Returns an arraylist of the cards for easy reference
	public ArrayList<Card> getList() {
		ArrayList<Card> list = new ArrayList<Card>();
		Node<Card> currNode = head;
		while (currNode != null) {
			list.add(currNode.getData());
			currNode = currNode.getNext();
		}
		return list;
	}

}