package Command;

import botlogick.AbstractUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
@Component
public class CommandUpdater implements Command {
    @Autowired
    private JdbcTemplate jdbcTemplate;
private  String sql = "UPDATE users SET UserName = ? WHERE user_id = ?";


    @Override
    public void execute(AbstractUser user) throws SQLException {
update(user);
    }

    private void update(AbstractUser user){
        jdbcTemplate.update(sql,user.getName() ,user.getID());
    }
}
