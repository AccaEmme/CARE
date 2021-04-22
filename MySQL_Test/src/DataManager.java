import java.util.List;
public interface DataManager {
    void createDB();
    void addItemsToSacche(int amount);
    //void addSacca(Sacca s); per il momento non utilizzato ne implementato
    List<MySqlDataManager.Sacca> getSacche(String g);
}
