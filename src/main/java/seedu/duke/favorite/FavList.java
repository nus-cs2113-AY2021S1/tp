package seedu.duke.favorite;

import java.util.ArrayList;

public class FavList {
    private static ArrayList<Fav> favList;

    public FavList() {
        favList = new ArrayList<>();
    }

    public static void addFav(Fav fav) {
    }

    public static void deleteFav(int index) {
    }
    public static void listFav() {
        for (Fav f : favList) {
            System.out.println(f.getCommand() + "|" + f.getDesc());
        }
    }
    public static ArrayList<Fav> getList(){
        return favList;
    }
}
