package botlogick;

import java.time.LocalDate;

public class DiaryEntry extends TextController implements Comparable<TextController>  {
    public DiaryEntry(LocalDate date, String text) {
        super(text);
    }

    @Override
    public LocalDate getDate() {
        return super.getDate();
    }

    @Override
    public String getText(LocalDate date) {
        return super.getText(date);
    }

    @Override
    public void setText(String newText) {
        super.setText(newText);
    }

    @Override
    public int compareTo(TextController o) {
        return super.compareTo(o);
    }
}
