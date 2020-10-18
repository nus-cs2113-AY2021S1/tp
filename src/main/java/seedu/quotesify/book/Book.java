package seedu.quotesify.book;

import org.json.simple.JSONObject;
import seedu.quotesify.author.Author;
import seedu.quotesify.parser.JsonSerializer;

import java.util.ArrayList;

public class Book implements JsonSerializer {
    private Author author;
    private String title;
    private ArrayList<String> categories = new ArrayList<>();

    public Book(Author author, String title) {
        this.author = author;
        this.title = title;
    }

    public Book(Author author, String title, ArrayList<String> category) {
        this.author = author;
        this.title = title;
        this.categories = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        if (categories != null) {
            return title + " by " + author.getName(); // removed display of category
        }

        return title + " by " + author.getName();
    }

    @Override
    public JSONObject toJson() {
        JSONObject details = new JSONObject();
        details.put("author", this.getAuthor().toJson());
        details.put("title", this.getTitle());
        details.put("categories", this.getCategories());
        return details;
    }
}
