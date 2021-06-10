package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces.AdministratorInterface;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Role;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Logger;

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
		UserManager um = new UserManager(u, dataManager);
		um.setRole(r);
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
	
}
