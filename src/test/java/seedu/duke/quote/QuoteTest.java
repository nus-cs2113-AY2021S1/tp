package seedu.duke.quote;

import org.junit.jupiter.api.Test;
import seedu.duke.author.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuoteTest {
    @Test
    public void quoteAddTest() {
        QuoteList quotes = new QuoteList();
        Author author = new Author("Gill Bates");
        Quote quote = new Quote("It was the butler", "Herlock Shomles", author);
        quotes.add(quote);
        assertEquals(quote, quotes.getQuote(0));
    }
}
