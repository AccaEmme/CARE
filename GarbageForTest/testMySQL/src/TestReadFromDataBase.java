import java.util.ArrayList;
import java.util.List;

public class TestReadFromDataBase {
    public static void main(String[] args) {
        DataManager dm = new MySqlDataManager();

        List<MySqlDataManager.Sacca> saccas;
        saccas=dm.getSacche("gr30");

        
        for (MySqlDataManager.Sacca sacca: saccas) {
            System.out.println(sacca);  //stampo la sacca con il metodo toString
        }
    }
}
