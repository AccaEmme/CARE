package it.unisannio.CARE.Control.u.Managers.Testers.MySQLDataManagerTesters;

import it.unisannio.CARE.Control.Classes.DataManager;
import it.unisannio.CARE.Control.u.Managers.MySqlDataManager;
import it.unisannio.CARE.Model.Util.Location;

public class AddLocationTester {
    public static void main(String[] args) {
        Location location = new Location(Location.Country.Italy, Location.Region.Campania, Location.Province.Benevento, Location.City.Benevento,"Via apice","n10","82018");
        DataManager manager = new MySqlDataManager();
        manager.addLocation(location);
    }
}
