package seedu.quotesify.quote;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.quotesify.author.Author;
import seedu.quotesify.exception.QuotesifyException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class QuoteListTest {
    private static final String movieQuote = "I am your father!";
    private static final String authorName = "Darth Vader";
    private static final String reference = "Star Wars";
    private static final String reflection = "Baby yoda is cute!";
    private static final int quoteIndex = 0;

    private QuoteList quoteList;
    private Quote quote;

    @BeforeEach
    void setQuote() {
        quoteList = new QuoteList();

        Author author = new Author(authorName);
        quote = new Quote(movieQuote, reference, author);

        quoteList.add(quote);
    }

    @Test
    public void updateQuote_quoteUpdated() {
        String param1 = "I am your mother!";
        Quote newQuote = new Quote(param1);
        quote.setReflection(reflection);

        quoteList.updateQuote(newQuote, quoteIndex);

        assertEquals(newQuote.toString(), quoteList.getQuote(quoteIndex).toString());
    }

    @Test
    public void addReflection_addSuccess() throws QuotesifyException {
        quoteList.addReflection(reflection, quoteIndex);
        assertEquals(reflection, quoteList.getQuote(quoteIndex).getReflection());
    }

    @Test
    public void deleteReflection_addSuccess() throws QuotesifyException {
        quoteList.addReflection(reflection, quoteIndex);
        quoteList.deleteReflection(quoteIndex);
        assertNull(quoteList.getQuote(quoteIndex).getReflection());
    }

    @Test
    public void updateReflection_updateSuccess() throws QuotesifyException {
        quoteList.addReflection(reflection, quoteIndex);

        String param = "Boba Fett is cool!";
        quoteList.updateReflection(param, quoteIndex);

        assertEquals(param, quoteList.getQuote(quoteIndex).getReflection());
    }

    @Test
    public void checkDuplicateQuotes() {
        assertTrue(quoteList.isDuplicateQuote(quote));
    }

    @Test
    public void findQuote_matchFound() {
        assertFalse(quoteList.findQuoteByKeyword("father").isEmpty());
    }

    @Test
    public void findQuote_noMatchFound() {
        assertTrue(quoteList.findQuoteByKeyword("mother").isEmpty());
    }

    @Test
    public void getRandomQuote() {
        assertEquals(quote.toString(), quoteList.getRandomQuote().toString());

        quoteList.delete(quoteIndex);
        assertEquals(quoteList.DEFAULT_QUOTE, quoteList.getRandomQuote().toString());
    }

    @Test
    public void getQuoteByAuthor() {
        String param = "1. " + quote.toString();
        assertEquals(param, quoteList.getQuotesByAuthor(authorName));
    }

    @Test
    public void getQuoteByReference() {
        String param = "1. " + quote.toString();
        assertEquals(param, quoteList.getQuotesByReference(reference));
    }

    @Test
    public void getQuoteByReferenceAndAuthor() {
        String param = "1. " + quote.toString();
        assertEquals(param, quoteList.getQuotesByReferenceAndAuthor(reference, authorName));
    }

    @Test
    public void printQuoteList() {
        String param = "1. " + quote.toString();
        assertEquals(param, quoteList.toString());
    }
}
