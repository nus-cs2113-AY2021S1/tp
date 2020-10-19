package seedu.quotesify.rating;

import org.json.simple.JSONObject;
import seedu.quotesify.parser.JsonSerializer;

public class Rating implements JsonSerializer {
    private String titleOfRatedBook;
    private int rating;

    public Rating(int rating, String titleOfRatedBook) {
        this.rating = rating;
        this.titleOfRatedBook = titleOfRatedBook;
    }

    public String getTitleOfRatedBook() {
        return titleOfRatedBook;
    }

    public void setTitleOfRatedBook(String titleOfRatedBook) {
        this.titleOfRatedBook = titleOfRatedBook;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return titleOfRatedBook + ": " + rating + " star";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("titleOfRatedBook", this.getTitleOfRatedBook());
        json.put("rating", this.getRating());
        return json;
    }
}
