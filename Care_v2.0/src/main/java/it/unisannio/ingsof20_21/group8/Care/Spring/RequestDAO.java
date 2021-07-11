/**
 * @author giuliano ranauro
 * Date: 10/07/2021 18:17
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package it.unisannio.ingsof20_21.group8.Care.Spring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class is used to model the p2p requests
 */
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "requests")
public class RequestDAO {
    @Id
    private String serial;

    private long timestamp;
    private String requestingNode;
    private String state;

    /**
     * @return the request serial referencing the blood bag
     */
    public String getSerial() {
        return serial;
    }

    /**
     * @param serial the blood bag serial
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    /**
     * @return the request timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the request timestamp
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the requesting node identifier
     */
    public String getRequestingNode() {
        return requestingNode;
    }

    /**
     * @param requestingNode the requesting node identifier
     */
    public void setRequestingNode(String requestingNode) {
        this.requestingNode = requestingNode;
    }

    /**
     * @return the request state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the request state
     */
    public void setState(String state) {
        this.state = state;
    }
}
