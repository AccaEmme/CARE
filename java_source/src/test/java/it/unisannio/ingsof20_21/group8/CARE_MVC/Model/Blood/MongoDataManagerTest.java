package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.MongoDataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Exceptions.UserException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Exceptions.NullPasswordException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.experimental.theories.internal.ParameterizedAssertionError;
import org.junit.jupiter.api.Test;

public class MongoDataManagerTest {
	MongoDataManager mdm;
	
	@Test
	public void ValidityTest_Constructor() {
		try {
			mdm = new MongoDataManager();
			mdm.createDB(); // useless Method for MongoDB
			assertNotNull(mdm);
		} catch(Exception e) {
			assertTrue(false);
		}
	}
	
	
	
	@Test
	public void ValidityTest_AddUser() throws UserException, NullPasswordException {
		try {
			mdm = new MongoDataManager();
			User u = new User("Bob.Alice", "Test4ll.+");
			mdm.addUser(u);
		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void InvalidityTest_AddUser_WrongPassPattern() throws UserException, NullPasswordException {
		assertThrows(Exception.class, () -> {
			mdm = new MongoDataManager();
			User u = new User("Bob.Alice", "testall");
			mdm.addUser(u);
		});	
	}
	
	
	
	public void ValidityTest_DeleteUser() {
		try {
			mdm = new MongoDataManager();
			User u = new User("Bob.Alice", "Test4ll.+");
			mdm.deleteUser(u);
		} catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}		
	}
	
	@Test
	public void InvalidityTest_DeleteUser_UserNotFound() throws UserException, NullPasswordException {
		assertThrows(Exception.class, () -> {
			mdm = new MongoDataManager();
			User u = new User("BobAl", "Test4ll.+");
			mdm.deleteUser(u);
		});	
	}
	
	
	@Test
	public void ValidityTest_ValidateLogin() {
		/* @TODO *** ... */
		assertTrue(false);
	}
	
	@Test
	public void InvalidityTest_ValidateLogin() {
		assertThrows(Exception.class, () -> {
			mdm = new MongoDataManager();
			User u = new User("BobAl", "Test4ll.+");
			mdm.deleteUser(u);
		});	
	}
	
	
	
	public void ValidityTest_getUser() throws UserException, NullPasswordException {
		String username		 = "Bob.Alice";
		String validPassword = "Test4ll.+";
		mdm = new MongoDataManager();
		User u = new User( username, validPassword );
		mdm.addUser(u);
		assertNotNull( mdm.getUser(username) );
	}
	
	@Test
	public void InvalidityTest_getUser_UserNotFound() throws UserException, NullPasswordException {
		//assertThrows(Exception.class, () -> {
		
			String username		 = "Bob.Alice";
			String validPassword = "Test4ll.+";
			mdm = new MongoDataManager();
			//User u = new User( username, validPassword );
			//mdm.addUser(u);
			assertNotNull( mdm.getUser(username) );
		//});	
	}
	
	
	
	
	@Test
	public void getBloodBagExpiring() {
		/* @TODO *** ... */
		assertTrue(false);
	}
	
	
	
	
	@Test
	public void report() {
		/* @TODO *** ... */
		assertTrue(false);
	}
	
	
	
	@Test
	public void addBloodBag() {
		/* @TODO *** ... */
		assertTrue(false);
	}
	
	
	
	
}
