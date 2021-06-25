package it.unisannio.CARE.Control.Testers;

import it.unisannio.CARE.Control.u.Managers.MongoDataManager;
import it.unisannio.CARE.Exceptions.NullPasswordException;
import it.unisannio.CARE.Exceptions.UserException;
import it.unisannio.CARE.Model.Classes.User;

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
