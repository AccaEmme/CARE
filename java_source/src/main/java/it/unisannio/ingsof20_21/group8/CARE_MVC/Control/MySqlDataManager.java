package it.unisannio.ingsof20_21.group8.CARE_MVC.Control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;
// import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Serial; Non necessaria in quanto il costruttore della sacca richiama la generazione del seriale.


public class MySqlDataManager implements DataManager{
	private  String host;
	private  String port;
	private  String db;
	

			
    private static final String username = "root";
    private  String password;


    //queste non le ho messe nella classe Constants perche non so come si comporta il punto interrogativo nelle stringhe
    private static final String SQL_INSERT = "INSERT INTO "+ Constants.TABLE_SACCHE +" ("+ Constants.COL_Serial+", "+Constants.COL_GROUP +") VALUES (?,?)";
    
	private static final String SQL_QUERY = "SELECT * FROM " + Constants.TABLE_SACCHE +  " WHERE "+ Constants.COL_GROUP + " = ?";
	
	public MySqlDataManager() {
		Properties loadProps = new Properties();
	    try {
			loadProps.loadFromXML(new FileInputStream(Constants.DB_SETTINGS_PATH));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    host = loadProps.getProperty(Constants.TAG_HOST);
	    port = loadProps.getProperty(Constants.TAG_PORT);
	    db = loadProps.getProperty(Constants.TAG_DB);

	    try {
	    	//la password viene letta da un xml, modificare "Constants.MYSQL_LOGIN_SETTINGS_PATH" con il proprio path
	        loadProps.loadFromXML(new FileInputStream(Constants.MYSQL_LOGIN_SETTINGS_PATH));
			//loadProps.loadFromXML(new FileInputStream(Constants.MYSQL_LOGIN_SETTINGS_PATH_MAC));
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

	    this.password = loadProps.getProperty("password");
    }

	public void createDB () {
        String url = "jdbc:mysql://"+host+":"+port;
		//String url = "jdbc:mysql://127.0.0.1:3306";

		/**
		 * @// TODO: 19/05/2021 modificare i metodi in modo tale che accettino dei argomenti. */
        try (Connection conn = DriverManager.getConnection(url, username, password);
        	PreparedStatement preparedStatement = conn.prepareStatement(Constants.SQL_CREATE_DB+db)) {
        	System.out.println(Constants.SQL_CREATE_DB+db);
        	preparedStatement.execute();
        	preparedStatement.close();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
       try (Connection conn = DriverManager.getConnection(url+"/"+db, username, password);
            PreparedStatement preparedStatement = conn.prepareStatement(Constants.SQL_CREATE_TABLE)) {
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
	    	PreparedStatement preparedStatement = conn.prepareStatement(Constants.SQL_DROP_DB+db)) {
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
        	/**
			 * private final Serial 	serial;
			 * 	private final Blood 	blood;
			 * 	private Date			creationDate;
			 * 	private Date 			expirationDate;
			 * 	private String			donatorCF; //=null;	 *** Attenzione al rischio di null pointer exception se richiamato il donatorCF
			 * 	private note
			 * 	*/

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

	@Override
	public List<BloodBag> getBloodBag(BloodGroup bloodGroup) {
		String url = "jdbc:mysql://"+host+":"+port+"/"+db;

		List<BloodBag> sacche = new ArrayList<BloodBag>();	// *** mettere un iterator

		try (Connection conn = DriverManager.getConnection(url, username, password);
			 PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERY)){

			preparedStatement.setString(1, bloodGroup.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()){
				//BloodBag bag = new BloodBag();
				BloodGroup group = BloodGroup.valueOf(rs.getString(Constants.COL_GROUP));


				/*BloodBag s = new BloodBag(
						BloodGroup.valueOf(rs.getString(Constants.COL_Serial) ,rs.getString(Constants.COL_GRUPPO) )
				);*/
				sacche.add(new BloodBag(group));
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

	@Override
	public void writeLog(Date currentDate, User currentUser, String currentClass, String currentMethod, String currentResult) {

	}

	public void writeLog(){

	}
	
    /*
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
	}*/



}
