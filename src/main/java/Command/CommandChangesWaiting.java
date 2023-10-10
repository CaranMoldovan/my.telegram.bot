package Command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
@Component
public class CommandChangesWaiting implements Command {
    private final String SQL="UPDATE waiting_users SET waiting = ? WHERE user_id = ?";
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public AbstractCarrier execute(AbstractCarrier message) throws SQLException {
       AbstractCarrier messageToInput =waiting(message);
        return messageToInput;
    }
    private AbstractCarrier waiting(AbstractCarrier message){
    jdbcTemplate.update(SQL,message.getUser().getID(),message.getUser().getUserWaiting());
    return message;
    }
}
