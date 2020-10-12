package seedu.duke.rating;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingTest {

    RatingList ratings = new RatingList();

    @Test
    public void addRating_validBookTitleAndRating_success() {
        String titleOfRatedBook = "Harry Potter";
        int ratingScore = 4;
        Rating rating = new Rating(ratingScore, titleOfRatedBook);
        ratings.add(rating);
        assertEquals(rating, ratings.getList().get(0));
    }

    @Test
    public void listRatings_emptyList_success() {
        assertEquals(ratings.toString(), "");
    }

    @Test
    public void listRatings_validRatings_sucess() {
        String titleOfRatedBook = "Book 1";
        int ratingScore = 2;
        Rating rating = new Rating(ratingScore, titleOfRatedBook);
        ratings.add(rating);
        assertEquals(ratings.toString(), titleOfRatedBook + ": " + ratingScore + " star" + System.lineSeparator());
    }
}
