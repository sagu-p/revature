package StringOp;

import java.util.Scanner;

public class PlaindromeStr {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter String: ");
		String str = sc.next();
		
		StringBuilder sb = new StringBuilder(str);
		
		if(sb.reverse().toString().equals(str))
			System.out.println("Palindrome Str");
		else
			System.out.println("Not Palindrome Str");
		
	}

}
