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


public class UserTest {
	//Location country = new Location(Country.Italy, Region.Campania, Province.Avellino ,City.Avellino,"via 25 Aprile","5", "82020");
	

	
	// =====================Constructor1 tests: User(String username, String plainTextPassword, Role role)
	@Test // Test Constructor1 works propertly with valid user and valid password pattern
	public void ValidityTest_Constructor1_notNullObject() throws UserException, NullPasswordException {
		String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER; 
		String validPassword = "Test4ll+";
		User u = new User(validUsername, validPassword, secretary);
		assertNotNull(u);
	}
		
	@Test // Test Constructor2 works propertly with valid user and invalid password pattern
	public void InvalidityTest_Constructor1_validUserInvalidPass() throws UserException, NullPasswordException {
		String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER;
		String invalidPassword = "testall";
		
		assertThrows(Exception.class, () -> {
			User u = new User(validUsername, invalidPassword, secretary);
		 }
		);
	}
	
	@Test(expected = IllegalPatternException.class) // Test Constructor2 works propertly with valid user and invalid password pattern
	public void InvalidityTest_Constructor1_validUserInvalidPass2() throws UserException, NullPasswordException {
		String validUsername = "Hermann";
		Role secretary 		 = Role.ROLE_OFFICER;
		String invalidPassword = "";
		
		User u = new User(validUsername, invalidPassword, secretary);
	}
	
	// =====================Constructor2 tests: User((String username, Password hiddenPassword)
	@Test // Test Constructor2 works propertly with valid user and valid password pattern
	public void ValidityTest_Constructor2_notNullObject() throws UserException, NullPasswordException, NullPasswordException, UserException {
		String validUsername = "Hermann";
		String validPassword = "Test4ll+";
		Password pw = new Password(validPassword);
		User u = new User(validUsername, pw);
		assertNotNull(u);
	}
	
	@Test(expected = IllegalPatternException.class) // Test Constructor1 throws exception with invalid user and valid password pattern
	public void InvalidityTest_Constructor2_validUserInvalidPass()  throws UserException, NullPasswordException {
		String validUsername = "Hermann";
		String validPassword = "";
		Password pw = new Password(validPassword);
		User u = new User(validUsername, pw);
		assertNotNull(u);
	}


	@Test // Test Constructor1 throws exception with valid user and invalid password pattern
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
	 * Creazione Junit per la verica del metodo GET dello Username
	 * @throws NullPasswordException 
	 * @throws UserException 
	 * @result ritorna lo UserName dell'utente
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
	 * Creazione Junit per la verica del metodo SET dello Username
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
	 * Creazione Junit per la verica del metodo SET dello Username inserendo uno nome invalido
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
	 * Creazione Junit per la verica del metodo GET per ottenere la passw dell'utente
	 * @throws NullPasswordException 
	 * @throws UserException 
	 * @result ritorna la passw dell'utente
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
	 * Creazione Junit per la verica del metodo SET della passw
	 * @throws NullPasswordException 
	 * @throws UserException 
	 * @result ritorna una eccezione nel caso la password sia non valida per il pattern 
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
	 * Creazione Junit per la verica del metodo SET della passw
	 * @throws NullPasswordException 
	 * @throws UserException 
	 * @result ritorna una eccezione poichè il pattern della nuova password è errato
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
	 * Creazione Junit per la verica del metodo GET per ottenere la data dell'ultima passw inserita
	 * @throws NullPasswordException 
	 * @throws UserException 
	 * @result ritorna la data dell'ultima passw inserita
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
	 * Creazione Junit per la verica del metodo SET della Email
	 * @throws NullPasswordException 
	 * @throws UserException 
	 * @throws lllegalEmailException 
	 * @result Il risultato è positivo perchè la passw è giusta
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
	 * Creazione Junit per la verica del metodo GET per ottenere l'email dell'utente
	 * @throws NullPasswordException 
	 * @throws UserException 
	 * @throws lllegalEmailException 
	 * @result ritorna l'email dell'utente
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
	 * Creazione Junit per la verica delle passw corrette con l'uso di uno stream
	 * @throws NullPasswordException 
	 * @throws UserException 
	 * @throws lllegalEmailException 
	 * @result I risulati sono tutti positivi
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
	 * Creazione Junit per la verica delle passw non corrette con l'uso di uno stream
	 * @throws NullPasswordException 
	 * @throws UserException 
	 * @throws lllegalEmailException 
	 * @result I risulati sono tutti negativi
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
	 * Creazione Junit per la verica del metodo GET per ottenere il ruolo dell'utente
	 * @throws NullPasswordException 
	 * @throws UserException 
	 * @result ritorna il ruolo dell'utente
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
	 * Creazione Junit per la verica del metodo SET del ruolo
	 * @throws NullPasswordException 
	 * @throws UserException 
	 * @result Il ruolo viene correttamente modificato
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
	 * Creazione Junit per la verica del metodo GET USERDAO 
	 * @throws NullPasswordException 
	 * @throws UserException 
	 * @result ritorna ud se il metodo è giusto
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
	 * Creazione Junit per la verica del metodo exist della classe USER
	 * @throws NullPasswordException 
	 * @throws UserException 
	 * @result ritorna true se l'oggetto USER esiste
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
