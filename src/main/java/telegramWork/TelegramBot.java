package telegramWork;

import DAOCommand.*;
import Config.BotConfig;
import botlogick.AbstractUser;
import botlogick.AbstractUserFabric;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
    @Autowired
  private BotConfig botConfig;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("commandRegister")
    private  Command register;
    @Autowired
    @Qualifier("commandUpdater")
    private Command updater;
    @Autowired
    private AbstractUserFabric usersFabric;

    @Autowired
    @Qualifier("addNewDiaryEntry")
    private Command addNewEntry ;
    @Autowired
    @Qualifier("simpleMessageFabric")
    private AbstractMessageFabric simpleMessageFabric;
    @Autowired
    @Qualifier("commandHasRegisted")
    private Command hasRegisted;
@Autowired
@Qualifier("updateWaiting")
private Command updateWaiting;
@Autowired
@Qualifier("getWaiting")
private Command getWaiting;
@Autowired
@Qualifier("getEntry")
private Command getEntry;
@Autowired
private DateTimeFormatter formatter;
@Autowired
@Qualifier("getDays")
private Command getGetEntryDay;
@Autowired
    @Qualifier("deleteUser")
    private Command deleteUser;


    public TelegramBot(BotConfig botConfig) {
        this.botConfig = botConfig;
        List<BotCommand>botCommandList=new ArrayList<>();
        botCommandList.add(new BotCommand("/creatediaryentry","Create a new diary entry"));
        botCommandList.add(new BotCommand("/getdiaryentry","Retrieving a day entry by date"));
        botCommandList.add(new BotCommand("/deletemydata","Clearing all my records"));
        botCommandList.add(new BotCommand("/getdays","Get the dates of my posts"));
        botCommandList.add(new BotCommand("/help","Information about bot commands"));
        botCommandList.add(new BotCommand("/settings","Settings")  );
        try {
            SetMyCommands setMyCommands = new SetMyCommands(botCommandList,new BotCommandScopeDefault(),null     );
            this.execute(setMyCommands);
        }catch (TelegramApiException e){
            System.out.println("Doesn't create command page"+ e.getMessage());//далее удалить
            log.error("Error settings bots command"+ e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }
private static final String  HELP_TEXT="Этот бот создан для ведения дневника в удобной форме." + "\n" +
        "Введите /start для того что бы увидеть вновь приветственное сообщение."+"\n"+
        "Введите /creatediaryentry для того что бы ввести новую запись в дневник."+"\n"+
        "Введите /getdiaryentry для получения своих записей"+"\n"+
        "Введите /getdays для того что бы получить даты собственных записей"+"\n"+
        "Введите /deletemydata для очистки своих записей."
        ;
    private static final String CREATE_DIARY_ENTRY_TEXT="Введите текст вашей новой записи";

    @Override
    public void onUpdateReceived(Update update) {
        String messageText = null;//метод принятия сообщений
    if (update.hasMessage()&&update.getMessage().hasText()){
       messageText  =update.getMessage().getText();}

        long chatId=update.getMessage().getChatId();
        AbstractUser user =createUser(update);
        AbstractCarrier carrier = createCarrier(user);
        hasRegisted(carrier);
       carrier = getWaiting(carrier);

       switch (carrier.getUser().getUserWaiting()){
           case 0:
            fistCommandWorking(messageText,chatId,carrier,update);
             break;
           case 1:
               carrier.setText(update.getMessage().getText());
               carrier.setDate(LocalDate.now());
               setAddNewEntry(carrier);
               carrier.getUser().setUserWaiting(0);
               updateWaiting(carrier);
               break;
           case 2:
               carrier.setDate(LocalDate.parse(messageText,formatter));
               getEntry(carrier);
               sendMessage(carrier.getUser().getID(),carrier.getText());
               carrier.getUser().setUserWaiting(0);
               updateWaiting(carrier);
               break;
           case 3:
               carrier.setText(messageText);
               deleteUser(carrier);

           }
            }


    private void deleteUser(AbstractCarrier carrier) {
        switch (carrier.getText()){
            case "/yes":
                try {
                    deleteUser.execute(carrier);
                } catch (SQLException e) {
                    new SQLException();
                }
                break;
                case "/no":
                carrier.getUser().setUserWaiting(0);
                updateWaiting(carrier);
                break;
            default:
                sendMessage(carrier.getUser().getID(),"Ошибка ввода команды");
        }


    }

    private void fistCommandWorking(String messageText,long chatId, AbstractCarrier carrier,Update update){
            switch (messageText) {
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getUserName());
                    hasRegisted(carrier);
                    break;
                case "/help":
                    sendMessage(chatId, HELP_TEXT);
                    break;
                case "/creatediaryentry":
                    sendMessage(chatId, "команда пока в разработке");
                    carrier.getUser().setUserWaiting(1);
                    updateWaiting(carrier);
                    break;
                case "/getdiaryentry":
                    carrier.getUser().setUserWaiting(2);
                    updateWaiting(carrier);
                    sendMessage(carrier.getUser().getID(),"Введите дату своего сообщения в формате dd-mm-yyyy");
                    break;
                case "/deletemydata":
                    sendMessage(chatId, "Вы точно хотите удалить все данные о себе");
                    carrier.getUser().setUserWaiting(3);
                    updateWaiting(carrier);
                    //здесь должен быть код очистки моих записей
                    break;
                case "/getdays":
                    //здесь должен быть код получения дней своих записей
                    carrier = getEntryDays(carrier);
                    sendMessage(carrier.getUser().getID(),carrier.datesToString());
                    break;

                default:
                    sendMessage(chatId, "комманда пока не поддерживается");
            }

        }
    


    private void setAddNewEntry(AbstractCarrier message){
        try {
            addNewEntry.execute(message);
        } catch (SQLException e) {
            new SQLException();
        }
    }



    private void startCommandReceived(long chatId, String name ){
        String answer ="Приеет,"+ name+" добро пожаловать в мой телеграмм бот-дневник.";
        sendMessage(chatId,answer);
    }

    private void sendMessage(long chatId, String textToSend){//метод отправки текстов
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        try {
            execute(message);

        }catch (TelegramApiException e){
                new TelegramApiException();
        }

    }
    private AbstractCarrier getWaiting(AbstractCarrier carrier){
        try {
            getWaiting.execute(carrier);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carrier;
    }

    private AbstractCarrier updateWaiting(AbstractCarrier message){
        try {
         return   updateWaiting.execute(message);
        } catch (SQLException e) {
new  SQLException();
        }
        return null;
    }


    private void hasRegisted(AbstractCarrier message)  {
        try {
            hasRegisted.execute(message);
        } catch (SQLException e) {
            new SQLException();
        }
    }

    private AbstractUser userCreator(String name, long id){
        return usersFabric.createNewUser(name, id);
    }



    private AbstractUser createUser(Update update) {
   AbstractUser user= usersFabric.createNewUser(update.getMessage().getChat().getUserName(),update.getMessage().getChatId());
   return  user;
}




private  AbstractCarrier createCarrier(AbstractUser user) {
AbstractCarrier carrier =simpleMessageFabric.createNewMessage(user,null,null);
        return carrier;

}
private AbstractCarrier getEntry(AbstractCarrier carrier){
    try {
     carrier = getEntry.execute(carrier);
    } catch (SQLException e) {
        new SQLException();
    }
    return carrier;

}
private AbstractCarrier getEntryDays(AbstractCarrier carrier){
    try {
        getGetEntryDay.execute(carrier);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return carrier;
}




}
