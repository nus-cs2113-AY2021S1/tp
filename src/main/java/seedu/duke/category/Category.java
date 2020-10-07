package seedu.duke.category;

import seedu.duke.book.BookList;
import seedu.duke.quote.QuoteList;

public class Category {
    private String category;
    private BookList books;
    private QuoteList quotes;
    private int size;

    public Category(String category) {
        this.category = category;
        books = new BookList();
        quotes = new QuoteList();
        size = 0;
    }

    public String getCategoryName() {
        return category;
    }

    public void setCategoryName(String category) {
        this.category = category;
    }

    public int getSize() {
        return books.getList().size() + quotes.getList().size();
    }

    public BookList getBooks() {
        return books;
    }

    public void setBooks(BookList books) {
        this.books = books;
    }

    public QuoteList getQuotes() {
        return quotes;
    }

    public void setQuotes(QuoteList quotes) {
        this.quotes = quotes;
    }
}
