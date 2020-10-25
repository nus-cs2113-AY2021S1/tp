package seedu.duke.favorite;

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
        for (Fav fav : favList) {
            if (fav.equals(item)) {
                return true;
            }
        }
        return false;
    }
}
