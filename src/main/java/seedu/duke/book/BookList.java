package seedu.duke.book;

import java.util.ArrayList;

public class BookList {
    private ArrayList<Book> books;

    public BookList() {
        this.books = new ArrayList<>();
    }

    public BookList(ArrayList<Book> books) {
        this.books = books;
    }

    public void add(Book newBook) {
        books.add(newBook);
    }
}
