package seedu.quotesify.rating;

import org.junit.jupiter.api.Test;
import seedu.quotesify.author.Author;
import seedu.quotesify.book.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingTest {

    RatingList ratings = new RatingList();

    @Test
    public void addRating_validBookTitleAndRating_success() {
        String titleOfRatedBook = "Harry Potter";
        String name = "J K Rowling";
        Author author = new Author(name);
        Book book = new Book(author, titleOfRatedBook);
        int ratingScore = 4;
        Rating rating = new Rating(book, ratingScore);
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
        String name = "someone";
        Author author = new Author(name);
        Book book = new Book(author, titleOfRatedBook);
        int ratingScore = 2;
        Rating rating = new Rating(book, ratingScore);
        ratings.add(rating);
        assertEquals(ratings.toString(), "[" + titleOfRatedBook + "] by " + name + ": "
                + ratingScore + " star" + System.lineSeparator());
    }
}
