package Command;

import botlogick.AbstractUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.sql.SQLException;
@Component
public class CommandRegister implements Command{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String command="INSERT INTO users (user_id, UserName) VALUES (?, ?)";

    @Override
    public void execute(AbstractUser user) throws SQLException {
        RegisterUser(user);
    }

    private void RegisterUser(AbstractUser user){
        jdbcTemplate.update(command,user.getID(),user.getName() );

    }
}
