package it.unisannio.CARE.Control.u.Managers;

import it.unisannio.CARE.Model.Classes.User;
import it.unisannio.CARE.Model.Classes.Interfaces.AdministratorInterface;
import it.unisannio.CARE.Model.Util.Logger;
import it.unisannio.CARE.Model.Util.Role;

public abstract class SystemManager implements AdministratorInterface {

	/* la classe astratta SystemManager, chiama:
	 * - ReportManager
	 * - MyNodeManager
	 * - UserManager
	 */
	
	private User 		currentUser;
	private DataManager dataManager;
	
	public SystemManager(User currentUser, DataManager dataManager) {
		this.currentUser = currentUser;
		this.dataManager = dataManager;
	}
	
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
		//dataManager.setRole(r);
		dataManager.writeLog(
						new Logger(currentUser, "SystemManager", "setRole:"+u.toString(), "ok")
						// currentUser(mario)+"ha aggiunto+"u(Francesco)
				);
	}
	
	public boolean setRoutingTable() { 
		return false;
	}

	public boolean reportGiacenza() {
		//Report r = new Report();
		return false;
	}

	public User checkLogin(String userName, String plainTextPassword) {
		return null;
		// TODO Auto-generated method stub
		
	}
	
}
