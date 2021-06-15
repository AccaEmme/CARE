package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password {
	/*
	 * [RATIONAL]
	 * Hermann: Per evitare di dare troppe responsabilità alla classe "User",
	 * deleghiamo alla classe Password di cui l'Utente si compone,
	 * creando così una relazione, per avere la complessità sotto controllo.
	 * Non perché sia una parte logica, ma perché delegando le responsabilità
	 * aiuta una realizzazione di tipo qualitativo.
	 */
	
	String hiddenPassword;

	
	public Password(String hiddenPassword) {
		this.hiddenPassword = hiddenPassword;
	}

	public String getHiddenPassword() {
		return this.hiddenPassword;
	}
	
	private void setHiddenPassword(String hiddenPass) {
		this.hiddenPassword = hiddenPass;
	}
	
    public static String getMd5(String input)    {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
  
            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
  
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
  
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext.toUpperCase();
        } 
  
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static char[] generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));
     
        for(int i = 4; i< length ; i++) {
           password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        
        if( validatePassword(password.toString()) ) return password; else generatePassword(length); // *** rischiamo loop?
        return password;
     }
    
    public static boolean validatePassword(final String givenPassword) throws IllegalArgumentException {
    	final String PASSWORD_PATTERN =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    	/* Regex explaination of password secure requirements:
    	 * Password must contain at least one digit [0-9].
    	 * Password must contain at least one lowercase Latin character [a-z].
    	 * Password must contain at least one uppercase Latin character [A-Z].
    	 * Password must contain at least one special character like ! @ # & ( ).
    	 * Password must contain a length of at least 8 characters and a maximum of 20 characters.
    	  	^                                   # start of line
  			(?=.*[0-9])                       # positive lookahead, digit [0-9]
  			(?=.*[a-z])                       # positive lookahead, one lowercase character [a-z]
  			(?=.*[A-Z])                       # positive lookahead, one uppercase character [A-Z]
  			(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]) # positive lookahead, one of the special character in this [..]
  			.                                 # matches anything
  			{8,20}                            # length at least 8 characters and maximum of 20 characters
			$                                   # end of line
    	 */
    	
        final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(givenPassword);
        //return matcher.matches();
        
        /*
        try {
        	if( matcher.matches() ) return true;
        	else throw new IllegalArgumentException("Password pattern conformity not valid");
        }catch(IllegalArgumentException e) {
        	System.err.println( e.getMessage() );

        }
        return false;
        */
        
        if( matcher.matches() ) return true; else throw new IllegalArgumentException("Password pattern conformity not valid");
    }
}