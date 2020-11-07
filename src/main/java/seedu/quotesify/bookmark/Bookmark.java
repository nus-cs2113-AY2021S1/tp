package seedu.quotesify.bookmark;

import org.json.simple.JSONObject;
import seedu.quotesify.book.Book;
import seedu.quotesify.parser.JsonSerializer;
//@@author lunzard
public class Bookmark implements JsonSerializer {
    private Book book;
    private int pageNum;

    public Bookmark(Book book, int pageNum) {
        this.book = book;
        this.pageNum = pageNum;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public String toString() {
        return "\"" + book.getTitle() + "\"" + " at page: " + Integer.toString(pageNum);
    }

    @Override
    public JSONObject toJson() {
        JSONObject details = new JSONObject();
        details.put("book", this.getBook().toJson());
        details.put("pageNum", this.pageNum);
        return details;
    }
    //@@author
}
