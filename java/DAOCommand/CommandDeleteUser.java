package DAOCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
@Component
public class CommandDeleteUser implements Command{
    @Autowired
    JdbcTemplate jdbcTemplate;
    private final String DELETE_WAITING ="DELETE From waiting WHERE user_id=?";
    private final String DELETE_ENTRYES ="DELETE from diary_entry WHERE user_id=?";
    private final String DELETE_USER="DELETE FROM users WHERE user_id=? ";
    @Override
    public AbstractCarrier execute(AbstractCarrier message) throws SQLException {
return deleteUser(message);
    }
    private AbstractCarrier deleteUser(AbstractCarrier carrier){
        jdbcTemplate.update(DELETE_ENTRYES,carrier.getUser().getID());
        jdbcTemplate.update(DELETE_WAITING,carrier.getUser().getID() );
        jdbcTemplate.update(DELETE_USER,carrier.getUser().getID()   );
        return carrier;
    }
}
