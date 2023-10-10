package Command;

import java.sql.SQLException;

public class CommandGetEntry implements Command{
    @Override
    public AbstractCarrier execute(AbstractCarrier message) throws SQLException {
return message;
    }
}
