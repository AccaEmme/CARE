package it.unisannio.CARE.spring;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Date;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
/*
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;*/
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import it.unisannio.CARE.model.util.Password;
import it.unisannio.CARE.model.user.User;
import it.unisannio.CARE.model.bloodBag.BloodBag;
import it.unisannio.CARE.model.bloodBag.BloodGroup;
import it.unisannio.CARE.model.exceptions.IllegalPatternException;
import it.unisannio.CARE.model.exceptions.NullPasswordException;
import it.unisannio.CARE.model.exceptions.UserException;
import it.unisannio.CARE.model.exceptions.lllegalEmailException;
import it.unisannio.CARE.model.user.Role;


/**
 * JUnit test for UserTest.
 */

public class UserTest {
	
	/**
	* Junit creation for the verification of the constructor of the UserTest class
	* @throws UserException
	* @throws NullPasswordException
	* @result the result is the correct creation of the user
	*/
	@Test 
	public void ValidityTest_Constructor1_notNullObject() throws UserException, NullPasswordException {
		String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER; 
		String validPassword = "Test4ll+";
		User u = new User(validUsername, validPassword, secretary);
		assertNotNull(u);
	}
		
	/**
	* Junit creation to verify the constructor of the UserTest class with an invalid password
	* @throws UserException
	* @throws NullPasswordException
	* @result the result is the exception for the invalid password
	*/
	@Test 
	public void InvalidityTest_Constructor1_validUserInvalidPass() throws UserException, NullPasswordException {
		String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER;
		String invalidPassword = "testall";
		
		assertThrows(Exception.class, () -> {
			User u = new User(validUsername, invalidPassword, secretary);
		 }
		);
	}
	
	/**
	* Junit creation to verify the constructor of the UserTest class with an invalid but empty password
	* @throws UserException
	* @throws NullPasswordException
	* @result the result is the exception for the invalid password
	*/
	@Test(expected = IllegalPatternException.class)
	public void InvalidityTest_Constructor1_validUserInvalidPass2() throws UserException, NullPasswordException {
		String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER;
		String invalidPassword = "";
		
		User u = new User(validUsername, invalidPassword, secretary);
	}
	
	/**
	* Junit creation to verify the constructor of the UserTest class with an invalid name
	* @throws UserException
	* @throws NullPasswordException
	* @result the result is the exception for the invalid name
	*/
	@Test(expected = IllegalArgumentException.class)
	public void InvalidityTest_Constructor1_invalidUservalidPass() throws UserException, NullPasswordException {
		String validUsername = "H";
		Role secretary 		 = Role.ROLE_OFFICER;
		String invalidPassword = "Test4ll+";
		
		User u = new User(validUsername, invalidPassword, secretary);
	}
	
	/**
	* Junit creation for the verification of the second constructor of the UserTest class
	* @throws UserException
	* @throws NullPasswordException
	* @result the result is the correct creation of the user
	*/
	@Test 
	public void ValidityTest_Constructor2_notNullObject() throws UserException, NullPasswordException, NullPasswordException, UserException {
		String validUsername = "Hermann";
		String validPassword = "Test4ll+";
		Password pw = new Password(validPassword);
		User u = new User(validUsername, pw);
		assertNotNull(u);
	}
	
	/**
	* Junit creation to verify the constructor of the UserTest class with an incorrect password
	* @throws UserException
	* @throws NullPasswordException
	* @result the result is the incorrect user creation due to the wrong password
	*/
	@Test(expected = IllegalPatternException.class)
	public void InvalidityTest_Constructor2_validUserInvalidPass()  throws UserException, NullPasswordException {
		String validUsername = "Hermann";
		String validPassword = "";
		Password pw = new Password(validPassword);
		User u = new User(validUsername, pw);
		assertNotNull(u);
	}

	/**
	* Junit creation to verify the constructor of the UserTest class with an incorrect username
	* @throws UserException
	* @throws NullPasswordException
	* @result the result is the incorrect user creation due to the wrong username
	*/
	@Test 
	public void InvalidityTest_Constructor2_InvalidUserValiddPass() throws UserException, NullPasswordException {
		String validUsername = "";
		String invalidPW = "Test4ll+";
		Password pw = new Password(invalidPW);
		assertThrows(Exception.class, () -> {
			User u = new User(validUsername, pw);
			}
		);
	}

	
	/**
	* Junit creation for the verification of the GET method of the Username
	* @throws NullPasswordException
	* @throws UserException
	* @result returns the UserName of the user
	*/
	@Test
	public void ValidityTest_getUsername_notNullObject() throws UserException, NullPasswordException {
		
		String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER;
		String validPassword = "Test4ll+";
		User u = new User(validUsername, validPassword, secretary);
		
		assertNotNull(u.getUsername());
	}
	
	/**
	* Junit creation for verification of the SET method of the Username
	* @throws NullPasswordException
	* @throws UserException
	*/
	@Test()
	public void ValidityTest_setUsername_notNullObject() throws UserException, NullPasswordException {
		
		String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER;
		String validPassword = "Test4ll+";
		User u = new User(validUsername, validPassword, secretary);
		
		u.setUsername("Donato");
		assertNotNull(u);
	}
	
	/**
	* Junit creation to verify the SET method of the Username by inserting an invalid name
	* @throws NullPasswordException
	* @throws UserException
	*/
	@Test(expected = IllegalArgumentException.class)
	public void InvalidityTest_setUsername_notNullObject() throws UserException, NullPasswordException {
		
		String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER;
		String validPassword = "Test4ll+";
		User u = new User(validUsername, validPassword, secretary);
		
		u.setUsername("D");
		assertNotNull(u);
	}
	
	
	/**
	* Junit creation to verify the GET method to get the user's passw
	* @throws NullPasswordException
	* @throws UserException
	* @result returns the user's password
	*/
	@Test
	public void ValidityTest_getPassword_notNullObject() throws UserException, NullPasswordException {
		
		String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER;
		String validPassword = "Test4ll+";
		User u = new User(validUsername, validPassword, secretary);
		
		assertNotNull(u.getPassword());
	}
	
	
	/**
	* Junit creation for the verification of the SET method of the passw
	* @throws NullPasswordException
	* @throws UserException
	* @result returns an exception in case the password is invalid for the pattern
	*/
	@Test
	public void ValidityTest_setPassword_notNullObject() throws UserException, NullPasswordException {
		
		String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER;
		String validPassword = "Test4ll+";
		User u = new User(validUsername, validPassword, secretary);
		
		u.setPassword("Test5ll+");
		assertNotNull(u);
	}
	
	/**
	* Junit creation for the verification of the SET method of the passw
	* @throws NullPasswordException
	* @throws UserException
	* @result returns an exception as the new password pattern is incorrect
	*/
	@Test(expected = IllegalPatternException.class)
	public void InvalidityTest_setPassword_notNullObject() throws UserException, NullPasswordException {
		
		String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER;
		String validPassword = "Test4ll+";
		User u = new User(validUsername, validPassword, secretary);
		
		u.setPassword("Test");
		assertNotNull(u);
	}
	
	/**
	* Junit creation for the verification of the GET method to obtain the date of the last entered passw
	* @throws NullPasswordException
	* @throws UserException
	* @result returns the date of the last password entered
	*/
	@Test
	public void ValidityTest_getPasswordLastUpdate_notNullObject() throws UserException, NullPasswordException {
		
		String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER;
		String validPassword = "Test4ll+";
		User u = new User(validUsername, validPassword, secretary);
		
		u.setPassword("Test4ll+");
		
		assertNotNull(u.getPasswordLastUpdate());
	}
	
	/**
	* Junit creation for the verification of the SET method of the Email
	* @throws NullPasswordException
	* @throws UserException
	* @throws lllegalEmailException
	* @result The result is positive because the password is correct
	*/
	@Test
	public void InvalidityTest_setEmail_notNullObject() throws UserException, NullPasswordException, lllegalEmailException {
		
		String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER;
		String validPassword = "Test4ll+";
		User u = new User(validUsername, validPassword, secretary);
		
		u.setEmail("Giggino1996@gmail.com");
		assertNotNull(u);
	}
	
	/**
	* Junit creation for the verification of the GET method to get the user's email
	* @throws NullPasswordException
	* @throws UserException
	* @throws lllegalEmailException
	* @result returns the user's email
	*/
	@Test
	public void ValidityTest_GetEmail_notNullObject() throws UserException, NullPasswordException, lllegalEmailException {
		
		String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER;
		String validPassword = "Test4ll+";
		User u = new User(validUsername, validPassword, secretary);
		u.setEmail("Giggino1996@gmail.com");
		assertNotNull(u.getEmail());
	}
	
	/**
	* Junit creation for verifying correct passwords with the use of a stream
	* @throws NullPasswordException
	* @throws UserException
	* @throws lllegalEmailException
	* @result The results are all positive
	*/
    @ParameterizedTest(name = "#{index} - Run test with valid Email complexity pattern = {0}")
    @MethodSource("validEmailsProvider")
    public void ValidityTest_setEmail_notNullObject(String Email) throws lllegalEmailException {
    	
    	String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER;
		String validPassword = "Test4ll+";
		User u = new User(validUsername, validPassword, secretary);
        u.setEmail(Email);
        assertNotNull(u);
    }

    /**
    * Junit creation for verifying incorrect passwords with the use of a stream
    * @throws NullPasswordException
    * @throws UserException
    * @throws lllegalEmailException
    * @result The results are all negative
    */
    @ParameterizedTest(name = "#{index} - Run test with invalid Email complexity pattern = {0}")
    @MethodSource("invalidEmailsProvider")
    public void InValidityTest_setEmail_notNullObject(String Email) throws lllegalEmailException {

		
			String validUsername = "Hermann";
			Role secretary 		 = Role.ROLE_OFFICER;
			String validPassword = "Test4ll+";
			User u = new User(validUsername, validPassword, secretary);
			
			assertThrows(Exception.class, ()->{
				u.setEmail(Email);
			});
	}
	
    /**
    * Junit creation for GET method verification to get user role
    * @throws NullPasswordException
    * @throws UserException
    * @result returns the user's role
    */
	@Test
	public void ValidityTest_getRole_notNullObject() throws UserException, NullPasswordException {
		
		String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER;
		String validPassword = "Test4ll+";
		User u = new User(validUsername, validPassword, secretary);
		
		assertNotNull(u.getRole());
	}
	
	/**
	* Junit creation for the verification of the role SET method
	* @throws NullPasswordException
	* @throws UserException
	* @result The role is successfully changed
	*/
	@Test
	public void ValidityTest_setRole_notNullObject() throws UserException, NullPasswordException {
		
		String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER;
		String validPassword = "Test4ll+";
		User u = new User(validUsername, validPassword, secretary);
		
		u.setRole(Role.ROLE_STOREMANAGER);
		assertNotNull(u);
	}
	
	/**
	* Junit creation for the verification of the GET USERDAO method
	* @throws NullPasswordException
	* @throws UserException
	* @result returns ud if the method is correct
	*/
	@Test
	public void ValidityTest_getUserDAO_notNullObject() throws UserException, NullPasswordException {
		
		String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER;
		String validPassword = "Test4ll+";
		User u = new User(validUsername, validPassword, secretary);
		
		assertNotNull(u.getUserDAO());
	}
	
	/**
	* Junit creation to verify the exist method of the USER class
	* @throws NullPasswordException
	* @throws UserException
	* @result returns true if the USER object exists
	*/
	@Test
	public void ValidityTest_exists_notNullObject() throws UserException, NullPasswordException {
		
		String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER;
		String validPassword = "Test4ll+";
		User u = new User(validUsername, validPassword, secretary); 
		assertNotNull(u.exists());
	}
	
	
    
    static Stream<String> validEmailsProvider() {
    	
        return Stream.of(
                "user@domain.com",
                "user@domain.co.in",
                "user1@domain.com",               
                "user.name@domain.com",           
                "user#@domain.co.in",            
                "user@domaincom" 
        );
    }
    
    static Stream<String> invalidEmailsProvider() {
        return Stream.of(
                "user#domain.com",                 
                "@yahoo.com"                
                ); 
    }
  

}
