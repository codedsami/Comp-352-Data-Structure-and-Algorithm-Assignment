package main.stuff;

public class TotallyReliableHASH extends MyHash {

	// Alright, a constructor! Simple, we're just calling the parent class
	// constructor.
	// But... what happens if the filename is null? Or doesn't exist? Or is a
	// directory? More things to consider...
	public TotallyReliableHASH(String filename) {
		super(filename);
	}

	// Okay, an insert method. We're trying to find the string first - that makes
	// sense. If we don't find it, we have a free spot! But... what if the table 
	// is full? TooFullException, that's what!
	// But wait... is using exceptions for control flow really the best idea here?
	// Seems a bit clunky...
	public void insert(String str) throws TooFullException {
		try {
			find(str);
		} catch (NotFoundException e) {
			put(e.getIndex(), str);
			incSize();
		}
	}

	// Alright, let's find a string in our hash table.
	// We're using linear probing here - simple, but is it the best strategy? What
	// about clustering issues?
	// And again, this reliance on exceptions for control flow... is that really the
	// best choice here?
	protected String find(String str) throws NotFoundException, TooFullException {
		int index = hash(str);
		int tries = 0;
		while (++tries <= getTableSize() + 1) {
			if (get(index) == null)
				throw new NotFoundException(index);
			if (get(index).equals(str)) {
				return str;
			}
			index = (index + 1) % getTableSize();
		}
		throw new TooFullException(str);
	}

	// Alright, the bread and butter of our hash table - the hash function!
	// This one... seems a bit basic. Just multiplying and adding characters in the
	// string.
	// It's alright for simple strings, but what about long strings? And what about
	// collision handling?
	// So many questions...
	protected int hash(String str) {
		int hash = 7;
		for (int i = 0; i < str.length(); i++) {
			hash = hash * 31 + str.charAt(i);
		}
		return Math.abs(hash) % getTableSize();
	}

	@Override
	protected int determineTableSize(int i) {
		return i;
	}
}
