package seedu.duke.model.favorite;

import static seedu.duke.ui.Ui.printDupeMessage;

public class Fav {
    private String command;
    private String desc;

    public Fav(String command, String desc) {
        this.command = command;
        this.desc = desc;
    }

    public Boolean equals(Fav item, int index) {
        assert item != null : "Invalid user favourites!";
        if (this.command.equals(item.command) || this.desc.equals(item.desc)) {
            printDupeMessage(index, this.desc, this.command);
            return true;
        }
        return false;
    }

    public String getCommand() {
        return command;
    }

    public String getDesc() {
        return desc;
    }

    void changeDesc(String desc) {
        this.desc = desc;
    }

    public String toString() {
        return command + ": \"" + desc + "\"";
    }

}
