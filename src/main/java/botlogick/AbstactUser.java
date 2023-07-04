package botlogick;

abstract class AbstactUser {
    private  String name;
    private long ID;
    private TextWorker diary = new Diary();

    public AbstactUser(String name, long ID) {
        this.name = name;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public long getID() {
        return ID;
    }

    public TextWorker getDiary() {
        return diary;
    }

}

