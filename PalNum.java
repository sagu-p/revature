package PalindromeNumber;

public class PalNum {
	
	public static void main(String[] args) {
		
		int num = 121, rev_num=0;
		
		int ch =num;
		while(ch>0)
		{
			rev_num = (rev_num * 10) + (ch % 10);
			ch = ch / 10;
		}
		
		if(num == rev_num)
			System.out.println(num + " is a palindrome Number.");
		else
			System.out.println(num + " is not a palindrome Number.");
		
	}

}
