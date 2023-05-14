package roadshow.demo.api.db;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import roadshow.demo.api.db.LogDataRepository;
import roadshow.demo.api.db.MessageLogData;

@Repository
public class JDBCLogDataRepository implements LogDataRepository {

    private static final Logger log = LoggerFactory.getLogger(JDBCLogDataRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<MessageLogData> findAll() {
        return jdbcTemplate.query(
            "SELECT id, ipaddress, requestmessage FROM message_request_log_data",
            (rs, rowNum) -> new MessageLogData(rs.getLong("id"), rs.getString("ipaddress"), rs.getString("requestmessage"))
        );
    }

    @Override
    public List<MessageLogData> findById(Long id) {
        return jdbcTemplate.query(
            "SELECT id, ipaddress, requestmessage FROM message_request_log_data WHERE id = ?",
            new Object[]{id},
            (rs, rowNum) -> new MessageLogData(rs.getLong("id"), rs.getString("ipaddress"), rs.getString("requestmessage"))
        );
    }

    @Override
    public int save(MessageLogData messageLogData) {
        return jdbcTemplate.update(
            "INSERT INTO message_request_log_data (ipaddress, requestmessage) VALUES (?, ?)",
            messageLogData.getIpAddress(), messageLogData.getRequestMessage()
        );
    }

    @Override
    public int update(MessageLogData messageLogData) {
        return jdbcTemplate.update(
            "UPDATE message_request_log_data SET ipaddress = ?, requestmessage = ? WHERE id = ?",
            messageLogData.getIpAddress(), messageLogData.getRequestMessage(), messageLogData.getId()
        );
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(
            "DELETE message_request_log_data WHERE id = ?",
            id
        );
    }
}
