package DAOCommand;

import java.sql.SQLException;

public class CommandSetting implements Command {


    @Override
    public AbstractCarrier execute(AbstractCarrier message) throws SQLException {
return message;
    }
}
