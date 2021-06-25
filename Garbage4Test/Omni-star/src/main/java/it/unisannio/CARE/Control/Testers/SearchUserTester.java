package it.unisannio.CARE.Control.Testers;

import it.unisannio.CARE.Control.u.Managers.MongoDataManager;
import it.unisannio.CARE.Model.Classes.User;

public class SearchUserTester {
    public static void main(String[] args) {
        MongoDataManager manager = new MongoDataManager();
        User user = manager.getUser("antonello");

        System.out.println(user.getUsername()+"\nLocation: "+user.getResidence().getDocument());
    }
}
