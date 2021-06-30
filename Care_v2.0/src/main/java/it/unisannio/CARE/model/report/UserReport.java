/**
 * @author giuliano ranauro
 * Date: 30/06/2021 22:31
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package it.unisannio.CARE.model.report;

public class UserReport {
    private long total;
    private long activeUsers;
    private long inactiveUsers;
    private long loggedLast24Hours;

    private long administrators;
    private long storeManagers;
    private long officers;

    public UserReport(long total, long activeUsers, long inactiveUsers, long loggedLast24Hours, long administrators, long storeManagers, long officers) {
        this.total = total;
        this.activeUsers = activeUsers;
        this.inactiveUsers = inactiveUsers;
        this.loggedLast24Hours = loggedLast24Hours;
        this.administrators = administrators;
        this.storeManagers = storeManagers;
        this.officers = officers;
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

    public long getDeactivatedUsers() {
        return inactiveUsers;
    }

    public void setDeactivatedUsers(long inactiveUsers) {
        this.inactiveUsers = inactiveUsers;
    }

    public long getLoggedLast24Hours() {
        return loggedLast24Hours;
    }

    public void setLoggedLast24Hours(long loggedLast24Hours) {
        this.loggedLast24Hours = loggedLast24Hours;
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
