package seedu.duke.category;

import seedu.duke.book.BookList;
import seedu.duke.quote.QuoteList;

public class Category {
    private String category;
    private BookList bookList;
    private QuoteList quoteList;
    private int size;

    public Category(String category) {
        this.category = category;
        bookList = new BookList();
        quoteList = new QuoteList();
        size = 0;
    }

    public String getCategoryName() {
        return category;
    }

    public void setCategoryName(String category) {
        this.category = category;
    }

    public int getSize() {
        return bookList.getList().size() + quoteList.getList().size();
    }

    public void setSize(int size) {
        this.size = size;
    }

    public BookList getBookList() {
        return bookList;
    }

    public void setBookList(BookList books) {
        this.bookList = books;
    }

    public QuoteList getQuoteList() {
        return quoteList;
    }

    public void setQuoteList(QuoteList quoteList) {
        this.quoteList = quoteList;
    }

    @Override
    public String toString() {
        return String.format("%s - (%d items)", getCategoryName(), getSize());
    }
}
