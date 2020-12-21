package Array;

import java.util.Scanner;

public class EvenOddArray {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Array Size: ");
		int num = sc.nextInt();
		
		int i, e, o;
		int []arr = new int[num];
		int []even = new int[num];
		int []odd = new int[num];
		
		for(i=0; i<num; i++)
		{
			System.out.print("Enter Array Element "+ (i+1) +": ");
			arr[i] = sc.nextInt();
		}
		
		for(i=0, e=0,o=0;i<num;i++)
		{
			if(arr[i] %2 == 0)
			{
				even[e]=arr[i];
				e++;
			}
			else
			{
				odd[o]=arr[i];
				o++;
			}
		}
		
		System.out.println("\nEven Numbers Array:");
		for(i=0; i<e;i++)
		{
			System.out.print(even[i]+" ");
		}
		
		System.out.println("\nOdd Numbers Array:");
		for(i=0; i<o;i++)
		{
			System.out.print(odd[i]+" ");
		}
		
	}

}
