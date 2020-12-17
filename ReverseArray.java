package Array;

import java.util.Scanner;

public class ReverseArray {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Array Size: ");
		int num = sc.nextInt();
		
		int []arr = new int[num];
		
		for(int i=0; i<num; i++)
		{
			System.out.print("Enter Array Element "+ (i+1) +": ");
			arr[i] = sc.nextInt();
		}
		
		System.out.println("\nReverse of the given Array:");
		for(int i= num-1; i>=0; i--)
			System.out.print(arr[i]+"\t");
		
		sc.close();
	}

}
