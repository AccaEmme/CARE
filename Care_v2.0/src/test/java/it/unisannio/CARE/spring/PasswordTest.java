package it.unisannio.CARE.spring;



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


	// =====================Constructor1 tests: Password(String hiddenPassword)
	@Test // Test Constructor1 works propertly with valid hiddenPassword
	public void ValidityTest_Constructor1_notNullObject() {
		String validHiddenPassword = "9964759D115DA0F4946D8C4AABF0A200";
		Password p = new Password(validHiddenPassword);
		assertNotNull(p);
	}
	
	@Test // Test Constructor1 works propertly with invalid hiddenPassword
	public void InvalidityTest_Constructor1_nullParam() {
		String invalidHiddenPassword = null; 

		assertThrows(Exception.class, ()->{
			Password p = new Password(invalidHiddenPassword);
		});
	}
	
	@Test // Test Constructor1 works propertly with invalid hiddenPassword
	public void InvalidityTest2_Constructor1_nullParam() {
		String invalidHiddenPassword = ""; 

		assertThrows(Exception.class, ()->{
			Password p = new Password(invalidHiddenPassword);
		});
	}

	// =====================getters tests
	@Test // Test getHiddenPassword() works propertly returning not null
	public void ValidityTest_getHiddenPassword_notNullObject() {
		String validHiddenPassword = "9964759D115DA0F4946D8C4AABF0A200";
		Password p = new Password(validHiddenPassword);
		assertNotNull( p.getHiddenPassword() );
	}
	
	/*@Test // Test getMd5 works propertly compairing hidden password with valid plaintext password.
	public void ValidityTest_getMd5_withGoodPlaintextpassword() {
		String	validPlainTextPassword 	= "P4ssword+";
		String 	validHiddenPassword 	= "972CEC7EC77482D87966789BC1E5A038"; // include SALT
		Password p = new Password( validHiddenPassword );
		assertEquals( p.getHiddenPassword(), Password.getBCrypt(validPlainTextPassword) );
	}*/ 
	@Test // Test String generatePassword(int length) 
	public void ValidityTest_generatePassword_checkLength() {
		int		passLength		 = 15;
		String generatedPassword = Password.generatePassword(passLength);
		assertTrue( generatedPassword.length()==passLength );
	}
	
	
	// =====================setters tests
	/*@Test 	// Test setHiddenPassword(String hiddenPass) equals to a valid hidden password
	public void ValidityTest_setHiddenPassword_withWrongPlaintextpassword() {
		String	validPlainTextPassword 	= "P4ssword+";		
		String 	validHiddenPassword 	= "972CEC7EC77482D87966789BC1E5A038"; // include SALT
		Password p = new Password( validHiddenPassword+"_old" );
		p.setHiddenPassword(validHiddenPassword);
		
		assertEquals( p.getHiddenPassword(), Password.getBCrypt(validPlainTextPassword) );
	}*/

	// In realtà questo test dovrebbe validare tutte le password valide
	@Test 	// Test validatePassword(final String givenPassword) equals to a valid hidden password
	public void ValidityTest_validatePassword_withValidPlaintextpassword() {
		String	validPlainTextPassword 	= "P4ssword+";
		assertTrue( Password.validatePlaintextPasswordPattern(validPlainTextPassword) );
	}
	
	@ParameterizedTest(name = "#{index} - Run test with invalid password complexity pattern = {0}")
    @MethodSource("invalidPasswordsProvider")
	public void InvalidityTest_validatePassword_withWrongPlaintextpassword(String password) {

		assertThrows(Exception.class, ()->{
			Password.validatePlaintextPasswordPattern(password);
		});
	}
	
	
	/* @TODO: below */
    @ParameterizedTest(name = "#{index} - Run test with valid password complexity pattern = {0}")
    @MethodSource("validPasswordsProvider")
    public void test_password_regex_valid(String password) {
        Assert.assertTrue( Password.validatePlaintextPasswordPattern(password) );
    }	
	
    /**
	 * Test del metodo validatePlaintextPasswordPattern per la validazione del pattern della passw
	 * @exception validatePlaintextPasswordPattern
	 * @result il risultato è il richiamo dell'eccezione per via del givenpassw null
	 */
    @Test(expected = IllegalPatternException.class)
    public void InvalidityTest_validatePlaintextPasswordPattern() {
		String givenPassw = null;
		Password p = new Password("9964759D115DA0F4946D8C4AABF0A200");
		p.validatePlaintextPasswordPattern(givenPassw);
	}
    
    
    
    /*
    @Test
    @Parameters({"17, false", 
                 "22, true" })
    public void ValidityTest_Constructor1_notNullObject(int age, boolean valid) throws Exception {
     
    	
          assertThat(
    		  new Person(age).isAdult(), is(valid)
    		  );
    }
    */
    
    /*
    @ParameterizedTest(name = "#{index} - Run test with wrong password complexity pattern = {0}")
    @MethodSource("invalidPasswordProvider")
    @Test(expected = IllegalArgumentException.class)
    public void test_password_regex_invalid(String password) {
    	//ExceptionThrower exceptionThrower = new ExceptionThrower();
    	
		assertThrows(Exception.class, () -> {
    			Password.validatePassword(password)
    			}
    			);
    	
    	//assertFalse(Password.validatePassword(password));
    	try {
    		Password.validatePassword(password);
        } catch(IllegalArgumentException e) {
        	Assert.assertFalse(false);
        }
    }
*/
    
    /*
    @Ignore
    @Test(expected = Exception.class)
    @Parameters(value = { "invalidInput1", "invalidInput2" })
    public void shouldThrowOnInvalidInput(String input) {
    	Password.validatePassword(input);
    	assertThat(1+1, 2);
    }
    
    
	@Ignore // Questo test non ha senso di esistere, purtroppo non possiamo validare una password MD5 in quanto hidden non sappiamo se il suo plaintext rispetta il pattern previsto.
	@Test 	// Test getMd5 doesn't work propertly compairing hidden password with not valid plaintext password.
	public void InvalidityTest_getMd5_withWrongPlaintextpassword() {
		String	invalidPlainTextPassword 	= "password";		
		String 	validHiddenPassword 		= "97283B8AA415245169B8C33904026FAE"; // include SALT
		Password p = new Password( validHiddenPassword );
		assertThrows( Exception.class, () -> Password.getBCrypt(invalidPlainTextPassword).equals(p.getHiddenPassword()) );
	}
	
	*/
    
    static Stream<String> validPasswordsProvider() {
    	/*
    	 *Requires
    	 	<properties>
    		 <maven.compiler.source>1.8</maven.compiler.source>
    		 <maven.compiler.target>1.8</maven.compiler.target>
			</properties>
    	 */
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
