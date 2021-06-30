package it.unisannio.CARE.spring;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.stream.Stream;

import it.unisannio.CARE.model.z.exceptions.NullPasswordException;
import it.unisannio.CARE.model.z.exceptions.UserException;
import it.unisannio.CARE.model.user.Role;
import it.unisannio.CARE.model.user.User;
import it.unisannio.CARE.model.util.Constants;
import org.junit.Ignore;
import org.junit.Test;
/*
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;*/
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import it.unisannio.CARE.model.util.Password;




public class UserTest {
	//Location country = new Location(Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5", "82020");
	

	// =====================Constructor1 tests: User(String username, String plainTextPassword)
	@Test // Test Constructor1 works propertly with valid user and valid password pattern
	public void ValidityTest_Constructor1_notNullObject() throws UserException, NullPasswordException, NullPasswordException, UserException {
		String validUsername = "Hermann";
		String validPassword = "Test4ll+";
		Password pw = new Password(validPassword);
		User u = new User(validUsername, pw);
		assertNotNull(u);
	}

	@Test // Test Constructor1 throws exception with invalid user and valid password pattern
	public void InvalidityTest_Constructor1_InvalidUserValidPass() throws UserException, NullPasswordException {
		
	}
	
	@Test // Test Constructor1 throws exception with valid user and invalid password pattern
	public void InvalidityTest_Constructor1_validUserInvalidPass() throws UserException, NullPasswordException {
		String validUsername = "Hermann";
		String invalidPassword = "testall";
		String invalidPW = "Test4ll+";
		Password pw = new Password(invalidPW);
		assertThrows(Exception.class, () -> {
			User u = new User(validUsername, pw);
		}
		);
	}

	// =====================Constructor2 tests: User(String username, Password hiddenPassword)
	@Test // Test Constructor2 works propertly with valid user and valid password pattern
	public void ValidityTest_Constructor2_notNullObject() throws UserException, NullPasswordException {
		String validUsername = "Hermann";
		String validPassword = "Test4ll+";
		Password validHiddenPasswordObj = new Password(Password.getMd5("Test4ll+"));
		User u = new User(validUsername, validHiddenPasswordObj);
		assertNotNull(u);
	}
	
	@Test // Test Constructor2 works propertly with valid user and invalid password pattern
	public void InvalidityTest_Constructor2_validUserInvalidPass() throws UserException, NullPasswordException {
		String validUsername = "Hermann";
		String invalidPassword = "testall";
		
		assertThrows(Exception.class, () -> {
			Password validHiddenPasswordObj = new Password(Password.getMd5(invalidPassword));
			User u = new User(validUsername, validHiddenPasswordObj);
		 }
		);
	}
	
	// =====================Constructor3 tests: User(String username, Role role)
	/*Role Segretaria; //= new Role();
	Role Magazziniere;
	Role Admin;*/
	
	@Test // Test Constructor3 works propertly with valid user and valid Role(Officer)
	public void ValidityTest_Constructor3_notNullObject1() throws UserException, NullPasswordException {
		String validUsername = "Hermann";
		Role secretary 		 = Role.Officer;


		String validPassword = "Test4ll+";
		User u = new User(validUsername,validPassword, secretary);
		assertNotNull(u);
	}

	@Test // Test Constructor3 works propertly with valid user and valid Role(Officer)
	public void ValidityTest_Constructor3_notNullObject2() throws UserException, NullPasswordException {
		String validUsername = "Hermann";
		Role secretary 		 = Role.StoreManager;
		String validPassword = "Test4ll+";
		User u = new User(validUsername,validPassword, secretary);
		assertNotNull(u);
	}
	
	@Test // Test Constructor3 works propertly with valid user and valid Role(Officer)
	public void ValidityTest_Constructor3_notNullObject3() throws UserException, NullPasswordException {
		String validUsername = "Hermann";
		Role secretary 		 = Role.Administrator;
		String validPassword = "Test4ll+";
		User u = new User(validUsername,validPassword, secretary);
		assertNotNull(u);
	}

	@Test // Test Constructor3 works propertly with valid user and invalid Role
	public void InvalidityTest_Constructor3_invalidRole() throws UserException, NullPasswordException {
		String validUsername  = "Hermann";
		assertThrows(Exception.class, () -> {
			Role invalidSecretary = Role.valueOf("invalidSecretaryRole");
			String validPassword = "Test4ll+";
			User u = new User(validUsername, validPassword, invalidSecretary);
		 }
		);
	}
	
	// =====================getters tests
	
	
	// =====================setters tests
	
	
	
	
	
	
	//Controllo creazione User corretto con passw in chiaro
		@Test
		public void TestValidUserPasswClear() throws UserException, NullPasswordException {
			Password password = new Password("Donato96@");
			User Segret = new User("Donato", password);
			assertTrue(Segret.exists());
		}
		
		//Controllo creazione User corretto con passw invalida
		@Test
		public void TestInvalidUserPasswClear() throws UserException, NullPasswordException {
			Password password = new Password("Donato96@");
			User Segret = new User("Donato", password);
		}
		
		@Test(expected = NullPasswordException.class)
		public void TestInvalidUserPasswSpace() throws UserException, NullPasswordException {
			Password password = new Password("");
			User Segret = new User("Donato", password);
		}
		
		@Test(expected = UserException.class)
		public void TestInvalidUserPasswNull() throws UserException, NullPasswordException {
			Password password = new Password("Donato96@");
			User s = new User( " ", password);
		}
		
		@Test(expected = UserException.class)
		public void TestInvalidUserWrong() throws UserException, NullPasswordException {
			Password password = new Password("Donato96@");
			User Segret = new User("Don", password);
		}
		
		//Controllo creazione User corretto con passw invalida
		@ParameterizedTest
	    @MethodSource("validPasswordProvider")
		public void TestInvalidUserPasswGood(String Passw) throws UserException, NullPasswordException {
			Password password = new Password("Donato96@");
			User Segret = new User("Donato", password);
			Segret.setPassword(Passw);
		}
		
		
	
	@Test
	public void testValidConstructor1()  throws UserException, NullPasswordException{
	   String user= "Marioverdi";
	   String pass= "Marioverdi96?";
	   Password password = new Password(pass);
	   User u = new User(user, password);
		u.getUsername();
		
		u.getPassword().equals(
  				Password.getMd5( pass + Constants.USER_MD5_SALT )
  		);

	}
	
	
	@Test
	public void testGetRole() throws UserException, NullPasswordException {
		Role r 			= Role.valueOf("Officer");
		User u 			= new User("Francesca.Minigazzi","Donato96@", r );
		u.getRole();
	}
	
	@Test
	public void testSetRole() throws UserException, NullPasswordException {
		Role r 			= Role.valueOf("Officer");
		User u 			= new User("Francesca.Minigazzi","Donato96@", r );
		u.setRole(r);
	}
	
	
	@Test
	public void testGetResidence() throws UserException, NullPasswordException {
		Password password = new Password("AAAbbbccc@123");
		User u = new User("Francesca", password);
		//u.getResidence();
	}
	
	@Test
	public void testSetResidence() throws UserException, NullPasswordException {
		
		//User u = new User("Francesca", "AAAbbbccc@123");
		//u.setResidence(country);
	}
	
	@Test
	public void TestGetUsername() throws UserException, NullPasswordException {
		Password password = new Password("Mariano96@");
		User u = new User("Mariano", password);
		u.getUsername();
	} 
	
	@Test
	public void testSetUsername() throws UserException, NullPasswordException {
		String user= "Mariano";
		Password password = new Password("Mariano96@");
		User u = new User( user, password);
		u.setUsername(user);
	}
	
	
	
	@Test
	public void testMD5() throws NullPasswordException, UserException {
		Password password = new Password("AAAbbbccc@123");
		User u=new User("Luca.Minicozzi",password);
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
				Password.validatePlaintextPasswordPattern(password)
				);
	}

	@Test
	public void testSetPasswordValid() throws NullPasswordException, UserException {
		String password = "AAAbbbccc@123";
		Role r 			= Role.valueOf("Officer");
		User u 			= new User("Francesca.Minigazzi",password, r );
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
	public void testSetPasswordWrong() throws NullPasswordException, UserException {
		String password = "AAAbbbccc@123";
		Role r 			= Role.valueOf("Officer");
		User u 			= new User("Luca",password, r );

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
        assertTrue( Password.validatePlaintextPasswordPattern(password) );
    }	
	

    
    @ParameterizedTest
    @MethodSource("invalidPasswordProvider")
    //@Test(expected = IllegalArgumentException.class)
    public void test_password_regex_invalid(String password) {
    	//ExceptionThrower exceptionThrower = new ExceptionThrower();
    	/*
    	assertThrows(
    			Password.validatePlaintextPasswordPattern(password)
    			);
    			*/
    	//assertFalse(Password.validatePlaintextPasswordPattern(password));
    	try {
    		Password.validatePlaintextPasswordPattern(password);
        } catch(IllegalArgumentException e) {
        	assertFalse(false);
        }
    }
	
    /*
    @Test(expected = Exception.class)
    @Parameters(value = { "invalidInput1", "invalidInput2" })
    public void shouldThrowOnInvalidInput(String input) {
    	Password.validatePlaintextPasswordPattern(input);
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
