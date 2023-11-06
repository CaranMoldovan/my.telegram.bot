package Command;


import java.sql.SQLException;

public interface Command {
    public AbstractCarrier execute(AbstractCarrier message)throws SQLException;


}
