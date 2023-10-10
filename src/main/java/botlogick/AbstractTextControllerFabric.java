package botlogick;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public abstract  class AbstractTextControllerFabric {
    public TextController createNewTextController(LocalDate date, String text){
        TextController textController = new DiaryEntry(date, text);
        return  textController;
    }
}
