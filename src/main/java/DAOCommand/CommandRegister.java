package DAOCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
@Component
public class CommandRegister implements Command{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final String SQL1 ="INSERT INTO users (user_id, username) VALUES (?, ?)";

    private final String SQL3="INSERT INTO waiting (user_id, waiting) VALUES (?, ?)";


    @Override
    public AbstractCarrier execute(AbstractCarrier message) throws SQLException {
        message.getUser().setUserWaiting(1);
       AbstractCarrier toReturn = RegisterUser(message);
        return toReturn;
    }

    private AbstractCarrier RegisterUser(AbstractCarrier message){
        jdbcTemplate.update(SQL1,message.getUser().getID(),message.getUser().getName() );
        jdbcTemplate.update(SQL3,message.getUser().getID(),message.getUser().getUserWaiting());

        return message;
    }
}
