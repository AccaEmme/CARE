package it.unisannio.CARE.model.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.unisannio.CARE.model.exceptions.IllegalPatternException;


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

	/*
	public Password() {
		String temppass = this.generatePassword(15);
		if(validatePassword(temppass)) {
			
		}
		
	}
	*/
	
	/**
	**************************************************************************
	 * Metodo che controlla se la passw è criptata
	 * @param String hiddenPassword
	 * @throws IllegalPatternException 
	 * @exception IllegalArgumentException
	 **************************************************************************
    */
	public Password(String hiddenPassword) throws IllegalPatternException {
		//assert hiddenPassword != null;
		if( (hiddenPassword == null) || (hiddenPassword.equals("")) )
			throw new IllegalPatternException("Password.java constructor: hiddenPassword is null");
		this.hiddenPassword = hiddenPassword;
	}

	/**
     **************************************************************************
     * Metodo per il GET della passw criptata
     * @return hiddenPassword
     **************************************************************************
    */
	public String getHiddenPassword() {
		return this.hiddenPassword;
	}
	
	/**
     **************************************************************************
     * Metodo per il Set della pasw Criptata
     * @param String hiddenPass
     **************************************************************************
    */
	public void setHiddenPassword(String hiddenPass) {
		assert hiddenPass != null;
		this.hiddenPassword = hiddenPass;
	}
	
	/*
	public void setPlainTextPassword(String plainTextPassword) {
		if(	validatePassword(plainTextPassword) ) {
			this.hiddenPassword = getMd5( 
										plainTextPassword + Constants.USER_MD5_SALT
										);
		System.out.println("plainTextPassword: "+plainTextPassword
							+"\t Constants.USER_MD5_SALT: "+ Constants.USER_MD5_SALT
							+"\t hiddenPassword:" + this.hiddenPassword);
		}
	}
	*/
	
	/**
	**************************************************************************
	 * Metodo per il ritorno dell'hashcode con algoritmo MD5 del valore in ingresso inclundento il SALT
	 * @param String input
	 * @return hashtext.toUpperCase()
	 **************************************************************************
    */
    public static String getBCrypt(String input)    {
    	
    	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	input+=Constants.PASSWORD_SALT;
	    return    passwordEncoder.encode(input);
 /*
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
        }*/
    }
    
    
    /**
	**************************************************************************
	 * metodo per generare la passw dell'utente
	 * @param int lenght
	 **************************************************************************
    */
    //public static char[] generatePassword(int length) {
    public static String generatePassword(int length) {
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
        
        //if( validatePassword(password.toString()) ) return password.toString(); else generatePassword(length); // *** rischiamo loop?
        //return password;
        //System.out.println("Password.java generatePassword, password: "+password);
        return String.valueOf(password);
     }
    

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
    
    /**
	**************************************************************************
	 * Metodo che controlla se il Pattern della passw è giusto
	 * @param final String givenPassword
	 * @exception IllegalArgumentException
	 **************************************************************************
    */
    public static boolean validatePlaintextPasswordPattern(final String givenPassword) {
    	final String PASSWORD_PATTERN =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
  	
        final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        //System.out.println("Password.java givenPassword: " + givenPassword);
        Matcher matcher;
        if(givenPassword != null)
        	matcher = pattern.matcher(givenPassword);
        else
        	throw new IllegalPatternException("Password.java validatePlaintextPasswordPattern: givenPassword is null. Value: "+givenPassword);
        	
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
        
        if( matcher.matches() ) 
        	return true; 
        else 
        	throw new IllegalPatternException("Password pattern conformity not valid");
        //if( !matcher.matches() ) throw new IllegalArgumentException("Password pattern conformity not valid");
    }

	public static String getMd5(String invalidPassword) {
		// TODO Auto-generated method stub
		return null;
	}
}