import java.util.ArrayList;

/**
 * Quiz 5: Extra methods for heaps
 * 
 * @author David Zhang
 */
public class MyMinHeap {
	private ArrayList<Integer> nodes;

	/**
	 * Initializes a new MyHeap.
	 */
	public MyMinHeap() {
		nodes = new ArrayList<Integer>();
	}

	/**
	 * Returns the size of the min-heap.
	 * 
	 * @return The size of the min-heap.
	 */
	public int size() {
		return nodes.size();
	}

	/**
	 * Given a position i, return the position of the parent node.
	 * 
	 * @param i Position to check for the parent node.
	 * 
	 * @return Position of the parent node.
	 */
	private int parent(int i) {
		return (i-1)/2;
	}

	/**
	 * Given a position i, return the position of the left child.
	 * 
	 * @param i Position to check for the left child.
	 * 
	 * @return Position of the left child.
	 */
	private int leftChild(int i) {
		return 2*i+1;
	}

	/**
	 * Given a position i, return the position of the right child.
	 * 
	 * @param i Position to check for the right child.
	 * 
	 * @return Position of the right child.
	 */
	private int rightChild(int i) {
		return 2*i+2;
	}

	/**
	 * Swap elements at positions pos1 and pos2.
	 * 
	 * @param pos1 First position.
	 * @param pos2 Second position (no order implied).
	 */
	private void swap(int pos1, int pos2) {
		int temporary = nodes.get(pos1);
		nodes.set(pos1, nodes.get(pos2));
		nodes.set(pos2, temporary);
	}

	/**
	 * Add value to the min-heap.
	 * 
	 * @param value Value to be added to the min-heap.
	 */
	public void add(int value) {
		// Add to the end of the array
		nodes.add(value);

		// Set current position to the newly added element
		int i = nodes.size() - 1;

		// While you are not the root element,
		// and the parent is bigger, swap with the parent
		// then update your current position
		while(i > 0 && nodes.get(parent(i)) > nodes.get(i)) {
			swap(i, parent(i));
			i = parent(i);
		}
	}

	/**
	 * Remove the smallest value from the min-heap.
	 * 
	 * @return The former smallest value from the min-heap.
	 */
	public int removeMin() {
		// Save the first element
		int removed = nodes.get(0);

		// Remove from the end
		int last = nodes.remove(nodes.size() - 1);

		// If the heap is empty now, you're done
		if(size() == 0) {
			return removed;
		}

		// Otherwise, replace the removed element into the first position
		nodes.set(0, last);

		// Set current position to the root of the heap
		int i = 0;

		// Switch spots with your smallest child as long as the
		// child's position does not go past the end of the heap
		while(true) {
			int swap = i;

			// Find the smallest child that is valid (i.e., smaller than nodes.size())
			if(leftChild(i) < nodes.size() && nodes.get(leftChild(i)) < nodes.get(swap)) {
				swap = leftChild(i);
			}

			if(rightChild(i) < nodes.size() && nodes.get(rightChild(i)) < nodes.get(swap)) {
				swap = rightChild(i);
			}

			// If you can't find such child, or the smallest valid child is already in place,
			// then you're done: break the loop
			if(swap == i) {
				break;
			}
			else {
				swap(swap, i);
				i = swap;
			}
		}

		return removed;
	}


	/**
	 * Reorganizes the elements of the provided array so that they represent a heap order,
	 * and initializes the heap with the reorganized array.
	 * 
	 * In essence, this is a "add-all-at-once" method for the elements originally in the provided array.
	 * 
	 * @param beingHeapified Array that will have its elements reorganized, "heapified".
	 */
	public void heapifyAndInitialize(ArrayList<Integer> beingHeapified) {
		for(int i = beingHeapified.size()/2; i>=0; i--){
			heapifyHelper(beingHeapified, i);
		}
		nodes = beingHeapified;
	}

	/**
	 * Helper method for heapify().
	 * 
	 * @param beingHeapified Array that will have its elements reorganized, "heapified".
	 * @param i Position of the root element being exchanged down the tree.
	 */
	private void heapifyHelper(ArrayList<Integer> beingHeapified, int i) {
		while(true) {
			int swap = i;
			int leftindex = 2*i+1;
			int rightindex = 2*i+2;
			if(leftindex < beingHeapified.size() && beingHeapified.get(leftindex) < beingHeapified.get(swap)) {
				swap = leftindex;
			}

			if(rightindex < beingHeapified.size() && beingHeapified.get(rightindex) < beingHeapified.get(swap)) {
				swap = rightindex;
			}

			if(swap == i) {
				break;
			}
			else {
				int temp = beingHeapified.get(i);
				beingHeapified.set(i,beingHeapified.get(swap));
				beingHeapified.set(swap,temp);
				i = swap;
			}
		}
	}

	/**
	 * Performs a heapsort over the "nodes" array, returns that array,
	 * and leaves the heap in an empty, re-initialized state.
	 * 
	 * @return A reverse-sorted array containing the original elements in the heap.
	 */
	public ArrayList<Integer> heapsortAndClean() {
		int originalSize = nodes.size();

		for(int j = 0; j < originalSize; j++) {
			// Switch with the last element
			swap(0, size()-(j+1));
			// Set current position to the root of the heap
			int i = 0;
			// Switch spots with your smallest child as long as the
			// child's position does not go past the end of the heap
			while(true) {
				int swap = i;

				if(leftChild(i) < nodes.size()-(1+j) && nodes.get(leftChild(i)) < nodes.get(swap)) {
					swap = leftChild(i);
				}

				if(rightChild(i) < nodes.size()-(1+j) && nodes.get(rightChild(i)) < nodes.get(swap)) {
					swap = rightChild(i);
				}

				if(swap == i) {
					break;
				}
				else {
					swap(swap, i);
					i = swap;
				}
			}
		}

		// Reinitializes the heap
		ArrayList<Integer> reordered = nodes;
		nodes = new ArrayList<Integer>();
		// Return the nodes array
		return reordered;
	}

	/**
	 * Returns a string representation of the min-heap
	 * in array format.
	 */
	public String toString() {
		return nodes.toString();
	}
}
