package seedu.quotesify.category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.quotesify.author.Author;
import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.quote.Quote;
import seedu.quotesify.quote.QuoteList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {
    private Book book1;
    private BookList bookList;
    private Quote quote1;
    private QuoteList quoteList;

    @BeforeEach
    public void setUp() {
        bookList = new BookList();
        quoteList = new QuoteList();

        Author author = new Author("me");
        book1 = new Book(author, "book1");
        bookList.add(book1);

        quote1 = new Quote("quote1");
        quoteList.add(quote1);
    }

    @Test
    public void equals() {
        Category category = new Category("romance");
        category.setBookList(bookList);
        category.setQuoteList(quoteList);
        assertEquals("romance", category.getCategoryName());
        assertEquals(2, category.getSize());
        assertEquals("book1 by me", category.getBookList().getBook(0).toString());
        assertEquals("\"quote1\"", category.getQuoteList().getQuote(0).toString());

        bookList.delete(0);
        assertEquals(1, category.getSize());
    }
}
