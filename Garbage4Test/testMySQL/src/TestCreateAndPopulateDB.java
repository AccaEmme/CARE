import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestCreateAndPopulateDB {
    public static void main(String[] args) {
        DataManager dm = new MySqlDataManager();
        dm.createDB();      //creo l'oggetto db
        dm.addItemsToSacche(20);
        


    }
}
