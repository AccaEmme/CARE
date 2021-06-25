package it.unisannio.CARE.Control.u.Managers.Testers.MySQLDataManagerTesters;

import it.unisannio.CARE.Control.Classes.DataManager;
import it.unisannio.CARE.Control.u.Managers.MySqlDataManager;
import it.unisannio.CARE.Model.Classes.BloodBag;
import it.unisannio.CARE.Model.Node.Node;
import it.unisannio.CARE.Model.Util.BloodGroup;
import it.unisannio.CARE.Model.Util.Location;

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
