package botlogick;

import java.time.LocalDate;

public class TextControllerFabric extends AbstractTextControllerFabric{
    @Override
    public TextController createNewTextController(LocalDate date, String text) {
        return super.createNewTextController(date, text);
    }
}
