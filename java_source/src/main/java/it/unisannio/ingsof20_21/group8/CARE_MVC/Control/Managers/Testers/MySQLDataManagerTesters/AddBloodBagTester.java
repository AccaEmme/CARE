package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.Testers.MySQLDataManagerTesters;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.DataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.MySqlDataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node.Node;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;

import java.text.ParseException;

public class AddBloodBagTester {
    public static void main(String[] args) throws ParseException {
        Location warehouse = new Location(Location.Country.Italy, Location.Region.Campania, Location.Province.Benevento, Location.City.Benevento,"Via apice","n10","82018");
        Node node = new Node("cod1","Fatebene fratelli",warehouse);
        BloodBag bag = new BloodBag(BloodGroup.Apos,"PNMPMZ56C43A396C",node);

        DataManager manager = new MySqlDataManager();
        manager.addBloodBag(bag);
    }
}
