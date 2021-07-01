/**
 * @author giuliano ranauro
 * Date: 01/07/2021 11:44
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package it.unisannio.ingsof20_21.group8.Care.Spring;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "logs")
public class LoggerBean {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idLog;

    private long currentTimeStamp;
    private String currentUserEmail;
    private String currentUserUsername;
    private String fromClass;           //the calling class
    private String result;              //the response of the op

    public LoggerBean(String currentUserEmail, String currentUserUsername, String fromClass, String result) {
        this.currentTimeStamp = new Date().getTime();
        this.currentUserEmail = currentUserEmail;
        this.currentUserUsername = currentUserUsername;
        this.fromClass = fromClass;
        this.result = result;
    }

    public long getCurrentTimeStamp() {
        return currentTimeStamp;
    }

    public void setCurrentTimeStamp(long currentTimeStamp) {
        this.currentTimeStamp = currentTimeStamp;
    }

    public String getCurrentUserEmail() {
        return currentUserEmail;
    }

    public void setCurrentUserEmail(String currentUserEmail) {
        this.currentUserEmail = currentUserEmail;
    }

    public String getCurrentUserUsername() {
        return currentUserUsername;
    }

    public void setCurrentUserUsername(String currentUserUsername) {
        this.currentUserUsername = currentUserUsername;
    }

    public String getFromClass() {
        return fromClass;
    }

    public void setFromClass(String fromClass) {
        this.fromClass = fromClass;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
