package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.Testers.MongoManagerTesters;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.MongoDataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.UserManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.UserException;

import java.util.Scanner;

public class UserManagerLogin {
    public static void main(String[] args) throws UserException {
        String username;
        String password;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Insert username: ");
        username = scanner.nextLine();
        System.out.print("\nInsert password: ");
        password = scanner.nextLine();

        UserManager manager = UserManager.checkLogin(username,password,new MongoDataManager());
        System.out.println(manager.getUser().getDocument().toString());
    }
}
