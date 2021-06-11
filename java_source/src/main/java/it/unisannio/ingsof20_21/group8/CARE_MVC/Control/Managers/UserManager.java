package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.User.LoginInterface;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces.AdministratorInterface;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Role;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;

public class UserManager extends SystemManager {

	public UserManager(User currentUser, DataManager dataManager) {
		super(currentUser, dataManager);
	}
	
	public static UserManager checkLogin(String userName, String password) {
		return null;
		//User user = dataManager.selectUser(userName, password);
		//if(user != null) {
		//	return new UserManager(user);
		//}
		/* TODO: *** */
	}
	
	public static UserManager recoverPassword(String username) {
		return null;
		/* TODO: *** */
	}
	
	
	public User getUser() {return user;}
	
	public void addUser(User user) {
		// TODO: DB insert. method on dataManager
		// dataManager.addUser();
		//return false;
	}
	
	public void editUser(User user) {
		//dataManager.updateUser(user);
		
	}
	
	public void dropUser(User user) {
				
	}	
	
	public void setRole(Role role) {
		//this.user
		//this.editUser(dataManager.select(user).setRole(role));
	}
	
	
	protected void setDataManager(DataManager dataManager) { this.dataManager = dataManager;}
	
	public Iterator<User> getUsers(){
		//users = dataManager.selectUsers();
		return users.iterator();
	}
	
	public String toString() {	
		return users.toString();
	}
	
	
	private DataManager dataManager;
	private User user;
	private TreeSet<User> users;
}