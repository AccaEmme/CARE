package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.Testers.MongoManagerTesters;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.MongoDataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Role;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Exceptions.UserException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Exceptions.NullPasswordException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;

public class MongoDatamanagerAddUserTester {
    public static void main(String[] args) throws NullPasswordException, UserException {
        MongoDataManager manager = new MongoDataManager();
        //Country country, 	Region region, 			Province province,
        //    				City city, 			String street,			String streetNumber, String zipcode
        Location location = new Location(Location.Country.Italy, Location.Region.Campania, Location.Province.Benevento, Location.City.Benevento,"Via apice","n10","82018");

        System.out.println(location.getDocument());
        User user = new User("antonello","Patente1+");


        user.setRole(Role.Administrator);
        user.setResidence(location);

        System.out.println(user.getDocument());
        manager.addUser(user);
    }
}
