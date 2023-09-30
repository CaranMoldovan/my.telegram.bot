package botlogick;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<AbstractUser> {
    @Override
    public AbstractUser mapRow(ResultSet resultSet, int i) throws SQLException {
        AbstractUserFabric abstractUserFabric = new SimpleUserFabric();
       return abstractUserFabric.createNewUser(resultSet.getString("username"),resultSet.getLong("user_id"));
    }
}
