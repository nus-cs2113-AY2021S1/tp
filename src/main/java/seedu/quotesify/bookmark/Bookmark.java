package seedu.quotesify.bookmark;

import org.json.simple.JSONObject;
import seedu.quotesify.book.Book;
import seedu.quotesify.parser.JsonSerializer;

//@@author lunzard

/**
 * Represents a bookmark.
 */
public class Bookmark implements JsonSerializer {
    private Book book;
    private int pageNum;

    /**
     * Constructor for bookmark with a book object and a page number.
     *
     * @param book Book of the bookmark.
     * @param pageNum Page number of the bookmark
     */
    public Bookmark(Book book, int pageNum) {
        this.book = book;
        this.pageNum = pageNum;
    }

    /**
     * Returns book object.
     *
     * @return Book object.
     */
    public Book getBook() {
        return book;
    }

    /**
     * Sets book object.
     *
     * @param book Book object.
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Returns page number of the bookmark.
     *
     * @return pageNum.
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * Sets page number of the bookmark.
     *
     * @param pageNum page number of the bookmark.
     */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * Returns a string listing the book title and its page number.
     *
     * @return String of book title and page number.
     */
    @Override
    public String toString() {
        return "\"" + book.getTitle() + "\"" + " at page: " + Integer.toString(pageNum);
    }

    /**
     * Converts the bookmark object to a JSON object.
     *
     * @return A Bookmark object as a JSONObject.
     */
    @Override
    public JSONObject toJson() {
        JSONObject details = new JSONObject();
        details.put("book", this.getBook().toJson());
        details.put("pageNum", this.pageNum);
        return details;
    }
}
