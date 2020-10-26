package seedu.duke.favorite;

import seedu.duke.model.favorite.Fav;

import java.util.ArrayList;

public class FavList {
    private static ArrayList<Fav> favList;

    public FavList() {
        favList = new ArrayList<>();
    }

    public static void addFav(Fav fav) {
    }

    public static void deleteFav(int index) {
        favList.remove(index + 1);
    }

    public static void listFav() {
    }

    public static ArrayList<Fav> getList() {
        return favList;
    }

    public static int getSize() {
        return favList.size();
    }
}
