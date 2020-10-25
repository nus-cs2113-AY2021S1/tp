package seedu.duke.favorite;

import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;

import java.util.ArrayList;

import static seedu.duke.ui.Ui.printClearFavMessage;
import static seedu.duke.ui.Ui.printFavList;

public class FavList {
    private static ArrayList<Fav> favList;

    public FavList() {
        favList = new ArrayList<>();
    }

    public static void addFav(Fav fav) {
    }

    public static void deleteFav(int index) {
    }

    public static void listFav() throws CustomException {
        if (favList.size() == 0) {
            throw new CustomException(ExceptionType.EMPTY_FAVLIST);
        }
        printFavList(favList);
    }

    public static void clearFav() {
        favList.clear();
        printClearFavMessage();
    }

    public static ArrayList<Fav> getList() {
        return favList;
    }
}
