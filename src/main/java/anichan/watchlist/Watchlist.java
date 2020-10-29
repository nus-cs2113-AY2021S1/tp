package anichan.watchlist;

import java.util.ArrayList;

/**
 * Represents a named list that contains the list of anime the user is tracking.
 */
public class Watchlist {
    private final String name;
    private final ArrayList<Integer> animeList;
    private static final String EMPTY_WATCHLIST_MESSAGE = "Uhh.. It's empty.. :(";

    //@@author OngDeZhi
    /**
     * Creates a new instance of Watchlist with the specified name.
     *
     * @param name specified watchlist name
     */
    public Watchlist(String name) {
        this.name = name;
        this.animeList = new ArrayList<>();
    }

    /**
     * Creates a new instance of Watchlist with the specified name and anime list.
     *
     * @param name the specified watchlist name
     * @param animeList the specified anime list
     */
    public Watchlist(String name, ArrayList<Integer> animeList) {
        this.name = name;
        this.animeList = animeList;
    }

    /**
     * Returns the name of this Watchlist.
     *
     * @return name of this Watchlist
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the anime list of this Watchlist.
     *
     * @return anime list of this Watchlist
     */
    public ArrayList<Integer> getAnimeList() {
        return animeList;
    }
    
    //@@author michaeldinata
    /**
     * Adds an anime into this Watchlist.
     * 
     * @param animeIndex the specified anime index to be added
     */
    public void addAnimeToList(Integer animeIndex) {
        this.animeList.add(animeIndex);
    }

    /**
     * Removes an anime from this Watchlist.
     * 
     * @param animeIndex the specified anime index in this Watchlist to be removed
     */
    public void removeAnimeFromList(Integer animeIndex) {
        this.animeList.remove(animeIndex);
    }

    /**
     * Returns the total number of anime in this Watchlist.
     * 
     * @return the total number of anime in this Watchlist
     */
    public int getWatchlistSize() {
        return this.animeList.size();
    }

    /**
     * Returns the actual anime index.
     * 
     * @param animeIndexInWatchlist the specified index of anime in this Watchlist
     * @return the actual anime index
     */
    public int getWatchlistListAnimeIndex(int animeIndexInWatchlist) {
        return this.animeList.get(animeIndexInWatchlist);
    }

    //@@author OngDeZhi
    /**
     * Returns a string representation of the anime list in this Watchlist.
     *
     * @return a string representation of the anime list in this Watchlist
     */
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

    /**
     * Returns a string representation of this Watchlist.
     *
     * @return a string representation of this Watchlist
     */
    @Override
    public String toString() {
        return name + System.lineSeparator() + animeListToString();
    }

    /**
     * Used to indicate whether some other object is "equal" to this one.
     *
     * @param otherObject the reference object with which to compare
     * @return {@code true} if this object is the same type and has the same name as the otherObject argument;
     *                      false otherwise.
     */
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
