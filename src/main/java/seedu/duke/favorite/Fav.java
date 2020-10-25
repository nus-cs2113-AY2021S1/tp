package seedu.duke.favorite;

public class Fav {
    private String command;
    private String desc;

    public Fav(String command, String desc) {
        this.command = command;
        this.desc = desc;
    }

    public Boolean equals(Fav item) {
        return this.command.equals(item.command) && this.desc.equals(item.desc);
    }

    public String getCommand() {
        return command;
    }

    public String getDesc() {
        return desc;
    }

}
