package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.Testers.MySQLDataManagerTesters;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.DataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.MySqlDataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node.Node;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;

public class AddNodeTester {
    public static void main(String[] args) {
        DataManager manager = new MySqlDataManager();
        Location warehouse = new Location(Location.Country.Italy, Location.Region.Campania, Location.Province.Benevento, Location.City.Benevento,"Via apice","n10","82018");
        Node node = new Node("cod1","Fatebene fratelli",warehouse);
        manager.addNode(node);
    }
}
