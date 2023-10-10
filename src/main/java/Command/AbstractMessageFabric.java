package Command;

import botlogick.AbstractUser;

import java.time.LocalDate;

public abstract class AbstractMessageFabric {
    public  AbstractCarrier createNewMessage(AbstractUser user, String text, LocalDate date) {
    return new SimpleCarrier(user, date, text);
    }
    public AbstractCarrier createNewMessage(AbstractUser user){
        return new SimpleCarrier(user);
    }
    public AbstractCarrier createNewMessage(AbstractUser user,String text){
        return new SimpleCarrier(user,text);
    }
}
