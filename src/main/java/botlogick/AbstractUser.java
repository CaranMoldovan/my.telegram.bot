package botlogick;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
abstract class AbstractUser {
    private  String name;
    private long ID;
    private TextWorker diary = new Diary();
    @Autowired
   private ApplicationContext context;
   @Autowired
   private JdbcTemplate jdbcTemplate;


    public AbstractUser(String name, long ID) {
        this.name = name;
        this.ID = ID;

    }

    public String getName() {
        return name;
    }

    public long getID() {
        return ID;
    }

    public TextWorker getDiary() {
        return diary;
    }

}

