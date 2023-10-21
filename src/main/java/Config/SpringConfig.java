package Config;

import DAOCommand.*;
import botlogick.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import telegramWork.TelegramBot;

import javax.sql.DataSource;
import java.time.format.DateTimeFormatter;


@Configuration

@PropertySource("classpath:botConfiguration.properties")

public class SpringConfig {

    private final Environment environment;
    private final ApplicationContext applicationContext;

    @Autowired
    public SpringConfig(Environment environment, ApplicationContext applicationContext) {
        this.environment = environment;
        this.applicationContext = applicationContext;
    }

    @Bean
    public BotConfig botConfig() {
        return new BotConfig();
    }

    @Bean
    public TelegramBot telegramBot(BotConfig botConfig) {
        return new TelegramBot(botConfig);
    }

    @Bean
    public BotInitializer botInitializer(TelegramBot telegramBot) {
        return new BotInitializer();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("db.home.driver"));
        dataSource.setUrl(environment.getProperty("db.home.url"));
        dataSource.setUsername(environment.getProperty("db.home.name"));
        dataSource.setPassword(environment.getProperty("db.home.password"));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public AbstractUserFabric SimpleUserFabric() {
        return new SimpleUserFabric();
    }

    @Bean
    public Command commandRegister() {
        return new CommandRegister();
    }

    @Bean
    public Command commandUpdater() {
        return new CommandUpdater();
    }

    @Bean
    public AbstractTextControllerFabric textControllerFabric() {
        return new TextControllerFabric();
    }

    @Bean
    public UsersList usersList() {
        return new UsersList();
        }
        @Bean
    public Command addNewDiaryEntry(){
        return  new CommandAddNewDiaryEntry();
        }
        @Bean
    public  AbstractMessageFabric simpleMessageFabric(){
        return new SimpleMessageFabric();
        }
        @Bean
    public Command commandChangesWaiting(){
        return new CommandChangesWaiting();
        }
        @Bean
        public  Command commandHasRegisted(){
        return new CommandHasRegisted();
        }
        @Bean
    public Command updateWaiting(){
        return new CommandUpdateWaiting();
        }
        @Bean
    public Command getWaiting(){
        return new CommandGetWaiting();
    }
    @Bean
    public Command getEntry(){
        return new CommandGetEntry();
    }
    @Bean
    public DateTimeFormatter dateTimeFormatter(){
 return DateTimeFormatter.ofPattern("dd-MM-yyyy");

    }

    @Bean
    public Command getDays(){
        return new CommandGetDays();
    }
    @Bean
    public Command deleteUser(){
        return new  CommandDeleteUser();
    }
}

