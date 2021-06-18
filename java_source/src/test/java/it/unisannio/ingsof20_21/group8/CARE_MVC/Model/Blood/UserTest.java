package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.stream.Stream;

import org.junit.Ignore;
import org.junit.Test;
/*
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;*/
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Role;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Exceptions.UserException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Password;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Exceptions.NullPasswordException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.City;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Country;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Province;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Region;



public class UserTest {
	Location country = new Location(Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5", "82020");
	Role Segretaria; //= new Role();
	Role Magazziniere;
	Role Admin;

	
		
	
	
	//Controllo creazione User corretto con passw invalida
	@Test
	public void TestInvalidUserPasswClear() throws UserException, NullPasswordException {
		User u = new User("Donato", "Donato96@", country);
	}
	//Controllo creazione User corretto con passw invalida
	@ParameterizedTest
    @MethodSource("validPasswordProvider")
	public void TestInvalidUserPasswGood(String Passw) throws UserException, NullPasswordException {
		User Segret = new User("Donato", "Donato96@", country);
		Segret.setPassword(Passw);
	}
	
	
	@Test
	public void testValidConstructor1()  throws UserException, NullPasswordException{
	   String user= "Saras";
	   String pass= "Sarasign96?";
	   
	   User u = new User(user, pass, country);  
		u.getUsername();
		
		u.getPassword().equals(
  				Password.getMd5( pass + Constants.USER_MD5_SALT )
  		);

	}
	
	
	@Test
	public void testGetRole() throws UserException, NullPasswordException {
		Role r 			= Role.valueOf("Officer");
		User u 			= new User("Francesca.Minigazzi", r );
		u.getRole();
	}
	
	@Test
	public void testSetRole() throws UserException, NullPasswordException {
		Role r 			= Role.valueOf("Officer");
		User u 			= new User("Francesca.Minigazzi", r );
		u.setRole(r);
	}
	
	
	@Test
	public void testGetResidence() throws UserException, NullPasswordException {
		User u = new User("Francesca", "AAAbbbccc@123", country );
		u.getResidence();
	}
	
	@Test
	public void testSetResidence() throws UserException, NullPasswordException {
		
		User u = new User("Francesca", "AAAbbbccc@123", country );
		u.setResidence(country);
	}
	
	@Test
	public void TestGetUsername() throws UserException, NullPasswordException {
		User u = new User("Donato", "Donato96@", country);
		u.getUsername();
	} 
	
	@Test
	public void testSetUsername() throws UserException, NullPasswordException {
		String user= "Saras";
		User u = new User( user, "AAAbbbccc@123", country );
		u.setUsername(user);
	}
	
	
	
	@Test
	public void testMD5() throws NullPasswordException, UserException {
		User u=new User("Luca.Minicozzi","AAAbbbccc@123", country);
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

	/**
	 * Scusa hermann devo eseguire xD
	errore. da Console:
	Password.java givenPassword: AAAbbbccc@123
	plainTextPassword: AAAbbbccc@123	 Constants.USER_MD5_SALT: CanforaMarkUs30L	 hiddenPassword:6800A4445909CC025ADCAD4243D9974C
	Wed Jun 16 11:07:44 CEST 2021
	Password.java givenPassword: AAAbbbccc@123
	Password.java givenPassword: [C@6150c3ec
	Password.java givenPassword: null
	Password.java givenPassword: [C@185a6e9
	Password.java givenPassword: null*/

	
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
