package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.Testers.MySQLDataManagerTesters;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.DataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.MySqlDataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;

public class CheckLocation {
    public static void main(String[] args) {
        //DataManager manager = new MySqlDataManager();
        /**
         * first test
        MySqlDataManager mySqlDataManager = new MySqlDataManager();

        for (String str: mySqlDataManager.getComuniFromProvincia("Napoli")){
            System.out.println(str);
        }*/
        Location l = new Location( Location.Country.Italy, Location.Region.Campania, Location.Province.Avellino , Location.City.Avellino,"via 25 Aprile","5", "82020");

        System.out.println("regione: "+l.getRegion().toString()+"\nprovincia: "+l.getProvince().toString()+"\ncomune: "+l.getCity().toString());

        MySqlDataManager manager = new MySqlDataManager();
        System.out.println(manager.checkLocation(l));
    }
}
