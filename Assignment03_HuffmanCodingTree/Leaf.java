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

public class Leaf extends Node {

	public char character;

	public Leaf(char character, int frequency) {
		super(frequency);
		this.character = character;
	}

	public char getCharacter() {
		return character;
	}

	public boolean isLeaf() {
		return true;
	}

	public String toString() {
		return "Leaf: " + character + " (" + frequency + ")";
	}

	public void setCharacter(char c) {
		character = c;
	}
}
