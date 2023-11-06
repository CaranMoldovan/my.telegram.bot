package Command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
@Component
public class CommandHasRegisted implements Command {
    @Autowired
    JdbcTemplate jdbcTemplate;
    private final String SQl1 = "SELECT EXISTS(SELECT 1 FROM users WHERE user_id= ?)";
    private final String SQL2 = "SELECT EXISTS(SELECT 1 FROM waiting WHERE user_id = ?)";
    private final String SQL3 ="SELECT waiting FROM waiting WHERE user_id = ?";
    @Autowired
    @Qualifier("commandRegister")
    Command register;

    @Override
    public AbstractCarrier execute(AbstractCarrier message) throws SQLException {
    AbstractCarrier toReturn = hasReg(message);
    return  toReturn;
    }

    private AbstractCarrier hasReg(AbstractCarrier message) {
        Boolean userCount = jdbcTemplate.queryForObject(SQl1,Boolean.class,message.getUser().getID());
        System.out.println(userCount);
        // Проверяем наличие соответствующей записи в таблице "waiting_users"
        Boolean waitingCount = jdbcTemplate.queryForObject(SQL2,Boolean.class,message.getUser().getID());
        System.out.println(waitingCount);
        if (userCount == true && waitingCount ==true) {
            // Если и user_id, и соответствующая запись существуют, возвращаем true
            message.getUser().setUserWaiting(jdbcTemplate.queryForObject(SQL3,Integer.class,message.getUser().getID()));
            return message;
        }
            try {
                register.execute(message);
            } catch (SQLException e) {
                new SQLException("ошибка ввода в БД");
            }
            return message;
    }
}
