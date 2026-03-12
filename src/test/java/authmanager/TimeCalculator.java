package authmanager;

import java.time.Instant;

public class TimeCalculator {
	
	public static void main(String[] args)
	{
		Instant timenow = Instant.now();
		System.out.println(timenow);
		
		Instant expiryTime = timenow.plusSeconds(3600-300);
		System.out.println(expiryTime);
		
	}

}
