
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

public class Node implements Comparable<Node> {
	protected final int frequency;
	private Node leftNode;
	private Node rightNode;

	public Node(Node leftNode, Node rightNode) {
		this.frequency = leftNode.getFrequency() + rightNode.getFrequency();
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}

	public Node(int frequency) {
		this.frequency = frequency;
	}

	public Node getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}

	public Node getRightNode() {
		return rightNode;
	}

	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}

	public int getFrequency() {
		return frequency;
	}

	@Override
	public int compareTo(Node node) {
		return Integer.compare(frequency, node.getFrequency());
	}


	public boolean isLeaf() {
		return false;
	}

	public String toString() {
		return "Node: " + " (" + frequency + ")";
	}


}
