package it.unisannio.CARE.Control.u.Managers.Testers.MySQLDataManagerTesters;

import it.unisannio.CARE.Control.Classes.DataManager;
import it.unisannio.CARE.Control.u.Managers.MySqlDataManager;
import it.unisannio.CARE.Model.Node.Node;
import it.unisannio.CARE.Model.Util.Location;

public class AddNodeTester {
    public static void main(String[] args) {
        DataManager manager = new MySqlDataManager();
        Location warehouse = new Location(Location.Country.Italy, Location.Region.Campania, Location.Province.Benevento, Location.City.Benevento,"Via apice","n10","82018");
        Node node = new Node("cod1","Fatebene fratelli",warehouse);
        manager.addNode(node);
    }
}
