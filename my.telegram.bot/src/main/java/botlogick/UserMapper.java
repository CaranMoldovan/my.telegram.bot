package botlogick;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<AbstactUser> {
    @Override
    public AbstactUser  mapRow(ResultSet resultSet, int i) throws SQLException {
        AbstractUserFabric abstractUserFabric = new UserFabric();
       return abstractUserFabric.createNewUser(resultSet.getString("username"),resultSet.getLong("user_id"));
    }
}
