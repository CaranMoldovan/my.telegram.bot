package DAOCommand;

import java.sql.SQLException;

public class CommandDeleteEntry implements Command{
    @Override
    public AbstractCarrier execute(AbstractCarrier message) throws SQLException {
return  message;
    }
}
