package seedu.duke.favorite;

import seedu.duke.model.favorite.Fav;

import java.util.ArrayList;

public class FavList {
    private static ArrayList<Fav> favList;

    public FavList() {
        favList = new ArrayList<>();
    }

    public static void addFav(Fav fav) {
        favList.add(fav);
    }

    public static void deleteFav(int index) {
    }

    public static void listFav() {
    }

    public static ArrayList<Fav> getList() {
        return favList;
    }

    public static boolean contains(Fav item) {
        int count = 0;
        for (Fav fav : favList) {
            count++;
            if (fav.equals(item,count)) {
                return true;
            }
        }
        return false;
    }

}
