package botlogick;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

abstract class TextWorker {
    private List<TextController> texts;
    private AbstractTextControllerFabric textFabric;


    public TextWorker() {
        texts=new ArrayList<>();
    }

    TextController giveAText(LocalDate date) {
        for (TextController text : texts) {
            if (text.getDate().isEqual(date)) {
                return text;

            }
        }
        throw new RuntimeException("Нет такой записи");
    }
    public void add(LocalDate date, String text) {
        texts.add(textFabric.createNewTextController(date, text));
    }

    public boolean remove(LocalDate date) {
       for (int i =0;i<texts.size();i++){
           if (texts.get(i).getDate().isEqual(date)){
               texts.remove(i);
               return true;
           }
       }
       return  false;
    }
}
