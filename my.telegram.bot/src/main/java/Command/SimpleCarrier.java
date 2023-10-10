package Command;

import botlogick.AbstractUser;

import java.time.LocalDate;

public class SimpleCarrier extends AbstractCarrier {
    public SimpleCarrier(AbstractUser user) {
        super(user);
    }

    public SimpleCarrier(AbstractUser user, LocalDate date, String text) {
        super(user, date, text);
    }
    public  SimpleCarrier(AbstractUser user,String text){
        super(user,text);
    }

    @Override
    public AbstractUser getUser() {
        return super.getUser();
    }

    @Override
    public LocalDate getDate() {
        return super.getDate();
    }

    @Override
    public String getText() {
        return super.getText();
    }
}
