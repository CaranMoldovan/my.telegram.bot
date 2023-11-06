package botlogick;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

abstract class AbstactUser {
    private  String name;
    private long ID;
    private TextWorker diary = new Diary();
   private ApplicationContext context;

    public AbstactUser(String name, long ID) {
        this.name = name;
        this.ID = ID;
        context = new AnnotationConfigApplicationContext();

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

