/*
 * Picking Number Task Description
 * 
 * Description
 *
 * Write a java application that works as follows.
 * It asks the user to give 2 positive integers: N and M, where both numbers are greater then 0 and N >= M.
 * In case of an invalid input, the program must write out the problem and ask it again.
 * User may cancel operation by writing q or quit.
 * If given numbers are fine, then the program should generate M different numbers between 1 and N, and  write out them in ascending order.
 * Sample input and output:
 * 
 * Example#1:
 * N = 10
 * M = 3
 * Result = 1, 5, 7
 * 
 * Example#2:
 * N = 5
 * M = 5
 * Result = 1, 2, 3, 4, 5
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Gustavo Luvizotto Cesar
 * Jun 9, 2015
 * gustavoluvizotto@gmail.com
 */
public class PickingNumber {
	
	private int n;
	private int m;
	public static final int QUIT = -2;

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}
	
	/**
	 * @return If success, return the integer of the number of input. If entered a character, return -1. If entered 'q' or 'quit', then return -2
	 * @throws IOException
	 */
	public int readPositiveInteger() throws IOException {
		int number = 0;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String buffer = null;
		
		buffer = reader.readLine();
		if (isInteger(buffer)) {
			number = Integer.parseInt(buffer);
			if (number > 0)
				return number;
			else
				return -1;
		} else {
			buffer = buffer.toUpperCase();
			if (buffer.compareTo("QUIT") == 0 || buffer.compareTo("Q") == 0)
				return QUIT;
			return -1;
		}
	}
	
	/**
	 * @param s String to check if it is a integer or not
	 * @return true if 's' is a integer o false otherwise
	 */
	public boolean isInteger(String s) {
	    return isInteger(s, 10);	// 10 base
	}

	/**
	 * @param s String to seek if have a valid digit
	 * @param radix Specific radix that represent a number
	 * @return true if 's' is a integer or false otherwise
	 */
	public boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) 
	    	return false;
	    
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) 
	            	return false;
	            else 
	            	continue;
	        }
	        if(Character.digit(s.charAt(i), radix) < 0) 
	        	return false;
	    }
	    
	    return true;
	}
	
	/**
	 * @param args Not used in this program
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int number = 0;
		PickingNumber pN = new PickingNumber();
		
		System.out.print("Write out 'quit' or 'q' if you want to stop the program at any time");
		System.out.println();
		
		for (;;) {
			System.out.print("N: ");
			number = pN.readPositiveInteger();
			if (number == QUIT)
				break;
			while (number == -1) {
				System.out.println("Invalid input. It's not a number! Try again.");
				System.out.print("N: ");
				number = pN.readPositiveInteger();
			}
			if (number == QUIT)
				break;
			pN.setN(number);
			
			System.out.print("M: ");
			number = pN.readPositiveInteger();
			if (number == QUIT)
				break;
			while (number == -1 || number > pN.getN()) {
				System.out.println("Invalid input. It's not a number or it's greater than N! Try again.");
				System.out.print("M: ");
				number = pN.readPositiveInteger();
			}
			if (number == QUIT)
				break;
			pN.setM(number);
			
			// creating the initial list with the N elements
	        for (int i = 1; i < pN.getN() + 1; i++) {
	            list.add(new Integer(i));
	        }
	        
	        // shuffle the list, removing N-M last elements from the list, sorting the list in ascending order and print the final list
	        Collections.shuffle(list);
	        list.subList(pN.getM(), pN.getN()).clear();
	        Collections.sort(list);
	        for (int i = 0; i < pN.getM(); i++) {
	            System.out.println(list.get(i));
	        }
	        
	        // clear the list to use again if the user want to
	        list.clear();
	        System.out.println();
		}
	}
}