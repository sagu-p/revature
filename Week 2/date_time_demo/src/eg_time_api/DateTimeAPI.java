package eg_time_api;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeAPI {

	public static void main(String[] args) {
		
		LocalDate ld = LocalDate.now();
		System.out.println("Date (Current): "+ld);
		ld = ld.plusMonths(2);
		System.out.println("After 2 months: "+ld);
		System.out.println("Leap Year: " + ld.isLeapYear());
		
		String s = "26-11-1998";
		LocalDate dob = LocalDate.parse(s, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		System.out.println("DOB: "+dob);
		LocalDate current = LocalDate.now();
		System.out.println("Current: "+current);
		
		Period p = Period.between(dob, current);
		System.out.print("Your Age\nYear: "+p.getYears());
		System.out.print("\tMonths: "+p.getMonths());
		System.out.println("\tDays: "+p.getDays());

		System.out.println(ChronoUnit.YEARS.between(dob, current));
		System.out.println(ChronoUnit.MONTHS.between(dob, current));
		System.out.println(ChronoUnit.WEEKS.between(dob, current));
		System.out.println(ChronoUnit.DAYS.between(dob, current));
	}

}
