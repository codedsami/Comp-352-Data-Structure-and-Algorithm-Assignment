package main.stuff;

/*
 * Hm, this NotFoundException is starting to concern me a bit.
 * 
 * Okay, here's what I'm thinking - exceptions are meant to signal when something goes wrong, right? They're 
 * supposed to be exceptional. But then why am I passing the index with this exception? It feels like I'm using 
 * an error condition to perform a standard operation. I mean, I'm essentially using this to find an empty 
 * slot for a new entry... Is this right?
 *
 * Part of me likes the idea, I won't lie. It seems efficient. I'm already walking through the array, and if I 
 * don't find the element, I know where I can put it. That's killing two birds with one stone. But, is this the 
 * right way to do it?
 *
 * On the other hand, the more I think about it, the more it feels off. I'm jumbling together two responsibilities 
 * into one class. It's like I'm trying to fit a square peg in a round hole. Exceptions are for errors or "exceptional"
 * situations, not for routine operations, right? Is this making my code harder to understand?
 * 
 * And, what about future changes? If I decide to change how I insert new entries, I'll have to mess with this 
 * exception class too. That doesn't seem right. It should be separate, shouldn't it?
 * 
 * Maybe I need to rethink this... Efficiency is important, yes, but clarity is key. Code should be easy to read, 
 * easy to maintain. And this design... it might be too clever for its own good.
 */
public class NotFoundException extends Exception {
	private int index;

	public NotFoundException(int index) {
		super("No element found at index: " + index);
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}
