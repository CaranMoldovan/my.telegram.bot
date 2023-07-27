import Config.BotInitializer;
import Config.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SpringApplicationDemo {
    public static void main(String[] args) throws TelegramApiException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        BotInitializer botInitializer = context.getBean(BotInitializer.class);
        botInitializer.init();
    }
}
