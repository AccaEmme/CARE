package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.Testers.MongoManagerTesters;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.MongoDataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Exceptions.UserException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Exceptions.NullPasswordException;

public class MongoDatamanagerAddUserTester {
    public static void main(String[] args) {
        MongoDataManager manager = new MongoDataManager();
        User user = null;
        try {
            user = new User("antonello","patente");
            manager.addUser(user);
        } catch (UserException | NullPasswordException e) {
            e.printStackTrace();
        }

    }
}
