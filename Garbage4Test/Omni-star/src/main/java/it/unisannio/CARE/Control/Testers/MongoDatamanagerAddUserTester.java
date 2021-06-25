package it.unisannio.CARE.Control.Testers;

import it.unisannio.CARE.Control.u.Managers.MongoDataManager;
import it.unisannio.CARE.Exceptions.NullPasswordException;
import it.unisannio.CARE.Exceptions.UserException;
import it.unisannio.CARE.Model.Classes.User;
import it.unisannio.CARE.Model.Util.Location;
import it.unisannio.CARE.Model.Util.Role;

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
