package seedu.quotesify.quote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.quotesify.author.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuoteTest {
    private static final String movieQuote = "I am your father!";
    private static final String authorName = "Darth Vader";
    private static final String reference = "Star Wars";
    private static final String reflection = "Baby yoda is cute!";

    private Author author;
    private Quote quote;

    @BeforeEach
    void setUp() {
        author = new Author(authorName);
        quote = new Quote(movieQuote, reference, author);
    }

    @Test
    public void equals() {
        assertEquals(author, quote.getAuthor());

    }


}