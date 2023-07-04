package botlogick;

import java.time.LocalDate;

public class Diary extends  TextWorker{
    LocalDate date;
    String text;
    @Override
    TextController giveAText(LocalDate date) {
        return super.giveAText(date);
    }

    @Override
    public void add(LocalDate date, String text) {
        super.add(date, text);
    }

    @Override
    public boolean remove(LocalDate date) {
        return super.remove(date);
    }

}
