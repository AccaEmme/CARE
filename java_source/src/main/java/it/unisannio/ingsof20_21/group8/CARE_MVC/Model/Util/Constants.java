package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util;

public class Constants {
    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final String DB_SETTINGS_PATH = "localsettings/db_settings.xml";
    public static final String SERIAL_SETTINGS_PATH = "localsettings/serial_settings.xml";
    
    // *** TEMP
    public static final String MYSQL_LOGIN_SETTINGS_PATH = "../../login.xml";
//    public static final String MYSQL_LOGIN_SETTINGS_PATH = "../../CareMySQLLogin.xml";
    //public static final String MYSQL_LOGIN_SETTINGS_PATH_MAC = "/Users/folly/Desktop/uri.xml"; *** use relative path.
    
    public static final String SERIAL = "SERIAL";
    public static final String GROUP = "GROUP";
    public static final String EXPIRATION = "EXPIRATION_DAY";
    public static final String CREATION = "CREATION_DAY";
    public static final String ORIGIN = "ORIGIN";

    //#################################################### DATABASE STRINGS ####################################################
    public static final String TAG_HOST = "db_mysql_host";      //non so perchè nelle costanti c'erano i valori dell'xml .-.
    public static final String TAG_PORT = "db_mysql_port";
    public static final String TAG_DB = "db_mysql_name";

    public static final String TABLE_SACCHE = "BloodBags";
    public static final String COL_Serial = "serial";       //modificato nel database
    public static final String COL_GROUP = "Rh";           //modificato nel database

    //#################################################### DATABASE QUERIES ####################################################
    public static final String SQL_CREATE_DB = "CREATE DATABASE IF NOT EXISTS ";
    public static final String SQL_DROP_DB = "DROP DATABASE IF EXISTS ";
    public static final String SQL_CREATE_TABLE 	= "CREATE TABLE IF NOT EXISTS " + Constants.TABLE_SACCHE
            + "("
            + Constants.COL_Serial + " varchar(20) NOT NULL,"
            + Constants.COL_GROUP + " varchar(5) NOT NULL,"
            + " PRIMARY KEY (Serial)"
            + ")";
    
}
