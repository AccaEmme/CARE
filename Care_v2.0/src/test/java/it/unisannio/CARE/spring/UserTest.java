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
	* @throws UserException The username was not found or is not valid
	* @throws NullPasswordException The password was not found or is not valid
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
	* @throws UserException The username was not found or is not valid
	* @throws NullPasswordException The password was not found or is not valid
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
	* @throws UserException The username was not found or is not valid
	* @throws NullPasswordException The password was not found or is not valid
	*/
	//@Test(expected = IllegalPatternException.class)
	public void InvalidityTest_Constructor1_validUserInvalidPass2() throws UserException, NullPasswordException {
		String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER;
		String invalidPassword = "";
		assertThrows(Exception.class, ()->{
			User u = new User(validUsername, invalidPassword, secretary);
		});
	}
	
	/**
	* Junit creation to verify the constructor of the UserTest class with an invalid name
	* @throws UserException The username was not found or is not valid
	* @throws NullPasswordException The password was not found or is not valid
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
	* @throws UserException The username was not found or is not valid
	* @throws NullPasswordException The password was not found or is not valid
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
	* @throws UserException The username was not found or is not valid
	* @throws NullPasswordException The password was not found or is not valid
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
	* @throws UserException The username was not found or is not valid
	* @throws NullPasswordException The password was not found or is not valid
	*/
	@Test 
	public void InvalidityTest_Constructor2_InvalidUserValiddPass() throws UserException, NullPasswordException {
		String invalidUsername = "A";
		String validPassword = "Test4ll+";
		assertThrows(Exception.class, () -> {
			Password pw = new Password(validPassword);
			User u = new User(invalidUsername, pw);
			}
		);
	}

	
	/**
	* Junit creation for the verification of the GET method of the Username
	* @throws UserException The username was not found or is not valid
	* @throws NullPasswordException The password was not found or is not valid
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
	* @throws UserException The username was not found or is not valid
	* @throws NullPasswordException The password was not found or is not valid
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
	* @throws UserException The username was not found or is not valid
	* @throws NullPasswordException The password was not found or is not valid
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
	* @throws UserException The username was not found or is not valid
	* @throws NullPasswordException The password was not found or is not valid
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
	* @throws UserException The username was not found or is not valid
	* @throws NullPasswordException The password was not found or is not valid
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
	* @throws UserException The username was not found or is not valid
	* @throws NullPasswordException The password was not found or is not valid
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
	* @throws UserException The username was not found or is not valid
	* @throws NullPasswordException The password was not found or is not valid
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
	* @throws UserException The username was not found or is not valid
	* @throws NullPasswordException The password was not found or is not valid
	* @throws lllegalEmailException The email is invalid
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
	* @throws UserException The username was not found or is not valid
	* @throws NullPasswordException The password was not found or is not valid
	* @throws lllegalEmailException The email is invalid
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
	* @param Email Character string to identify the email
	* @throws lllegalEmailException The email is invalid
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
    * @param Email Character string to identify the email
	* @throws lllegalEmailException The email is invalid
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
	* @throws UserException The username was not found or is not valid
	* @throws NullPasswordException The password was not found or is not valid
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
	* @throws UserException The username was not found or is not valid
	* @throws NullPasswordException The password was not found or is not valid
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
	* @throws UserException The username was not found or is not valid
	* @throws NullPasswordException The password was not found or is not valid
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
	* @throws UserException The username was not found or is not valid
	* @throws NullPasswordException The password was not found or is not valid
	*/
	@Test
	public void ValidityTest_exists_notNullObject() throws UserException, NullPasswordException {
		
		String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER;
		String validPassword = "Test4ll+";
		User u = new User(validUsername, validPassword, secretary); 
		assertNotNull(u.exists());
	}
	
	
    /**
     * Stream with all valid email inside
     * @return Return valid emails one at a time
     */
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
    
    /**
     * Stream with all invalid email inside
     * @return Return invalid emails one at a time
     */
    static Stream<String> invalidEmailsProvider() {
        return Stream.of(
                "user#domain.com",                 
                "@yahoo.com"                
                ); 
    }
}
