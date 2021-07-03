/**
 * @author giuliano ranauro
 * Date: 30/06/2021 22:31
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package it.unisannio.CARE.model.report;

import java.util.Date;

public class UserReport {
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

    public UserReport(long total, long activeUsers, long inactiveUsers, long loggedLast24Hours, long blockedBySystem, long deleted, long administrators, long storeManagers, long officers) {
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
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(long activeUsers) {
        this.activeUsers = activeUsers;
    }

    public long getInactiveUsers() {
        return inactiveUsers;
    }

    public void setInactiveUsers(long inactiveUsers) {
        this.inactiveUsers = inactiveUsers;
    }

    public long getLoggedLast24Hours() {
        return loggedLast24Hours;
    }

    public void setLoggedLast24Hours(long loggedLast24Hours) {
        this.loggedLast24Hours = loggedLast24Hours;
    }

    public long getBlockedBySystem() {
        return blockedBySystem;
    }

    public void setBlockedBySystem(long blockedBySystem) {
        this.blockedBySystem = blockedBySystem;
    }

    public long getDeleted() {
        return deleted;
    }

    public void setDeleted(long deleted) {
        this.deleted = deleted;
    }

    public long getAdministrators() {
        return administrators;
    }

    public void setAdministrators(long administrators) {
        this.administrators = administrators;
    }

    public long getStoreManagers() {
        return storeManagers;
    }

    public void setStoreManagers(long storeManagers) {
        this.storeManagers = storeManagers;
    }

    public long getOfficers() {
        return officers;
    }

    public void setOfficers(long officers) {
        this.officers = officers;
    }
}
