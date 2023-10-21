package DAOCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class CommandGetEntry implements Command{
    @Autowired
    JdbcTemplate jdbcTemplate;
            private final String SQL="SELECT text FROM diary_entry where user_id=? and date_entry=?";
    @Override
    public AbstractCarrier execute(AbstractCarrier message) throws SQLException {
        message = getEntry(message);
return message;
    }
    private AbstractCarrier getEntry(AbstractCarrier message){

        message.setText(jdbcTemplate.queryForObject(SQL, String.class,message.getUser().getID(),message.getDate()));
        if (message.getText()==null){
            throw new RuntimeException("ввод неправильной даты" );
        }
        return message;
    }
}
