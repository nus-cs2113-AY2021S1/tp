package seedu.duke.rating;

import seedu.duke.lists.QuotesifyList;

import java.util.ArrayList;

public class RatingList extends QuotesifyList<Rating> {
    private ArrayList<Rating> ratings = super.getList();

    public RatingList() {
        super(new ArrayList<>());
    }

    public RatingList(ArrayList<Rating> ratings) {
        super(ratings);
    }

    public Rating getRating(int index) {
        return ratings.get(index);
    }

    @Override
    public void add(Rating newRating) {
        ratings.add(newRating);
    }

    @Override
    public void delete(int index) {
        ratings.remove(index);
    }

    @Override
    public String toString() {
        String ratingsToReturn = "";
        for (Rating rating : ratings) {
            String titleOfRatedBook = rating.getTitleOfRatedBook();
            int ratingScore = rating.getRating();
            ratingsToReturn += titleOfRatedBook + ": " + ratingScore + " star"
                    + System.lineSeparator();
        }
        return ratingsToReturn;
    }
}
