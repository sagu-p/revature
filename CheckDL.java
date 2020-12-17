package RegularExp;

import java.util.Scanner;

public class CheckDL {
	
public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the Driver's License: ");
		String ssn = sc.next();
		
		if( ssn.matches("[A-z]-[0-9]{3}-[0-9]{3}-[0-9]{3}-[0-9]{3}") )
			System.out.println("Valid Driver's License Number.");
		else
			System.out.println("Invalid Driver's License Number.");
		
	}

}
