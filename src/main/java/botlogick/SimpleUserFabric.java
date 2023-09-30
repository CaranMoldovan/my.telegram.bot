package botlogick;

import org.springframework.stereotype.Component;

@Component
public class SimpleUserFabric extends AbstractUserFabric  {
    @Override
    public AbstractUser createNewUser(String username, long ID) {

        return new SimpleUser(username,ID);
    }
}
