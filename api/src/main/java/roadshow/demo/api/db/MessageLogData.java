package roadshow.demo.api.db;

import javax.annotation.processing.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="message_request_log_data")
public class MessageLogData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ipAddress;
    private String requestMessage;

    // 생성자를 만들꺼야
    public MessageLogData() {
        super();
    }

    public MessageLogData(Long id, String ipAddress, String requestMessage) {
        super();

        this.id = id;
        this.ipAddress = ipAddress;
        this.requestMessage = requestMessage;
    }

    // getter, setter를 만들꺼야
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    @Override
    public String toString() {
        return "MessageLogDB [id=" + id + ", ipAddress=" + ipAddress + ", requestMessage=" + requestMessage + "]";
    }

}