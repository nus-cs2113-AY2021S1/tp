package seedu.quotesify.rating;

import org.json.simple.JSONObject;
import seedu.quotesify.book.Book;
import seedu.quotesify.parser.JsonSerializer;

public class Rating implements JsonSerializer {
    private String title;
    private String author;
    private int rating;

    public Rating(Book ratedBook, int rating) {
        title = ratedBook.getTitle();
        author = ratedBook.getAuthor().getName();
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "[" + title + "] by " + author + ": " + rating + " star";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("titleOfRatedBook", this.getTitle());
        json.put("authorOfRatedBook", this.getAuthor());
        json.put("rating", this.getRating());
        return json;
    }
}
