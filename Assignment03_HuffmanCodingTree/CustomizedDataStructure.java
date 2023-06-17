// -----------------------------------------------------
/**
 * Assignment 03
 * COMP-352
 * Written by: 
 * Miskat Mahmud (40250110)
 * Due Date: 16 June 2023
 */
// -----------------------------------------------------

package ca.concordia.algos.huffman;

public class CustomizedDataStructure {
	private final int INITIAL_CAPACITY = 10;
	public Node[] elements;
	private int size;

	public CustomizedDataStructure() {
		elements = new Node[INITIAL_CAPACITY];
		size = 0;
	}


	public void reSize() {
		Node[] newElements = new Node[size];
		System.arraycopy(elements, 0, newElements, 0, size);
		elements = newElements;
	}

	public void add(Node node) {
		if (size == elements.length) {
			extension();
		}

		elements[size] = node;
		size++;
	}

	public Node get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of range: " + index);
		}

		return elements[index];
	}


	public void set(int index, Node node) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of range: " + index);
		}

		elements[index] = node;
	}


	public Node delete(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of range: " + index);
		}

		Node deletedNode = elements[index]; 

		for (int i = index + 1; i < size; i++) {
			elements[i - 1] = elements[i];
		}

		elements[size - 1] = null;
		size--;

		return deletedNode; 
	}


	public int size() {
		return size;
	}

	private void extension() {
		Node[] newElements = new Node[elements.length * 2];
		System.arraycopy(elements, 0, newElements, 0, size);
		elements = newElements;
	}


	public void sort() {
		insertionSortbyReverseAsciiCode(elements);
		insertionSortByFrequency(elements);

	}


	public void insertionSortByFrequency(Node[] elements) {
		int n = elements.length;
		for (int i = 1; i < n; i++) {
			Node key = elements[i];
			int j = i - 1;
			while (j >= 0 && (elements[j] == null || (key != null && elements[j].frequency > key.frequency))) {
				elements[j + 1] = elements[j];
				j--;
			}
			elements[j + 1] = key;
		}
	}


	public void insertionSortbyReverseAsciiCode(Node[] elements) {
		for (int i = 1; i < elements.length; i++) {
			int j = i - 1;
			Node temp = elements[i];
			while (j >= 0 && reverseAsciiCode(getCharacter(elements[j])) > reverseAsciiCode(getCharacter(temp))) {
				elements[j + 1] = elements[j];
				j--;
			}
			elements[j + 1] = temp;
		}
	}

	public int reverseAsciiCode(char c) {
		return 127 - (int) c;
	}

	private char getCharacter(Node node) {
		if (node instanceof Leaf) {
			return ((Leaf) node).getCharacter();
		} else {
			return '\0'; 
		}
	}

}

