package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Testers.MySQLDataManagerTesters;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.DataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Control.MySqlDataManager;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;

import java.util.List;

public class SimpleTest {
    public static void main(String[] args) {
        DataManager manager = new MySqlDataManager();
        List<BloodBag> bags = manager.getBloodBag(BloodGroup.valueOf("Apos"));

        for (BloodBag bag : bags){
            System.out.println(bag.toString()); 
        }
    }
}
