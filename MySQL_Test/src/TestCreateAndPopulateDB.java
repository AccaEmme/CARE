import java.sql.*;

public class TestCreateAndPopulateDB {
    public static void main(String[] args) {
        DataManager dm = new MySqlDataManager();
        dm.createDB();      //creo l'oggetto db


        //final int NUMSACCHE = 15;

        /*
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/university" , "user", "password"
            );

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from aula");
            while (rs.next()){
                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3));
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
*/
    }
}
