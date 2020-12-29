package eg1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFormatting {

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println("Current Date: "+date);
		/*
		 *  dd - date in 2 digits 
		 *  MM - month in 2 digits   MMM - short name of month    MMMM - full month name
		 *  yy - last 2 digits of year    yyyy - year in full
		 *  EEE - day in shortname,  EEEE full day name
		 *  mm - minutes
		 *  ss - seconds
		 *  HH - 24hour
		 *  hh - 12hour
		 *  z/Z - time zone
		 *  a - am/pm
		 *  
		 */
		
		SimpleDateFormat simpleFormat = new SimpleDateFormat("EEEE MMM dd,yyyy	hh:mm:ss a z");
		System.out.println("Formated Date: "+simpleFormat.format(date));

	}

}
