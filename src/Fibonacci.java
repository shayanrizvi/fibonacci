import java.util.*;

/**
 * 
 * @author Shayan
 *
 * This program takes a user entered integer n between 1 and 92 and returns the nth fibonacci number.
 * The user can toggle between dynamic and normal mode to get the fibonacci number either dynamically or normally.
 * 
 */
public class Fibonacci {

	private static int c;														// method call count variable
	private static long[] fibList = {};											// fibonacci number list
	
	public static void main(String args[]) {
		
		// initialize variables
		boolean q = false;														// program state
		boolean d = false;														// dynamic/normal mode
		Scanner in = new Scanner(System.in);									// input scanner
		String input;															// user input
		
		int n;																	// fibonacci index to return
		long f;																	// fibonacci number f(n)
		
		// user input loop
		do {
			
			System.out.println("Enter an integer between 1 and 92");			// input prompt
			System.out.println("Enter \"d\" to toggle between dynamic and normal mode");
			System.out.println("Enter \"q\" to quit: ");
			System.out.println();												// print new line
					
			input = in.nextLine();												// record user input
			System.out.println();												// print new line
			
			if(input.equals("q")) {
				
				q = true;
				System.out.println("Program terminated.");						// program termination prompt

			} else if (input.equals("d")) {
				
				d = !(d);														// toggle dynamic/normal mode
				
				if (d) System.out.println("Dynamic mode");						// check and display mode
				else System.out.println("Normal mode");
				
				System.out.println();
				
			} else {
				
				// check for integer input
				try {
					
					n = Integer.parseInt(input);								// copy input to number variable
					
				} catch (NumberFormatException e) {
					
					n = 0;														// set input to invalid number
					
				}
				
				// check for valid input range
				if(0 < n && n < 93) {
					
					if(d) f = dFib(n);											// dynamically get fibonacci number
					else f = fib(n);											// get fibonacci number
					
					System.out.println("fib(" + n + ") = " + f);				// print results
					
				} else System.out.println("Invalid input.");					// invalid input prompt
				
				System.out.println();											// print new line
				
			}

			System.out.println("# of method calls: " + c);						// print method call count
			System.out.println("Fibonacci list: " + Arrays.toString(fibList));	// print fibonacci number list
			System.out.println();												// print new line

			c = 0;																// reset method call count
			
		} while(!(q));															// check for program termination
		
		in.close();																// close input
		
	}

	/**
	 * Takes a positive integer n and returns the nth fibonacci number.
	 * This method is not dynamic.
	 * 
	 * @param n fibonacci index to calculate
	 * @return nth fibonacci number
	 */
	public static long fib(int n) {
		
		c++;																	// increment method call count
		
		if(n < 3) return 1;														// return 1 for 1 and 2
		else return fib(n-1) + fib(n-2);										// return sum of previous two fibonacci numbers
		
	}
	
	/**
	 * Takes a positive integer n and returns the nth fibonacci number.
	 * This method is dynamic.
	 * 
	 * @param n fibonacci index to calculate
	 * @return nth fibonacci number
	 */
	public static long dFib(int n) {
		
		c++;																	// increment method call count
		
		// check if fibonacci number is stored for index n
		if(fibList.length >= n) {												// check if fibonacci number list has n length
			
			if(fibList[n-1] != 0) return fibList[n-1];							// check if nth index is not 0
			
		} else fibList = Arrays.copyOf(fibList, n);								// copy fibonacci number list to new list with n indexes
		
		if(n < 3) return fibList[n-1] = 1;										// return 1 for 1 and 2
		else return fibList[n-1] = dFib(n-1) + dFib(n-2);						// store and return sum of previous two fibonacci numbers
		
	}
	
}
