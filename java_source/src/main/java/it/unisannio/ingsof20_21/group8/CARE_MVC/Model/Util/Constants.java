package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util;

public class Constants {
    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final String DATE_FORMAT_STRING = "yyyy-MM-dd hh:mm";
    public static final String DB_SETTINGS_PATH = "/localsettings/db_settings.xml";
    public static final String SERIAL_SETTINGS_RELATIVEPATH = "localsettings/";
    public static final String SERIAL_SETTINGS_FILENAME = "serial_settings.xml/";
    public static final String SERIAL_SETTINGS_FILENAME_RELATIVEPATH = "localsettings/serial_settings.xml"; 
    public static final String BLOODBAG_SETTINGS_FILENAME_RELATIVEPATH = "localsettings/bloodBag_settings.xml";
    //SERIAL_SETTINGS_RELATIVEPATH+SERIAL_SETTINGS_FILENAME;
    
    public static final String SERIAL = "SERIAL";
    public static final String GROUP = "GROUP";
    public static final String EXPIRATION = "EXPIRATION_DAY";
    public static final String CREATION = "CREATION_DAY";
    public static final String ORIGIN = "ORIGIN";

    //#################################################### DATABASE STRINGS ####################################################
    public static final String TAG_HOST = "db_mysql_host";      //non so perchè nelle costanti c'erano i valori dell'xml .-.
    public static final String TAG_PORT = "db_mysql_port";
    public static final String TAG_DB = "db_mysql_dbname";

    public static final String DB_TABLE_BLOODBAGS = "BloodBags";
    private static final String COL_Serial = "serial";
    private static final String COL_GROUP = "Rh";
    private static final String COL_creation = "creation";
    private static final String COL_expiring = "expiring";
    private static final String COL_donatorCF = "donatorCF";
    private static final String COL_note = "note";
    private static final String COL_idstate = "id_state";
    
    // *** TEMP
    public static final String MYSQL_LOGIN_SETTINGS_PATH = "../../login.xml";
//    public static final String MYSQL_LOGIN_SETTINGS_PATH = "../../CareMySQLLogin.xml";
    //public static final String MYSQL_LOGIN_SETTINGS_PATH_MAC = "/Users/folly/Desktop/uri.xml"; *** use relative path.
    
    
    //#################################################### DATABASE QUERIES ####################################################
    public static final String SQL_CREATE_DB 		= "CREATE DATABASE IF NOT EXISTS ";
    public static final String SQL_DROP_DB 			= "DROP DATABASE IF EXISTS ";
    public static final String SQL_CREATE_TABLE 	= "CREATE TABLE IF NOT EXISTS " + Constants.DB_TABLE_BLOODBAGS
            + "("
            + Constants.COL_Serial 		+ " varchar(33) NOT NULL,"
            + Constants.COL_GROUP 		+ " varchar(7) NOT NULL,"
            + Constants.COL_creation 	+ " int NOT NULL,"
            + Constants.COL_expiring	+ " int NOT NULL,"
            + Constants.COL_donatorCF	+ " char(16) NOT NULL,"
            + Constants.COL_note		+ " TEXT,"
            + Constants.COL_idstate		+ " smallint NOT NULL,"
            + " PRIMARY KEY (Serial)"
            + "foreign key ("+COL_idstate+") references `state_table`(id_state) -- referenzia la tabella degli stati"
            + ")";
    public static final String SQL_INSERT = 
    					"INSERT INTO "+
    					Constants.DB_TABLE_BLOODBAGS +
    					" ("+ Constants.COL_Serial+
    					", "+ Constants.COL_GROUP +
    					", "+ Constants.COL_creation +
    					", "+ Constants.COL_expiring +
    					", "+ Constants.COL_donatorCF +
    					", "+ Constants.COL_note +
    					", "+ Constants.COL_idstate +
    					") VALUES (?,?,?,?,?,?,?)";
    
	public static final String SQL_QUERY = 
						"SELECT * FROM " + 
						Constants.DB_TABLE_BLOODBAGS +  
						" WHERE "+ 
						Constants.COL_GROUP + 
						" = ?";
}
