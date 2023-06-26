
// -----------------------------------------------------
/**
 * Assignment 04
 * COMP-352
 * Written by: 
 * Miskat Mahmud (40250110)
 * Due Date: 25 June 2023
 */
// -----------------------------------------------------


//Theory Questions


/**
1. Describe how hash functions distribute data into buckets. How did the hash function you used
achieve this distribution?

ANSWER: The hash function takes the input data and then maps it to a specific index inside the hash table.
A hash function is better if it is able to distribute the mapped data evenly throughout the table.
It should also focus on minimizing collisions such that it takes less time to retrieve a data. In general,
a hash function performs series of some mathematical operations on an input data in order to create a unique value 
for the corresponding input data. That values is then used to distribute the data into buckets.
In my case of hash function, a quadratic probing is used. Multiplication and addition is performed using the characters of the string.
The use of the number 31 (a prime number) ensures that data is spread uniformly across the bucket.
It also uses modulo of the table size to make sure that the hash value remains in the range of available indices. 


2. How does load factor influence the performance of a hash table? How did you manage the load
factor in your implementation?

ANSWER: The load factor is the ratio between the number of elements stored in the hash table and total number of available places.
This factor is important because the load factor is proportional to possibility of collisions. The load factor plays a crucial role 
in the performance of a hash table because it directly affects the likelihood of collisions. Hence, a good load factor is important
to avoid longer data manipulation inside the hash table. In the code, the load factor is not explicitly managed. Instead, the table
size is dynamically increased upon successful insertions. This continuous increment of the table size allows for accommodating more 
elements and reduces the load factor. However, instead quadratic probing is used to minimize possibility of collisions.
This strategy helps minimize the possibility of collisions by distributing elements more evenly across the table.
So, even if the load factor is high, the continuous increment of table size and quadratic probing ensures the performance of the hash table.


3. What is collision in hashing and what strategies did you employ in your MyHash subclass to handle
it? Discuss the pros and cons of your chosen strategy.

ANSWER: A collision in hashing refers to the case where more than one elements ends up having the same hash value. This is not ideal because
in order to handle this problem, sometimes we need to introduce other strategy like probing or use of linked list. This may not be ideal because 
these solution often ends up 'misplacing' the data or it increases the data manipulation complexity of the hash table.
In this code, quadratic probing is used to minimize collisions. This strategy uses a function to jump to 'distant' index in case a slot is occupied. 
This strategy is good because it can ensure avoiding primary clustering where consecutive collisions occurs at the same index. 
So, this strategy can evenly distribute the hash values. However, it can be problematic because it can lead to secondary clustering, which can increase
the complexity. Also, it has the possibility of going out of bound of the size. When this happens an exception may be thrown to tackle the problem.
 */


package ca.concordia.a4;

import main.stuff.MyHash;
import main.stuff.NotFoundException;
import main.stuff.TooFullException;

public class SpeedyHashing extends MyHash {

	//constructor of the class
	//it calls the super method and MyHash class deals with it where a text file is opened
	public SpeedyHashing(String filename) {
		super(filename);
	}


	//this method is used to insert a string into the hash table. First calling the hash function to 
	//get the index value to check if it already exist. If not found then it will assign a new hash value
	//to the corresponding string and add it to the table.
	//It also deals with exception in case the table is full.\
	//also a quadratic probing is use in case a space is already taken, so it searches for next available spot
	@Override
	protected void insert(String str) throws TooFullException {
		int hashValue = hash(str); //calling the hash function
		int startingIndex = hashValue;
		int probe = 1;

		while (get(hashValue) != null) {
			if (get(hashValue).equals(str)) {
				return; //at this point string is already found so no insertion occurs
			}
			hashValue = (startingIndex + probe * probe) % getTableSize(); //this is where probing occurs
			probe++;
			if (hashValue == startingIndex) {
				throw new TooFullException(str); //if it reaches this step, table is full, exception thrown
			}
		}

		put(hashValue, str);  //insertion occurs here using parents' method
		incSize(); //size is incremented
	}



	// This method is used to find a string in the hash table. It first calls the hash function to
	// get the index value for the string. If the string is found at the calculated index, it is returned.
	// If the string is not found, quadratic probing is used to search for the string in subsequent indices.
	// The probing continues until the string is found or the entire table is traversed.
	// If the string is not found after traversing the entire table, a NotFoundException is thrown.
	@Override
	protected String find(String str) throws NotFoundException, TooFullException {
		int hashValue = hash(str); //calling the hash function
		int startingIndex = hashValue;
		int probe = 1;

		while (get(hashValue) != null) {
			if (get(hashValue).equals(str)) {
				return str; // String found, return it
			}
			hashValue = (startingIndex + probe * probe) % getTableSize(); // Quadratic probing
			probe++;

			if (hashValue == startingIndex) {
				throw new NotFoundException(hashValue); // String not found, throw NotFoundException
			}
		}

		throw new TooFullException(str); // String not found, throw TooFullException
	}



	//This method calculates the hash value for a given string. It iterates over each character of the string,
	//multiplying the current hash value by 31 and adding the ASCII value of the character. The result is then
	//taken modulo the table size to ensure it falls within the valid index range. The final hash value is returned.
	@Override
	protected int hash(String str) {
		int hash = 0;
		for (int i = 0; i < str.length(); i++) {
			hash = (hash * 31 + str.charAt(i)) % getTableSize(); //quadratic probing
		}
		return hash;
	}

	//This method determines the size of the hash table based on the input parameter. In this implementation,
	//the table size is directly set to the provided parameter value. The chosen size determines the number of
	//buckets or slots available in the hash table. The determined table size is returned.
	@Override
	protected int determineTableSize(int i) {
		return i;
	}



}
