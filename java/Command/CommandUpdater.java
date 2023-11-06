package Command;

import botlogick.AbstractUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
@Component
public class CommandUpdater implements Command {
    @Autowired
    private JdbcTemplate jdbcTemplate;
private  String sql = "UPDATE users SET username = ? WHERE user_id = ?";


    @Override
    public AbstractCarrier execute(AbstractCarrier message) throws SQLException {
update(message.getUser());
return message;
    }

    private void update(AbstractUser user){
        jdbcTemplate.update(sql,user.getName() ,user.getID());
    }


}
