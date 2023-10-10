package Command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
@Component
public class CommandUpdateWaiting implements Command{
    @Autowired
    JdbcTemplate jdbcTemplate;
    private final String SQL= "UPDATE waiting SET waiting =? WHERE user_id=?";
    @Override
    public AbstractCarrier execute(AbstractCarrier message) throws SQLException {
        AbstractCarrier toReturn = updateWaiting(message);
        return toReturn;
    }
    private AbstractCarrier updateWaiting(AbstractCarrier message){
        jdbcTemplate.update(SQL,message.getUser().getUserWaiting(),message.getUser().getUserWaiting());
        return message;
    }
}
