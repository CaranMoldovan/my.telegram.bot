package botlogick;


import java.time.LocalDate;


abstract class  TextController implements Comparable<TextController> {
    LocalDate date;
    String text;

    public TextController(String text) {//constructor
        this.date = LocalDate.now();
        this.text = text;
    }

    public LocalDate getDate() {//return date of create this text
        return date;
    }

    public String getText(LocalDate date) {// return this text if user input date of this text
        if (this.date.isEqual(date)){
            return text;
        }else{
            throw new  RuntimeException("ошибка ввода даты");
        }
    }
    public void setText(String newText){// replace text to new
        this.text=newText;
    }


    @Override
    public int compareTo(  TextController o) {
        if (date.isAfter(o.getDate()) ){
            return -1;
        }else if ((date.isBefore(o.getDate()))){
            return 1;
        }

        return 0;
    }
}
