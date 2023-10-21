package botlogick;

 public abstract class AbstractUserFabric {
    public AbstractUser createNewUser(String username, long ID){
        return new SimpleUser(username,ID);

    }
    }

