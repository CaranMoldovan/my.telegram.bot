package botlogick;

import java.time.LocalDate;

public abstract  class AbstractTextControllerFabric {
    public TextController createNewTextController(LocalDate date, String text){
        TextController textController = new DiaryEntry(date, text);
        return  textController;
    }
}
