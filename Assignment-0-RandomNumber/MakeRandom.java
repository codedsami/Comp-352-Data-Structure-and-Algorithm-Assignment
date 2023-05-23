//Miskat Mahmud

import java.util.Random;

public class MakeRandom {

	public static void main(String[] args) {
		if (args.length == 1) {
			makeRandom(Integer.parseInt(args[0]));
        } 
		else if (args.length == 2) {
			makeRandom(Long.parseLong(args[0]), Integer.parseInt(args[1]));

        }
		
        else {
        	System.out.println("Only two arguments, please!!!");
        }

	}
	
	public static void makeRandom(int num1) {
        int [] randomInteger = new int[num1];
    	Random random = new Random();
        for(int i=0; i<randomInteger.length; i++) {
        	randomInteger[i] = random.nextInt(Integer.MAX_VALUE);
        	System.out.print(randomInteger[i] + " ");
        }
	}
	
	public static void makeRandom(Long num1, int num2) {
		 
        int [] randomInteger = new int[num2];

    	Random random = new Random(num1);

        for(int i=0; i<randomInteger.length; i++) {
        	int randomNumber =  random.nextInt(Integer.MAX_VALUE);
        	randomInteger[i] = randomNumber;
        }
        for (int i=0; i<randomInteger.length; i++) {
        	System.out.print(randomInteger[i] + " ");
        }
	}
	

}
