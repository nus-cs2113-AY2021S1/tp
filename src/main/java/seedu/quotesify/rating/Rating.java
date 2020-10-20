package seedu.quotesify.rating;

import org.json.simple.JSONObject;
import seedu.quotesify.book.Book;
import seedu.quotesify.parser.JsonSerializer;

public class Rating implements JsonSerializer {
    private String titleOfRatedBook;
    private String authorOfRatedBook;
    private int rating;
    private Book ratedBook;

    public Rating(Book ratedBook, int rating) {
        titleOfRatedBook = ratedBook.getTitle();
        authorOfRatedBook = ratedBook.getAuthor().getName();
        this.rating = rating;
        this.ratedBook = ratedBook;
    }

    public String getTitleOfRatedBook() {
        return titleOfRatedBook;
    }

    public String getAuthorOfRatedBook() {
        return authorOfRatedBook;
    }

    public int getRating() {
        return rating;
    }

    public Book getRatedBook() {
        return ratedBook;
    }

    @Override
    public String toString() {
        return "[" + titleOfRatedBook + "] by " + authorOfRatedBook + ": " + rating + " star";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("titleOfRatedBook", this.getTitleOfRatedBook());
        json.put("rating", this.getRating());
        return json;
    }

}
