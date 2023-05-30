// -----------------------------------------------------
 /**
 * Assignment 01
 * COMP-352
 * Written by: 
 * Miskat Mahmud (40250110)
 * Due Date: 26 May 2023
 */
// -----------------------------------------------------





/*Q2) Theory: Qualify This Recursion
How does the nature of the sequence (sum of all previous values except the ones we skip, and multiplication
of certain values) affect the complexity of a recursive solution?

Answer: Skipping values can lead the program to be less time consuming,because it has less recursive calls to make.
On the other hand, multiplying certain values can make the program to be more time consuming, because of extra operation.
However, the time complexity of the algorithm is mainly dependent on the input number, with the number of recursive calls being the primary factor affecting its efficiency.
In this function each recursive call goes on to call the function three more times. Hence that is the main factor behind the time complexity of this method.
And the case of skipping or multiplying values has limited contribution in terms of time complexity as the input gets larger. 

*/



/*
Q3) Theory: Impact of Size
How does the size of the input sequence affect the memory requirements of your program? Explain.

Answer: Since I am creating an array of the 'input' size, the memory requirement is directly proportional to the input size. 
Therefore, the memory required by the array increases linearly with the size of the input sequence.
There is also another factor where we have to consider the call stack usage by the function. Since every call makes three more 
recursive calls, the usage of call stack can potentially grow as the input number becomes larger.
For smaller values of callingNumber, the depth of the call stack remains relatively small.
However, as callingNumber increases, the depth of the call stack grows accordingly. 
This can lead to an increase in the memory requirements for the call stack,
potentially causing stack overflow errors if the memory limit is exceeded.
 
*/


package ca.concordia.algos;


public class FibonacciSequenceObserver {
		
	
	public static int counter = 0;
	
	public static void main(String[] args) {

		 int callingNumber = Integer.parseInt(args[0]);
	
		 
		 int[] arr = new int[callingNumber];
		 for(int i=0; i<arr.length; i++) {
			 if(i <= 4) {
				 arr[i] = sequence(i);
			 }
			 else {
				 arr[i] = sequence(i); 
		 }
	
	}
		 
		 for (int number : arr) {
		        System.out.print(number + " ");
		    }
		    System.out.println();
		    System.out.println("Calls: " + counter);
	}
	

	 public static int sequence(int input) {
		 counter++;
		 
		 if (input < 5) {
	            return 1;
	        }
		 else {
	            return (2 * sequence(input - 4)) + sequence(input - 2) + sequence(input - 1);
	        }
	    }
		
	

}

