package seedu.quotesify.book;

import org.json.simple.JSONObject;
import seedu.quotesify.author.Author;
import seedu.quotesify.parser.JsonSerializer;
import seedu.quotesify.ui.UiMessage;

import java.util.ArrayList;

//@@author chloesyy

/**
 * Represents a book.
 */
public class Book implements JsonSerializer {
    private Author author;
    private String title;
    private boolean isDone;
    private ArrayList<String> categories = new ArrayList<>();
    private int rating;

    /**
     * Constructor for book with an author and title.
     *
     * @param author Author for the book.
     * @param title Title of the book.
     */
    public Book(Author author, String title) {
        this.author = author;
        this.title = title;
        this.rating = 0;
        this.isDone = false;
    }

    /**
     * Constructor for book with an author, title and categories.
     *
     * @param author Author for the book.
     * @param title Title of the book.
     * @param category Categories the book is in.
     */
    public Book(Author author, String title, ArrayList<String> category) {
        this.author = author;
        this.title = title;
        this.isDone = false;
        this.categories = category;
        this.rating = 0;
    }

    /**
     * Constructor for book with an author, title, categories, and rating.
     *
     * @param author Author for the book.
     * @param title Title of the book.
     * @param category Categories the book is in.
     * @param rating Rating for the book.
     */
    public Book(Author author, String title, ArrayList<String> category, int rating) {
        this.author = author;
        this.title = title;
        this.isDone = false;
        this.categories = category;
        this.rating = rating;
    }

    /**
     * Constructor for book with an author, title, categories, rating and completion.
     *
     * @param author Author for the book.
     * @param title Title of the book.
     * @param isDone Boolean if book is completed.
     * @param category Categories the book is in.
     * @param rating Rating for the book.
     */
    public Book(Author author, String title, boolean isDone, ArrayList<String> category, int rating) {
        this.author = author;
        this.title = title;
        this.isDone = isDone;
        this.categories = category;
        this.rating = rating;
    }

    /**
     * Returns author object.
     *
     * @return Author object.
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Sets author object.
     *
     * @param author Author object.
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * Returns title of the book.
     *
     * @return Title of book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title of the book.
     *
     * @param title Title of book.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns whether book is completed.
     *
     * @return isDone boolean.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the completion of the book.
     *
     * @param done If book has been completed.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns status icon of the book dependent on completion.
     *
     * @return [v] if book is completed, [x] if book is not completed.
     */
    public String getStatusIcon() {
        return isDone ? "[v] " : "[x] ";
    }

    /**
     * Returns a list of categories the book is in.
     *
     * @return List of categories.
     */
    public ArrayList<String> getCategories() {
        return categories;
    }

    /**
     * Assigns the book a list of categories.
     *
     * @param categories List of categories.
     */
    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    /**
     * Returns the rating of the book.
     *
     * @return Rating of book.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Assigns a rating to the book.
     *
     * @param ratingScore Rating score to be assigned.
     */
    public void setRating(int ratingScore) {
        this.rating = ratingScore;
    }

    /**
     * Returns the details of the book.
     * Details include completion, title, author, categories and rating.
     *
     * @return A string of book details.
     */
    public String getBookDetailString() {
        String stringToReturn = "";

        if (isDone) {
            stringToReturn += "[Completed]" + System.lineSeparator();
        }
        stringToReturn += "Title: " + title + System.lineSeparator();
        stringToReturn += "Author: " + author.getName() + System.lineSeparator();
        stringToReturn += "Categories: " + System.lineSeparator();

        int index = 1;
        if (categories.size() > 0) {
            for (String category : categories) {
                stringToReturn += index + ". " + category + System.lineSeparator();
                index++;
            }
        } else {
            stringToReturn += UiMessage.EMPTY_CATEGORY_LIST_MESSAGE + System.lineSeparator();
        }

        if (rating != 0) {
            stringToReturn += "Rating: " + rating + System.lineSeparator();
        }

        return stringToReturn;
    }

    /**
     * Returns a string listing the book title and author.
     *
     * @return String of book title and author.
     */
    @Override
    public String toString() {
        return title + " by " + author.getName();
    }

    /**
     * Converts the book object to a JSON object.
     *
     * @return A Book object as a JSONObject.
     */
    @Override
    public JSONObject toJson() {
        JSONObject details = new JSONObject();
        details.put("author", this.getAuthor().toJson());
        details.put("title", this.getTitle());
        details.put("isDone", this.isDone());
        details.put("categories", this.getCategories());
        details.put("rating", this.getRating());
        return details;
    }
}
