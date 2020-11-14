package seedu.quotesify.quote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.quotesify.author.Author;
import seedu.quotesify.exception.QuotesifyException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuoteParserTest {
    private static final String movieQuote = "I am your father!";
    private static final String authorName = "Darth Vader";
    private static final String reference = "Star Wars";
    public static final String FLAG_AUTHOR = "/by";
    public static final String FLAG_REFERENCE = "/from";
    public static final String FLAG_REFLECT = "/reflect";
    private Quote quote;
    private Quote quoteWithAuthor;
    private Quote quoteWithReference;
    private Quote getQuoteWithReferenceAndAuthor;

    @BeforeEach
    void setQuote() {
        Author author = new Author(authorName);

        quote = new Quote(movieQuote);
        quoteWithAuthor = new Quote(movieQuote, author);
        quoteWithReference = new Quote(movieQuote, reference);
        getQuoteWithReferenceAndAuthor = new Quote(movieQuote, reference, author);
    }

    @Test
    public void getQuote_quoteWithNoFlags_parseSuccess() throws QuotesifyException {
        String param = "I am your father!";
        assertEquals(quote.toString(), QuoteParser.parseParametersIntoQuote(param).toString());
    }

    @Test
    public void getQuote_missingQuoteField_throwsQuotesifyException() {
        String param = " ";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.parseParametersIntoQuote(param));
        assertEquals(QuoteParser.ERROR_MISSING_QUOTE, exception.getMessage());
    }

    @Test
    public void getQuote_quoteWithAuthorFlag_parseSuccess() throws QuotesifyException {
        String param = "I am your father! /by Darth Vader";
        assertEquals(quoteWithAuthor.toString(), QuoteParser.parseParametersIntoQuote(param).toString());
    }

    @Test
    public void getQuote_quoteWithReferenceFlag_parseSuccess() throws QuotesifyException {
        String param = "I am your father! /from Star Wars";
        assertEquals(quoteWithReference.toString(), QuoteParser.parseParametersIntoQuote(param).toString());
    }

    @Test
    public void getQuote_quoteWithAuthorAndReferenceFlag_parseSuccess() throws QuotesifyException {
        String param = "I am your father! /by Darth Vader /from Star Wars";
        assertEquals(getQuoteWithReferenceAndAuthor.toString(),
                QuoteParser.parseParametersIntoQuote(param).toString());
    }

    @Test
    public void getQuoteWithAuthorFlag_extraAuthorFlags_throwsQuotesifyException() {
        String param = "I am your father! /by Darth Vader /by My Dad";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.getQuoteWithAuthor(param));
        assertEquals(QuoteParser.ERROR_DUPLICATE_AUTHOR_FLAG, exception.getMessage());
    }

    @Test
    public void getQuoteWithAuthorFlag_missingAuthorFlag_throwsQuotesifyException() {
        String param = "I am your father! /by ";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.getQuoteWithAuthor(param));
        assertEquals(QuoteParser.ERROR_MISSING_AUTHOR, exception.getMessage());

    }

    @Test
    public void getQuoteWithReferenceFlag_extraReferenceFlags_throwsQuotesifyException() {
        String param = "I am your father! /from Star Wars /from Moon Wars";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.getQuoteWithReference(param));
        assertEquals(QuoteParser.ERROR_DUPLICATE_REFERENCE_FLAG, exception.getMessage());
    }

    @Test
    public void getQuoteWithReferenceFlag_missingReferenceFlag_throwsQuotesifyException() {
        String param = "I am your father! /from ";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.getQuoteWithReference(param));
        assertEquals(QuoteParser.ERROR_MISSING_REFERENCE, exception.getMessage());
    }

    @Test
    public void getQuoteWithReferenceAndAuthorFlag_emptyAuthorName_throwsQuotesifyException() {
        String param1 = "I am your father! /by /from Star Wars";
        String param2 = "I am your father! /from Star Wars /by";

        Throwable exception1 = assertThrows(QuotesifyException.class, () ->
                QuoteParser.getQuoteWithReferenceAndAuthor(param1));
        assertEquals(QuoteParser.ERROR_MISSING_REFERENCE_OR_AUTHOR, exception1.getMessage());

        Throwable exception2 = assertThrows(QuotesifyException.class, () ->
                QuoteParser.getQuoteWithReferenceAndAuthor(param2));
        assertEquals(QuoteParser.ERROR_MISSING_REFERENCE_OR_AUTHOR, exception2.getMessage());
    }

    @Test
    public void getQuoteWithReferenceAndAuthorFlag_emptyReferenceTitle_throwsQuotesifyException() {
        String param = "I am your father! /by Darth Vader /from ";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.getQuoteWithReferenceAndAuthor(param));
        assertEquals(QuoteParser.ERROR_MISSING_REFERENCE_OR_AUTHOR, exception.getMessage());
    }

    @Test
    public void getQuoteWithReferenceAndAuthorFlag_extraFlags_throwsQuotesifyException() {
        String param = "I am your father! /from Star Wars /by Darth Vader /by My Dad";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.getQuoteWithReferenceAndAuthor(param));
        assertEquals(QuoteParser.ERROR_INVALID_PARAMETERS, exception.getMessage());
    }

    @Test
    public void getQuoteWithReferenceAndAuthorFlag_emptyAuthorNameAndReferenceTitle_throwsQuotesifyException() {
        String param = "I am your father!";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.getQuoteWithReferenceAndAuthor(param));
        assertEquals(QuoteParser.ERROR_INVALID_PARAMETERS, exception.getMessage());
    }

    @Test
    public void parseListCommand_invalidFormat_throwsQuotesifyException() {
        String param = "yoda /by Darth Vader";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.parseListCommand(param, FLAG_AUTHOR));
        assertEquals(QuoteParser.ERROR_INVALID_PARAMETERS, exception.getMessage());
    }

    @Test
    public void parseListCommand_emptyAuthorName_throwsQuotesifyException() {
        String param = "/by";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.parseListCommand(param, FLAG_AUTHOR));
        assertEquals(QuoteParser.ERROR_MISSING_AUTHOR, exception.getMessage());
    }

    @Test
    public void parseListCommand_emptyReferenceName_throwsQuotesifyException() {
        String param = "/from";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.parseListCommand(param, FLAG_REFERENCE));
        assertEquals(QuoteParser.ERROR_MISSING_REFERENCE, exception.getMessage());
    }

    @Test
    public void parseListCommand_extraFlag_throwsQuotesifyException() {
        String param = "/from Star Wars /from Empire strikes back";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.parseListCommand(param, FLAG_REFERENCE));
        assertEquals(QuoteParser.ERROR_INVALID_PARAMETERS, exception.getMessage());
    }

    @Test
    public void getQuoteNumber_missingFlag_throwsQuotesifyException() {
        String param = "1";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.getQuoteNumber(param, 1, FLAG_REFLECT));
        assertEquals("Invalid parameters, the \"/reflect\" flag is missing!", exception.getMessage());
    }

    @Test
    public void getQuoteNumber_missingQuoteNumber_throwsQuotesifyException() {
        String param = "/reflect Baby Yoda is cute!";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.getQuoteNumber(param, 1, FLAG_REFLECT));
        assertEquals(QuoteParser.ERROR_MISSING_QUOTE_NUM, exception.getMessage());
    }

    @Test
    public void getQuoteNumber_invalidParameters_throwsQuotesifyException() {
        String param = "1 /random /reflect Baby Yoda is cute!";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.getQuoteNumber(param, 1, FLAG_REFLECT));
        assertEquals(QuoteParser.ERROR_INVALID_PARAMETERS, exception.getMessage());
    }

    @Test
    public void getQuoteNumber_invalidQuoteNumber_throwsQuotesifyException() {
        String param = "2 /reflect Baby Yoda is cute!";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.getQuoteNumber(param, 1, FLAG_REFLECT));
        assertEquals(QuoteParser.ERROR_INVALID_QUOTE_NUM, exception.getMessage());
    }

    @Test
    public void getEditedQuote_parseSuccess() throws QuotesifyException {
        String param = "1 /to I am your father! /by Darth Vader /from Star Wars";
        assertEquals(getQuoteWithReferenceAndAuthor.toString(), QuoteParser.getEditedQuote(param).toString());
    }


    @Test
    public void getReflectionToAdd_parseSuccess() throws QuotesifyException {
        String reflection = "Baby Yoda is cute!";
        String param = "1 /reflect Baby Yoda is cute!";

        assertEquals(reflection, QuoteParser.getReflectionToAdd(param));
    }

    @Test
    public void getReflectionToAdd_emptyReflection_throwsQuotesifyException() {
        String param = "1 /reflect ";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.getReflectionToAdd(param));
        assertEquals(QuoteParser.ERROR_MISSING_REFLECTION_FIELD, exception.getMessage());
    }

    @Test
    public void getReflectionToAdd_extraReflectFlags_throwsQuotesifyException() {
        String param = "1 /reflect Baby Yoda /reflect is cute!";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.getReflectionToAdd(param));
        assertEquals(QuoteParser.ERROR_DUPLICATE_REFLECT_FLAG, exception.getMessage());
    }

    @Test
    public void getReflectionToAdd_missingReflectFlag_throwsQuotesifyException() {
        String param = "1 Baby Yoda is cute!";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.getReflectionToAdd(param));
        assertEquals(QuoteParser.ERROR_INVALID_PARAMETERS, exception.getMessage());
    }

    @Test
    public void getEditedReflection_missingEditFlag_throwsQuotesifyException() {
        String param = "1 Strong in you is the force";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.getEditedReflection(param));
        assertEquals(QuoteParser.ERROR_INVALID_PARAMETERS, exception.getMessage());
    }

    @Test
    public void getEditedReflection_extraEditFlag_throwsQuotesifyException() {
        String param = "1 /to /to Strong in you is the force";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.getEditedReflection(param));
        assertEquals(QuoteParser.ERROR_DUPLICATE_EDIT_FLAG, exception.getMessage());
    }

    @Test
    public void getEditedReflection_emptyReflection_throwsQuotesifyException() {
        String param = "1 /to";

        Throwable exception = assertThrows(QuotesifyException.class, () ->
                QuoteParser.getEditedReflection(param));
        assertEquals(QuoteParser.ERROR_MISSING_REFLECTION_FIELD, exception.getMessage());
    }

}