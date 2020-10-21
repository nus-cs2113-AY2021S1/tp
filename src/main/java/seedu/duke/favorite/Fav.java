package seedu.duke.favorite;

public class Fav {
    private String command;
    private String desc;

    public Fav(String command, String desc) {
        this.command = command;
        this.desc = desc;
    }
    public String getCommand() {
        return command;
    }
    public String getDesc() {
        return desc;
    }

}
