package it.unisannio.CARE.spring;

/*
 *  JUnit test for Password class.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Date;
import java.util.stream.Stream;

import org.junit.Ignore;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import it.unisannio.CARE.model.exceptions.IllegalPatternException;
import it.unisannio.CARE.model.util.Password;


import static org.junit.Assert.assertThrows;

import java.util.stream.Stream;

import junitparams.converters.Param;
import junitparams.custom.combined.CombinedParameters;
import junitparams.mappers.CsvWithHeaderMapper;
import junitparams.naming.TestCaseName;

public class PasswordTest {


	/**
	* Junit build to verify password constructor method with valid hiddenPassword values
	*/
	@Test 
	public void ValidityTest_Constructor1_notNullObject() {
		String validHiddenPassword = "9964759D115DA0F4946D8C4AABF0A200";
		Password p = new Password(validHiddenPassword);
		assertNotNull(p);
	}
	
	/**
	* Junit creation to verify the passwd constructor method with invalid hiddenPassword values
	*/
	@Test 
	public void InvalidityTest_Constructor1_nullParam() {
		String invalidHiddenPassword = null; 

		assertThrows(Exception.class, ()->{
			Password p = new Password(invalidHiddenPassword);
		});
	}
	
	/**
	* Junit creation to verify the passwd constructor method with invalid hiddenPassword values
	*/
	@Test
	public void InvalidityTest2_Constructor1_nullParam() {
		String invalidHiddenPassword = ""; 

		assertThrows(Exception.class, ()->{
			Password p = new Password(invalidHiddenPassword);
		});
	}

	/**
	* Junit creation to verify the password GET method
	*/
	@Test 
	public void ValidityTest_getHiddenPassword_notNullObject() {
		String validHiddenPassword = "9964759D115DA0F4946D8C4AABF0A200";
		Password p = new Password(validHiddenPassword);
		assertNotNull( p.getHiddenPassword() );
	}
	
	
	/**
	* Junit creation for the verification of the generatePassword method
	*/
	@Test
	public void ValidityTest_generatePassword_checkLength() {
		int		passLength		 = 15;
		String generatedPassword = Password.generatePassword(passLength);
		assertTrue( generatedPassword.length()==passLength );
	}
	

	/**
	* Junit creation for the verification of the insertion of a password combo through the static streams
	* @param password the password to be tested
	*/
	@ParameterizedTest(name = "#{index} - Run test with invalid password complexity pattern = {0}")
    @MethodSource("invalidPasswordsProvider")
	public void InvalidityTest_validatePassword_withWrongPlaintextpassword(String password) {

		assertThrows(Exception.class, ()->{
			Password.validatePlaintextPasswordPattern(password);
		});
	}
	
	
	/**
	* Junit creation for verifying the insertion of an incorrect password combo through the static streams
	* @param password the password to be tested
	*/
    @ParameterizedTest(name = "#{index} - Run test with valid password complexity pattern = {0}")
    @MethodSource("validPasswordsProvider")
    public void test_password_regex_valid(String password) {
        Assert.assertTrue( Password.validatePlaintextPasswordPattern(password) );
    }	
	
    /**
    * Test of the validatePlaintextPasswordPattern method to validate the passw pattern
    * @exception IllegalPatternException
    */
    @Test(expected = IllegalPatternException.class)
    public void InvalidityTest_validatePlaintextPasswordPattern() {
		String givenPassw = null;
		Password.validatePlaintextPasswordPattern(givenPassw);
	}
    

    static Stream<String> validPasswordsProvider() {
        return Stream.of(
                "AAAbbbccc@123",
                "Hello world$123",
                "A!@#&()–a1",               // valid: punctuation part 1
                "A[{}]:;',?/*a1",           // valid: punctuation part 2
                "A~$^+=<>a1",               // valid: symbols
                "0123456789$abcdefgAB",     // valid: 20 chars
                "123Aa$Aa"                  // valid: 8 chars
        );
    }
    
    static Stream<String> invalidPasswordsProvider() {
        return Stream.of(
                "12345678",                 // invalid: only digit
                "abcdefgh",                 // invalid: only lowercase
                "ABCDEFGH",                 // invalid: only uppercase
                "abc123$$$",                // invalid: at least one uppercase
                "ABC123$$$",                // invalid: at least one lowercase
                "ABC$$$$$$",                // invalid: at least one digit
                "java REGEX 123",           // invalid: at least one special character
                "java REGEX 123 %",         // invalid: % is not in the special character group []
                "________",                 // invalid
                "--------",                 // invalid
                "A12i@+",     				// invalid: less 8 chars (6 chars)
                "A123456789bcdefghi@++",     // invalid: over 20 chars (21 chars)
                " ",                        // empty
                ""                       	// empty
                ); 
    }
}
