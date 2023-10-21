package botlogick;

import DAOCommand.Command;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class AbstractUser implements Comparable<AbstractUser> {
    private  String name;
    private long ID;
    private TextWorker diary = new Diary();
    private Integer userWaiting;
    @Autowired
    @Qualifier("commandChangesWaiting")
    Command changesWaiting;

    public AbstractUser(String name, long ID) {
        this.name = name;
        this.ID = ID;
        this.userWaiting=0;

    }

    public int getUserWaiting() {
        return userWaiting;
    }

    public void setUserWaiting(Integer userWaiting) {
        this.userWaiting = userWaiting;

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

    @Override
    public int compareTo(@NotNull AbstractUser o) {
        if (ID>o.getID()){
            return 1;
        }else if (ID<o.getID()) {
            return -1;
        }
        return 0;
    }

}

