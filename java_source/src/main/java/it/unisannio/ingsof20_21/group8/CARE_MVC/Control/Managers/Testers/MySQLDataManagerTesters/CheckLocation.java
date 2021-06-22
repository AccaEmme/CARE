package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.Testers.MySQLDataManagerTesters;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.DataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.MySqlDataManager;

public class CheckLocation {
    public static void main(String[] args) {
        //DataManager manager = new MySqlDataManager();
        MySqlDataManager mySqlDataManager = new MySqlDataManager();

        for (String str: mySqlDataManager.getComuniFromProvincia("Napoli")){
            System.out.println(str);
        }
    }
}
