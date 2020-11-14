package seedu.quotesify.rating;

import org.json.simple.JSONArray;

import seedu.quotesify.lists.QuotesifyList;

import java.util.ArrayList;

/**
 * Represents a Rating List.
 */
public class RatingList extends QuotesifyList<Rating> {
    private ArrayList<Rating> ratings = super.getList();

    /**
     * Constructor for rating list.
     */
    public RatingList() {
        super(new ArrayList<>());
    }

    /**
     * Constructor for rating list with existing list of ratings.
     *
     * @param ratings List of ratings from storage.
     */
    public RatingList(ArrayList<Rating> ratings) {
        super(ratings);
    }

    /**
     * Adds a rating to the list.
     *
     * @param newRating Rating to be added.
     */
    @Override
    public void add(Rating newRating) {
        ratings.add(newRating);
    }

    /**
     * Deletes a rating.
     *
     * @param index Index of rating to be deleted.
     */
    @Override
    public void delete(int index) {
        ratings.remove(index);
    }

    /**
     * Returns details of ratings in the list.
     *
     * @return Rating details.
     */
    @Override
    public String toString() {
        String ratingsToReturn = "";
        for (Rating rating : ratings) {
            ratingsToReturn += rating.toString() + System.lineSeparator();
        }
        return ratingsToReturn;
    }

    /**
     * Returns JSONArray of the ratings for storage.
     *
     * @return JSONArray.
     */
    @Override
    public JSONArray toJsonArray() {
        JSONArray list = new JSONArray();
        for (Rating rating : ratings) {
            list.add(rating.toJson());
        }
        return list;
    }
}
