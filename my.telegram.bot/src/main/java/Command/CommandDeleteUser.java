package Command;

import java.sql.SQLException;

public class CommandDeleteUser implements Command{
    @Override
    public AbstractCarrier execute(AbstractCarrier message) throws SQLException {
return message;
    }
}
