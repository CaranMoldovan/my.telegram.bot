package Command;

import botlogick.AbstractUser;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class SimpleMessageFabric extends AbstractMessageFabric{

    @Override
    public AbstractCarrier createNewMessage(AbstractUser user, String text, LocalDate date) {
        return super.createNewMessage(user, text, date);
    }


    @Override
    public AbstractCarrier createNewMessage(AbstractUser user) {
        return super.createNewMessage(user);
    }

    @Override
    public AbstractCarrier createNewMessage(AbstractUser user, String text) {
        return super.createNewMessage(user, text);
    }
}
