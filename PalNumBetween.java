package PalindromeNumbersBet;

public class PalNumBetween {

	public static void main(String[] args) {

		for(int num=1000; num<=9999; num++)
		{
			String str = num+"";
			
			StringBuilder sb = new StringBuilder(str);
			
			if(sb.reverse().toString().equals(str))
				System.out.println(num);
		}		
	}

}
