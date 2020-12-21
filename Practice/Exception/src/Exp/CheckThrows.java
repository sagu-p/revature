package Exp;

import java.io.FileNotFoundException;

public class CheckThrows {
	
	public static void main(String[] args) {
		
		ThrowsExp th = new ThrowsExp();
		
		try {
			th.openFile("ABC.txt");
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		
		
	}

}
