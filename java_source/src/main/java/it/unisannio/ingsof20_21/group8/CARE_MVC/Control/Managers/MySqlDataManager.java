package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Exceptions.UserException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;
//import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Exceptions.NullPasswordException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Exceptions.NullPasswordException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Logger;
// non eliminare questo commento. import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Serial; Non necessaria in quanto il costruttore della sacca richiama la generazione del seriale.


public class MySqlDataManager implements DataManager{
	private final String mysql_settings_path = Constants.MYSQL_SETTINGS_PATH;
	
	private String host;
	private String port;
	private String db;
	private String url;
	private String url_db;	//String url_db = "jdbc:mysql://127.0.0.1:3306/CARE";

	private String username;
	private String password;

	/**
	 * potremmo aggiungere anche la possibilita di inizializzarlo con un user*/
	public MySqlDataManager(String username, String password) {
	
		Properties loadProps=MySqlDataManager.getProps(mysql_settings_path);
		host 	= loadProps.getProperty(Constants.TAG_HOST);
		port 	= loadProps.getProperty(Constants.TAG_PORT);
		db 		= loadProps.getProperty(Constants.TAG_DB);
		url 	= "jdbc:mysql://"+host+":"+port+"/";
		url_db 	= url+db;

		this.username = username;
		this.password = password;
	}

	public MySqlDataManager() {
		Properties loadProps=MySqlDataManager.getProps(mysql_settings_path);
		host 	= loadProps.getProperty(Constants.TAG_HOST);
		port 	= loadProps.getProperty(Constants.TAG_PORT);
		db 		= loadProps.getProperty(Constants.TAG_DB);
		url 	= "jdbc:mysql://"+host+":"+port+"/";
		url_db 	= url+db;

		Properties loadProps1=MySqlDataManager.getProps(Constants.MYSQL_LOGIN_SETTINGS_PATH);
		this.username = loadProps1.getProperty("username");
		this.password = loadProps1.getProperty("password");
	}

	private static Properties getProps(String xmlfilepath) {
		Properties loadProps = new Properties();
		try {
			loadProps.loadFromXML(new FileInputStream(xmlfilepath));
			return loadProps;
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private List<String> readSQL(String fileName) throws IOException {
		List<String> queries = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(fileName));

		StringBuilder builder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.equals("")){
				queries.add(builder.toString());
				builder = new StringBuilder();
			}else {
				builder.append(line + "\n");
			}

		}

		System.out.println(builder.toString());
		return queries;
	}

	@Override
	public void writeLog(Logger logger) {

	}

	public void createDB () {
		try {
			System.out.println(url);
			Connection conn = DriverManager.getConnection(url,username,password);
			Statement s = conn.createStatement();

			List<String> queries = readSQL(Constants.MYSQL_INITDB_PATH);

			for (String str : queries){
				System.out.println(str);
				if (!str.isEmpty())
					s.addBatch(str);
			}

			s.executeBatch();
			conn.close();
		} catch (BatchUpdateException database_not_found) {
			System.err.println("The database was not found or the query was empty.\nThe query was skipped.");
			database_not_found.printStackTrace();
		}catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void dropDB () {
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

	@Override
	public User validateLogin(String username, String password) throws UserException, NullPasswordException {
		return null;
	}

	public void addBloodBag (BloodBag s) { // in realtà aggiunge ciò che fornisce il manager, forse ***
		try (Connection conn = DriverManager.getConnection(url_db, username, password);
			 PreparedStatement preparedStatement = conn.prepareStatement(Constants.SQL_INSERT)) {
			/**
			 * private final Serial 	serial;
			 * 	private final Blood 	blood;
			 * 	private Date			creationDate;
			 * 	private Date 			expirationDate;
			 * 	private String			donatorCF; //=null;	 *** Attenzione al rischio di null pointer exception se richiamato il donatorCF
			 * 	private note
			 * id_state
			 * 	*/
			preparedStatement.setString(1, s.getSerial().toString());
			preparedStatement.setString(2, s.getBloodType().toString());
			//System.out.println( (int)(s.getCreationDate().getTime()/1000L) );
			preparedStatement.setInt(3, (int)(s.getCreationDate().getTime()/1000L) ); // unixtimestamp
			preparedStatement.setInt(4, (int) ( s.getExpirationDate().getTime()/1000L) ); // unixtimestamp
			//System.out.println( (int) ( s.getExpirationDate().getTime()/1000L) );
			preparedStatement.setString(5, s.getDonatorCF().toString() );
			preparedStatement.setString(6, s.getNote());
			preparedStatement.setShort(7, (short) 1);
			// TODO: *** by manager preparedStatement.setString(7, s.getState());

			System.out.println(s.getSerial().toString() + " - " + s.getBloodType().toString() + "creation: "+s.getCreationDate().getTime()/1000L + "expiration: "+s.getExpirationDate().getTime()/1000L );
			preparedStatement.execute();
			preparedStatement.close();

		} catch (SQLException e) {
			System.err.format("[addBloodBag] SQL State: %s - %s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
/*
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
*/

	/*BloodBag s = new BloodBag(
            BloodGroup.valueOf(rs.getString(Constants.COL_Serial) ,rs.getString(Constants.COL_GRUPPO) )
    );*/
	/*
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
*/
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
	}

	 */
	/* *** da implementare */

	public boolean createDump() {
		return false;
	}

	public void restoreDump(String filename) {
		/*
		 * TODO: da rivedere perché potrebbe non essere presente mysqlsh o mysql
		 */
		String query = "mysqlsh -u "+username+" -p"+password+" -f "+filename;
		System.out.println(query);
		try {
			Runtime.getRuntime().exec(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		/*
		 * implemento novità, non mostrata dal prof. ma fa schifo perché una riga potrebbe non avere un comando.
		 */
		/*
		String url = "jdbc:mysql://"+host+":"+port+"/";
		try (Connection conn = DriverManager.getConnection(url, username, password);
			      Statement stmt = conn.createStatement()){

	        int i = 0 ;
	        String line = "";
	        FileReader fr = new FileReader(filename) ;
            @SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr) ;
            String query = ".";
	        while( (line = br.readLine()) != null) {
	            query += line;
	            //stmt.execute(line);
	            System.out.println(line);
	        }
	        System.out.println(query);
	        stmt.execute(query);
	        stmt.close();
	        conn.close();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
		//return false;
	}

	public List<BloodBag> getBloodBag(BloodBag blood) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateExpirationDate(BloodBag b, Date newExpirationDate) {
		// TODO Auto-generated method stub

	}

	public void setStateTable(/*short id_state, */String state) {
		String INSERTTABLE_StateTable = "INSERT INTO `state_table` (id_state, state) VALUES (?,?)";
		try (Connection conn = DriverManager.getConnection(url_db, username, password);
			 PreparedStatement preparedStatement = conn.prepareStatement(INSERTTABLE_StateTable)) {
			preparedStatement.setString(1, null);
			preparedStatement.setString(2, state);

			preparedStatement.execute();
			preparedStatement.close();

		} catch (SQLException e) {
			System.err.format("[setStateTable] SQL State: %s - %s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




}
