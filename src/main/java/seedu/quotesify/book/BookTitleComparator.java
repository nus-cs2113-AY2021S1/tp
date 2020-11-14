package seedu.quotesify.book;

import java.util.Comparator;

//@@author chloesyy
public class BookTitleComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b1.getTitle().toLowerCase().compareTo(b2.getTitle().toLowerCase());
    }
}
