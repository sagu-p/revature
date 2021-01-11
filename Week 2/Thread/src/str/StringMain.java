package str;

import java.util.List;

public class StringMain {
	
	public static void main(String[] args) {
		
		String s = "Money Transafer from 100002";
		String s1 = "Money Transafer from 100001";
		
		String[] a1 = s.split(" ");
		String[] a2 = s1.split(" ");
		
		for(String i: a1) {
			if(i.matches("[0-9]{6}")) {
				long n = Long.parseLong(i);
				System.out.println(n);
			}				
		}
		for(String i: a2) {
			if(i.matches("\\d+")) {
				long n = Long.parseLong(i);
				System.out.println(n);
			}
		}
	}

}
