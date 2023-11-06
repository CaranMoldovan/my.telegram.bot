package botlogick;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class TextControllerFabric extends AbstractTextControllerFabric{
    @Override
    public TextController createNewTextController(LocalDate date, String text) {
        return super.createNewTextController(date, text);
    }
}
