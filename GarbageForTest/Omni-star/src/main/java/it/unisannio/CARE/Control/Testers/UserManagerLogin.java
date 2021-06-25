package it.unisannio.CARE.Control.Testers;

import it.unisannio.CARE.Control.u.Managers.MongoDataManager;
import it.unisannio.CARE.Control.u.Managers.UserManager;
import it.unisannio.CARE.Exceptions.NullPasswordException;
import it.unisannio.CARE.Exceptions.UserException;

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
