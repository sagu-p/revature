package Array;

import java.util.Scanner;

public class DelArrayElement {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter Array Size: ");
		int num = sc.nextInt();
		
		int []arr = new int[num];
		int []new_arr = new int[num-1];
		
		for(int i=0; i<num; i++)
		{
			System.out.print("Enter Array Element "+ (i+1) +": ");
			arr[i] = sc.nextInt();
		}
		
		System.out.print("Enter element which you want to delete: ");
		int del_num = sc.nextInt();
		
		for(int i=0, j=0; i<num-1; i++, j++)
		{
			if(del_num == arr[i])
				j++;
			new_arr[i] = arr[j];
		}
		
		System.out.println("Old Array:");
		for(int i=0; i<num; i++)
			System.out.print(arr[i]+" ");
		
		System.out.println("\n\nNew Array:");
		for(int i=0; i<num-1; i++)
			System.out.print(new_arr[i]+" ");
		
	}
}
