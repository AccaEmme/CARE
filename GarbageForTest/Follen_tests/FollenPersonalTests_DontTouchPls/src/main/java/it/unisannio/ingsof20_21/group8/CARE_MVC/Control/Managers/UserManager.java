package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers;

import java.util.Iterator;
import java.util.TreeSet;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Role;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Exceptions.UserException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Exceptions.NullPasswordException;

public class UserManager {

	private DataManager dataManager;
	private User user;
	private TreeSet<User> users;

	public UserManager(User user) throws UserException {
		
		this.user = user;


		System.out.println(user.getRole().toString());
		if (user.getRole() == Role.Administrator){
			AdminInterface admin = new MongoDataManager();
			this.dataManager = admin;
		}else if (user.getRole() == Role.StoreManager){
			WhareHouseWorkerInterface worker = new MongoDataManager();
			this.dataManager = worker;
		}else {
			throw new UserException("The user does not have a role!");
		}

	}
	
	public static UserManager checkLogin(String userName, String password, DataManager dataManager) throws UserException, NullPasswordException {
		User user = dataManager.validateLogin(userName, password);
		if(user != null) {
			return new UserManager(user);
		}
		return null;
	}
	
	public static UserManager recoverPassword(String username) {
        return null;
	}
	
	public User getUser() {return user;}

	
	public void addUser(User user) {
		
		
	}
	
	public void editUser(User user) {

		
	}
	
	public void dropUser(User user) {
		
		
	}
	
	public void setRole(User user, Role role) {
		
		//this.editUser(dataManager.select(user).setRole(role));
	}
	
	public void setRoutingTable() {
		
	}
	
	protected void setDataManager(DataManager dataManager) { this.dataManager = dataManager;}
	
	public Iterator<User> getUsers(){
		/*
		users = dataManager.selectUsers();
		return users.iterator();*/
        return null;
	}
	
	public String toString() {
		
		return users.toString();
	}
	


}
