package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Serial;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag.BloodBagState;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces.BloodBagInterface;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node.Node;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.Exceptions.UserException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;
//import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Exceptions.NullPasswordException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Exceptions.NullPasswordException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.City;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Country;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Province;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Region;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Logger;
// non eliminare questo commento. import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Serial; Non necessaria in quanto il costruttore della sacca richiama la generazione del seriale.
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.XMLHelper;


public class MySqlDataManager implements DataManager{
	private final String mysql_settings_path = Constants.MYSQL_SETTINGS_PATH;
	
	private String host;
	private String port;
	private String db;
	private String url;
	private String url_db;	//String url_db = "jdbc:mysql://127.0.0.1:3306/CARE";

	private String username;
	private String password;
	
	private static final String select_blood_bag = "SELECT* FROM BLOODBAGS"
												+ "WHERE BLOODGROUP = ?";		

	/**
	 * potremmo aggiungere anche la possibilita di inizializzarlo con un user*/
	public MySqlDataManager(String username, String password) {
	
		Properties loadProps = XMLHelper.getProps(mysql_settings_path);
		host 	= loadProps.getProperty(Constants.TAG_HOST);
		port 	= loadProps.getProperty(Constants.TAG_PORT);
		db 		= loadProps.getProperty(Constants.TAG_DB);
		url 	= "jdbc:mysql://"+host+":"+port+"/";
		url_db 	= url+db;

		this.username = username;
		this.password = password;
	}

	public MySqlDataManager() {
		Properties loadProps = XMLHelper.getProps(mysql_settings_path);
		host 	= loadProps.getProperty(Constants.TAG_HOST);
		port 	= loadProps.getProperty(Constants.TAG_PORT);
		db 		= loadProps.getProperty(Constants.TAG_DB);
		url 	= "jdbc:mysql://"+host+":"+port+"/";
		url_db 	= url+db;

		Properties loadProps1 = XMLHelper.getProps(Constants.MYSQL_LOGIN_SETTINGS_PATH);
		this.username = loadProps1.getProperty("username");
		this.password = loadProps1.getProperty("password");
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
			 PreparedStatement preparedStatement = conn.prepareStatement(Constants.SQL_INSERT_BLOODBAGS)) {

			preparedStatement.setString(1, s.getSerial().toString());
			preparedStatement.setString(2, s.getBloodType().toString());
			//System.out.println( (int)(s.getCreationDate().getTime()/1000L) );
			preparedStatement.setInt(3, (int)(s.getCreationDate().getTime()/1000L) ); // unixtimestamp
			preparedStatement.setInt(4, (int) ( s.getExpirationDate().getTime()/1000L) ); // unixtimestamp
			//System.out.println( (int) ( s.getExpirationDate().getTime()/1000L) );
			preparedStatement.setString(5, s.getDonatorCF());
			preparedStatement.setInt(6, 101);//s.getNode().getCodStr()
			preparedStatement.setShort(7, (short) 1);		/**@// TODO: 18/06/2021  da dove prendo lo stato!? */
			preparedStatement.setString(8, s.getNote());

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

	/**@Todo aggiornare*/
	@Override
	public void addStates(){
		String query = "insert into `state_table` (id_state, state) values\n" +
				"    (null, 'presente'),\n" +
				"    (null, 'trasferito'),\n" +
				"    (null, 'scaduto');";
		try {
			Connection connection = DriverManager.getConnection(url_db,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			System.out.println("Inserting : \n"+query);

			preparedStatement.execute();

			System.out.println("The operation was successful.");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Override
	public void addRoles(){
		String query = "insert into `role_table` (id_role, usrRole) values\n" +
				"    (null, 'Officer'),\n" +
				"    (null, 'StoreManager'),\n" +
				"    (null, 'Administrator');";
		try {
			Connection connection = DriverManager.getConnection(url_db,username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			System.out.println("Inserting : \n"+query);

			preparedStatement.execute();

			System.out.println("The operation was successful.");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Override
	public void addLocation(Location location){
		try {
			Connection conn = DriverManager.getConnection(url_db, username, password);
			PreparedStatement preparedStatement = conn.prepareStatement(Constants.SQL_INSERT_LOCATION);

			preparedStatement.setInt(1, (short)1);	/**@// TODO: 18/06/2021 non ho idea di dove prendere id_location */
			preparedStatement.setString(2, location.getCountry().toString());
			preparedStatement.setString(3, location.getRegion().toString());
			preparedStatement.setString(4, location.getProvince().toString());
			preparedStatement.setString(5, location.getCity().toString());
			preparedStatement.setString(6, location.getStreet());
			preparedStatement.setString(7, location.getStreetNumber());
			preparedStatement.setString(8, location.getZipCode());

			preparedStatement.execute();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}


	public void addNode(Node node){
		try {
			Connection conn = DriverManager.getConnection(url_db, username, password);
			PreparedStatement preparedStatement = conn.prepareStatement(Constants.SQL_INSERT_NODE);

			preparedStatement.setNull(1, Types.NULL);	/**@ TODO: 18/06/2021 non so come aggiungere l'auto increment*/
			preparedStatement.setString(2, node.getCodStr().toString());
			preparedStatement.setString(3, node.getNodeName().toString());

			Location warehouse = node.getWarehouse();

			preparedStatement.setString(4, warehouse.getCountry().toString());
			preparedStatement.setString(5, warehouse.getRegion().toString());
			preparedStatement.setString(6, warehouse.getProvince().toString());
			preparedStatement.setString(7, warehouse.getCountry().toString());
			preparedStatement.setString(8, warehouse.getStreet());
			preparedStatement.setString(9, warehouse.getStreetNumber());
			preparedStatement.setString(10, warehouse.getZipCode());


			preparedStatement.setNull(11, Types.SMALLINT);
			preparedStatement.setNull(12, Types.SMALLINT);
			preparedStatement.setNull(13, Types.SMALLINT);
			preparedStatement.setNull(14, Types.SMALLINT);
			preparedStatement.setNull(15, Types.SMALLINT);
			preparedStatement.setNull(16, Types.SMALLINT);
			preparedStatement.setNull(17, Types.SMALLINT);
			preparedStatement.setNull(18, Types.SMALLINT);
			preparedStatement.setNull(19, Types.SMALLINT);
			preparedStatement.setNull(20, Types.SMALLINT);
			preparedStatement.setNull(21, Types.SMALLINT);
			preparedStatement.setNull(22, Types.SMALLINT);
			preparedStatement.setNull(23, Types.SMALLINT);
			preparedStatement.setNull(24, Types.SMALLINT);
			preparedStatement.setNull(25, Types.SMALLINT);

			preparedStatement.execute();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}



	@Override
	public List<BloodBag> getBloodBag(BloodGroup bloodGroup) {
		String url = "jdbc:mysql://"+host+":"+port+"/"+db;
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		

		List<BloodBag> bags = new ArrayList<BloodBag>();	// *** mettere un iterator

		try (Connection conn = DriverManager.getConnection(url, username, password);
			 PreparedStatement preparedStatement = conn.prepareStatement(this.select_blood_bag)){

			preparedStatement.setString(1, bloodGroup.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()){
				/**this.serial 		= serial;
				this.bloodGroup=group;
				this.creationDate =cd;
				this.expirationDate=ed;
				this.donatorCF=donatorCF2;
				this.node=n1;
				this.note=note2;		
				this.bloodBagState=valueOf2;*/
				
				Serial serial = new Serial("test");
				BloodGroup group = BloodGroup.valueOf(rs.getString("bloodgroup"));
				
				int creation = rs.getInt("creationDate");
				
				
				int expiring = rs.getInt("expiringDate");
				
				
				String donatorCF = rs.getString("donatorCF");
				//da modificare
				Node node = new Node("206","Rummo",		new Location(Country.Italy, Region.Campania, Province.Benevento, City.Benevento, "via Roma", "44C","82018"));
				String note = rs.getString("note");
				
				String stateSTR = rs.getString("state");

				BloodBagState bbs = BloodBagState.valueOf(stateSTR);
				
				//Serial serial, BloodGroup valueOf, Date cd, Date ed, String donatorCF2, Node n1,BloodBagState valueOf2, String note2
				BloodBag bag = new BloodBag(serial, group, new Date(), new Date(),donatorCF,node,bbs,note);
			}
			rs.close();
			preparedStatement.close();
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bags;
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
