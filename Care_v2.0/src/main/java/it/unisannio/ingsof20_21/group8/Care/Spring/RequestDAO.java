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

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "requests")
public class RequestDAO {
    @Id
    private String serial;

    private long timestamp;
    private String requestingNode;
    private String state;

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getRequestingNode() {
        return requestingNode;
    }

    public void setRequestingNode(String requestingNode) {
        this.requestingNode = requestingNode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
