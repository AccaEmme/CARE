package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.Testers.MySQLDataManagerTesters;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.DataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.MySqlDataManager;

public class AddStatesAndRolesTester {
    public static void main(String[] args) {
        DataManager dataManager = new MySqlDataManager();
        dataManager.addStates();

        dataManager.addRoles();
    }
}
