/**
 *  A linked list of integers.
 * 
 * @author David Zhang 
 */
public class MyLinkedList {
	// Points to the first element of the list
	public Node head;

	// Points to the last element of the list
	public Node tail;

	// Stores the current size of the list
	private int size;

	/**
	 *  Default constructor: constructs an empty linked list.
	 */
	public MyLinkedList() {
		this.head = null;
		this.tail = null;

		this.size = 0;
	}           

	/////////////////
	// Add methods //
	/////////////////

	/**
	 * Add the specified element in the first position of the list.
	 * 
	 * @param element Element to be inserted in the first position of the list.
	 */
	public void addFirst(int element) {
		if(size == 0) {
			Node temp = new Node(element);

			// Not necessary: Node's constructor does that for us
			temp.next = null;

			head = temp;
			tail = temp;
		}
		else {
			Node temp = new Node(element);

			temp.next = head;
			head = temp;
		}

		size++;
	}

	/**
	 * Add the specified element in the last position of the list.
	 * 
	 * @param element Element to be inserted in the last position of the list.
	 */
	public void addLast(int element) {
		if(size == 0) {
			Node temp = new Node(element);

			// Not necessary: Node's constructor does that for us
			temp.next = null;

			head = temp;
			tail = temp;
		}
		else {
			Node temp = new Node(element);

			// Not necessary: Node's constructor does that for us
			temp.next = null;

			tail.next = temp;	
			tail = temp;
		}

		size++;
	}

	////////////////////
	// Remove methods //
	////////////////////

	/**
	 * Remove an element from the first position of the list.
	 * 
	 * @return The element removed from the first position of the list,
	 *         or -1 if no such element exists.
	 */
	public int removeFirst() {
		if(size == 0) {
			return -1;
		}

		Node temp = head;

		if(size == 1) {
			head = null;
			tail = null;
		}
		else {
			head = head.next;
		}

		size --;

		return temp.value;
	}

	////////////////////////////
	// Methods to query state //
	////////////////////////////

	/**
	 * Retrieves, but does not remove, the first element of the list.
	 * If no such element exists, return -1.
	 * 
	 * @return The first element of the list, or -1 if no such element exists.
	 */
	public int first() {
		if(size == 0) {
			return -1;
		}

		return head.value;
	}

	/**
	 * Retrieves, but does not remove, the last element of the list.
	 * If no such element exists, return -1.
	 * 
	 * @return The last element of the list, or -1 if no such element exists.
	 */
	public int last() {
		if(size == 0) {
			return -1;
		}

		return tail.value;
	}

	/**
	 * Returns the list size.
	 * 
	 * @return An integer representing the list size
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns the value stored at the specified position.
	 * 
	 * @param position The position of the node we want to get the value from.
	 * 
	 * @return The value at the specified position.
	 */
	public int get(int position) {
		if(position < 0 || position > (size - 1)) {
			throw new IllegalArgumentException("Index out of bounds");
		}

		// Walk through the list 'position' times,
		// and return the value of the node
		Node temp = head;

		for(int i = 0; i < position; i++) {
			temp = temp.next;
		}

		return temp.value;
	}

	/**
	 * Inserts an element at the specified position.
	 * 
	 * @param position Position in which the new value should be added.
	 */
	public void add(int position, int element) {
		if(position < 0 || position > size) {
			throw new IllegalArgumentException("Index out of bounds");
		}

		if(position == 0) {
			addFirst(element);
		}
		else if(position == size) {
			addLast(element);
		}
		else {
			// Find the node located before the one we want to add
			// Walk through the list 'position - 1' times,
			// (use similar code as in 'get')

			Node prev = head;

			for(int i = 0; i < position - 1; i++) {
				prev = prev.next;
			}	
			
			// Insert the new node after 'prev'
			
			Node in = new Node(element);
			
			in.next = prev.next;
			prev.next = in;
			
			// Increase the size
			
			size++;
		}
	}

	/**
	 * Removes an element at the specified position.
	 * 
	 * @param position Position from which the value should be removed.
	 */
	public int remove(int position) {
		if(position < 0 || position > (size - 1)) {
			throw new IllegalArgumentException("Index out of bounds");
		}

		if(position == 0) {
			return removeFirst();
		}
		else {
			// Find the node located before the one we want to remove
			// Walk through the list 'position - 1' times,
			// (use similar code as in 'get')

			Node prev = head;

			for(int i = 0; i < position - 1; i++) {
				prev = prev.next;
			}

			// Skip the node after 'prev', and then return its value

			Node out = prev.next;

			prev.next = out.next;
			
			// Decrease the size

			size--;
			
			// If the former out node was the tail, update tail
			
			if(tail == out) {
				tail = prev;
			}

			// Return the value

			return out.value;
		}
	}
	
	/**
	 * Concatenates in O(1) time otherList to the end of this list
	 * 
	 * After the operation, this list should contain all elements originally present,
	 * and all elements from otherList.
	 * 
	 * Besides, otherList should be made empty.
	 * 
	 * All of this should be done in O(1) time.
	 * 
	 * @param list1 A list of integers.
	 * @param list2 A list of integers.
	 * 
	 * @return The concatenation of list
	 */
	public void concatenate(MyLinkedList otherList) {
		if(this.head!=null&&otherList.head!=null) {
			this.tail.next=otherList.head;
			this.tail = otherList.tail;
			this.size+=otherList.size;
			otherList.head=null;
			otherList.tail=null;
			otherList.size=0;
		}
		if(this.head==null&&otherList.head!=null) {
			//I used addLast here to simplify the code. 
			//addLast() runs in a constant time.
			this.addLast(otherList.head.value);
			this.tail.next=otherList.head.next;
			this.tail=otherList.tail;
			this.size=otherList.size;
			otherList.head=null;
			otherList.tail=null;
			otherList.size=0;
		}
		if(this.head==null&&otherList.head==null||this.head!=null&&otherList.head==null) {
			// In this case we don't need to do anything.
		}
	}

	/**
	 * Returns a string representation of the list.
	 */
	public String toString() {
		if(size == 0) {
			return "[]";
		}

		String result = "[";

		for(Node current = head; current != null; current = current.next) {
			result += current + ", ";
		}

		return result.substring(0, result.length() - 2) + "]";
	}
}