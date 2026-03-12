package utility;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateAndTimeProvider {
	
	public static String getCurrentDateAndTime()
	{
		LocalDate date=LocalDate.now();
		LocalTime time = LocalTime.now();
		String timeValue = time.format(DateTimeFormatter.ofPattern("HH:mm"));
		System.out.println(date+" "+timeValue);
		
		String value=date+" "+timeValue;
		return value;
		
	}
	public static void main(String[] args)
	{
		getCurrentDateAndTime();
	}

}
