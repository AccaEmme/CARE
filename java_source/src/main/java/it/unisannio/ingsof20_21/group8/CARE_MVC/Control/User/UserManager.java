package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.MongoDataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.DataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Role;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;

public class UserManager implements LoginInterface, AdminInterface{

	public UserManager(User user) {
		
		this.user = user;
		this.dataManager = new MongoDataManager(); 
	}
	
	
	
	public static UserManager checkLogin(String userName, String password) {
		
		User user = dataManager.selectUser(userName, password);
		if(user != null) {
			
			return new UserManager(user);
		}
	}
	
	public User getUser() {return user;}
	
	public User getUser() {return user;}
	
	public void addUser(User user) {
		
		
	}
	
	public void editUser(User user) {
		
		dataManager.updateUser(user);
		
	}
	
	public void dropUser(User user) {
		
		
	}
	
	public void setRole(User user, Role role) {
		
		this.editUser(dataManager.select(user).setRole(role));
	}
	
	public void setRoutingTable() {
		
	}
	
	protected void setDataManager(DataManager dataManager) { this.dataManager = dataManager;}
	
	public Iterator<User> getUsers(){
		
		users = dataManager.selectUsers();
		return users.iterator();
	}
	
	public String toString() {
		
		return users.toString();
	}
	
	
	private DataManager dataManager;
	private User user;
	private TreeSet<User> users;
}
