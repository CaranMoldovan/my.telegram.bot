package DAOCommand;

import botlogick.AbstractUser;

import java.time.LocalDate;
import java.util.List;

abstract public class AbstractCarrier {
    private AbstractUser user;
    private LocalDate date;
    private String text;
    private List<LocalDate> dates;

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
    public List<LocalDate> getDates(){
        return dates;
    }
    public void setDates(List<LocalDate> dates){
        this.dates=dates;
    }
    public String datesToString(){
        StringBuilder sb = new StringBuilder();
        for (LocalDate date : dates) {
            sb.append(date.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
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
