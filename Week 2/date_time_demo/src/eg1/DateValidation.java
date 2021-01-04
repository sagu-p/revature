package eg1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidation {
	
	public static void main(String[] args) { 
		
		String s = "11-26-1998";
		if(s.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
			sdf.setLenient(false);
			Date date;
			try {
				date = sdf.parse(s);
				System.out.println("Date: "+date);
			} catch (ParseException e) {
				System.out.println("invalid Date.");
			}

		}
		else
			System.out.println("Invalid");
				
		
	}

}
