package Command;

import org.aopalliance.reflect.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
@Component
public class CommandGetWaiting implements Command{
    @Autowired
    JdbcTemplate jdbcTemplate;
    private final String SQL ="SELECT waiting FROM waiting WHERE user_id = ?";

    @Override
    public AbstractCarrier execute(AbstractCarrier message) throws SQLException {
        return getWaiting(message);
    }
    private AbstractCarrier getWaiting(AbstractCarrier message){
        message.getUser().setUserWaiting(jdbcTemplate.queryForObject(SQL, Integer.class,message.getUser().getID()));
        System.out.println(message.getUser().getUserWaiting());
        return message;

    }
}
