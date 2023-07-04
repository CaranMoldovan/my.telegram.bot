package Config;

import botlogick.AbstractUserFabric;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import telegramWork.TelegramBot;


@Configuration

@PropertySource("botConfiguration.properties")

public class SpringConfig {
    @Bean
    public BotConfig botConfig(){
        return  new BotConfig();
    }
    @Bean
    public TelegramBot telegramBot(BotConfig botConfig){
        return new TelegramBot(botConfig);
    }
    @Bean
    public BotInitializer botInitializer (TelegramBot telegramBot) {
        return new BotInitializer();
    }
    @Bean
    public  DataBaseConfig dataBaseConfig (){
        return new DataBaseConfig();

    }



}

