package botlogick;

import org.springframework.stereotype.Component;

@Component
public class UserFabric extends AbstractUserFabric  {
    @Override
    public AbstactUser createNewUser(String username, long ID) {
        return super.createNewUser(username, ID);
    }
}
