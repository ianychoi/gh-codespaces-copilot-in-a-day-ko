package roadshow.demo.api.db;

import org.springframework.stereotype.Repository;

import java.util.List;

import roadshow.demo.api.db.MessageLogData;

@Repository
public interface LogDataRepository {

    List<MessageLogData> findAll();

    List<MessageLogData> findById(Long id);

    int save(MessageLogData messageLogData);

    int update(MessageLogData messageLogData);

    int deleteById(Long id);
}