package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.Testers;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.DataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers.MySqlDataManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class MySqlDataManagerTester {
    public static void main(String[] args) {
        DataManager manager = new MySqlDataManager(getLogin()[0],getLogin()[1] );

        manager.createDB();


    }

    private static String[] getLogin(){
        String username = "";
        String password = "";

        String[] user_pw = new String[2];

        Properties properties = new Properties();

        try {
            //properties.loadFromXML(new FileInputStream("C:/Users/giuli/Desktop/uri.xml"));  //pc fisso
            properties.loadFromXML(new FileInputStream("C:\\Users\\giuli\\Desktop\\uri.xml"));  //mac
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        username = properties.getProperty("username");
        password = properties.getProperty("password");

        user_pw[0] = username;
        user_pw[1] = password;
        return user_pw;
        //return "mongodb+srv://"+username+":"+password+"@care.a1sy7.mongodb.net/test";
    }
}
