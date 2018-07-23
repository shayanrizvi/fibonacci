import java.util.*;

/**
 * 
 * @author Shayan
 *
 * This program takes a user entered integer n between 0 and 92 and returns the nth fibonacci number.
 * The user can toggle between dynamic and normal mode to get the fibonacci number either dynamically or normally.
 * The user can also toggle between a top-down memoization approach and a bottom-up tabulation approach.
 * 
 */
public class Fibonacci {

	private static int c;														// method call count variable
	private static long[] fibList = {};											// fibonacci number list
	
	public static void main(String args[]) {
		
		// initialize variables
		boolean q = false;														// program state
		boolean d = false;														// dynamic/normal mode
		boolean t = false;														// tabulation/memoization mode
		Scanner in = new Scanner(System.in);									// input scanner
		String input;															// user input
		
		int n;																	// fibonacci index to return
		long f;																	// fibonacci number f(n)
		
		// user input loop
		do {
			
			System.out.println("Enter an integer between 0 and 92");			// input prompt
			System.out.println("Enter \"d\" to toggle between dynamic and normal mode");
			System.out.println("Enter \"t\" to toggle between tabulation and memoization mode");
			System.out.println("Enter \"c\" to clear fibonacci sequence memory: ");
			System.out.println("Enter \"q\" to quit: ");
			System.out.println();												// print new line
					
			input = in.nextLine();												// record user input
			System.out.println();												// print new line
			
			// check for command input
			if(input.equals("q")) {
				
				q = true;														// change program state to termination
				System.out.println("Program terminated.");						// program termination message
				
			} else if (input.equals("c")) {
				
				fibList = new long[0];											// clear fibonacci sequence list
				System.out.println("Fibonacci sequence memory cleared.");		// display list cleared message
				
			} else if (input.equals("d")) {
				
				d = !(d);														// toggle dynamic/normal mode
				
				if (d) System.out.println("Dynamic mode");						// check and display mode
				else System.out.println("Normal mode");
				
			} else if (input.equals("t")) {
				
				d = true;														// set mode to dynamic
				t = !(t);														// toggle tabulation/memoization mode
				
				if (t) System.out.println("Tabulation mode");					// check and display mode
				else System.out.println("Memoization mode");
				
			} else {
				
				// check for integer input
				try {
					
					n = Integer.parseInt(input);								// copy input to number variable
					
				} catch (NumberFormatException e) {
					
					n = 0;														// set input to invalid number
					
				}
				
				// check for valid input range
				if(-1 < n && n < 93) {
					
					if(d) {
						
						if(t) f = tFib(n);										// get fibonacci number - tabulation
						else f = mFib(n);										// get fibonacci number - memoization
						
					} else f = fib(n);											// get fibonacci number - normal
					
					System.out.println("fib(" + n + ") = " + f);				// print results
					
				} else System.out.println("Invalid input.");					// invalid input prompt
				
			}
			
			System.out.println();												// print new line
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
		
		if(n < 2) return n;														// return fib(0) and fib(1)
		else return fib(n-1) + fib(n-2);										// return sum of previous two fibonacci numbers
		
	}

	/**
	 * Takes a positive integer n and returns the nth fibonacci number.
	 * This method is dynamic and uses a top-down memoization approach.
	 * 
	 * @param n fibonacci index to calculate
	 * @return nth fibonacci number
	 */
	public static long mFib(int n) {
		
		c++;																	// increment method call count
		
		// check if fibonacci number is stored for index n
		if(fibList.length > n) {												// check if fibonacci number list has n length
			
			if(fibList[n] != 0) return fibList[n];								// check if nth index is not 0
			
		} else fibList = Arrays.copyOf(fibList, n + 1);							// copy fibonacci number list to new list with n indices
		
		if(n < 2) return fibList[n] = n;										// return fib(0) and fib(1)
		else return fibList[n] = mFib(n-1) + mFib(n-2);							// store and return sum of previous two fibonacci numbers
		
	}

	/**
	 * Takes a positive integer n and returns the nth fibonacci number.
	 * This method is dynamic and uses a bottom-up tabulation approach.
	 * 
	 * @param n fibonacci index to calculate
	 * @return nth fibonacci number
	 */
	public static long tFib(int n) {
		
		c++;																	// increment method call count
		
		// check if fibonacci number is not stored for index n
		if(fibList.length <= n) {
			
			fibList = Arrays.copyOf(fibList, n + 1);							// copy fibonacci number list to new list with n indices
			
			// for the number of indices up to n
			for(int i = 0; i < n + 1; i++) {
				
				if(i < 2) fibList[i] = i;										// initialize first two fibonacci numbers
				else fibList[i] = fibList[i-1] + fibList[i-2];					// calculate and store the fibonacci number for the index
				
			}
		
		}
		
		return fibList[n];														// return f(n)
		
	}
	
}
