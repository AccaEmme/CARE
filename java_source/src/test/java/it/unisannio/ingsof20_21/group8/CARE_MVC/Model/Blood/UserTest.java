package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
/*
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;*/
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Role;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Exceptions.UserException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Password;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Exceptions.NullPasswordException;



public class UserTest {
	Role segretaria; //= new Role();
	
	@Test
	public void testValidConstructor1(/*String username, String plainTextPassword*/) throws UserException, NullPasswordException{
		String username="AccaEmme";
		String pass="Care4+Blood";
		User u = new User(username, pass); 
		u.getUsername();
		
		assertTrue(
				u.getPassword().equals(
   				  Password.getMd5( pass + Constants.USER_MD5_SALT )
				)
		);
	}

	@Test
	public void testValidConstructor2(/*String username, Password hashedPassword*/) throws UserException, NullPasswordException{
		String username="AccaEmme";
		String pass="Care4+Blood";
		String hiddenPass = Password.getMd5(pass + Constants.USER_MD5_SALT);
		User u = new User(username, new Password(hiddenPass));
		
		assertTrue(
				u.getPassword().equals(
   				  Password.getMd5( pass + Constants.USER_MD5_SALT )
				)
		);
	}

	@Test
	public void testValidConstructor3(/*String username, Role role*/){
		String 	username = "AccaEmme";
		Role r 			 = Role.valueOf("Officer");
		User u 			 = new User(username, r);
		String temppass  = u.getTempPass();
		
		assertTrue(
				u.getPassword().equals(
   				  Password.getMd5( temppass + Constants.USER_MD5_SALT )
				)
		);
	}
	
	@Test
	public void testInvalidConstructor1(/*String username, String plainTextPassword*/) throws UserException, NullPasswordException{
		String username="AccaEmme";
		String pass="passwordsemplice";
		
    	try {
    		User u = new User(username, pass); 
    		u.getUsername();
   			u.getPassword().equals(
       			  Password.getMd5( pass + Constants.USER_MD5_SALT )
    		);
        } catch(Exception e) {
        	assertFalse(false);
        }
	}

	@Test
	public void testInvalidConstructor2(/*String username, Password hashedPassword*/){
		String username="AccaEmme";
		String pass="simplepasswordpattern";

    	try {
    		String hiddenPass = Password.getMd5(pass + Constants.USER_MD5_SALT);
    		User u = new User(username, new Password(hiddenPass));
			u.getPassword().equals(  Password.getMd5( pass + Constants.USER_MD5_SALT )  );
        } catch(Exception e) {
        	assertFalse(false);
        }
	}

	@Test
	public void testInvalidConstructor3(/*String username, Role role*/){
		String 	username = "AccaEmme";
		
    	try {
    		Role r 			 = Role.valueOf("Officer");
    		User u 			 = new User(username, r);
    		String temppass  = u.getTempPass();
    		u.getPassword().equals(  Password.getMd5( temppass + Constants.USER_MD5_SALT ) );
        } catch(Exception e) {
        	assertFalse(false);
        }
	}
	
	@Test
	public void test_set() {
		
	}
	
	
	@Test
	public void testMD5() throws NullPasswordException, UserException {
		User u=new User("Luca.Minicozzi","AAAbbbccc@123");
		String password="AAAbbbccc@123";
		assertTrue( 
				   		u.getPassword().equals(
				   				Password.getMd5( password + Constants.USER_MD5_SALT )
				   		)
				   );
	}
	
	@Test
	public void testValidatePassword() {
		String password="AAAbbbccc@123";
		assertTrue(
				Password.validatePassword(password)
				);
	}
	
	@Test
	public void testSetPasswordValid() {
		Role r 			= Role.valueOf("Officer");
		User u 			= new User("Francesca.Minigazzi", r );
		String password = "AAAbbbccc@123";
		u.setPassword(password);
		assertTrue(
				u.getPassword().equals(
						Password.getMd5( password + Constants.USER_MD5_SALT )
						)
				);
	}
	
	@Test
	public void testSetPasswordWrong() {
		Role r 			= Role.valueOf("Officer");
		User u 			= new User("Luca", r );
		String password = "AAAbbbccc@123";
		u.setPassword(password);
		assertTrue(
				u.getPassword().equals(
						Password.getMd5( password+Constants.USER_MD5_SALT ).toUpperCase()
						)
				);
	}
	
	
	// PASSWORD TESTS.
    @ParameterizedTest
    @MethodSource("validPasswordProvider")
    public void test_password_regex_valid(String password) {
        assertTrue( Password.validatePassword(password) );
    }	
	
    
    @ParameterizedTest
    @MethodSource("invalidPasswordProvider")
    //@Test(expected = IllegalArgumentException.class)
    public void test_password_regex_invalid(String password) {
    	//ExceptionThrower exceptionThrower = new ExceptionThrower();
    	
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
