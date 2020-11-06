package seedu.quotesify.category;

import org.json.simple.JSONObject;
import seedu.quotesify.book.BookList;
import seedu.quotesify.parser.JsonSerializer;
import seedu.quotesify.quote.QuoteList;

/**
 * Represents a category for a book or quote.
 */
public class Category implements JsonSerializer {
    private String category;
    private BookList bookList;
    private QuoteList quoteList;
    private int size;

    /**
     * Constructor for category.
     *
     * @param category Category name.
     */
    public Category(String category) {
        this.category = category;
        bookList = new BookList();
        quoteList = new QuoteList();
        size = 0;
    }

    /**
     * Returns the category name.
     *
     * @return Category name.
     */
    public String getCategoryName() {
        return category;
    }

    /**
     * Sets the category name.
     *
     * @param category Category name.
     */
    public void setCategoryName(String category) {
        this.category = category;
    }

    /**
     * Returns the number of items tagged with the category.
     *
     * @return Number of items under the category.
     */
    public int getSize() {
        return bookList.getList().size() + quoteList.getList().size();
    }

    /**
     * Set the size of the category.
     *
     * @param size Any integer value.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Returns the list of books tagged with the category.
     *
     * @return A list of books.
     */
    public BookList getBookList() {
        return bookList;
    }

    /**
     * Set the list of books for the category.
     *
     * @param books List of books tagged with the category.
     */
    public void setBookList(BookList books) {
        this.bookList = books;
    }

    /**
     * Returns a list of quotes tagged under the category.
     *
     * @return A list of quotes.
     */
    public QuoteList getQuoteList() {
        return quoteList;
    }

    /**
     * Set a list of quotes for the category.
     *
     * @param quoteList A list of quotes tagged under the category.
     */
    public void setQuoteList(QuoteList quoteList) {
        this.quoteList = quoteList;
    }

    /**
     * Returns category details.
     *
     * @return Category details.
     */
    @Override
    public String toString() {
        return String.format("%s - (%d items)", getCategoryName(), getSize());
    }

    /**
     * Returns a JSON object of the category.
     *
     * @return JSON object.
     */
    @Override
    public JSONObject toJson() {
        JSONObject details = new JSONObject();
        details.put("category", this.getCategoryName());
        return details;
    }
}
