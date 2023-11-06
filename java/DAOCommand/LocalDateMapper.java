package DAOCommand;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class LocalDateMapper implements RowMapper<LocalDate> {
    @Override
    public LocalDate mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getDate("date_entry").toLocalDate();
    }
}
