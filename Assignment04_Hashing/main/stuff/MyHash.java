package main.stuff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class MyHash {
	private String[] table;
	private int size = 0;

	// Okay, so I need a way to check if the table is full. This should do it,
	// right?
	// Comparing the number of elements with the table's length... that's gotta be
	// right.
	public boolean isFull() {
		return size == table.length;
	}

	// And what's a hash table without knowing its size? It's nothing, I tell ya!
	public int getTableSize() {
		return table.length;
	}

	// Yeah, let's keep track of how many elements we insert. We're responsible like
	// that.
	public void incSize() {
		++size;
	}

	// Oh, and we might want to know the current size too, right? Yes, definitely.
	public int getSize() {
		return size;
	}

	// Now, here comes the big stuff - constructing the hash table from a file.
	// Reading the file twice? I guess that's okay... as long as it's not too big.
	// Gosh, what if the file is huge? Maybe I should reconsider this approach.
	public MyHash(String filename) {
		super();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(System.getProperty("user.dir"), filename).getAbsolutePath()));
			String line;
			int totalsize = 0;

			// Count the number of non-empty lines in the file
			while ((line = reader.readLine()) != null) {
				String str = line.trim();
				if (!str.isEmpty()) {
					totalsize++;
				}
			}

			// Create the hash table

			System.out.println("Our hash table will be " + determineTableSize(totalsize) + " long for " + totalsize
					+ " elements.");

			this.table = new String[determineTableSize(totalsize)];
			reader.close();
			reader = new BufferedReader(new FileReader(new File(System.getProperty("user.dir"), filename).getAbsolutePath()));
			while ((line = reader.readLine()) != null) {
				String str = line.trim();
				if (str.isEmpty())
					continue;
				insert(str);
			}

			reader.close();
		} catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
		} catch (TooFullException e) {
			System.err.println("Error filling hash table: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// These getter and setter, though... I mean, they're simple, but they're
	// crucial.
	// Mess these up, and everything falls apart. No pressure, right?
	protected String get(int index) {
		return table[index];
	}

	protected void put(int index, String record) {
		table[index] = record;
	}

	// Here are our abstract methods, our promises of functionality to our future
	// subclasses.
	// We're setting the bar here - hash, find, insert - the core of a hash table.
	// They better not let us down...
	abstract protected int hash(String str);

	abstract protected String find(String str) throws NotFoundException, TooFullException;

	abstract protected void insert(String str) throws TooFullException;

	// I'm not sure what's the best size for the table yet, so let's leave it up to
	// our subclasses.
	// It's a big responsibility, but I believe in them. They've got this!
	abstract protected int determineTableSize(int i);

}
