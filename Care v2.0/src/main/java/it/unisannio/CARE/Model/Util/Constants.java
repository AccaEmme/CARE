package it.unisannio.CARE.Model.Util;

import java.text.SimpleDateFormat;

import it.unisannio.CARE.Model.BloodBag.BloodGroup;



public class Constants {

    //#################################################### General Strings ####################################################	
	public static final String DATE_FORMAT 					= "yyyyMMdd";
	public static final SimpleDateFormat dateFormat 		= new SimpleDateFormat(DATE_FORMAT);
    public static final String DATE_FORMAT_STRING 			= "yyyy-MM-dd";
    public static final SimpleDateFormat dateFormatString 	= new SimpleDateFormat(DATE_FORMAT_STRING);
    
    //#################################################### Serial Strings ####################################################
    public static final String SERIAL_SETTINGS_RELATIVEPATH = "localsettings/";
    public static final String SERIAL_SETTINGS_FILENAME 	= "serial_settings.xml";
    public static final String SERIAL_SETTINGS_FILENAME_RELATIVEPATH = "localsettings/serial_settings.xml";
    
    public static final String SERIAL 						= "SERIAL";
    public static final String GROUP 						= "GROUP";
    public static final String EXPIRATION 					= "EXPIRATION_DAY";
    public static final String CREATION 					= "CREATION_DAY";
    public static final String ORIGIN 						= "ORIGIN";
    
    //#################################################### User Strings ####################################################
    public static final String USER_MD5_SALT 				= "CanforaMarkUs30L";
    public static final String USER_DEFAULT_TEMP_PASS 		= "CARE:changemenow";
    public static final int USER_TEMPPASS_LENGTH 			= 10;
    public static final int USER_DAYS_EXPIRATION_PASS 		= 60;

    
    //#################################################### Generic DATABASE STRINGS ####################################################
    public static final String TAG_HOST 					= "db_mysql_host";      //non so perchè nelle costanti c'erano i valori dell'xml .-.
    public static final String TAG_PORT 					= "db_mysql_port";
    public static final String TAG_DB 						= "db_mysql_dbname";

    public static final String DB_TABLE_BLOODBAGS 			= "Bloodbags";
    private static final String COL_Serial 					= "serial";
    private static final String COL_GROUP 					= "bloodgroup";
    private static final String COL_creation 				= "creation";
    private static final String COL_expiring 				= "expiring";
    private static final String COL_donatorCF 				= "donatorCF";
    private static final String COL_note 					= "note";
    private static final String COL_idstate 				= "id_state";
    
    //#################################################### MySQL DATABASE STRINGS ####################################################
    public static final String MYSQL_LOGIN_SETTINGS_PATH 	= "../../login.xml";		// Credentials are not shared, file should be outside github sync path.
    public static final String MYSQL_SETTINGS_PATH 			= "localsettings/db_settings.xml";
    public static final String MYSQL_INITDB_PATH 			= "QueriesSQL/creation.sql";
    
    			// BloodBags attributes columns
    private static final String COL_SERIAL 					= "idSerial";
    private static final String COL_CREATION 				= "creationDate";
    private static final String COL_EXPIRING 				= "expiringDate";
    private static final String COL_DONATOR_CF 				= "donatorCF";
    private static final String COL_NOTE 					= "note";
    private static final String COL_IDSTATE 				= "id_state";
    private static final String COL_IDNODE 					= "id_node";

    			// Location attributes columns
    public static final String DB_TABLE_LOCATION 			= "location";
    private static final String COL_IDLOCATION 				= "id_location";
    private static final String COL_COUNTRY 				= "country";
    private static final String COL_REGION 					= "region";
    private static final String COL_PROVINCE 				= "province";
    private static final String COL_CITY 					= "city";
    private static final String COL_STREET 					= "street";
    private static final String COL_STREETNUMBER 			= "streetNumber";
    private static final String COL_ZIPCODE 				= "ZipCode";

    			// Nodes attributes columns
    public static final String DB_TABLE_NODE 				= "nodes_table";
    private static final String COL_ID_NODE 				= "id_node";
    private static final String COL_CODSTR 					= "codStr";
    private static final String COL_NODENAME 				= "nodeName";
    private static final String APOSMIN 					= BloodGroup.valueOf("Apos")+"_min";
    private static final String APOSMAX 					= BloodGroup.valueOf("Apos")+"_max";
    private static final String ANEGMIN 					= BloodGroup.valueOf("Aneg")+"_min";
    private static final String ANEGMAX 					= BloodGroup.valueOf("Aneg")+"_max";
    private static final String BPOSMIN 					= BloodGroup.valueOf("Bpos")+"_min";
    private static final String BPOSMAX 					= BloodGroup.valueOf("Bpos")+"_max";
    private static final String ABPOSMIN 					= BloodGroup.valueOf("ABpos")+"_min";
    private static final String ABPOSMAX 					= BloodGroup.valueOf("ABpos")+"_max";
    private static final String ABNEGMIN 					= BloodGroup.valueOf("ABneg")+"_min";
    private static final String ABNEGMAX 					= BloodGroup.valueOf("ABneg")+"_max";
    private static final String ZEROPOSMIN 					= BloodGroup.valueOf("ZEROpos")+"_min";
    private static final String ZEROPOSMAX 					= BloodGroup.valueOf("ZEROpos")+"_max";
    private static final String ZERONEGMIN 					= BloodGroup.valueOf("ZEROneg")+"_min";
    private static final String ZERONEGMAX 					= BloodGroup.valueOf("ZEROneg")+"_max";

    public static final String SQL_INSERT_NODE =
            "INSERT INTO "+
                    Constants.DB_TABLE_NODE +
                    " ("+ Constants.COL_ID_NODE +
                    ", "+ Constants.COL_CODSTR +
                    ", "+ Constants.COL_NODENAME +
                    ", "+ Constants.COL_COUNTRY +
                    ", "+ Constants.COL_REGION +
                    ", "+ Constants.COL_PROVINCE +
                    ", "+ Constants.COL_CITY +
                    ", "+ Constants.COL_STREET +
                    ", "+ Constants.COL_STREETNUMBER +
                    ", "+ Constants.COL_ZIPCODE +
                    ", "+ Constants.APOSMIN +
                    ", "+ Constants.APOSMAX +
                    ", "+ Constants.ANEGMIN +
                    ", "+ Constants.ANEGMAX +
                    ", "+ Constants.BPOSMIN +
                    ", "+ Constants.BPOSMAX +
                    ", "+ Constants.ABPOSMIN +
                    ", "+ Constants.ABPOSMAX +
                    ", "+ Constants.ABNEGMIN +
                    ", "+ Constants.ABNEGMAX +
                    ", "+ Constants.ZEROPOSMIN +
                    ", "+ Constants.ZEROPOSMAX +
                    ", "+ Constants.ZERONEGMIN +
                    ", "+ Constants.ZERONEGMAX +
                    ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    //#################################################### MySQL DATABASE STRINGS ####################################################
    public static final String MONGODB_CREDENTIALS          = "./../../uri.xml";		// Credentials are not shared, file should be outside github sync path. So we use relative parents path
    public static final String MONGODBL_SETTINGS_PATH       = "localsettings/mongo_settings.xml";
    
    
    //#################################################### MySQL DATABASE QUERIES ####################################################
    public static final String SQL_CREATE_DB 				= "CREATE DATABASE IF NOT EXISTS ";
    public static final String SQL_DROP_DB 					= "DROP DATABASE IF EXISTS ";
    public static final String SQL_CREATE_TABLE 			= "CREATE TABLE IF NOT EXISTS " + Constants.DB_TABLE_BLOODBAGS
			            + "("
			            + Constants.COL_SERIAL + " varchar(33) NOT NULL,"
			            + Constants.COL_GROUP 		+ " varchar(7) NOT NULL,"
			            + Constants.COL_CREATION + " int NOT NULL,"
			            + Constants.COL_EXPIRING + " int NOT NULL,"
			            + Constants.COL_DONATOR_CF + " char(16) NOT NULL,"
			            + Constants.COL_NOTE + " TEXT,"
			            + Constants.COL_IDSTATE + " smallint NOT NULL,"
			            + " PRIMARY KEY ("+Constants.COL_SERIAL+")"
			            + "foreign key ("+ COL_IDSTATE +") references"
			            + "`state_table`(id_state) -- referenzia la tabella degli stati"
			            + ")";
    public static final String SQL_INSERT_BLOODBAGS =
    					"INSERT INTO "+
    					Constants.DB_TABLE_BLOODBAGS +
    					" ("+ Constants.COL_SERIAL +
    					", "+ Constants.COL_GROUP +
    					", "+ Constants.COL_CREATION +
    					", "+ Constants.COL_EXPIRING +
    					", "+ Constants.COL_DONATOR_CF +
                        ", "+ Constants.COL_IDNODE +
    					", "+ Constants.COL_IDSTATE +
    					", "+ Constants.COL_NOTE +
    					") VALUES (?,?,?,?,?,?,?,?)";

    public static final String SQL_INSERT_LOCATION =
            "INSERT INTO "+
                    Constants.DB_TABLE_LOCATION +
                    " ("+ Constants.COL_IDLOCATION +
                    ", "+ Constants.COL_COUNTRY +
                    ", "+ Constants.COL_REGION +
                    ", "+ Constants.COL_PROVINCE +
                    ", "+ Constants.COL_CITY +
                    ", "+ Constants.COL_STREET +
                    ", "+ Constants.COL_STREETNUMBER +
                    ", "+ Constants.COL_ZIPCODE +
                    ") VALUES (?,?,?,?,?,?,?,?)";

	public static final String SQL_SELECT_BLOODBAGS =
						"SELECT * FROM " + 
						Constants.DB_TABLE_BLOODBAGS +  
						" WHERE "+ 
						Constants.COL_GROUP + 
						" = ?";
	
	//#################################################### InitSettings Strings ####################################################
	
	
	
	
	//#################################################### InitSettings Strings ####################################################
	public static final String InitSettings_askNationality 					= "Nazionalita' [IT]:";
	public static final String InitSettings_askProvince 					= "Provincia [NA]:";
	public static final String InitSettings_askCodStr 						= "Codice Struttura [206]:";
	public static final String InitSettings_askIntCod 						= "Eventuale codice ufficio interno [000]:";
	
    //#################################################### Exception Strings ####################################################
	public static final String ExceptionIllegalArgument_SerialNotValid 		= "Seriale non valido: ";
	public static final String ExceptionIllegalArgument_BloodGroupNotValid 	= "BloodGroup non valido: ";
	public static final String ExceptionIllegalArgument_BloodBagNotValid 	= "BloodBag non valida: ";
	
    //#################################################### RegExp pattern Data Validation ####################################################
	// Some people, when confronted with a problem, think "I know, I'll use regular expressions." Now they have two problems. -  Jamie Zawinski, 1997	
	public static final String RegexSerial									= "^IT-\\w{2}\\d{6}-(" 	+ BloodGroup.delimitedValues("|") + ")-\\d{8}-\\d{4}$";
	public static final String RegexDonatorCF 								= "^^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$";
	public static final String RegexPasswordCriteria 						= "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])(?=.{8,})";
}
