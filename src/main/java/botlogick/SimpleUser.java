package botlogick;

public class SimpleUser extends  AbstactUser{
    public SimpleUser(String name, long ID) {
        super(name, ID);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public long getID() {
        return super.getID();
    }

    @Override
    public TextWorker getDiary() {
        return super.getDiary();
    }
}
