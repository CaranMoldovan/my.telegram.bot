package Command;

import botlogick.AbstractUser;

import java.time.LocalDate;

abstract public class AbstractCarrier {
    private AbstractUser user;
    private LocalDate date;
    private String text;

    public AbstractCarrier(AbstractUser user) {
        this.user = user;
    }

    public AbstractCarrier(AbstractUser user, LocalDate date, String text) {
        this.user = user;
        this.date = date;
        this.text = text;
    }
    public AbstractCarrier(AbstractUser user, String text){
        this.user= user;
        this.text=text;
    }

    public AbstractUser getUser() {
        return user;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }
}
