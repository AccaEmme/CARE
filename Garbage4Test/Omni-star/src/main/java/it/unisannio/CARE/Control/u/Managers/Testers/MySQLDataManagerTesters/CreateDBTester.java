package it.unisannio.CARE.Control.u.Managers.Testers.MySQLDataManagerTesters;

import it.unisannio.CARE.Control.Classes.DataManager;
import it.unisannio.CARE.Control.u.Managers.MySqlDataManager;

public class CreateDBTester {
    public static void main(String[] args) {
        DataManager dataManager = new MySqlDataManager();
        dataManager.createDB();
    }
}
