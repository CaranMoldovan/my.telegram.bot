package Command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

public class CommandAddNewDiaryEntry implements Command {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String command= "INSERT INTO diary_entry (user_id, entry_text, время) VALUES (?, ?, ?)";
    @Override
    public AbstractCarrier execute(AbstractCarrier message) throws SQLException {
       AbstractCarrier toReturn = inputNewDiary(message);
        return toReturn;
    }
private  AbstractCarrier inputNewDiary(AbstractCarrier message){
        jdbcTemplate.update(command,message.getUser().getID(),message.getText(),message.getDate());
        return message;
}



}
