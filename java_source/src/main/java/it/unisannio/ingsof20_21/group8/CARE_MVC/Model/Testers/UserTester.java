package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Testers;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Password;

public class UserTester {

	public static void main(String[] args) {
		/*
		System.out.println( Password.generatePassword(10) );
		System.out.println( Password.generatePassword(10) );
		System.out.println( Password.generatePassword(10) );
		System.out.println( Password.generatePassword(10) );
		System.out.println( Password.generatePassword(10) );
		System.out.println( Password.generatePassword(10) );
		System.out.println( Password.generatePassword(10) );
		*/
		
		System.out.println( "Generated password 5 chars: " + String.valueOf(Password.generatePassword(5)) );
		System.out.println( "Generated password 10 chars: " + String.valueOf(Password.generatePassword(10)) );
		System.out.println( "Generated password 15 chars: " + String.valueOf(Password.generatePassword(15)) );
		System.out.println( "Generated password 20 chars: " + String.valueOf(Password.generatePassword(20)) );
		/* Please keep note of your temporary password */
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY,17);
		cal.set(Calendar.MINUTE,30);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);

		Date d = cal.getTime();
		//Date prova = new Date("1900-01-01");
		System.out.println( Constants.dateFormatString.format(d) );

		// tester valid password regex complexity
		String notvalidpasswordcriteria = "123456789";
		String validpasswordcriteria 	= "PasswordBuona1@";

		if (
				Password.validatePlaintextPasswordPattern(validpasswordcriteria)==true
			)
				System.out.println("3");
		else
				System.out.println("not 3");
	
		if (
			Password.validatePlaintextPasswordPattern(notvalidpasswordcriteria)==true
			)
			System.out.println("4");
		else
			System.out.println("not 4");
	
}

}
