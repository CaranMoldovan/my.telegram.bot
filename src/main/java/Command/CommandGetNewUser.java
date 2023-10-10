package Command;

import botlogick.SimpleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class CommandGetNewUser implements Command {
@Autowired
JdbcTemplate jdbcTemplate;
private final String SQl="\"SELECT u.username, w.waiting \" +\n" +
        "                         \"FROM  waiting_users w ON u.user_id = w.user_id \" +\n" +
        "                         \"WHERE u.user_id = ?\"";


    @Override
    public AbstractCarrier execute(AbstractCarrier message) throws SQLException {
        return null;
    }
    private AbstractCarrier returnUser(AbstractCarrier message){
    message.getUser().setUserWaiting(jdbcTemplate.queryForObject(SQl,
            new Object[]{message.getUser().getID()}, Integer.class));
        return message;


    }
    }

