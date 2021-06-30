package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers;

import java.text.ParseException;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Exceptions.NullPasswordException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Exceptions.UserException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces.AdministratorInterface;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Role;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Logger;

public class UserManager extends SystemManager {

	/* la classe astratta SystemManager, chiama:
	 * - ReportManager
	 * - MyNodeManager
	 * - UserManager
	 */
	
	private User 		currentUser;
	private DataManager dataManager;

	
	public UserManager(User currentUser, DataManager dataManager) {
		super(currentUser, dataManager);
		this.currentUser = currentUser;
		this.dataManager = dataManager;
	}
	public UserManager(User user) throws UserException {
		super(user,null);

		this.currentUser = user;



		System.out.println(user.getRole().toString());
		if (user.getRole() == Role.Administrator){
			AdminInterface admin = new MongoDataManager();
			this.dataManager = admin;
		}else if (user.getRole() == Role.StoreManager){
			WareHouseWorkerInterface worker = new MongoDataManager();
			this.dataManager = worker;
		}else {
			throw new UserException("The user does not have a role!");
		}

	}

	/*
	public void addUser(User u) {
		UserManager um = new UserManager(u, dataManager);
		um.addUser(u);
		dataManager.writeLog(
						new Logger(currentUser, "SystemManager", "addUser:"+u.toString(), "ok")
						// currentUser(mario)+"ha aggiunto+"u(Francesco)
				);
	}
	
	public void editUser(User u) {
		UserManager um = new UserManager(u, dataManager);
		um.addUser(u);
		dataManager.writeLog(
						new Logger(currentUser, "SystemManager", "editUser:"+u.toString(), "ok")
						// currentUser(mario)+"ha aggiunto+"u(Francesco)
				);
	}
	
	public void dropUser(User u) {
		UserManager um = new UserManager(u, dataManager);
		um.addUser(u);
		dataManager.writeLog(
						new Logger(currentUser, "SystemManager", "dropUser:"+u.toString(), "ok")
						// currentUser(mario)+"ha aggiunto+"u(Francesco)
				);
	}
	
	public void setRole(User u, Role r) {
		UserManager um = new UserManager(u, dataManager);
		//um.setRole(r);
		dataManager.writeLog(
						new Logger(currentUser, "SystemManager", "setRole:"+u.toString(), "ok")
						// currentUser(mario)+"ha aggiunto+"u(Francesco)
				);
	}*/


	public static UserManager checkLogin(String userName, String password, DataManager dataManager) throws UserException, NullPasswordException, NullPasswordException, UserException {
		User user = dataManager.validateLogin(userName, password);
		if(user != null) {
			return new UserManager(user);
		}
		return null;
	}
	@Override
	public void report() throws ParseException {
		// TODO Auto-generated method stub
		
	}
	public User getCurrentUser(){
		return this.currentUser;
	}
	@Override
	public void dropUserCollection() {
		// TODO Auto-generated method stub
		
	}
	
}
