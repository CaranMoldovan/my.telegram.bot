package telegramWork;

import Command.*;
import Config.BotConfig;
import botlogick.AbstractTextControllerFabric;
import botlogick.AbstractUser;
import botlogick.AbstractUserFabric;
import botlogick.UsersList;
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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.BotOptions;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

import java.sql.SQLException;
import java.time.LocalDate;
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
    @Qualifier("textControllerFabric")
    private AbstractTextControllerFabric textControllerFabric;
    @Autowired
    private UsersList users;

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
       carrier = getWaiting(carrier);
       switch (carrier.getUser().getUserWaiting()){
           case 0:
            fistCommandWorking(messageText,chatId,carrier,update);
             break;
           case 1:
               carrier.setText(update.getMessage().getText());
               carrier.setDate(LocalDate.now());
               setAddNewEntry(carrier);
               break;
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
                    sendMessage(chatId, "команда пока в разработке");
                    break;
                case "/deletemydata":
                    sendMessage(chatId, "Вы точно хотите удалить все данные о себе");

                    //здесь должен быть код очистки моих записей
                    break;
                case "/getdays":
                    //здесь должен быть код получения дней своих записей
                    sendMessage(chatId, "команда пока в разработке");
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
        System.out.println(message.getText());
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


    private AbstractUser hasUser(Update update){
        if (users.binarySearch(update.getMessage().getChatId())!=null){
           return users.binarySearch(update.getMessage().getChatId());
        }
        AbstractUser user = userCreator(update.getMessage().getChat().getUserName(),update.getMessage().getChatId());
        users.add(user);
        return user ;
    }
    private AbstractUser createUser(Update update) {
   AbstractUser user= usersFabric.createNewUser(update.getMessage().getChat().getUserName(),update.getMessage().getChatId());
    users.add(user);
    return  user;
}




private  AbstractCarrier createCarrier(AbstractUser user) {
AbstractCarrier carrier =simpleMessageFabric.createNewMessage(user,null,null);
        return carrier;

}




}
