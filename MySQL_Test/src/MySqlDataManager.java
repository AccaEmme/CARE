import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class MySqlDataManager implements DataManager{
    private String host;
    private String db;

    private static final  String username = null;
    private static final String password = null;

    private static final String SQL_CREATE_DB = "CREATE DATABASE IF NOT EXISTS";
    private static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS SACCHE"
            + "("
            + " SERIALE varchar(20) NOT NULL,"
            + " GRUPPO varchar(5) NOT NULL,"
            + " PRIMARY KEY (SERIALE)"
            + ")";

    private static final String SQL_INSERT = "INSERT INTO SACCHE (SERIALE, GRUPPO) VALUES (?,?)";

    private static final String SQL_QUERY = "SELECT * FROM sacche WHERE GRUPPO = ?";

    public MySqlDataManager(){
        Properties loadProps = new Properties();

        try {
            loadProps.loadFromXML(new FileInputStream("C:\\Users\\giuli\\Desktop\\CARE\\MySQL_Test\\src\\db_settings.xml")); //non dimenticare di aggiungere il file mostrato dal prof per i settings

        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean readCredentials(){
        boolean correctlyRead = true;

        try {
            File credentials = new File();
        }

        return correctlyRead;
    }

    public void createDB(){
        String url = "jdbc:mysql://"+host;

        try (Connection conn = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_CREATE_DB+db)){
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
