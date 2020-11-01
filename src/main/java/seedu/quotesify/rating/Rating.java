package seedu.quotesify.rating;

import org.json.simple.JSONObject;
import seedu.quotesify.book.Book;
import seedu.quotesify.parser.JsonSerializer;

/**
 * Represents a rating for a book.
 */
public class Rating implements JsonSerializer {
    private String title;
    private String author;
    private int rating;

    /**
     * Constructor for rating.
     *
     * @param ratedBook Book that is rated.
     * @param rating Rating score given to book.
     */
    public Rating(Book ratedBook, int rating) {
        title = ratedBook.getTitle();
        author = ratedBook.getAuthor().getName();
        this.rating = rating;
    }

    /**
     * Returns title of rated book.
     *
     * @return Title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns author name of rated book.
     *
     * @return Author name
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns rating score of book.
     *
     * @return Rating score.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Assigns a rating to book.
     *
     * @param rating Rating score.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Returns details of rating.
     *
     * @return Rating details.
     */
    @Override
    public String toString() {
        return "[" + title + "] by " + author + ": " + rating + " star";
    }

    /**
     * Returns JSON object of the rating for storage.
     *
     * @return JSON object.
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("titleOfRatedBook", this.getTitle());
        json.put("authorOfRatedBook", this.getAuthor());
        json.put("rating", this.getRating());
        return json;
    }
}
