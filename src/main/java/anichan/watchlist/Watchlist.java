package anichan.watchlist;

import java.util.ArrayList;

public class Watchlist {
    private final String name;
    private final ArrayList<Integer> animeList;
    private static final String EMPTY_WATCHLIST_MESSAGE = "Uhh.. It's empty.. :(";

    public Watchlist(String name) {
        this.name = name;
        this.animeList = new ArrayList<>();
    }

    public Watchlist(String name, ArrayList<Integer> animeList) {
        this.name = name;
        this.animeList = animeList;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getAnimeList() {
        return animeList;
    }

    public void addAnimeToList(Integer animeIndex) {
        this.animeList.add(animeIndex);
    }
    
    public void removeAnimeFromList(int watchlistListIndex) {
        this.animeList.remove(watchlistListIndex);
    }
    
    public int getWatchlistSize() {
        return this.animeList.size();
    }
    
    public int getWatchlistListAnimeIndex(int watchlistListIndex) {
        return this.animeList.get(watchlistListIndex);
    }

    public String animeListToString() {
        StringBuilder sbAnimeList = new StringBuilder();
        if (animeList.size() == 0) {
            sbAnimeList.append(EMPTY_WATCHLIST_MESSAGE);
            sbAnimeList.append(System.lineSeparator());
        }

        for (int i = 0; i < animeList.size(); i++) {
            sbAnimeList.append(i + 1).append(". ");
            sbAnimeList.append(animeList.get(i));
            sbAnimeList.append(System.lineSeparator());
        }

        return sbAnimeList.toString();
    }

    @Override
    public String toString() {
        return name + System.lineSeparator() + animeListToString();
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        } else if (getClass().equals(otherObject.getClass())) {
            Watchlist otherWatchlist = (Watchlist) otherObject;
            return this.getName().equals(otherWatchlist.getName());
        }

        return false;
    }
}
