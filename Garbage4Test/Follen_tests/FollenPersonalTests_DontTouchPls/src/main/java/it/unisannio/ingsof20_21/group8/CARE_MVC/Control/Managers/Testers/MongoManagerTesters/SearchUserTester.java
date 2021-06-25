package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.Testers.MongoManagerTesters;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.MongoDataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;

public class SearchUserTester {
    public static void main(String[] args) {
        MongoDataManager manager = new MongoDataManager();
        User user = manager.getUser("Peppe");

        System.out.println(user.getUsername()+user.getPassword());
    }
}
