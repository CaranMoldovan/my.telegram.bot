package botlogick;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


public class SimpleUser extends AbstractUser implements Comparable<AbstractUser> {

    public SimpleUser(String name, long ID) {
        super(name, ID);
    }

    @Override
    public int getUserWaiting() {
        return super.getUserWaiting();
    }

    @Override
    public void setUserWaiting(Integer userWaiting) {
        super.setUserWaiting(userWaiting);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public long getID() {
        return super.getID();
    }

    @Override
    public TextWorker getDiary() {
        return super.getDiary();
    }

    @Override
    public int compareTo(@NotNull AbstractUser o) {
        return super.compareTo(o);
    }
}
