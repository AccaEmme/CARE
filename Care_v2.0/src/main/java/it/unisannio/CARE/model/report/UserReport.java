/**
 * @author giuliano ranauro
 * Date: 30/06/2021 22:31
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package it.unisannio.CARE.model.report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
/**
 * Class that contains all methods for user reporting
 */
import java.util.Date;

import it.unisannio.CARE.model.util.Constants;

public class UserReport implements Reportable{
    private long total;
    private long activeUsers;
    private long inactiveUsers;
    private long loggedLast24Hours;
    private long blockedBySystem;
    private long deleted;

    private long administrators;
    private long storeManagers;
    private long officers;

    private long timestamp;
    
    private String json;

    /**
      * Constructor method of the UseReport class
      * @param total the total number of users
      * @param activeUsers the total number of active users
      * @param inactiveUsers the total number of inactive users
      * @param loggedLast24Hours the total number of users logged in the last 24h
      * @param blockedBySystem the total number of users blocked by the system
      * @param deleted the deleted users
      * @param administrators the administrators
      * @param storeManagers the storemanagers
      * @param officers the officers
     */
    
    public UserReport(long total, long activeUsers, long inactiveUsers, long loggedLast24Hours, long blockedBySystem, long deleted, 
    		long administrators, long storeManagers, long officers) {
        this.total = total;
        this.activeUsers = activeUsers;
        this.inactiveUsers = inactiveUsers;
        this.loggedLast24Hours = loggedLast24Hours;
        this.blockedBySystem = blockedBySystem;
        this.deleted = deleted;
        this.administrators = administrators;
        this.storeManagers = storeManagers;
        this.officers = officers;
        this.timestamp = new Date().getTime();
        
        this.json=toJson(total,activeUsers,  inactiveUsers,loggedLast24Hours,
        		blockedBySystem, 
        		deleted,  administrators,storeManagers, officers);
    }
    public String toString() {
        return "UserReport{" 
                +"activeUsers=" 				+ activeUsers
                +", inactiveUsers=" 		+ inactiveUsers 
                +", loggedLast24Hours=" 				+loggedLast24Hours
                +", blockedBySystem=" 		+ blockedBySystem
                +", deleted=" 			+ deleted 
                +", administrators=" 				+ administrators
                +", storeManagers=" 				+ storeManagers
                +", officers=" 				+ officers 
                +'}';
    }
    
    public String toJson(long total, long activeUsers, long inactiveUsers, long loggedLast24Hours, long blockedBySystem, long deleted, 
    		long administrators, long storeManagers, long officers) {
    
        		return "UserReport{" 
                +"activeUsers=" 				+ activeUsers
                +", inactiveUsers=" 		+ inactiveUsers 
                +", loggedLast24Hours=" 				+loggedLast24Hours
                +", blockedBySystem=" 		+ blockedBySystem
                +", deleted=" 			+ deleted 
                +", administrators=" 				+ administrators
                +", storeManagers=" 				+ storeManagers
                +", officers=" 				+ officers 
                +'}';
    }
    public void saveReport() throws IOException   {
        FileWriter fw = new FileWriter(Constants.USER_REPORT_PATH_JSON);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(this.json);
        bw.newLine();
        bw.close();

        fw = new FileWriter(Constants.USER_REPORT_PATH_CSV);
        bw = new BufferedWriter(fw);
        bw.write(this.getCSV(true));
        bw.newLine();
        bw.close();

        fw = new FileWriter(Constants.USER_REPORT_PATH_TXT);
        bw = new BufferedWriter(fw);
        bw.write(this.toString());
        bw.newLine();
        bw.close();
    }
    
    public String getCSV(Boolean header){
        StringBuilder sb = new StringBuilder();
        if (header)
        sb.append("total,activeUsers,inactiveUsers,loggedLast24Hours,blockedBySystem,deleted,administrators,storeManagers,officers,timestamp"+"\n");
        sb.append(this.total+",");
        sb.append(this.activeUsers+",");
        sb.append(this.activeUsers+",");
        sb.append(this.loggedLast24Hours+",");
        sb.append(this.blockedBySystem+",");
        sb.append(this.deleted+",");
        sb.append(this.administrators+",");
        sb.append(this.storeManagers+",");
        sb.append(this.officers+",");
        sb.append(this.timestamp+",");

        return sb.toString();
    }
public void print(PrintStream ps) {
    	
    	ps.println("Utenti totali: " + total + ";\n" +
    			"activeUsers: " + activeUsers + ";\n" +
    			"inactiveUsers: " + inactiveUsers + ";\n" +
    			"loggedLast24Hours: " + loggedLast24Hours + ";\n" +
    			"Utentibloccati dal sistema: " +blockedBySystem + ";\n" +
    			"Utenti eliminati: " + deleted + ";\n" +
    			"Administrator: " + administrators + ";\n" +
    			"StoreManager: " + storeManagers + ";\n" +
    			"Officer: " + officers + ";\n" +
    			"timestamps: " + timestamp + ";\n" 
    			);
    }

    /**
      * GET method to get total users
      * @return returns the total of users
     */
    public long getTotal() {
        return total;
    }

    /**
      * SET method to enter the total of users
      * @param total variable for the amount of users
     */
    public void setTotal(long total) {
        this.total = total;
    }

    /**
      * GET method to get the amount of connected users
      * @return returns the quantity in long format of the connected users
     */
    public long getActiveUsers() {
        return activeUsers;
    }

    /**
      * SET method to enter the amount of active users
      * @param activeUsers variable to insert active users
     */
    public void setActiveUsers(long activeUsers) {
        this.activeUsers = activeUsers;
    }

    /**
      * GET method to get inactive users
      * @return returns inactive users in long format
     */
    public long getInactiveUsers() {
        return inactiveUsers;
    }

    /**
      * SET method to enter the amount of inactive users
      * @param inactiveUsers variable to insert the amount of inactive users
     */
    public void setInactiveUsers(long inactiveUsers) {
        this.inactiveUsers = inactiveUsers;
    }

    /**
      * GET method to obtain the amount of users logged in in the last 24H
      * @return returns the amount of users logged in the last 24H
     */
    public long getLoggedLast24Hours() {
        return loggedLast24Hours;
    }

    /**
      * SET method to enter the amount of users logged in in the last 24 H
      * @param loggedLast24Hours Variable to enter the amount of users logged in the last 24H
     */
    public void setLoggedLast24Hours(long loggedLast24Hours) {
        this.loggedLast24Hours = loggedLast24Hours;
    }

    /**
      * Get method to get all blocked users from the system
      * @return returns all users locked out of the system
     */
    public long getBlockedBySystem() {
        return blockedBySystem;
    }

    /**
      * SET method to enter all users blocked by the system
      * @param blockedBySystem variable to define all users blocked by the system
     */
    public void setBlockedBySystem(long blockedBySystem) {
        this.blockedBySystem = blockedBySystem;
    }

    /**
      * GET method to get all deleted users
      * @return returns the amount of deleted users in long format
     */
    public long getDeleted() {
        return deleted;
    }

    /**
      * SET method to enter the amount of deleted users
      * @param deleted variable to insert the amount of deleted users
     */
    public void setDeleted(long deleted) {
        this.deleted = deleted;
    }

    /**
      * Method to get the amount of administrators
      * @return returns the quantity of administrators in long format
     */
    public long getAdministrators() {
        return administrators;
    }

    /**
      * SET method to enter the amount of system administrators
      * @param administrators Enter the amount of administrators
     */
    public void setAdministrators(long administrators) {
        this.administrators = administrators;
    }

    /**
      * GET method to get the store manager quantity
      * @return returns the amount of stormanager
     */
    public long getStoreManagers() {
        return storeManagers;
    }

    /**
      * SET method to enter the amount of the system's store manager
      * @param storeManagers quantity in long format of the store managers
     */
    public void setStoreManagers(long storeManagers) {
        this.storeManagers = storeManagers;
    }

    /**
      * GET method to get the amount of Officer of the system
      * @return returns the system officer quantity
     */
    public long getOfficers() {
        return officers;
    }

    /**
      * Set method to enter the quantity of officer
      * @param officers quantity of officer
     */
    public void setOfficers(long officers) {
        this.officers = officers;
    }
}
