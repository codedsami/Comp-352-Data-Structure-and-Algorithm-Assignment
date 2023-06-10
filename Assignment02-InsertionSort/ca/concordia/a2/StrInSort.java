// -----------------------------------------------------
 /**
 * Assignment 02
 * COMP-352
 * Written by: 
 * Miskat Mahmud (40250110)
 * Due Date: 02 June 2023
 */
// -----------------------------------------------------

/*
 * Q2) Theory: Qualify This Structuring
How did the structuring pass you performed, specifically the reversals chosen, affect swaps and comparisons?
Was anything else affected?

Answer: Since the reversal of certain runs does bring a change to the original array, this structuring 
pass have the ability to bring some improvement on the insertion sort that is performed later. Since the reversing portion
partially sorts the array beforehand, it gets easier and faster for the insertion sort method to do the required task. 
This has the potential of also affecting the swaps and comparison counts as well since these actions are also done during 
the reversals. Hence, the overall number of comparisons and swaps gets affected due to the structuring pass performed.
 */

// -----------------------------------------------------

/*
 * Q3) Theory: Size of Runs
How do you feel the size of the specific runs you recorded (ASCENDING order of length 2) impacted
performance?
 *
 * Answer: Since the tracking of the increasing runs of length two was not used else where during the structuring pass or
 * insertion sort, it did not have any significant impact on the performance. It was rather an statistic that shows 
 * the pattern of the given list. Hence, their impact is limited. 
 */

// -----------------------------------------------------

/**
 * Q4) Theory: Doubly Linked Lists
What would implementing this as a Doubly Linked List do? How would the specified structuring affect
results?

Answer: If the list was implemented using a doubly linked list, it could have both advantages and disadvantages. It would be easier to insert or deleting 
elements from the list, however, one have to be careful during the reversal process and make sure that the nodes are being interchanged correctly such that
it did not affect the rest of the list. However, in general, the specific structuring could have been done using linked list with ease as well amd since it's doubly,
traversing the list forward or backward would have been easy as well.
 */


package ca.concordia.a2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class StrInSort {
	
	public static int incRunLenTwo = 0;
	public static int revRunDecOrder = 0;
	public static int comparesStructuring = 0;
	public static int comparesOverall = 0;
	public static int swaps = 0;


	public static void main(String[] args) {
		
		ArrayList<Integer> A = new ArrayList<>();
		
		
		try {
			Scanner sc = new Scanner(new FileInputStream(args[0]));
			while(sc.hasNext()) {
				int number = Integer.parseInt(sc.next());
				A.add(number);
			}
			
			int[] initialArray = new int[A.size()];
	        for (int i = 0; i < A.size(); i++) {
	            initialArray[i] = A.get(i);
	        }
			
			for (int num : initialArray) {
	            System.out.print(num + " ");
	        }
			
			structuringPass(initialArray);

			insertionSort(initialArray);
			
			System.out.println("\nWe sorted in DEC order\r\n"
					+ "We counted " +  incRunLenTwo  + " INC runs of length 2\r\n"
					+ "We performed " +  revRunDecOrder  + " reversal of runs in DEC order\r\n"
					+ "We performed " + comparesStructuring + " compares during structuring\r\n"
					+ "We performed " + comparesOverall + " compares overall\r\n"
					+ "We performed " + swaps + " swaps overall");
			
			for (int num : initialArray) {
	            System.out.print(num + " ");
	        }
			
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}

	} //end of main()

	
	public static void reverseArrayPart(int[] array, int start, int end) {
		revRunDecOrder++;

        while (start < end) {
        	swaps++;
        	
        	comparesStructuring++;
        	comparesOverall++;
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
	}
	
	
	private static void structuringPass(int [] array) {

		for(int i = 0; i < array.length-1; ) {  //iterate for
			int counter = 0;

			boolean endOfRun = false;
			int pointer = i;
			
			while(!endOfRun) {
			//check for decreasing order
			if(array[i] > array[i+1]) {
				comparesOverall++;
				comparesStructuring++;
				pointer++;
				while((pointer < array.length-1) && (array[pointer] > array[pointer+1])) {
					    comparesStructuring++;
					    comparesOverall++;
					    pointer++;
				 }
				
				endOfRun = true;
				reverseArrayPart(array, i, pointer);
				i = pointer+1;
			}
			
			//check for increasing order
			else if (array[i] < array[i+1]) {
				comparesOverall++;
				comparesStructuring++;
				while((pointer < array.length-1) && (array[pointer] < array[pointer+1])) {
					comparesOverall++;
					comparesStructuring++;
					pointer++;
					counter++;
				  }
				
				if(counter == 1) {
					incRunLenTwo++;
				}
				
				 endOfRun = true;
				 i = pointer+1;
			   }
			
			}//end of while(!endOfRun) loop
			
		}//end of  iterate for
		
	}//end of method
	
	private static void insertionSort(int [] array) {
		
		for(int i = 1; i < array.length; i++) {
			int temp = array[i];
			comparesOverall++;
			int j = i-1;
			while((j >= 0) && (array[j] < array[j+1]) ) {
				
				array[j+1] = array[j];
				array[j] = temp;

				comparesOverall++;
				swaps++;
				j--;
				if(j<0) {
					comparesOverall--;
				}
			}
		}
	}
	
} //end of class

