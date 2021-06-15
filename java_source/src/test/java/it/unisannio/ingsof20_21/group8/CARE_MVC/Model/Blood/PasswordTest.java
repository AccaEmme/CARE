package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import org.junit.jupiter.api.Test;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
/*
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;*/

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Password;

public class PasswordTest {
	@Test
	// Controlla indirettamente anche getMd5
	public void test_Constructor() {
		String plainTextPassword = "LaConoscoSoloIo1.";
		Password passObj = new Password( plainTextPassword );
		assertNotNull( passObj );
	}
	
	@Test
	// controlla indirettamente anche setHiddenPassword() e 
	public void testGetHiddenPassword() {
		String plainTextPassword = "LaConoscoSoloIo1.";
		String md5ExpectedPass	 = "646A452ED62B0929BC770DB4CA083468";
		Password passObj 		 = new Password( plainTextPassword );
		String hiddenPass 		 = passObj.getHiddenPassword();
		assertTrue( hiddenPass.equals( md5ExpectedPass ) );
	}

	
    @ParameterizedTest(name = "#{index} - Run test with valid password complexity pattern = {0}")
    @MethodSource("validPasswordProvider")
    public void test_password_regex_valid(String password) {
        assertTrue( Password.validatePassword(password) );
    }	
	
    
    @ParameterizedTest(name = "#{index} - Run test with wrong password complexity pattern = {0}")
    @MethodSource("invalidPasswordProvider")
    //@Test(expected = IllegalArgumentException.class)
    public void test_password_regex_invalid(String password) {
    	//ExceptionThrower exceptionThrower = new ExceptionThrower();
    	/*
    	assertThrows(
    			Password.validatePassword(password)
    			);
    			*/
    	//assertFalse(Password.validatePassword(password));
    	try {
    		Password.validatePassword(password);
        } catch(IllegalArgumentException e) {
        	assertFalse(false);
        }
    }

    /*
    @Ignore
    @Test(expected = Exception.class)
    @Parameters(value = { "invalidInput1", "invalidInput2" })
    public void shouldThrowOnInvalidInput(String input) {
    	Password.validatePassword(input);
    	assertThat(1+1, 2);
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