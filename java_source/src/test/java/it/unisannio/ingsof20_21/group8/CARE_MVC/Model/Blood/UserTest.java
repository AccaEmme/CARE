package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Role;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Password;

public class UserTest {
	@Test
	public void testMD5() {
	User u=new User("Luca","123456");
	String password="123456";
	System.out.println(u.getPasswordLastUpdate());
	   assertTrue(u.getPassword().equals(Password.getMd5( password+Constants.USER_MD5_SALT ).toUpperCase()));
	}
	
	
	
	@Test
	public void testSetPassword() {
	Role r=Role.valueOf("Officer");
	User u=new User("Luca", r );
	String password="123456";
	u.setPassword(password);
	   assertTrue(u.getPassword().equals(Password.getMd5( password+Constants.USER_MD5_SALT ).toUpperCase()));
	}
	
}
