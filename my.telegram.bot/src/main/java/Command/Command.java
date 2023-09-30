package Command;

import botlogick.AbstractUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

public interface Command {
    public void execute(AbstractUser user)throws SQLException;


}
