package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.duke.author.Author;
import seedu.duke.quote.Quote;
import seedu.duke.quote.QuoteList;

class DukeTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void quoteAddTest() {
        QuoteList quotes = new QuoteList();
        Author author = new Author("Gill Bates");
        Quote quote = new Quote("It was the butler", "Herlock Shomles", author);
        quotes.add(quote);
        assertEquals(quote, quotes.getQuote(0));
    }
}
