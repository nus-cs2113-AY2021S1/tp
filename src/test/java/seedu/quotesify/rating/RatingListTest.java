package seedu.quotesify.rating;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.quotesify.author.Author;
import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.lists.ListManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingListTest {

    private RatingList ratings;
    private BookList books;
    private Book book;
    private Rating rating;

    @BeforeEach
    void setUp() {
        ListManager.initialiseAllLists();
        ratings = (RatingList) ListManager.getList(ListManager.RATING_LIST);
        books = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        String title = "Harry Potter";
        String name = "JK Rowling";
        Author author = new Author(name);
        book = new Book(author, title);
        books.add(book);
        rating = new Rating(book, 5);
    }

    @Test
    public void add_validRating_ratingAdded() {
        ratings.add(rating);
        assertEquals(ratings.toString(), "[Harry Potter] by JK Rowling: 5 star" + System.lineSeparator());
    }

    @Test
    public void delete_validRating_ratingDeleted() {
        ratings.add(rating);
        ratings.delete(0);
        book.setRating(0);
        assertEquals(book.getRating(), 0);
        assertEquals(ratings.getList().size(), 0);
    }
}
