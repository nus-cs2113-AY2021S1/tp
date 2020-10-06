package seedu.duke.rating;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingTest {

    @Test
    public void addRating_validBookTitleAndRating_success() {
        RatingList ratings = new RatingList();
        String titleOfRatedBook = "Harry Potter";
        int ratingScore = 4;
        Rating rating = new Rating(ratingScore, titleOfRatedBook);
        ratings.add(rating);
        assertEquals(rating, ratings.getList().get(0));
    }
}
