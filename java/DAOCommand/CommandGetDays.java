package DAOCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


@Component
public class CommandGetDays implements Command{
    @Autowired
 JdbcTemplate jdbcTemplate;
private final String SQL="SELECT date_entry FROM diary_entry WHERE user_id = ?";
    @Override
    public AbstractCarrier execute(AbstractCarrier message) throws SQLException {
        message = getEntryesDate(message);
return message;
    }
    private AbstractCarrier getEntryesDate(AbstractCarrier carrier){
        List<LocalDate> dates=jdbcTemplate.query(SQL,new Object[]{carrier.getUser().getID()},new LocalDateMapper());
        carrier.setDates(dates);
        return carrier;
    }

}
