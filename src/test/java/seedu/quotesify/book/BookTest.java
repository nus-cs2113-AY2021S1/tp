package seedu.quotesify.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.quotesify.author.Author;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {
    private Book book;
    private Author author;

    @BeforeEach
    void setUp() {
        author = new Author("JK Rowling");
        book = new Book(author, "Harry Potter");
    }

    @Test
    public void toString_titleAuthor_titleByAuthor() {
        assertEquals("Harry Potter by JK Rowling", book.toString());
    }

    @Test
    public void getBookDetailString_noRating() {
        assertEquals("Title: Harry Potter" + System.lineSeparator()
                + "Author: JK Rowling" + System.lineSeparator()
                + "Categories: " + System.lineSeparator()
                + "There are no categories created!" + System.lineSeparator(), book.getBookDetailString());
    }

    @Test
    public void getBookDetailString_hasRating() {
        book.setRating(5);
        assertEquals("Title: Harry Potter" + System.lineSeparator()
                + "Author: JK Rowling" + System.lineSeparator()
                + "Categories: " + System.lineSeparator()
                + "There are no categories created!" + System.lineSeparator()
                + "Rating: 5" + System.lineSeparator(), book.getBookDetailString());
    }

    @Test
    public void getBookDetailString_hasCategories() {
        ArrayList<String> categories = new ArrayList();
        categories.add("Fantasy");
        book.setCategories(categories);
        assertEquals("Title: Harry Potter" + System.lineSeparator()
                + "Author: JK Rowling" + System.lineSeparator()
                + "Categories: " + System.lineSeparator()
                + "1. Fantasy" + System.lineSeparator(), book.getBookDetailString());
    }
}