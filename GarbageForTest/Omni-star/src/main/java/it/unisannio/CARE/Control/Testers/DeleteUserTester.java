package it.unisannio.CARE.Control.Testers;

import it.unisannio.CARE.Control.Classes.AdminInterface;
import it.unisannio.CARE.Control.u.Managers.MongoDataManager;
import it.unisannio.CARE.Exceptions.NullPasswordException;
import it.unisannio.CARE.Exceptions.UserException;
import it.unisannio.CARE.Model.Classes.User;

import java.text.ParseException;

public class DeleteUserTester {
    public static void main(String[] args) throws Exception {
        AdminInterface manager = new MongoDataManager();
        User user = new User("antonello","Patente1+");
        manager.deleteUser(user);
    }
}
