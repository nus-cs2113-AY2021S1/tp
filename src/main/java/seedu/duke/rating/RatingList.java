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
        return null;
    }
}
