package it.unisannio.ingsof20_21.group8.CARE_MVC.Control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Blood;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
// import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Serial; Non necessaria in quanto il costruttore della sacca richiama la generazione del seriale.


public class MySqlDataManager implements DataManager{
	
	private  String host = "127.0.0.1";
	private  String port = "3306";
	private  String db = "CARE";
	
	private static final String TAG_HOST = "127.0.0.1";
	private static final String TAG_PORT = "3306";
	private static final String TAG_DB = "CARE";

	private static final String TABLE_SACCHE = "BloodBags";
	private static final String COL_Serial = "Serial";
	private static final String COL_GRUPPO = "BloodType";
			
    private static final String username = "root";
    private static final String password = "CanforaMarkUs30Lode";

    private static final String SQL_CREATE_DB 		= "CREATE DATABASE IF NOT EXISTS ";
    private static final String SQL_DROP_DB 		= "DROP DATABASE IF EXISTS ";
    private static final String SQL_CREATE_TABLE 	= "CREATE TABLE IF NOT EXISTS " + TABLE_SACCHE
            + "("
            + COL_Serial + " varchar(20) NOT NULL,"
            + COL_GRUPPO + " varchar(5) NOT NULL,"
            + " PRIMARY KEY (Serial)"
            + ")";
    
    private static final String SQL_INSERT = "INSERT INTO "+ TABLE_SACCHE +" ("+ COL_Serial+", "+COL_GRUPPO+") VALUES (?,?)";
    
	private static final String SQL_QUERY = "SELECT * FROM " + TABLE_SACCHE +  " WHERE "+ COL_GRUPPO + " = ?";
	
	public MySqlDataManager() {
		Properties loadProps = new Properties();
	    try {
			loadProps.loadFromXML(new FileInputStream("localsettings/db_settings.xml"));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    //host = loadProps.getProperty(TAG_HOST);
	    //port = loadProps.getProperty(TAG_PORT);
	    //db = loadProps.getProperty(TAG_DB);	
	}

	public void createDB () {
        //String url = "jdbc:mysql://"+host+":"+port;
		String url = "jdbc:mysql://127.0.0.1:3306";

        try (Connection conn = DriverManager.getConnection(url, username, password);
        	PreparedStatement preparedStatement = conn.prepareStatement(SQL_CREATE_DB+db)) {
        	System.out.println(SQL_CREATE_DB+db);
        	preparedStatement.execute();
        	preparedStatement.close();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
       try (Connection conn = DriverManager.getConnection(url+"/"+db, username, password);
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_CREATE_TABLE)) {
       		preparedStatement.execute();
       		preparedStatement.close();;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }		
	}
	
	public void dropDB () {
		//String url = "jdbc:mysql://"+host+":"+port+"/"+db;
		String url = "jdbc:mysql://127.0.0.1:3306/CARE";
		
	    try (Connection conn = DriverManager.getConnection(url, username, password);
	    	PreparedStatement preparedStatement = conn.prepareStatement(SQL_DROP_DB+db)) {
	        preparedStatement.execute();
	        preparedStatement.close();
	    } catch (SQLException e) {
	         System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	    } catch (Exception e) {
	         e.printStackTrace();
	    }
		
	}
	
	public void addBloodBag (BloodBag s) {
        String url = "jdbc:mysql://"+host+":"+port+"/"+db;

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT)) {

            preparedStatement.setString(1, s.getSerial().toString());
            preparedStatement.setString(2, s.getBloodType().toString());
        	preparedStatement.execute();
        	preparedStatement.close();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } 

    }
	
	public List<BloodBag> getBloodBag(Blood g) {
		String url = "jdbc:mysql://"+host+":"+port+"/"+db;
		
		List<BloodBag> sacche = new ArrayList<BloodBag>();	// *** mettere un iterator
		
		try (Connection conn = DriverManager.getConnection(url, username, password);
		      PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY)){

			preparedStatement.setString(1, g.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()){
				BloodBag s = new BloodBag(
											Blood.valueOf( rs.getString(COL_GRUPPO) )
										);
			    sacche.add(s);
			}
			rs.close();
			preparedStatement.close();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
		return sacche;
	}



}
