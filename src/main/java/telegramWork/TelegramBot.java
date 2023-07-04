package telegramWork;

import Config.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
    @Autowired
  private BotConfig botConfig;

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
public static final String  HELP_TEXT="Этот бот создан для ведения дневника в удобной форме." + "\n" +
        "Введите /start для того что бы увидеть вновь приветственное сообщение."+"\n"+
        "Введите /creatediaryentry для того что бы ввести новую запись в дневник."+"\n"+
        "Введите /getdiaryentry для получения своих записей"+"\n"+
        "Введите /getdays для того что бы получить даты собственных записей"+"\n"+
        "Введите /deletemydata для очистки своих записей."
        ;

    @Override
    public void onUpdateReceived(Update update) {//метод принятия сообщений
    if (update.hasMessage()&&update.getMessage().hasText()){
        String messageText =update.getMessage().getText();
        long chatId=update.getMessage().getChatId();
        switch (messageText){
            case "/start":
                startCommandReceived(chatId, update.getMessage().getChat().getUserName());
                break;
            case "/help":
                sendMessage(chatId,HELP_TEXT);
                break;
            case"/creatediaryentry":
                sendMessage(chatId,"команда пока в разработке");
                //здесь должен быть код логики вставки нового сообщения
                break;
            case "/getdiaryentry":
                sendMessage(chatId,"команда пока в разработке");
                break;
            case "/deletemydata":
                sendMessage(chatId,"команда пока в разработке");
                //здесь должен быть код очистки моих записей
                break;
            case "/getdays":
                //здесь должен быть код получения дней своих записей
                sendMessage(chatId,"команда пока в разработке");
                break;

            default:
                sendMessage(chatId,"комманда пока не поддерживается");

        }
    }
    }
    private void startCommandReceived(long chatId, String name ){
        String answer ="Привет,"+ name+" добро пожаловать в мой телеграмм бот-дневник.";
        sendMessage(chatId,answer);
    }
    private void sendMessage(long chatId, String textToSend){//метод отправки текстов
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        try {
            execute(message);

        }catch (TelegramApiException e){

        }

    }
}
