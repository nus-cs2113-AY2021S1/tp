package seedu.duke.book;

import org.junit.jupiter.api.Test;
import seedu.duke.author.Author;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    public void toString_titleAuthor_titleByAuthor() {
        Author author = new Author("author");
        Book book = new Book(author, "title");
        assertEquals("title by author", book.toString());
    }
}