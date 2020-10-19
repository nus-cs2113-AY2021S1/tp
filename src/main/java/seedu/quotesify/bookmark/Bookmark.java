package seedu.quotesify.bookmark;

import seedu.quotesify.book.Book;

public class Bookmark {
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
}
