package RegularExp;

import java.util.Scanner;

public class CheckSSN {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the SSN: ");
		String ssn = sc.next();
		
		if( ssn.matches("[0-9]{3}-[0-9]{2}-[0-9]{3}") )
			System.out.println("Valid Social Security Number.");
		else
			System.out.println("Invalid Social Security Number.");
		
	}

}
