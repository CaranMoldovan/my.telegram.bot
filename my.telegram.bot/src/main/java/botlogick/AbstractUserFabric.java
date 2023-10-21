package botlogick;

 public abstract class AbstractUserFabric {
    public AbstractUser createNewUser(String username, long ID){
        AbstractUser user= new SimpleUser(username,ID);
        return user;
    }

    }

