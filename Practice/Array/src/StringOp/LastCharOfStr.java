package StringOp;

import java.util.Scanner;

public class LastCharOfStr {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Line: ");
		String str = sc.nextLine();
		
		String ar[] = str.split(" ");
		
		for(int i=0; i<ar.length; i++)
		{
			ar[i] = ar[i].substring(0, ar[i].length()-1 ) + ar[i].substring(ar[i].length()-1).toUpperCase();
		}
		
		System.out.println("New Sentence will be:\t"+ String.join(" ", ar) );
		sc.close();
	}

}
