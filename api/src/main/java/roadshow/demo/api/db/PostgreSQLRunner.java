package roadshow.demo.api.db;

import java.sql.Connection;
import java.sql.Statement;

import javax.annotation.processing.Generated;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

import roadshow.demo.api.db.MessageLogData;
import roadshow.demo.api.db.JDBCLogDataRepository;

import roadshow.demo.api.db.LogDataRepository;

@Component
public class PostgreSQLRunner implements ApplicationRunner {
    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (Connection connection = dataSource.getConnection()){
            System.out.println(dataSource.getClass());
            System.out.println(connection.getMetaData().getURL());
            System.out.println(connection.getMetaData().getUserName());

            Statement statement = connection.createStatement();
            String sql = "";

            sql = "CREATE SEQUENCE post_seq start 1 increment 1";
            // statement.executeUpdate(sql);

            sql = "CREATE TABLE message_request_log_data( " +
                            "ID INTEGER PRIMARY KEY DEFAULT nextval('post_seq') NOT NULL, " +
                            "IPADDRESS VARCHAR(255), " +
                            "REQUESTMESSAGE VARCHAR(255) )";
            // statement.executeUpdate(sql);
        }
        //jdbcTemplate.execute("INSERT INTO message_request_log_data(ipaddress, requestmessage) VALUES ('0.0.0.0', 'Test Request Message')");
    }

    @Autowired
    private LogDataRepository logDataRepository;

    @GetMapping
    public List<MessageLogData> findAll() {
        return logDataRepository.findAll();
    }

    @PostMapping
    public int save(@RequestBody MessageLogData messageLogData) {
        return logDataRepository.save(messageLogData);
    }

    @PutMapping
    public int update(@RequestBody MessageLogData messageLogData) {
        return logDataRepository.update(messageLogData);
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return logDataRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public List<MessageLogData> findById(@PathVariable Long id) {
        return logDataRepository.findById(id);
    }
}