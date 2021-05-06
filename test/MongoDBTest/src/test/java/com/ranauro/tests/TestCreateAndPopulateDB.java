package com.ranauro.tests;

import com.ranauro.persistence.DataManager;
import com.ranauro.persistence.MySqlDataManager;

public class TestCreateAndPopulateDB {
    public static void main(String[] args) {
        DataManager dm = new MySqlDataManager();
        dm.createDB();      //creo l'oggetto db
        dm.addItemsToSacche(20);
    }
}
