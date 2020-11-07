package seedu.duke.model.favorite;

import static seedu.duke.ui.Ui.printDupeMessage;

public class Fav {
    /** Command saved.*/
    private String command;
    /** Description for the command.*/
    private String desc;

    public Fav(String command, String desc) {
        this.command = command;
        this.desc = desc;
    }

    /**
     * Returns a boolean indicating if command stored in item is the same
     * as the command stored in the current Fav object.
     *
     * @param item Fav object to be checked
     * @param index index of Fav object in FavList
     * @return true if command stored in item is the same as the command stored in this Fav object,
     *         else return false.
     */
    public Boolean equals(Fav item, int index) {
        if (this.command.toLowerCase().equals(item.command.toLowerCase())) {
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
