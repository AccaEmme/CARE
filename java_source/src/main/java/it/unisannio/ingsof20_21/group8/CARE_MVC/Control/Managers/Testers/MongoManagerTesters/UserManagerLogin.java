package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.Testers.MongoManagerTesters;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.MongoDataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.UserManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Exceptions.UserException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Exceptions.NullPasswordException;

import java.util.Scanner;

public class UserManagerLogin {
    public static void main(String[] args) throws UserException, NullPasswordException {
        String username;
        String password;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Insert username: ");
        username = scanner.nextLine();
        System.out.print("\nInsert password: ");
        password = scanner.nextLine();

        UserManager manager = UserManager.checkLogin(username,password,new MongoDataManager());
        System.out.println(manager.getCurrentUser().getDocument().toString());
    }
}
