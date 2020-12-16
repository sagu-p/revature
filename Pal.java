package PalindromeString;

public class Pal {
	
	public static void main(String[] args) {
		
		String str = "sagar";
		StringBuffer sr = new StringBuffer(str);
		sr.reverse();
		String rev_str = sr.toString();
		if(rev_str.equals(str))
			System.out.println(str + " is Palindrome String");
		else
			System.out.println(str + " is not Palindrome String");
		
		
	}

}
