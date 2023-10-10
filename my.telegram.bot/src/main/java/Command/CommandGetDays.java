package Command;

import java.sql.SQLException;

public class CommandGetDays implements Command{
    @Override
    public AbstractCarrier execute(AbstractCarrier message) throws SQLException {
return message;
    }

}
