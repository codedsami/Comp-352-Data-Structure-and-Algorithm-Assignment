
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





/*
Q2) Theory 1
What is the purpose of using a priority queue in Huffman coding, and how does it help to generate an
optimal code?

ANSWER: When building a Huffman coding tree, the priority queue helps by facilitating the combination
of nodes with the lowest frequencies. By using a priority queue, we can efficiently retrieve nodes with
the smallest frequency in constant time. As we continue to retrieve nodes and remove them from the queue,
it progressively shrinks, allowing us to handle scenarios where only the root node remains. This root node
contains the necessary information to traverse the entire tree and obtain the desired output,
ensuring efficient space utilization. Moreover, the priority queue's ability to reposition a node
at its correct position after being added back aids in generating an optimal prefix code.
Also, higher frequency nodes receive shorter codes due to their positioning closer to the root
node based on the sorting order.

Q3) Theory 2
How does the length of a Huffman code relate to the frequency of the corresponding symbol, and why
is this useful for data compression?

ANSWER: In Huffman coding tree, if a character is more frequent it will have shorted codes, and
less frequent character gets longer codes. The inversely proportional relation helps with overall
data compression because it shortens the length of encoded message. Also, when decoding because of the
shorter code the tree traversal happens fast as well where the pointer will reach leaf node sooner.
This improves the necessary time required when decoding  a message. Additionally, because of shorter
and longer codes the optimal encoding is insured where no character’s encoding is a prefix of another’s.
So this frequency based code length help with data compression and accurate character representation.   




Q4) Theory 3
What is the time complexity of building a Huffman code, and how can you optimize it?

ANSWER: Building a Huffman code generally has a time complexity of O(n log n), where "n" represents
the number of characters. To optimize this process, we can use a heap data structure,
which reduces the time complexity to O(n). By using a heap structure, the insertion and deletion operations
can be performed efficiently in logarithmic time. This ensures that the priority queue maintains the lowest
frequency nodes at the top, allowing for efficient combination and building of the Huffman coding tree.
The heap structure provides the necessary sorting and repositioning of nodes, resulting in an optimal code 
generation process. Additionally, we can choose a more efficient sorting algorithm, such as quicksort or merge sort,
to construct the Huffman coding tree and further improve the time complexity to O(n log n).


 */





import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class HCDriver {

	public static void main(String[] args) {
		String textFile = args[0];
		String command = args[1];


		if(args.length > 2) {
			System.out.println("Wrong number of arguments in the command line. Will exit");
			System.exit(0);
		}


		//reading text file
		int[] frequencies = new int[128];
		try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
			int currentChar;

			while ((currentChar = br.read()) != -1) {
				char c = (char) currentChar;
				if (Character.isLetter(c)) {
					c = Character.toLowerCase(c);
				}


				frequencies[c]++;



			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}



		int uniqueCharacters = 0;

		for (int i = 0; i < frequencies.length; i++) {
			if (frequencies[i] != 0) {
				uniqueCharacters++;
			}
		}

		char[] charArray = new char[uniqueCharacters];




		int j = 0;
		for (int i = 0; i < frequencies.length; i++) {
			if (frequencies[i] != 0) {
				charArray[j] = (char) i;
				j++;
			}
		}



		//creating data structure
		CustomizedDataStructure CDS = new CustomizedDataStructure();

		for (int i = 0; i < charArray.length; i++) {
			int index = (int) charArray[i];

			int frequency = frequencies[index];


			CDS.add(new Leaf(charArray[i], frequency));

		}

		//only leaf are added

		CDS.reSize();
		CDS.sort();


		//addition of internal nodes
		while (CDS.size() > 1) {
			Node left = CDS.get(0);
			Node right = CDS.get(1);

			Node internalNode = new Node(left, right);
			CDS.delete(0);
			CDS.delete(0);

			CDS.add(internalNode);
			CDS.insertionSortByFrequency(CDS.elements);
		}

		Node root = CDS.get(0);
		CDS.reSize();


		//tree traversal to encode
		finalChars = new char[uniqueCharacters];
		finalCodes = new String[uniqueCharacters];
		treeTraversal(root, "");




		//taking input
		Scanner sc = new Scanner(System.in);
		String encodedString = "";
		if (args[1].equals("encode")) {
			String Input = sc.nextLine();
			String input = Input.toLowerCase();

			int index = 0;
			while(index < input.length()) {
				char currentChar = (char) input.charAt(index);

				for(int i = 0; i < finalChars.length; i++) {
					if(currentChar == finalChars[i]) {
						encodedString = encodedString + finalCodes[i];
					}
					else {
						continue;
					}
				}
				index++;
			}

			System.out.println(encodedString);


		} else if (command.equals("decode")) {
			String input = sc.nextLine();
			String decodedString = decode(root, input);
			System.out.println(decodedString);
		}



	}




	//method to decode
	public static String decode(Node root, String input) {
		String decodedString = "";
		Node currentNode = root;

		for (int i = 0; i < input.length(); i++) {
			char code = input.charAt(i);
			if (code == '1') {
				currentNode = currentNode.getLeftNode();
			} else if (code == '0') {
				currentNode = currentNode.getRightNode();
			}

			if (currentNode instanceof Leaf) {
				Leaf leaf = (Leaf) currentNode;
				decodedString += leaf.getCharacter();
				currentNode = root; 
			}
		}

		return decodedString;
	}




	static char[] finalChars;
	static String[] finalCodes;
	static int index = 0;

	//method for encoding
	public static void treeTraversal(Node root, String code) {
		if (root instanceof Leaf) {
			Leaf leaf = (Leaf) root;
			finalChars[index] = leaf.getCharacter();
			finalCodes[index] = code;
			index++;
		} else {
			Node left = root.getLeftNode();
			Node right = root.getRightNode();

			treeTraversal(left, code + "1");
			treeTraversal(right, code + "0");
		}
	}



}
