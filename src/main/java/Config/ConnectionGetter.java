package Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public class ConnectionGetter {
    @Autowired
    DataBaseConfig dataBaseConfig;
    Connection connection;

}
