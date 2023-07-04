package Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component

public class DataBaseConfig {
    @Value("${db.home.name}")
    private String name;
    @Value("${db.home.password")
    private String password;
    @Value("${db.home.url")
    private String url;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }
}
