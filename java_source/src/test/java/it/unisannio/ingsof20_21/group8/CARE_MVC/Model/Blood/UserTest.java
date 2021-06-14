package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Role;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Password;


public class UserTest {
	Role segretaria = new Role.;
	
	
	@Test
	public void test_set() {
		
	}
	
	@Test
	public void testMD5() {
		User u=new User("Luca","AAAbbbccc@123");
		String password="AAAbbbccc@123";
		System.out.println(u.getPasswordLastUpdate());
		assertTrue( 
				   		u.getPassword().equals(
				   				Password.getMd5( password+Constants.USER_MD5_SALT ).toUpperCase()
				   		)
				   );
	}
	
	
	@Test
	public void testSetPasswordValid() {
		Role r = Role.valueOf("Officer");
		User u = new User("Luca", r );
		String password="AAAbbbccc@123";
		u.setPassword(password);
		assertTrue(
				u.getPassword().equals(
						Password.getMd5( password+Constants.USER_MD5_SALT ).toUpperCase()
						)
				);
	}
	
	@Test
	public void testSetPasswordWrong() {
		Role r = Role.valueOf("Officer");
		User u = new User("Luca", r );
		String password="AAAbbbccc@123";
		u.setPassword(password);
		assertTrue(
				u.getPassword().equals(
						Password.getMd5( password+Constants.USER_MD5_SALT ).toUpperCase()
						)
				);
	}
	
	
	// PASSWORD TESTS.
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
    @Test(expected = Exception.class)
    @Parameters(value = { "invalidInput1", "invalidInput2" })
    public void shouldThrowOnInvalidInput(String input) {
    	Password.validatePassword(input);
    	assertThat(1+1, 2);
    }
    */
    static Stream<String> validPasswordProvider() {
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
    
    static Stream<String> invalidPasswordProvider() {
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
