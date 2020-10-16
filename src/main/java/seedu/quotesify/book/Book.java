package seedu.quotesify.book;

import seedu.quotesify.author.Author;

import java.util.ArrayList;

public class Book {
    private Author author;
    private String title;
    private ArrayList<String> category = new ArrayList<>();

    public Book(Author author, String title) {
        this.author = author;
        this.title = title;
    }

    public Book(Author author, String title, ArrayList<String> category) {
        this.author = author;
        this.title = title;
        this.category = category;
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

    public ArrayList<String> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        if (category != null) {
            return title + " by " + author.getName(); // removed display of category
        }

        return title + " by " + author.getName();
    }
}
