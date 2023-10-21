package Repository;

import botlogick.AbstractUser;
import botlogick.SimpleUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public class UserRepository  {
    private List<SimpleUser> userList;

    public UserRepository(List<SimpleUser> userList) {
        this.userList = userList;
    }
    
}
