package seedu.duke;

import java.util.ArrayList;

import static seedu.duke.ui.Ui.addFavMessage;

public class Favourites {

    private static ArrayList<UserFavourite> favCommands = new ArrayList<>();

    public static void addFav(String inputCommand, String description) {
        UserFavourite item;

        if (description.equals(" ")) {
            item = new UserFavourite(inputCommand, inputCommand);
        } else {
            item = new UserFavourite(inputCommand, description);
        }
        favCommands.add(item);
        addFavMessage(inputCommand);
    }

    public static void delFav(int index) {
        favCommands.remove(index + 1);
    }

    public static ArrayList<UserFavourite> getFavCommands() {
        return favCommands;
    }

}
