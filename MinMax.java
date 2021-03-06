package Array;

import java.util.Scanner;

public class MinMax {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Array Size: ");
		int num = sc.nextInt();
		
		int i, max, min;
		int []arr = new int[num];
		
		for(i=0; i<num; i++)
		{
			System.out.print("Enter Array Element "+ (i+1) +": ");
			arr[i] = sc.nextInt();
		}
		
		min=arr[0];
		max=arr[0];
		for(i=0;i<num;i++)
		{
			if(arr[i]>max)
				max=arr[i];
			if(arr[i]<min)
				min=arr[i];
		}
		
		System.out.println("\nMinimum digit from array is "+min);
		System.out.println("\nMaximum digit from array is "+max);
		
		sc.close();
	}

}
