package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.Testers.MongoManagerTesters;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.MongoDataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Exceptions.UserException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Exceptions.NullPasswordException;

import java.util.Scanner;

public class Login {
    public static void main(String[] args) throws UserException {
        Scanner scanner = new Scanner(System.in);
        MongoDataManager manager = new MongoDataManager();

        System.out.print("Enter username: ");

        String inUsername = scanner.nextLine();
        User dbUser = manager.getUser(inUsername);
        if (dbUser==null)   throw new UserException("User not found!");

        System.out.print("\nEnter password: ");
        User inUser = null;  //leggo in questo modo cosi nessuna password viene memorizzata nemmeno in ram
        try {
            while (inUser == null)
                inUser = new User(inUsername,scanner.nextLine());
        } catch (NullPasswordException e) {
            System.out.println("Password non valida! riprova: ");
        }


        if (inUser.getPassword().equals(dbUser.getPassword()))
            System.out.println("Correttamente autenticato!");
        else throw new UserException("Wrong password!");

        System.out.println("USER:\n"+inUser.getDocument().toString());
    }
}
